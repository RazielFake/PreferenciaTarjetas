package com.ibm.academia.restapi.usuario.clientes;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.ibm.academia.restapi.usuario.model.entities.Perfil;


@FeignClient(name = "api-tarjetas")
public interface TarjetaClienteRest {

	@GetMapping("/api/v1/tarjetas/perfiles/listar")
	public List<Perfil> consultarTodosPerfiles();
	
	@GetMapping("/api/v1/tarjetas/perfiles/perfil/perfilId/{perfilId}")
	public Perfil buscarPerfil(@PathVariable Integer perfilId);
	
	@GetMapping("/api/v1/tarjetas/perfiles/perfil-pasion/pasion/{pasion}")
	public List<Perfil> buscarPerfilesPorPasion(@PathVariable String pasion);
}
