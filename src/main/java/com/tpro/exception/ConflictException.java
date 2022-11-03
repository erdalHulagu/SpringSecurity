package com.tpro.exception;

public class ConflictException extends RuntimeException{// bu exceptionu biz olusturduk ondan dolayi javada olan
	                                                     //bir exceptiondan extend etmemiz lazim
	public ConflictException(String message) {
		super(message); // burada superi extend ettigimiz RuntimeException'a gidecek ve exception gonderecek 
		//biz bunun yerine bizim parametre olarak  girdigimiz mesaji kullaniciya gosterecek

	}

}
