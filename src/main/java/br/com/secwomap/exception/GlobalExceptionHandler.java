package br.com.secwomap.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.com.secwomap.domain.ErrorMessage;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(value = AddressNotFoundException.class)
	protected ResponseEntity<Object> handleNotFound(AddressNotFoundException exception) {
		return new ResponseEntity<Object>(new ErrorMessage(404, "Não há endereço com os dados inseridos!"), HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(value = MethodArgumentNotValidException.class)
	protected ResponseEntity<Object> handleBadRequest(MethodArgumentNotValidException exception) {
		return new ResponseEntity<Object>(new ErrorMessage(400, "Faltam dados! Apenas o CEP é opcional!"), HttpStatus.BAD_REQUEST);
	}
}
