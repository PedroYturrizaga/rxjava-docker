package com.prueba.moneda.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.prueba.moneda.dto.CambioMonedaDTORequest;
import com.prueba.moneda.dto.CambioMonedaDTOResponse;
import com.prueba.moneda.dto.ListarCambioMonedaDTO;
import com.prueba.moneda.dto.TipoCambioDTORequest;
import com.prueba.moneda.model.CambioMoneda;
import com.prueba.moneda.service.ICambioMonedaService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/cambiomoneda")
public class CambioMonedaController {

	private static final Logger log = LoggerFactory.getLogger(CambioMonedaController.class);

	@Autowired
	private ICambioMonedaService service;

	@PostMapping(value = "/updateTipoCambio", produces = MediaType.APPLICATION_JSON_VALUE)
	public Mono<ResponseEntity<Integer>> updateTipoCambio(@RequestBody TipoCambioDTORequest tipoCambio) {
		Integer upt = service.update(tipoCambio);
		return Mono.just(ResponseEntity.ok()
				.contentType(MediaType.APPLICATION_JSON)
				.body(upt));
	}
	
	@PostMapping(value = "/calcularCambioMoneda", produces = MediaType.APPLICATION_JSON_VALUE)
	public Mono<ResponseEntity<CambioMonedaDTOResponse>> calcularCambioMoneda(@RequestBody CambioMonedaDTORequest cambioMoneda) {
		CambioMonedaDTOResponse cambio = service.tipoCambio(cambioMoneda);
		return Mono.just(ResponseEntity.ok()
				.contentType(MediaType.APPLICATION_JSON)
				.body(cambio));//new ResponseEntity(cambio, HttpStatus.OK);
	}

	@PostMapping("/mono-listar")
	public Mono<CambioMoneda> monoListar(@RequestBody ListarCambioMonedaDTO id) {
		CambioMoneda cambioMoneda = service.listarCambioMoneda(id);
		return Mono.just(cambioMoneda); // (new CambioMoneda());
	}

	@PostMapping("/flux-listar")
	public Flux<CambioMoneda> fluxListar() {
		List<CambioMoneda> lsCambioMoneda = new ArrayList<>();
		lsCambioMoneda = service.listar();
		Flux<CambioMoneda> cambioFlux = Flux.fromIterable(lsCambioMoneda);
		return cambioFlux;
	}
}
