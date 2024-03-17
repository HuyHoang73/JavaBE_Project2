package com.javaweb.customexception;

public class FieldRequiredException extends RuntimeException {
	public FieldRequiredException(String message) {
		super(message);
	}
}
