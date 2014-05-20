package com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski;

public enum DireccionAtaque {
	Adelante(1),
	Atras(-1);
	
	private Integer signoDireccion;

	DireccionAtaque(Integer signoDireccion) {
		this.signoDireccion = signoDireccion;
	}
	
	public Integer getSigno(){
		return this.signoDireccion;
	}
}
