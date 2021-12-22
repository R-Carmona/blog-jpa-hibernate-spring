package com.rubencarmona.blog.controlador;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.rubencarmona.blog.dao.ComentarioDAO;
import com.rubencarmona.blog.dao.PostDAO;
import com.rubencarmona.blog.modelo.Comentario;
import com.rubencarmona.blog.modelo.Post;
import com.rubencarmona.blog.modelo.Usuario;
import com.rubencarmona.blog.modelo.beans.ComentarioBean;
import com.rubencarmona.blog.modelo.beans.PostBean;

@Controller
public class ControladorPost {
	
	@Autowired
	private PostDAO postDao;
	
	@Autowired 
	ComentarioDAO commentDao;
	
	@Autowired
	private HttpSession httpSession;
	
	@GetMapping(value="/submit") 
	public String showForm(Model model){
		model.addAttribute("post", new PostBean());
		return "submit";
	}
	
	@PostMapping(value="/submit/newPost")
	public String submitPost(@ModelAttribute("post") PostBean postBean, BindingResult result, Model model) {

		Post post = new Post();
		post.setTitulo(postBean.getTitulo());
		post.setContenido(postBean.getContenido());
		post.setUrl(postBean.getUrl());
		Usuario autor = (Usuario) httpSession.getAttribute("userLoggedIn");
		post.setAutor(autor);
		postDao.create(post);
		autor.getPosts().add(post);
		
		return "redirect:/";
	}
	
	
	@GetMapping(value="/post/{id}")
	public String detail(@PathVariable("id") long id, Model model) {
		
		Post result = null;
		if ((result = postDao.getById(id)) != null) {
			model.addAttribute("post", result);
			model.addAttribute("commentForm", new ComentarioBean());
			return "postdetail";			
		} else
			return "redirect:/";
	}
	
	@PostMapping(value="/submit/newComment")
	public String submitComment(@ModelAttribute("commentForm") ComentarioBean commentBean, BindingResult result, Model model) {
		
		Usuario autor = (Usuario) httpSession.getAttribute("userLoggedIn");
		

		Comentario comment = new Comentario();
		comment.setUsuario(autor);
		
		Post post = postDao.getById(commentBean.getPost_id());
		comment.setPost(post);
		comment.setTexto(commentBean.getContenido());
		
		commentDao.create(comment);
		
		post.getComentarios().add(comment);
		autor.getComentarios().add(comment);
		
		return "redirect:/post/" + commentBean.getPost_id() ;
	}

}
