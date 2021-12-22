package com.rubencarmona.blog.modelo;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.annotations.ColumnTransformer;
import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Usuario {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "idUsuario", updatable = false, nullable = false)
	private Long idUsuario;
	
	private String nombre;
	
	private String email;
	
	@CreationTimestamp
	private Date fechaDeAlta;
	
	private String ciudad;
	
	
	//Revisar con Lombok
	public Usuario(String nombre, String email, String ciudad, String password) {
		super();
		this.nombre = nombre;
		this.email = email;
		this.ciudad = ciudad;
		this.password = password;
	}

	/**
	 * Cifrado del guardado de la contraseña.
	 */
	@ColumnTransformer(write=" MD5(?) ")
	private String password;
	
	@JsonIgnore
	@OneToMany(mappedBy = "usuario", fetch = FetchType.EAGER)
	private Set<Comentario> comentarios = new HashSet<>();
	
	@JsonIgnore
	@OneToMany(mappedBy = "autor", fetch = FetchType.EAGER)
	private Set<Post> posts = new HashSet<>();
	
	

	
	

}
