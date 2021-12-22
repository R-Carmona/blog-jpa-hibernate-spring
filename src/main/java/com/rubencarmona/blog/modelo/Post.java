package com.rubencarmona.blog.modelo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Post {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "idPost", updatable = false, nullable = false)
	private long idPost;
	
	private String url;
	
	private String titulo;
	
	
	//Revisar con Lombok
	public Post(String url, String titulo, String contenido, Usuario autor, List<Comentario> comentarios) {
		super();
		this.url = url;
		this.titulo = titulo;
		this.contenido = contenido;
		this.autor = autor;
		this.comentarios = comentarios;
	}

	/**
	 * @Lob Indicamos a JPA que es un objeto pesado, cómo los arrays de byte[].
	 */
	@Lob
	@Basic(optional = false, fetch = FetchType.LAZY)
	private String contenido;
	
	@CreationTimestamp
	private Date fecha;
	
	@ManyToOne
	private Usuario autor;
	
	@JsonIgnore
	@OneToMany(mappedBy = "post")
	private List<Comentario> comentarios = new ArrayList<>();

}
