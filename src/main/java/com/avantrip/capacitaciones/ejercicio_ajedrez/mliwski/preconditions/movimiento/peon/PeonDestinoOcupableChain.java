package com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.preconditions.movimiento.peon;

import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.TableroSnapshot;
import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.movimientos.Movimiento;

public abstract class PeonDestinoOcupableChain {
	private PeonDestinoOcupableChain next;
	
	public PeonDestinoOcupableChain() {
		this.next = null;
	}
	
	public PeonDestinoOcupableChain(PeonDestinoOcupableChain next) {
		this.next = next;
	}

	public void check(TableroSnapshot tableroSnapshot, Movimiento movimiento) {
		checkPrecondition(tableroSnapshot, movimiento);

		if(this.next != null) {
  			this.next.check(tableroSnapshot, movimiento);
  		}
	}
	
	protected abstract void checkPrecondition(TableroSnapshot tableroSnapshot, Movimiento movimiento);
}
