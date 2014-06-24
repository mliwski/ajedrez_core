package com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.commands;

import java.util.ArrayList;
import java.util.List;

import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.Escaque;
import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.Tablero;
import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.TableroInstance;
import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.TableroSnapshot;
import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.capturas.CapturaStrategy;
import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.movimientos.Movimiento;
import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.notifications.Notification;
import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.notifications.TrebejoCapturadoNotification;
import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.trebejos.Trebejo;

public class MoverCommand extends Command {
	private TableroInstance tablero;
	private Movimiento movimiento;
	private Trebejo trebejo;

	public MoverCommand(TableroInstance tablero, Escaque origen, Escaque destino) {
		checkArgumentsPreconditions(tablero, origen, destino);
		checkTrebejoEnOrigenPrecondition(origen);
		
		this.movimiento = new Movimiento(origen,destino);
		this.tablero = tablero;
	}
	
	private void checkArgumentsPreconditions(Tablero tablero, Escaque origen, Escaque destino) {
		if(tablero == null) {
			throw new IllegalArgumentException("Para mover se necesita conocer el tablero");
		}
		if(origen == null || destino == null) {
			throw new IllegalArgumentException("Para mover se necesitan conocer el origen y el destino");
		}
	}

	private void checkTrebejoEnOrigenPrecondition(Escaque origen) {
		Trebejo trebejoEncontrado = tablero.getTrebejo(origen);
		if(trebejoEncontrado == null){
			throw new IllegalArgumentException("Para poder mover, el origen debe tener un trebejo");
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


		return notifications;
	}

	private List<Notification> evaluateNotificationsForTrebejoCapturado(TableroSnapshot tableroSnapshot) {
		List<Notification> notifications = new ArrayList<Notification>();
		
		Trebejo trebejoCapturado = getTrebejoCapturado(tableroSnapshot, movimiento);
		
		if(trebejoCapturado != null) {
			TrebejoCapturadoNotification trebejoCapturadoNotification = new TrebejoCapturadoNotification(trebejoCapturado);
			notifications.add(trebejoCapturadoNotification);
		}
		
		return notifications;
	}

	private Trebejo getTrebejoCapturado(TableroSnapshot tableroSnapshot, Movimiento movimiento) {
		CapturaStrategy capturaStrategy = trebejo.getCapturaStrategy();
		Trebejo trebejoCapturado = capturaStrategy.getTrebejoCapturado(tableroSnapshot, movimiento);
		return trebejoCapturado;
	}
}