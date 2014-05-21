package com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski;

import java.util.List;

import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.commands.Command;
import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.movimientos.Movimiento;
import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.notifications.Notification;
import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.trebejos.Color;
import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.trebejos.Pieza;
import com.google.common.collect.HashBiMap;
import com.google.common.collect.Multimap;

public class Tablero {
	private HashBiMap<Escaque, Pieza> escaques;
	private Multimap<Color, Pieza> piezasComidas;
	
	private static Tablero instance;
	
	private Tablero() {
		escaques = HashBiMap.create();
	}
	
	synchronized public static Tablero getInstance() {
		if(instance == null) {
			instance = new Tablero();
		}
		return instance;
	}

	public Pieza getPieza(Escaque escaque) {
		return escaques.get(escaque);
	}


	public boolean isCaminoLibre(Movimiento movimiento) {
		// TODO Auto-generated method stub
		return true;
	}
	
	public List<Notification> ejecutar(Command command){
		List<Notification> notifications = command.ejecutar();
		return notifications;
	}

	//TODO: Refactor de nombre para aclarar que retorna pieza comida o pensar si va aca el comer
	public Pieza ponerPiezaEnEscaque(Pieza pieza, Escaque escaque){
		Escaque origen = escaques.inverse().get(pieza);
		escaques.remove(origen);
		
		//TODO: Soportar peon al paso
		Pieza piezaComida = escaques.get(escaque);
		if(piezaComida != null) {
			piezasComidas.put(pieza.getColor(), pieza);
		}
		
		escaques.put(escaque, pieza);
		
		return piezaComida;
	}
}