package com.diego.preExamen.usuario;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.diego.preExamen.nota.Nota;
import com.diego.preExamen.rol.Rol;

@Entity
@Table(name="usuarios")
public class Usuario implements UserDetails { 		

	@Id
	@Column
	@Size(min=2, message = "SE NECESITA UN MINIMO DE DOS CARACTERES")
	@Size(max = 6, message = "El numero maximo de caracteres es 6") 
	private String usuario;
	
	@Column
	private String contrasena;
	
	@Column
	private String nombre;
	
	@Column
	private Integer edad;
	
	@Column
	private String fecha;
	
	/*RELACION ENTRE TABLAS*/
	//Relacion con rol
	@ManyToOne
	private Rol rol = new Rol();
	
	//Relacion con nota
	@OneToMany( fetch=FetchType.EAGER, mappedBy = "usuarioNota")
	private List<Nota> notas = new ArrayList<Nota>();


	
	/*GETTERS & STTERS*/
	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Integer getEdad() {
		return edad;
	}

	public void setEdad(Integer edad) {
		this.edad = edad;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public Rol getRol() {
		return rol;
	}

	public void setRol(Rol rol) {
		this.rol = rol;
	}
	

	public String getContrasena() {
		return contrasena;
	}

	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}
	
	public List<Nota> getNotas() {
		return notas;
	}

	public void setNotas(List<Nota> notas) {
		this.notas = notas;
	}

	/*USER DETAILS*/
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<GrantedAuthority> grantedAutorities = new ArrayList<>();
		grantedAutorities.add(new SimpleGrantedAuthority(rol.getCredencial()));
		return grantedAutorities;
	}

	@Override
	public String getPassword() {
		
		return this.contrasena;
	}

	@Override
	public String getUsername() {
		
		return this.usuario;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

	
	@Override
	public String toString() {
		return "Usuario [usuario=" + usuario + ", contrasena=" + contrasena + ", nombre=" + nombre + ", edad=" + edad
				+ ", fecha=" + fecha + ", rol=" + rol.getCredencial() + ", notas=" + notas + "]";
	}
	
	
	
	
}
