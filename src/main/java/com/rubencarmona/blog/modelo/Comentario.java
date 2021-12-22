package com.rubencarmona.blog.modelo;

import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.CreationTimestamp;

import lombok.Data;

@Entity
@Data
public class Comentario {
	
	@Id	
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "idPost", updatable = false, nullable = false)
	private long idComentario;
	
	@ManyToOne
	@JoinColumn(name = "usuario_id", updatable = false)
	private Usuario usuario;

	@ManyToOne
	@JoinColumn(name = "post_id", updatable = false)
	private Post post;

	@Lob
	@Basic(optional = false, fetch = FetchType.LAZY)
	private String texto;

	@CreationTimestamp
	private Date fecha;
	
	

}
