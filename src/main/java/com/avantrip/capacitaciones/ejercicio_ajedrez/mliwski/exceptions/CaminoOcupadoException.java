package com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.exceptions;

public class CaminoOcupadoException extends MovimientoIlegalException {
	private static final long serialVersionUID = -7935185692563138101L;

	public CaminoOcupadoException(String mensaje) {
		super(mensaje);
	}

}
