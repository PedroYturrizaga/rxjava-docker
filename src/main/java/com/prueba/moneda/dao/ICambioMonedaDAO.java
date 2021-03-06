package com.prueba.moneda.dao;

import java.util.List;

import com.prueba.moneda.dto.CambioMonedaDTORequest;
import com.prueba.moneda.dto.CambioMonedaDTOResponse;
import com.prueba.moneda.dto.ListarCambioMonedaDTO;
import com.prueba.moneda.dto.TipoCambioDTORequest;
import com.prueba.moneda.model.CambioMoneda;

public interface ICambioMonedaDAO {

	public List<CambioMoneda> listar();

	public CambioMoneda listarCambioMoneda(ListarCambioMonedaDTO id);

	public CambioMoneda listarCambioMonedaByName(String name);
	
	public Integer update(TipoCambioDTORequest tipoCambio);

	public CambioMonedaDTOResponse tipoCambio(CambioMonedaDTORequest cambioMoneda);
	
}
