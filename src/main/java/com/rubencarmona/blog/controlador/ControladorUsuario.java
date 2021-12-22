package com.rubencarmona.blog.controlador;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.rubencarmona.blog.dao.UsuarioDAO;
import com.rubencarmona.blog.modelo.Usuario;

@Controller
public class ControladorUsuario {

	@Autowired
	private UsuarioDAO userDao;
	
	@GetMapping(value="/autores")
	public String listaAutores(Model model) {
		
		List<Usuario> autores = userDao.getAll();
		
		model.addAttribute("autores", autores);
		
		return "userlist";
	}
}
