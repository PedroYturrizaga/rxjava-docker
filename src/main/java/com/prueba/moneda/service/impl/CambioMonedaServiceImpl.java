package com.prueba.moneda.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prueba.moneda.dao.impl.CambioMonedaDAOImpl;
import com.prueba.moneda.dto.CambioMonedaDTORequest;
import com.prueba.moneda.dto.CambioMonedaDTOResponse;
import com.prueba.moneda.dto.ListarCambioMonedaDTO;
import com.prueba.moneda.dto.TipoCambioDTORequest;
import com.prueba.moneda.model.CambioMoneda;
import com.prueba.moneda.service.ICambioMonedaService;

@Service
public class CambioMonedaServiceImpl implements ICambioMonedaService {

	@Autowired
	private CambioMonedaDAOImpl dao;

	@Override
	public List<CambioMoneda> listar() {
		return dao.listar();
	}

	@Override
	public CambioMoneda listarCambioMoneda(ListarCambioMonedaDTO id) {
		return dao.listarCambioMoneda(id);
	}

	@Override
	public Integer update(TipoCambioDTORequest tipoCambio) {
		return dao.update(tipoCambio);
	}

	@Override
	public CambioMonedaDTOResponse tipoCambio(CambioMonedaDTORequest cambioMoneda) {
		return dao.tipoCambio(cambioMoneda);
	}

}
