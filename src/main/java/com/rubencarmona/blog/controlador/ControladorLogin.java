package com.rubencarmona.blog.controlador;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.rubencarmona.blog.dao.UsuarioDAO;
import com.rubencarmona.blog.modelo.Usuario;
import com.rubencarmona.blog.modelo.beans.LoginBean;

@Controller
public class ControladorLogin {
	
	@Autowired
	private UsuarioDAO userDao;
	
	@Autowired
	private HttpSession httpSession;
	
	@GetMapping(value="/signin") 
	public String showForm(Model model){
		model.addAttribute("userLogin", new LoginBean());
		return "login";
	}
	
	@PostMapping(value="/login")
	public String submit(@ModelAttribute("userLogin") LoginBean loginBean, 
			BindingResult result, Model model) {
		
		Usuario u = userDao.getByEmailAndPassword(loginBean.getEmail(), loginBean.getPassword());
		
		if (u != null) {
			httpSession.setAttribute("userLoggedIn", u);
			return "redirect:/";
		} else {
			model.addAttribute("error", "Error al introducir el usuario o la contraseña");
			return "login";
		}
		
		
	}
	
	@GetMapping(value="/logout")
	public String logout(Model model) {
		httpSession.removeAttribute("userLoggedIn");
		return "redirect:/";
	}

}
