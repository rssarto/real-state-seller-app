package com.realstate.realstatesellerapp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@SuppressWarnings("serial")
@ResponseStatus(value=HttpStatus.OK)
public class ImmobileNotFoundException extends NotFoundException {
	
	public ImmobileNotFoundException(String message) {
		super(message);
	}

}
