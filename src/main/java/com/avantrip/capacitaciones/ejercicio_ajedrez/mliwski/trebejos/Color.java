package com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.trebejos;

import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.DireccionAtaque;

public enum Color {
	Blanco(DireccionAtaque.Adelante),
	Negro(DireccionAtaque.Atras);

	private DireccionAtaque direccionAtaque;

	Color(DireccionAtaque direccionAtaque) {
		this.direccionAtaque = direccionAtaque;
	}
	
	public DireccionAtaque getDireccionAtaque(){
		return this.direccionAtaque;
	}
	
	public Color getContrincante() {
		return this.equals(Blanco) ? Negro : Blanco ;
	}
}
