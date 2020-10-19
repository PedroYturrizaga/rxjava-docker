package com.prueba.moneda;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootApplication
public class CambiomonedaApplication implements CommandLineRunner {
	
	@Autowired
	private JdbcTemplate template;

	public static void main(String[] args) {
		SpringApplication.run(CambiomonedaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		template.execute("DROP TABLE tipocambio IF EXISTS");
		template.execute(
				"CREATE TABLE tipocambio( id Integer (11) PRIMARY KEY auto_increment, moneda_origen VARCHAR(50) NOT NULL, moneda_destino VARCHAR (50) NOT NULL, tipo_cambio DOUBLE NOT NULL )");

		/* Insertar primeros datos */
		template.update("INSERT INTO tipocambio( moneda_origen, moneda_destino, tipo_cambio) VALUES ( 'SOLES', 'DOLARES', '3.51')");
		template.update("INSERT INTO tipocambio( moneda_origen, moneda_destino, tipo_cambio) VALUES ( 'SOLES', 'EUROS', '3.89')");
		template.update("INSERT INTO tipocambio( moneda_origen, moneda_destino, tipo_cambio) VALUES ( 'SOLES', 'BOLIVIANO', '1.10')");
		template.update("INSERT INTO tipocambio( moneda_origen, moneda_destino, tipo_cambio) VALUES ( 'SOLES', 'PESO ARGENTINO', '0.75')");
		template.update("INSERT INTO tipocambio( moneda_origen, moneda_destino, tipo_cambio) VALUES ( 'SOLES', 'REALES', '2.98')");
		
		template.execute("DROP TABLE usuario IF EXISTS");
		template.execute(
				"CREATE TABLE usuario( idUsuario Integer (11) PRIMARY KEY auto_increment, nombre VARCHAR(50) NOT NULL, clave VARCHAR (50) NOT NULL, estado BOOLEAN NOT NULL )");

//		/* Insertar primeros datos */
//		template.update("INSERT INTO tipocambio( moneda_origen, moneda_destino, tipo_cambio) VALUES ( 'SOLES', 'DOLARES', '3.51')");
//		template.update("INSERT INTO tipocambio( moneda_origen, moneda_destino, tipo_cambio) VALUES ( 'SOLES', 'EUROS', '3.89')");
//		template.update("INSERT INTO tipocambio( moneda_origen, moneda_destino, tipo_cambio) VALUES ( 'SOLES', 'BOLIVIANO', '1.10')");
//		template.update("INSERT INTO tipocambio( moneda_origen, moneda_destino, tipo_cambio) VALUES ( 'SOLES', 'PESO ARGENTINO', '0.75')");
//		template.update("INSERT INTO tipocambio( moneda_origen, moneda_destino, tipo_cambio) VALUES ( 'SOLES', 'REALES', '2.98')");
		
	}
}
