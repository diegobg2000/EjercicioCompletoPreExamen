package com.diego.preExamen.rol;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.diego.preExamen.usuario.Usuario;

@Entity
@Table(name="roles")
public class Rol {

	@Id
	private String credencial;
	
	/*RELACIONES ENTRE TABLAS*/
	@OneToMany(mappedBy = "rol", fetch=FetchType.EAGER )
	private List<Usuario> usuarios = new ArrayList<Usuario>();
		
	

	
	/*GETTERS & SETTERS*/

	public String getCredencial() {
		return credencial;
	}

	public void setCredencial(String credencial) {
		this.credencial = credencial;
	}

	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}
	
	
	
	

}	
