package com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.exceptions;

public class ReyAmenazadoException extends MovimientoIlegalException {
	private static final long serialVersionUID = 4846574184440798109L;

	public ReyAmenazadoException(String mensaje) {
		super(mensaje);
	}

}
