package com.diego.preExamen.nota;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.diego.preExamen.nota.NotaDAO;


@Controller
public class RutasNota {
	@Autowired
	private NotaDAO notaDAO;

	@GetMapping("/notas")
	public ModelAndView usuarios() {
		ModelAndView model = new ModelAndView();
		
		model.setViewName("notas");
		
		model.addObject("nota", new Nota());
		
		List<Nota> listaNotas = (List<Nota>)notaDAO.findAll();
		model.addObject("notas", listaNotas);	
		
		
		return model;		
	}
	
	@PostMapping("/notas/anadir")
	public String anadirNota(@ModelAttribute Nota nota) {
		
		notaDAO.save(nota);
		
		return "redirect:/notas";
	}
	
	@GetMapping("/notas/eliminar/{id}")
	public String eliminarNota(@PathVariable Long id) {
		
		notaDAO.deleteById(id);
		
		return "redirect:/notas";
	}
	
	
	
}
