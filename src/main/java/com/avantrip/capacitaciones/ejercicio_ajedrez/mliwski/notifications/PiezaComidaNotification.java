package com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.notifications;

import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.piezas.Pieza;

public class PiezaComidaNotification extends SuccesNotification {
	private Pieza pieza;

	public PiezaComidaNotification(String message) {
		super(message);
	}
	
	public PiezaComidaNotification(Pieza pieza) {
		super(pieza.toString());
		this.setPieza(pieza);
	}

	public Pieza getPieza() {
		return pieza;
	}
	public void setPieza(Pieza pieza) {
		this.pieza = pieza;
	}
}
