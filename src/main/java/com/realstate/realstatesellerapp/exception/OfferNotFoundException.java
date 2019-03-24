package com.realstate.realstatesellerapp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@SuppressWarnings("serial")
@ResponseStatus(value=HttpStatus.OK)
public class OfferNotFoundException extends NotFoundException {
	
	public OfferNotFoundException(String message) {
		super(message);
	}

}
