package org.fugerit.java.demo.quarkus.photobook.dto;

import io.quarkus.runtime.annotations.RegisterForReflection;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RegisterForReflection
@RequiredArgsConstructor
public class ResultDTO<T> {

	@NonNull @Getter @Setter
	T content;
	
}
