package com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.exceptions;

public class DireccionNoPermitidaException extends MovimientoIlegalException {
	private static final long serialVersionUID = -488024227759828162L;

	public DireccionNoPermitidaException(String mensaje) {
		super(mensaje);
	}

}
