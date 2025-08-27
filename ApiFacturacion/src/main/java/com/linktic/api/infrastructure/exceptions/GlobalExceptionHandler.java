package com.linktic.api.infrastructure.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import com.linktic.api.infrastructure.errors.ErrorResponseWrapper;
import com.linktic.api.infrastructure.errors.NegocioException;

import java.time.LocalDateTime;
import java.util.Collections;

@ControllerAdvice
public class GlobalExceptionHandler {
	@ExceptionHandler(RuntimeException.class)
	public ResponseEntity<ErrorResponseWrapper> handleRuntimeException(RuntimeException ex, WebRequest request) {
		ErrorResponseWrapper.ErrorResponse error = ErrorResponseWrapper.ErrorResponse.builder()
				.timestamp(LocalDateTime.now()).path(request.getDescription(false).replace("uri=", ""))
				.errorCode("GENERIC_ERROR").errorMessage(ex.getMessage())
				.details("Ha ocurrido un error inesperado en la aplicación.").build();

		ErrorResponseWrapper response = ErrorResponseWrapper.builder().errors(Collections.singletonList(error)).build();

		return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(NegocioException.class)
	public ResponseEntity<ErrorResponseWrapper> handleNegocioException(NegocioException ex, WebRequest request) {
		ErrorResponseWrapper.ErrorResponse error = ErrorResponseWrapper.ErrorResponse.builder()
				.timestamp(LocalDateTime.now()).path(request.getDescription(false).replace("uri=", ""))
				.errorCode(ex.getCodigo()).errorMessage(ex.getMensaje()).details(ex.getDetalle()).build();

		ErrorResponseWrapper response = ErrorResponseWrapper.builder().errors(Collections.singletonList(error)).build();

		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	}
}
