package com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.notifications;

import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.trebejos.Trebejo;

public class TrebejoComidoNotification extends SuccesNotification {
	private Trebejo trebejo;

	public TrebejoComidoNotification(String message) {
		super(message);
	}
	
	public TrebejoComidoNotification(Trebejo trebejo) {
		super(trebejo.toString());
		this.setTrebejo(trebejo);
	}

	public Trebejo getTrebejo() {
		return trebejo;
	}
	public void setTrebejo(Trebejo trebejo) {
		this.trebejo = trebejo;
	}
}
