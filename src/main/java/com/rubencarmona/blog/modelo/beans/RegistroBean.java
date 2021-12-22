package com.rubencarmona.blog.modelo.beans;

import java.util.Date;

import lombok.Data;

@Data
public class RegistroBean {
	
	private String nombre;
	
	private String email;
	
	private Date fechaDeAlta;
	
	private String ciudad;
	
	private String password;
	
	private String secondPassword;

}
