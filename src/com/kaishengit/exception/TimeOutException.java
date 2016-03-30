package com.kaishengit.exception;

public class TimeOutException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	public TimeOutException(){};
	public TimeOutException(String msg){
		super(msg);
	}
	public TimeOutException(Throwable e){
		super(e);
	}
	public TimeOutException(String msg,Throwable e){
		super(msg,e);
	}

}
