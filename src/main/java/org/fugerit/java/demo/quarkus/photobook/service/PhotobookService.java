package org.fugerit.java.demo.quarkus.photobook.service;

import com.mongodb.client.*;
import io.quarkus.cache.CacheResult;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import org.bson.Document;
import org.fugerit.java.demo.quarkus.photobook.service.mongodb.PhotobookDownloadAggregation;
import org.fugerit.java.demo.quarkus.photobook.service.mongodb.PhotobookImagesAggregation;
import org.fugerit.java.demo.quarkus.photobook.service.mongodb.PhotobookListAggregation;

import java.util.Base64;

@ApplicationScoped
@Slf4j
public class PhotobookService {

	@Inject
	private MongoClient mongoClient;

	public PhotobookService(MongoClient mongoClient) {
		this.mongoClient = mongoClient;
	}

	private MongoDatabase getDatabase() {
		return this.mongoClient.getDatabase( "photobook_demo" );
	}

	public Document listPhotobooks( String langCode, int perPage, int currentPage ) {
		MongoCollection<Document> collection = this.getDatabase().getCollection( "photobook_meta" );
		AggregateIterable<Document> result = collection.aggregate( PhotobookListAggregation.getAggregation(langCode, perPage, currentPage) );
		Document doc = null;
		MongoCursor<Document> cursor = result.iterator();
		if ( cursor.hasNext() ) {
			doc = cursor.next();
		}
		return doc;
	}
	
	public Document listImages( String photobookId, String langCode, int perPage, int currentPage ) {
		MongoCollection<Document> collection = this.getDatabase().getCollection( "photobook_images" );
		AggregateIterable<Document> result = collection.aggregate( PhotobookImagesAggregation.getAggregation(photobookId, langCode, perPage, currentPage) );
		Document doc = null;
		MongoCursor<Document> cursor = result.iterator();
		if ( cursor.hasNext() ) {
			doc = cursor.next();
		}
		return doc;
	}
	

	@CacheResult( cacheName =  "images" )
	public byte[] downloadImage( String photobookId, String imageId ) {
		MongoCollection<Document> collection = this.getDatabase().getCollection( "photobook_images" );
		AggregateIterable<Document> result = collection.aggregate( PhotobookDownloadAggregation.getAggregation(photobookId, imageId) );
		byte[] data = null;
		MongoCursor<Document> cursor = result.iterator();
		if ( cursor.hasNext() ) {
			Document doc = cursor.next();
			String base64 = (String)doc.get( "base64" );
			data = Base64.getDecoder().decode( base64 );
			log.debug( "found! {}, {}, size: {}", photobookId, imageId, data.length );
		} else {
			log.debug( "not found! {}, {}", photobookId, imageId );
		}
		return data;
	}
	
}
