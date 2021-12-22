package com.rubencarmona.blog.dao;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.apache.commons.codec.digest.MessageDigestAlgorithms;

import java.util.logging.Level; 
import java.util.logging.Logger; 

import com.rubencarmona.blog.modelo.Usuario;

@Transactional
@Repository
public class UsuarioDAO {
	
    private static final Logger LOGGER =  Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);   
	
	@PersistenceContext
	private EntityManager entityManager;

	/**
	 * Almacena el usuario en la base de datos
	 */
	public void create(Usuario usuario) {
		entityManager.persist(usuario);		
	}

	/**
	 * Elimina el usuario de la base de datos.
	 */
	public void delete(Usuario usuario) {
		if (entityManager.contains(usuario))
			entityManager.remove(usuario);
		else
			entityManager.remove(entityManager.merge(usuario));		
	}

	/**
	 * Devuelve todos los usuarios de la base de datos.
	 */
	@SuppressWarnings("unchecked")
	public List<Usuario> getAll() {
		return entityManager.createQuery("select u from Usuario u").getResultList();
	}

	/**
	 * Devuelve un usuario en base a su Id
	 */
	public Usuario getById(int id) {
		return entityManager.find(Usuario.class, id);
	}

	/**
	 * Actualiza el usuario proporcionado
	 */
	/*
	 * public void update(User user) { entityManager.merge(user); return; }
	 */

	public Usuario update(Usuario usuario) {
		return entityManager.merge(usuario);
	}

	
	/* OTRAS CONSULTAS */
	//Consultas utilizadas en el login
	
	public Usuario getByEmailAndPassword(String email, String password) {
		Usuario result;
		
		String strPwd = new String();
		MessageDigest md = null;
		try {
			md = MessageDigest.getInstance(MessageDigestAlgorithms.MD5);
		} catch (NoSuchAlgorithmException e) {			
			e.printStackTrace();
		}
		
	      md.update(password.getBytes());
	      byte[] digest = md.digest();

	      // Se escribe byte a byte en hexadecimal
	      for (byte b : digest) {
	    	  strPwd += Integer.toHexString(0xFF & b);	        
	      }
	     
	      // Se escribe codificado base 64. Se necesita la librería
	      // commons-codec-x.x.x.jar de Apache
	      // byte[] encoded = Base64.encodeBase64(digest);
	      
	      
	      LOGGER.log(Level.INFO,"My pwd MD5: " + strPwd);
	           
	      

		try {
			
			result = (Usuario) entityManager
					.createNativeQuery("SELECT * FROM usuario WHERE email = :email AND password = :password",
							Usuario.class)
					.setParameter("email", email).setParameter("password", strPwd).getSingleResult();
			
		} catch (NoResultException ex) {
			result = null;
		}

		return result;
	}
	
	//Consulta para saber el número post que ha escrito el usuario
	public int getNumPostByUser(Usuario usuario) {
		int result;
		
		result = (int) entityManager.createQuery("select count(p) from Post p WHERE p.autor = :autor")
				.setParameter("autor", usuario)
				.setFirstResult(0)
				.setMaxResults(1)
				.getSingleResult();
		
		return result;
	}

}
