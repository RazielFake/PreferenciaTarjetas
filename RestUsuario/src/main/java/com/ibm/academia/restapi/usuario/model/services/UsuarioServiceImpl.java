package com.ibm.academia.restapi.usuario.model.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.RestTemplate;

import com.ibm.academia.restapi.usuario.mapper.PerfilMapper;
import com.ibm.academia.restapi.usuario.model.dto.PerfilDTO;
import com.ibm.academia.restapi.usuario.model.entities.Perfil;
import com.ibm.academia.restapi.usuario.model.entities.Tarjeta;
import com.ibm.academia.restapi.usuario.model.entities.Usuario;
import com.ibm.academia.restapi.usuario.model.entities.UsuarioValidacion;
import com.ibm.academia.restapi.usuario.model.enums.NombreTarjeta;
import com.ibm.academia.restapi.usuario.model.enums.Pasion;


@Service("serviceRestTemplate")
public class UsuarioServiceImpl implements IUsuarioService{

	
	@Autowired 
	private RestTemplate clienteRest;
	
	@Autowired
	private IValidacionService validacionService;
	
	@Override
	public ResponseEntity<?> obtenerTarjetas(@Valid @RequestBody UsuarioValidacion usuarioValidacion, BindingResult result) {
		
		Usuario usuario = this.validacionService.validarCampos(usuarioValidacion);
		
		Map<String, String> variables = new HashMap<String, String>();
		variables.put("pasion", usuario.getPasion().toString());
		List<Perfil> perfiles = Arrays.asList(clienteRest.getForObject(
				"http://api-tarjetas/api/v1/tarjetas/perfiles/perfil-pasion/pasion/{pasion}", Perfil[].class, variables));
		
		perfiles
		.stream()
		.filter(perfil -> !(usuario.getSalario() < perfil.getSalarioMinimo()) && !(usuario.getSalario() > perfil.getSalarioMaximo()))
		.filter(perfil -> !(usuario.getEdad() < perfil.getEdadMinima()) && !(usuario.getEdad() > perfil.getEdadMaxima()))
		.collect(Collectors.toList());
		
		if(perfiles.isEmpty()) {
			return new ResponseEntity<String>("No se hayaron tarjetas para el perfil ingresado.", HttpStatus.PRECONDITION_FAILED);
		}
		
		List<PerfilDTO> listaPerfiles = perfiles
				.stream()
				.map(PerfilMapper::mapPerfil)
				.collect(Collectors.toList());
		return new ResponseEntity<List<PerfilDTO>>(listaPerfiles, HttpStatus.OK);
	}


	@Override
	public ResponseEntity<?> buscarPerfilPorId(Integer perfilId) {
		Map<String, String> variables = new HashMap<String, String>();
		variables.put("perfilId", perfilId.toString());
		Perfil perfil = clienteRest.getForObject(
				"http://api-tarjetas/api/v1/tarjetas/perfiles/perfil/perfilId/{perfilId}", Perfil.class, variables);
		return new ResponseEntity<Perfil>(perfil, HttpStatus.OK);
	}


	public ResponseEntity<?> metodoAlternativo(){
		Perfil perfil = new Perfil();
		List<Perfil> perfiles = new ArrayList<Perfil>();
		perfil.setId(0);
		perfil.setEdadMinima(18);
		perfil.setEdadMaxima(75);
		perfil.setSalarioMinimo(7000.0);
		perfil.setPasion(Pasion.MY_STYLE);
		Tarjeta tarjeta = new Tarjeta(NombreTarjeta.CLASICA);
		Set<Tarjeta> tarjetas = new HashSet<Tarjeta>();
		tarjetas.add(tarjeta);
		perfil.setTarjetas(tarjetas);
		perfiles.add(perfil);
		List<PerfilDTO> listaPerfiles = perfiles
				.stream()
				.map(PerfilMapper::mapPerfil)
				.collect(Collectors.toList());
		return new ResponseEntity<List<PerfilDTO>>(listaPerfiles, HttpStatus.OK);
	}


}
