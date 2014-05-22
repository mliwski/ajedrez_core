package com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.exceptions;

public class DestinoNoOcupableException extends MovimientoIlegalException {
	private static final long serialVersionUID = 7066337819775589968L;

	public DestinoNoOcupableException(String mensaje) {
		super(mensaje);
	}

}
