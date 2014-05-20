package com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.exceptions;

public class TipoMovimientoDesconocidoException extends RuntimeException {
	private static final long serialVersionUID = 2751615038552147653L;

	public TipoMovimientoDesconocidoException(String mensaje) {
		super(mensaje);
	}
}
