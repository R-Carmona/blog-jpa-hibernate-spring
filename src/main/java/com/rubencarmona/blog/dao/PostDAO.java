package com.rubencarmona.blog.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.rubencarmona.blog.modelo.Post;
import com.rubencarmona.blog.modelo.Usuario;

@Transactional
@Repository
public class PostDAO {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	
	/**
	 * Almacena el post en la base de datos
	 */
	public void create(Post post) {
		entityManager.persist(post);
		return;
	}

	/**
	 * Elimina el post de la base de datos.
	 */
	public void delete(Post post) {
		if (entityManager.contains(post))
			entityManager.remove(post);
		else
			entityManager.remove(entityManager.merge(post));
		return;
	}

	/**
	 * Devuelve todos los post de la base de datos.
	 */
	@SuppressWarnings("unchecked")
	public List<Post> getAll() {
		return entityManager.createQuery("select p from Post p").getResultList();		
	}

	/**
	 * Devuelve un post en base a su Id
	 */
	public Post getById(long id) {
		return entityManager.find(Post.class, id);
	}

	/**
	 * Actualiza el usuario proporcionado
	 */
	public Post update(Post post) {
		return entityManager.merge(post);
	}
	
	public Post refresh(Post post) {
		entityManager.refresh(post);
		return post;
	}
	
	/**
	 * Devuelve los post escritos por un usuario
	 */	
	@SuppressWarnings("unchecked")
	public List<Post> getByAuthor(Usuario usuario) {
		return entityManager.createQuery("select p from Post p where p.autor :autor")
				.setParameter("autor", usuario)
				.getResultList();
		
		
	}


}
