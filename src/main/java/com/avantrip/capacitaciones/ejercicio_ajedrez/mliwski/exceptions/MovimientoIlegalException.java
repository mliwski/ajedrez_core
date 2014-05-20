package com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.exceptions;

public class MovimientoIlegalException extends RuntimeException {
	private static final long serialVersionUID = -1657958655246065875L;

	public MovimientoIlegalException(String mensaje) {
		super(mensaje);
	}

}
