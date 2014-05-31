package com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.commands;

import java.util.List;

import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.Escaque;
import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.Tablero;
import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.capturas.CapturaStrategy;
import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.movimientos.Movimiento;
import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.notifications.Notification;
import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.trebejos.Color;
import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.trebejos.Trebejo;

public class MoverCommand extends Command {
	private static Tablero tablero = Tablero.getInstance();
	
	private Movimiento movimiento;
	private Trebejo trebejo;

	public MoverCommand(Escaque origen, Escaque destino) {
		checkArgumentsPreconditions(origen, destino);
		checkTrebejoEnOrigenPrecondition(origen);
		
		this.movimiento = new Movimiento(origen,destino);
	}
	
	private void checkArgumentsPreconditions(Escaque origen, Escaque destino) {
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
		List<Notification> notifications = null;
		trebejo.checkPreconditions(movimiento);
		
		//TODO: Implementar el notifications evaluator, debe evaluar por:
		// - Pieza comida Basarlo en el capturaStrategy del Trebejo
		CapturaStrategy capturaStrategy = trebejo.getCapturaStrategy();
		Trebejo trebejoCapturado = capturaStrategy.getTrebejoCapturado(movimiento);
		
		tablero.moverTrebejo(movimiento);
		
		// TODO: Implementar el jaque evaluator para rollback de mi rey
		Color color = trebejo.getColor();
		Color colorContrincante = color.getContrincante();
		Escaque rey = tablero.getEscaqueRey(color);
		if(tablero.isEscaqueAmenazadoPorColor(rey, colorContrincante)){
			tablero.rollback();
		}

		
		
		trebejo.addMovimiento(movimiento);

		//TODO: Implementar el notifications evaluator, debe evaluar por:
		// -- Comida para pasante
		//TODO: Implementar el notification Coronacion (Solo para el peon) 
		// - Tablas
		// - Jaque como notificacion


		return notifications;
	}
}