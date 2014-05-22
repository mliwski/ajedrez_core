package com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.exceptions;

public class TipoMovimientoNoPermitidoException extends MovimientoIlegalException {
	private static final long serialVersionUID = -488024227759828162L;

	public TipoMovimientoNoPermitidoException(String mensaje) {
		super(mensaje);
	}

}
