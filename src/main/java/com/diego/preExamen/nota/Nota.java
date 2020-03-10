package com.diego.preExamen.nota;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.diego.preExamen.usuario.Usuario;

@Entity
@Table(name="notas")
public class Nota {

	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	@Column
	private Long id;
	
	@Column
	private String mensaje;

	
	/*RELACIONES ENTRE TABLAS*/
	@ManyToOne
	private Usuario usuarioNota = new Usuario();
	
	
	
	/*GETTES & SETTERS*/

	

	public String getMensaje() {
		return mensaje;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	public Usuario getUsuarioNota() {
		return usuarioNota;
	}

	public void setUsuarioNota(Usuario usuarioNota) {
		this.usuarioNota = usuarioNota;
	}
	
	
}
