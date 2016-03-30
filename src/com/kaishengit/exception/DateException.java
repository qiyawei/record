package com.kaishengit.exception;

public class DateException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	public DateException(){};
	public DateException(String msg){
		super(msg);
	}
	public DateException(Throwable e){
		super(e);
	}
	public DateException(String msg,Throwable e){
		super(msg,e);
	}

}
