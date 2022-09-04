package com.ibm.academia.restapi.model.services;

import org.springframework.stereotype.Service;

import com.ibm.academia.restapi.exception.BadRequestException;
import com.ibm.academia.restapi.model.enums.Pasion;

@Service
public class ValidacionServiceImpl implements IValidacionService {

	
	
	@Override
	public Pasion obtenerPasion(String pasion) {
		switch(pasion.toUpperCase()) {
		case "SHOPPING": 
			return Pasion.SHOPPING;
		case "TRAVELS":
			return Pasion.TRAVELS;
		case "HELP":
			return Pasion.HELP;
		case "MY_BUSSYNESS":
			return Pasion.MY_BUSSYNESS;
		case "SPORTS":
			return Pasion.SPORTS;
		case "MY_STYLE":
			return Pasion.MY_STYLE;
		default:
			throw new BadRequestException(
					"Pasion: '" + pasion + "' no se encuentra definida");
		}
	}


}
