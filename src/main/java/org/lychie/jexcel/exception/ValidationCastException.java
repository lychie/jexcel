package org.lychie.jexcel.exception;

/**
 * 校验异常类
 * 
 * @author Lychie Fan
 */
public class ValidationCastException extends Exception {

	private String message;
	private static final long serialVersionUID = 3046628936474014397L;

	public ValidationCastException(String message) {
		super(message);
		this.message = message;
	}
	
	public String getErrorMessage(){
		return message;
	}

}