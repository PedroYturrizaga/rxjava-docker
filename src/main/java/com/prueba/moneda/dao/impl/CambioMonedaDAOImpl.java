package com.prueba.moneda.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.prueba.moneda.dao.ICambioMonedaDAO;
import com.prueba.moneda.dto.CambioMonedaDTORequest;
import com.prueba.moneda.dto.CambioMonedaDTOResponse;
import com.prueba.moneda.dto.ListarCambioMonedaDTO;
import com.prueba.moneda.dto.TipoCambioDTORequest;
import com.prueba.moneda.model.CambioMoneda;

@Repository
public class CambioMonedaDAOImpl implements ICambioMonedaDAO{

	@Autowired
	private JdbcTemplate template;
	
	@Override
	public List<CambioMoneda> listar() {
		String sql = "SELECT * FROM TIPOCAMBIO";
		List<CambioMoneda> cambioMoneda = template.query(sql, new BeanPropertyRowMapper<CambioMoneda>(CambioMoneda.class));
		return cambioMoneda;
	}

	@Override
	public CambioMoneda listarCambioMoneda(ListarCambioMonedaDTO dto) {
		String sql = "SELECT * FROM TIPOCAMBIO WHERE id = "+dto.getId();
		List<CambioMoneda> cambioMoneda = template.query(sql, new BeanPropertyRowMapper<CambioMoneda>(CambioMoneda.class));
		return (CambioMoneda) cambioMoneda.get(0);
	}

	@Override
	public CambioMoneda listarCambioMonedaByName(String name) {
		String sql = "SELECT * FROM TIPOCAMBIO WHERE moneda_destino LIKE '%"+name+"%'";
		List<CambioMoneda> cambioMoneda = template.query(sql, new BeanPropertyRowMapper<CambioMoneda>(CambioMoneda.class));
		return (CambioMoneda) cambioMoneda.get(0);
	}

	@Override
	public Integer update(TipoCambioDTORequest tipoCambio) {		
		Integer upt = template.update("UPDATE TIPOCAMBIO SET tipo_cambio = ? WHERE id = ?", tipoCambio.getMontoTipoCambio(), tipoCambio.getId());
		return upt;		
	}

	@Override
	public CambioMonedaDTOResponse tipoCambio(CambioMonedaDTORequest cambioMoneda) {
		CambioMonedaDTOResponse response = new CambioMonedaDTOResponse();
		CambioMoneda cambMoneda = listarCambioMonedaByName(cambioMoneda.getMonedaDestino());
		Double tipoCambio = cambMoneda.getTipoCambio();
		Double monto = cambioMoneda.getMonto();
		Double montoTipoCambio = monto * tipoCambio;
		
		response.setMonedaDestino(cambioMoneda.getMonedaDestino());
		response.setMonedaOrigen(cambioMoneda.getMonedaOrigen());
		response.setMonto(cambioMoneda.getMonto());
		response.setMontoTipoCambio(montoTipoCambio);
		response.setTipoCambio(tipoCambio);
		
		return response;
	}

}
