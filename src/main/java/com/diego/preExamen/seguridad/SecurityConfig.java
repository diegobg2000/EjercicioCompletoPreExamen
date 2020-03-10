package com.diego.preExamen.seguridad;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import com.diego.preExamen.Crypt.GeneradorCrypt;
import com.diego.preExamen.servicios.Autenticacion;



@Configuration
@EnableWebSecurity(debug = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	  @Autowired
	   private Autenticacion autenticacion;
	   
	   @Autowired
	   private GeneradorCrypt generadorCrypt;
	   
	   
	    @Override
	    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
	    
	        
	    	DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
	        //Le digo quien es mi encoder
	    	provider.setPasswordEncoder(generadorCrypt.encriptador());
	    	//Le digo cual es mi servicio. Quien se encarga de ir a buscar a los usuario a la base de datos
	        provider.setUserDetailsService(autenticacion);
	    	
	    	auth.authenticationProvider(provider);
	    }   
	    
		   @Override
	    protected void configure(HttpSecurity http) throws Exception {
	    	http
	    	/*Es importnate el orden de forma que se va de las ordenes menos restrictivas a las mas restrictivas???*/
	    	
	    	//.anonymous().disable()/*Desactiva los usuarios anonimos*/
	      .authorizeRequests()/*COnfiguro las rutas de configuracion*/
	      	
	      
	        .antMatchers("/usuarios/anadir").hasAuthority("ADMIN")/*Para todas las operacion de usuario: borrar, añadir. Tienes que ser un ADMIN*/
	        .antMatchers("/usuarios/elimina/{usuario}").hasAuthority("ADMIN")
	      .antMatchers("/**").authenticated()/*Exige a todos los usuarios que se autentiquen si quieren entrar en cualquier ruta que este por debajo de ("/")*/

	      

	     //.antMatchers("/").permitAll()/*Permite acceder a esta ruta a todos los usuarios del sistema */
	        
	        //.antMatchers("/usuarios").permitAll()/*Si esa utorizacion de peticion de una ruta encaja con /usuarios. Entonces permite que entre todo el mundo*/
	        	//.antMatchers("/mensajes").authenticated()/*Todo el mundo que este autenticado y yo tenga en mi sistema como usuario*/
	        //	.antMatchers("/usuarios/anadir").hasAnyAuthority("ADMIN","MODERADOR")/*Permite realiazr la operacion tanto al ADMIN como al MODERADOR*/
	        //	.antMatchers("/usuarios/eliminar/**").hasAnyAuthority("ADMIN", "MODERADOR")
	       //	.antMatchers("/usuarios/eliminar/**").denyAll()

	        	
	        //	.anyRequest().authenticated()/*Cualquier peticion necesita que el usuario este autenticado*/

		        .and() /*Le digo que queiero configurar otra cosa*/   	
		       
		        /*CONFIGURACION DEL LOGIN Y LOGOUT*/
		        .formLogin()/*Formulario de loggin configurration*/
	            .loginPage("/login")/*Indicamos cual es la druta de nuestra ventana de loggin*/
	            .permitAll()/*Permitimos acceso a esta ruta a todos los usuarios*/
	            .defaultSuccessUrl("/mensajes")/*Si esta pagina de loggin me permite entrar la pagina que visualizo cuando autentifica mis credenciales es "/" en este caso*/
	            .failureUrl("/login?error=true")/*Si falla la entrada controlamos la salida para que nos muestre en mensaje de error que nosotros deseamos*/
	            .usernameParameter("username")/*En el formulario que te voy a mandar va a ser un input que se va a llamr username*/
	            
	            .passwordParameter("password")/*Le indicamos que nombre tiene la contraseña*/
	            .and()
	        .logout()/*Configuracion del logout*/
	            .permitAll()
	            .logoutUrl("/logout")
	            .logoutSuccessUrl("/")/*Ruta a la que nos lleva cuando el logOut tiene exito*/
	            .and()
	        .csrf().disable();/*Desabilita el sistema de seguridad csrf que viene activado por defecto en Spring*/

	    }

	   
	   
	   
	
}
