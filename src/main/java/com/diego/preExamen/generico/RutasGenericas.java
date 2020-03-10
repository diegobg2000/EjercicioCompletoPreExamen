package com.diego.preExamen.generico;

import javax.servlet.http.HttpSession;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.diego.preExamen.carrito.Carrito;


@Controller
public class RutasGenericas {

	@GetMapping("/")
	public String principal() {
		
		return "principal";
	}
	
	
	
	
	@GetMapping("/login")
	public String iniciarSesion(HttpSession session) {
		
		/*Le indicamos que lo que queremos guardar en la sesion es
		 * un objeto de tipo carrito*/
		session.setAttribute("carrito", new Carrito());
		
		return "login";
	}
	
	
	@GetMapping("/logout")
	public String terminarSesion(Authentication authenticacion) {
		
		return "ok";
	}
}
