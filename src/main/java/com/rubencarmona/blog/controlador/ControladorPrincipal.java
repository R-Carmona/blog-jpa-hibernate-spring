package com.rubencarmona.blog.controlador;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.rubencarmona.blog.dao.PostDAO;
import com.rubencarmona.blog.modelo.Post;

@Controller
public class ControladorPrincipal {

	@Autowired
	private PostDAO postDao;
	
	@GetMapping(value="/")
	public String welcome(Model model) {
		List<Post> posts = postDao.getAll();
		model.addAttribute("postList", posts);
		
		return "index";
	}
}
