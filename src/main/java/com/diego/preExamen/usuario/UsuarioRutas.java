package com.diego.preExamen.usuario;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.w3c.dom.ls.LSInput;

import com.diego.preExamen.Crypt.GeneradorCrypt;
import com.diego.preExamen.rol.Rol;
import com.diego.preExamen.rol.RolDAO;



@Controller
public class UsuarioRutas {
	
	/*INYECCIONES*/
	@Autowired
	private UsuarioDAO usuarioDAO;
	
	@Autowired
	private GeneradorCrypt generadorCrypt;
	
	
	@Autowired
	private RolDAO rolDAO;
	
	/*RUTAS*/
	@GetMapping("/usuarios")
	public ModelAndView usuarios() {
		ModelAndView model = new ModelAndView();
		
		model.setViewName("usuarios");
		
		model.addObject("usuario", new Usuario());
		
		List<Usuario> listaUsuarios = (List<Usuario>)usuarioDAO.findAll();
		model.addObject("usuarios", listaUsuarios);	
		
		
		model.addObject("rol", new Rol());
		
		List<Rol> listaRoles = (List<Rol>)rolDAO.findAll();
		model.addObject("roles", listaRoles);
		
		
		return model;		
	}
	
	@PostMapping("/usuarios/anadir")
	public String nuevoUsuario(@ModelAttribute Usuario usuario) {
		//if(errores.hasErrors()) {
		usuario.setContrasena(generadorCrypt.encriptador().encode(usuario.getContrasena()));
	
		//}
		usuarioDAO.save(usuario);
		
		
		return "redirect:/usuarios";
	}
	
	@GetMapping("/usuarios/eliminar/{usuario}")
	public String eliminarUsuario(@PathVariable String usuario) {
		
		usuarioDAO.deleteById(usuario);
		
		return "redirect:/usuarios";
		
	}
	
	
	
	
	/*EDITAR*/
	@GetMapping("/usuarios/editar/{id}")
	public ModelAndView editarUsuario(@ModelAttribute Usuario usuario,
										Authentication authentication,
										@PathVariable String id ) {
	 
		ModelAndView mav = new ModelAndView();
//		//Indicamos la ruta que mostrará cuando se ejecute el metodo
//		mav.setViewName("redirect:/usuarios");
//	 
//		//Obtenemos quien es el usuario que se ha seleccionado para ser editado
//		String quien = authentication.getName();
//		
//		//Buscamos si ese usuario existe en la base de datos
//		Usuario userLogeado = usuarioDAO.findById(quien).get();
//		
//		//Sacamos su rol
//		List<GrantedAuthority> gantedAuthorities = (List<GrantedAuthority>)authentication.getAuthorities();
//		
//		//Añade seguridad de forma que se acabara la ejecucion si el nombre no coincide con ningun usuario de la BD
//		if(!quien.equalsIgnoreCase(id)) {
//			
//			return mav;
//		}
		
		mav.setViewName("editarUser");
		//Almacena en user el usuario de la base de datos que estamos buscando
		Usuario user = usuarioDAO.findById(id).get();
		
		//Añadimos ese objeto a la vista
		mav.addObject("user", user);
		
		//Creamos y pasamos a la vista una lista con todos los roles
		List<Rol> listaRoles = (List<Rol>) rolDAO.findAll();
		mav.addObject("roles", listaRoles);
		
		//Finalmente insertamos el usuario, pero como nos hemos asegurado de que exista, el nombre coicidirá y se hara un update;
		//usuarioDAO.save(user);
	 
	 return mav;
	}
	
	
	@PostMapping("/usuarios/editar")
	public ModelAndView usuarioEditar(@Valid @ModelAttribute("user") Usuario usuario,
										BindingResult bindingResult) {
		
		ModelAndView mav = new ModelAndView();
		
		/*Si encuentra algun erro se mantiene en /editarUser y le vuelve a pasar la lista de toles,
		 * , sino quiere decir que todo esta bien asi que se guardara el usuario y se regresará a /usuarios	*/
		if (bindingResult.hasErrors()) {
			
			mav.setViewName("editarUser");
			
			List<Rol> listaRoles = (List<Rol>)rolDAO.findAll();
			mav.addObject("roles",listaRoles);
			
			return mav;
		}
		
		usuarioDAO.save(usuario);
		mav.setViewName("redirect:/usuarios");
		
		return mav;
		
		
		
	}
	
	
	
	
	/*QUERYS*/
	@GetMapping("/consultas")
	public String  consultas() {
		
		//ModelAndView mac = new ModelAndView();
		List<Usuario> usuariosEdad = (List<Usuario>)usuarioDAO.buscarPorEdad(19);
		System.out.println(usuariosEdad);
		
		List<Usuario> menoresEdad = (List<Usuario>)usuarioDAO.findByEdadLessThan(30);
		
		System.out.println(menoresEdad);
		return "redirect:/";
	}
	

}
