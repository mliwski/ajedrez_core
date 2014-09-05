package com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.commands;

import java.util.ArrayList;
import java.util.List;

import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.Escaque;
import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.movimientos.Movimiento;
import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.notifications.Notification;
import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.notifications.TrebejoCapturadoNotification;
import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.tableros.Tablero;
import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.tableros.TableroInstance;
import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.tableros.TableroSnapshot;
import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.trebejos.Trebejo;

public class MoverCommand extends Command {
	private TableroInstance tablero;
	private Movimiento movimiento;
	private Trebejo trebejo;

	public MoverCommand(TableroInstance tablero, Escaque origen, Escaque destino) {
		checkArgumentsPreconditions(tablero, origen, destino);
		checkTrebejoEnOrigenPrecondition(tablero, origen);
		
		this.movimiento = new Movimiento(origen,destino);
		this.tablero = tablero;
		this.trebejo = tablero.getTrebejo(origen);
	}
	
	private void checkArgumentsPreconditions(Tablero tablero, Escaque origen, Escaque destino) {
		if(tablero == null) {
			throw new IllegalArgumentException("Para mover se necesita conocer el tablero");
		}
		if(origen == null || destino == null) {
			throw new IllegalArgumentException("Para mover se necesitan conocer el origen y el destino");
		}
		if(origen.equals(destino)) {
			throw new IllegalArgumentException("El origen y el destino deben ser distintos");
		}
	}

	private void checkTrebejoEnOrigenPrecondition(TableroInstance tablero, Escaque origen) {
		Trebejo trebejoEncontrado = tablero.getTrebejo(origen);
		if(trebejoEncontrado == null){
			throw new IllegalStateException("Para poder mover, el origen debe tener un trebejo");
		}
	}

	@Override
	public List<Notification> ejecutar() {
		TableroSnapshot tableroSnapshot = tablero.getSnapshot();		
		
		trebejo.checkPreconditions(tableroSnapshot, movimiento);
		List<Notification> notifications = evaluateNotificationsForTrebejoCapturado(tableroSnapshot);

		tablero.ejecutarMovimiento(movimiento);
		trebejo.addMovimiento(movimiento);

		//TODO: Implementar el notification (Aca o en un observer) para 
		// - Coronacion (Solo para el peon) 
		// - Tablas
		// - Jaque como notificacion
		//Seguramente convenga realizaro cuando tenga la lista de movimientos habilitados, es mas facil de evaluar


		return notifications;
	}

	private List<Notification> evaluateNotificationsForTrebejoCapturado(TableroSnapshot tableroSnapshot) {
		List<Notification> notifications = new ArrayList<Notification>();
		
		Trebejo trebejoCapturado = trebejo.getTrebejoCapturado(tableroSnapshot, movimiento);
		
		if(trebejoCapturado != null) {
			TrebejoCapturadoNotification trebejoCapturadoNotification = new TrebejoCapturadoNotification(trebejoCapturado);
			notifications.add(trebejoCapturadoNotification);
		}
		
		return notifications;
	}
}