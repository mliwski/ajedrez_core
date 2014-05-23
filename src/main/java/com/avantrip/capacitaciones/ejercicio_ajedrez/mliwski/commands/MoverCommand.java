package com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.commands;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.Stack;

import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.DireccionAtaque;
import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.Escaque;
import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.Tablero;
import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.movimientos.Movimiento;
import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.notifications.Notification;
import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.notifications.TrebejoComidoNotification;
import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.trebejos.Peon;
import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.trebejos.Trebejo;

public class MoverCommand extends Command {
	private static Tablero tablero = Tablero.getInstance();
	
	private Movimiento movimiento;
	private Trebejo trebejo;

	public MoverCommand(Escaque origen, Escaque destino) {
		checkArgumentsPreconditions(origen, destino);
		checkTrebejoEnOrigenPrecondition(origen);
		
		//TODO: Con el refactor de direccion de ataque basado en color, estas dos lineas siguientes vuelan!!!
		this.trebejo = tablero.getTrebejo(origen);
		DireccionAtaque direccionAtaque = trebejo.getColor().getDireccionAtaque();
		this.movimiento = new Movimiento(origen,destino,direccionAtaque);
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
		trebejo.checkPreconditions(movimiento);
		
		List<Notification> notifications = moverTrebejo();

		Deque<Trebejo> x = new ArrayDeque<Trebejo>();
		x.push(trebejo);
		x.pop();
		
		if(trebejo instanceof Peon == false) {
			
		}
		
		
		//TODO: Implementar el notifications evaluator, debe evaluar por:
		// - Pieza comida (antes o despues de mover?) Se puede basar en un comer strategy del Trebejo
		// -- Comida para pasante
		// - Coronacion
		// - Tablas
		// - Jaque como notificacion

		// TODO: Implementar el jaque evaluator para rollback de mi rey
		return notifications;
	}

	private List<Notification> moverTrebejo() {
		List<Notification> notifications = new ArrayList<Notification>();
		
		Escaque destino = movimiento.getDestino();
		Trebejo trebejoComido = tablero.moverTrebejoAEscaque(trebejo, destino);
		if(trebejoComido != null) {
			notifications.add(new TrebejoComidoNotification(trebejoComido));
		}
		
		trebejo.addMovimiento(movimiento);
		
		return notifications;
	}
}