package com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.exceptions;

public class CantidadMaximaSuperadaException extends MovimientoIlegalException {
	private static final long serialVersionUID = -8968853895071761055L;

	public CantidadMaximaSuperadaException(String mensaje) {
		super(mensaje);
	}

}
