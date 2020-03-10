package com.diego.preExamen.carrito;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RutasCarrito {

	@GetMapping("/addCarrito")
	public String addCarrito(HttpSession session) {
		
		Carrito carrito = (Carrito) session.getAttribute("carrito");
		if(carrito != null) {
			carrito.addProductos();
		}
		return "redirect:/";
	}
}