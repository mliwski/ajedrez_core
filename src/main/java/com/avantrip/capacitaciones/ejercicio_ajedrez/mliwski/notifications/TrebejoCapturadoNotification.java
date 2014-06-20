package com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.notifications;

import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.trebejos.Trebejo;

public class TrebejoCapturadoNotification extends SuccesNotification {

	private Trebejo trebejoCapturado;
	
	//TODO: Ver como cerrar bien este codigo
	public TrebejoCapturadoNotification(Trebejo trebejoCapturado) {
		this.trebejoCapturado = trebejoCapturado;
	}

	public Trebejo getTrebejoCapturado() {
		return trebejoCapturado;
	}
}
