package com.rubencarmona.blog.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.rubencarmona.blog.dao.UsuarioDAO;
import com.rubencarmona.blog.modelo.Usuario;
import com.rubencarmona.blog.modelo.beans.RegistroBean;

@Controller
public class ControladorRegistro {

	@Autowired
	private UsuarioDAO userDao;
	
	
	@GetMapping(value="/signup") 
	public String showForm(Model model){
		model.addAttribute("userRegister", new RegistroBean());
		return "register";
	}
	
	@PostMapping(value="/register")
	public String submit(@ModelAttribute("userRegister") RegistroBean r, 
			BindingResult result, Model model) {
		
		userDao.create(new Usuario(r.getNombre(), r.getEmail(), 
				r.getCiudad(), r.getPassword()));
		return "redirect:/autores";
		
	}
}
