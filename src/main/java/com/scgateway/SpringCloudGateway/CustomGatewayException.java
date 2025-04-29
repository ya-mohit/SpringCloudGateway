package com.scgateway.SpringCloudGateway;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.reactive.result.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebExceptionHandler;

import reactor.core.publisher.Mono;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class CustomGatewayException implements WebExceptionHandler{					//for reactive, spring-webflux
//public class CustomGatewayException extends ResponseEntityExceptionHandler{		//for non-reactive, spring-web

	@ExceptionHandler(Exception.class)
	protected ResponseEntity<Object> handleAllExceptions(Exception e){
		Status status = new Status(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Mohit Custom Exception :: "+e.getMessage());
		return new ResponseEntity<>(status, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@Override
	public Mono<Void> handle(ServerWebExchange exchange, Throwable ex) {
	        Status status = new Status(HttpStatus.INTERNAL_SERVER_ERROR.value(),
	                "Mohit Custom Exception :: " + ex.getMessage());
	        exchange.getResponse().setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR);
	        return exchange.getResponse().writeWith(
	                Mono.just(exchange.getResponse()
	                        .bufferFactory().wrap(status.getMsg().getBytes())));
	}
}

class Status{
	int statusCode;
	String msg;
	
	public Status(int statusCode, String msg) {
		super();
		this.statusCode = statusCode;
		this.msg = msg;
	}
	public int getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	
}
