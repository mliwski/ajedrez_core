package com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.commands;

import java.util.ArrayList;
import java.util.List;

import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.DireccionAtaque;
import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.Escaque;
import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.Tablero;
import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.movimientos.Movimiento;
import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.notifications.Notification;
import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.notifications.PiezaComidaNotification;
import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.trebejos.Pieza;

public class MoverCommand extends Command {
	private static Tablero tablero = Tablero.getInstance();
	
	private Movimiento movimiento;
	private Pieza pieza;

	private List<Notification> notifications;

	public MoverCommand(Escaque origen, Escaque destino) {
		checkArgumentsPreconditions(origen, destino);
		checkPiezaFoundPrecondition(origen);
		
		
		//TODO: Con el refactor de direccion de ataque basado en color, esto vuela!!!
		DireccionAtaque direccionAtaque = pieza.getColor().getDireccionAtaque();
		this.movimiento = new Movimiento(origen,destino,direccionAtaque);
	}
	
	private void checkArgumentsPreconditions(Escaque origen, Escaque destino) {
		if(origen == null || destino == null) {
			throw new IllegalArgumentException("Para mover se necesitan conocer el origen y el destino");
		}
	}

	private void checkPiezaFoundPrecondition(Escaque origen) {
		Pieza piezaFound = tablero.getPieza(origen);
		if(piezaFound == null){
			throw new IllegalArgumentException("Para poder mover, el origen debe tener una pieza");
		} else {
			this.pieza = piezaFound;
		}
	}

	@Override
	public List<Notification> ejecutar() {
		notifications = new ArrayList<Notification>();

		pieza.checkPreconditions(movimiento);
		
		moverPieza();
		
		return notifications;
	}

	private List<Notification> moverPieza() {
		Pieza piezaComida = tablero.ponerPiezaEnEscaque(pieza, movimiento.getOrigen());
		
		if(piezaComida != null) {
			notifications.add(new PiezaComidaNotification(piezaComida));
		}
		
		return notifications;
	}
}
