package com.rubencarmona.blog.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.rubencarmona.blog.modelo.Comentario;
import com.rubencarmona.blog.modelo.Post;
import com.rubencarmona.blog.modelo.Usuario;

@Repository
@Transactional
public class ComentarioDAO {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	
	/**
	 * Almacena el comentario en la base de datos
	 */
	public void create(Comentario comentario) {
		entityManager.persist(comentario);
		return;
	}

	/**
	 * Elimina un comentario de la base de datos.
	 */
	public void delete(Comentario comentario) {
		
		if (entityManager.contains(comentario))
			entityManager.remove(comentario);
		else
			entityManager.remove(entityManager.merge(comentario));
		return;
	}

	/**
	 * Devuelve todos los comentarios de la base de datos.
	 */
	@SuppressWarnings("unchecked")
	public List<Post> getAll() {
		
		return entityManager.createQuery("select c from Comment c").getResultList();
		
	}

	/**
	 * Devuelve un comentario en base a su Id
	 */
	public Comentario getById(long id) {
		return entityManager.find(Comentario.class, id);
	}

	/**
	 * Actualiza el comentario proporcionado
	 */
	public Comentario update(Comentario comentario) {
		return entityManager.merge(comentario);
	}

	/**
	 * Refresca el comentario proporcionado
	 */
	public Comentario refresh(Comentario comentario) {
		entityManager.refresh(comentario);
		return comentario;
	}
	
	/**
	 * Devuelve los comentarios escritos por un usuario
	 */	
	@SuppressWarnings("unchecked")
	public List<Post> getByAuthor(Usuario usuario) {
		return entityManager.createQuery("select c from Comment c where c.autor :autor")
				.setParameter("autor", usuario)
				.getResultList();
		
		
	}

}
