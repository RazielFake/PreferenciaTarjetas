package com.ibm.academia.restapi.usuario.model.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;

import com.ibm.academia.restapi.usuario.clientes.TarjetaClienteRest;
import com.ibm.academia.restapi.usuario.mapper.PerfilMapper;
import com.ibm.academia.restapi.usuario.model.dto.PerfilDTO;
import com.ibm.academia.restapi.usuario.model.entities.Perfil;
import com.ibm.academia.restapi.usuario.model.entities.Tarjeta;
import com.ibm.academia.restapi.usuario.model.entities.Usuario;
import com.ibm.academia.restapi.usuario.model.entities.UsuarioValidacion;
import com.ibm.academia.restapi.usuario.model.enums.NombreTarjeta;
import com.ibm.academia.restapi.usuario.model.enums.Pasion;

@Service("serviceFeign")
@Primary
public class UsuarioServiceFeign implements IUsuarioService{

	@Autowired
	private TarjetaClienteRest clienteFeign;
	

	@Autowired
	private IValidacionService validacionService;
	

	@Override
	public ResponseEntity<?> obtenerTarjetas(@Valid @RequestBody UsuarioValidacion usuarioValidacion, BindingResult result) {
		Map<String, Object> validations = new HashMap<String, Object>();
		if(result.hasErrors()) {
			List<String> listErrors = result.getFieldErrors()
					.stream()
					.map(errors -> "Field: " + errors.getField() + " - " + errors.getDefaultMessage())
					.collect(Collectors.toList());
			validations.put("Errors List", listErrors);
			return new ResponseEntity<Map<String, Object>>(validations, HttpStatus.BAD_REQUEST);
		}
		
		Usuario usuario = this.validacionService.validarCampos(usuarioValidacion);
		
		List<Perfil> perfiles = this.clienteFeign.buscarPerfilesPorPasion(usuario.getPasion().toString());
		
		List<Perfil> perfilesCandidatos = new ArrayList<>();
				
				perfiles.forEach(perfilIterable ->{
					if(!(usuario.getSalario() < perfilIterable.getSalarioMinimo()) 
							&& !(usuario.getSalario() > perfilIterable.getSalarioMaximo())) {
						if(!(usuario.getEdad() < perfilIterable.getEdadMinima()) && 
								!(usuario.getEdad() > perfilIterable.getEdadMaxima())) {
							perfilesCandidatos.add(perfilIterable);
						}
					}
				});
				
				if(perfilesCandidatos.isEmpty()) {
					return new ResponseEntity<String>("No se hayaron tarjetas para el perfil ingresado.", HttpStatus.PRECONDITION_FAILED);
				}
				
				List<PerfilDTO> listaPerfiles = perfilesCandidatos
						.stream()
						.map(PerfilMapper::mapPerfil)
						.collect(Collectors.toList());
				return new ResponseEntity<List<PerfilDTO>>(listaPerfiles, HttpStatus.OK);
	}
	
	@Override
	public ResponseEntity<?> buscarPerfilPorId(Integer perfilId) {
		return new ResponseEntity<Perfil>(
				this.clienteFeign.buscarPerfil(perfilId), HttpStatus.OK);
	}


	@Override
	public ResponseEntity<?> metodoAlternativo(){
		Perfil perfil = new Perfil();
		List<Perfil> perfiles = new ArrayList<Perfil>();
		perfil.setId(0);
		perfil.setEdadMinima(18);
		perfil.setEdadMaxima(75);
		perfil.setSalarioMinimo(7000.0);
		perfil.setSalarioMaximo(9000000.0);
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
