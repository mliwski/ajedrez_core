package com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.commands;

import java.util.ArrayList;
import java.util.List;

import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.DireccionAtaque;
import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.Escaque;
import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.Tablero;
import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.movimientos.Movimiento;
import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.notifications.Notification;
import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.notifications.TrebejoComidoNotification;
import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.trebejos.Trebejo;

public class MoverCommand extends Command {
	private static Tablero tablero = Tablero.getInstance();
	
	private Movimiento movimiento;
	private Trebejo trebejo;

	private List<Notification> notifications;

	public MoverCommand(Escaque origen, Escaque destino) {
		checkArgumentsPreconditions(origen, destino);
		checkTrebejoEncontradoPrecondition(origen);
		
		
		//TODO: Con el refactor de direccion de ataque basado en color, esto vuela!!!
		DireccionAtaque direccionAtaque = trebejo.getColor().getDireccionAtaque();
		this.movimiento = new Movimiento(origen,destino,direccionAtaque);
	}
	
	private void checkArgumentsPreconditions(Escaque origen, Escaque destino) {
		if(origen == null || destino == null) {
			throw new IllegalArgumentException("Para mover se necesitan conocer el origen y el destino");
		}
	}

	private void checkTrebejoEncontradoPrecondition(Escaque origen) {
		Trebejo trebejoEncontrado = tablero.getTrebejo(origen);
		if(trebejoEncontrado == null){
			throw new IllegalArgumentException("Para poder mover, el origen debe tener un trebejo");
		} else {
			this.trebejo = trebejoEncontrado;
		}
	}

	@Override
	public List<Notification> ejecutar() {
		notifications = new ArrayList<Notification>();

		trebejo.checkPreconditions(movimiento);
		
		moverTrebejo();
		
		return notifications;
	}

	private List<Notification> moverTrebejo() {
		Trebejo trebejoComido = tablero.moverTrebejoAEscaque(trebejo, movimiento.getOrigen());
		
		if(trebejoComido != null) {
			notifications.add(new TrebejoComidoNotification(trebejoComido));
		}
		
		return notifications;
	}
}