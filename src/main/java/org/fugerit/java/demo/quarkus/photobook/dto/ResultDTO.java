package org.fugerit.java.demo.quarkus.photobook.dto;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
public class ResultDTO<T> {

	@NonNull @Getter @Setter
	T content;
	
}
