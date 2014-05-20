package com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.movimientos;

public enum DireccionMovimiento {
	Adelante(1),
	Atras(-1),
	Derecha(1),
	Izquierda(-1);
	
	private Integer signoDireccion;

	DireccionMovimiento(Integer signoDireccion) {
		this.signoDireccion = signoDireccion;
	}
	
	public Integer getSigno() {
		return this.signoDireccion;
	}
}
