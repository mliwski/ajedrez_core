package com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski;

import java.util.List;

import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.commands.Command;
import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.movimientos.Movimiento;
import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.notifications.Notification;
import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.trebejos.Color;
import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.trebejos.Trebejo;
import com.google.common.collect.HashBiMap;
import com.google.common.collect.Multimap;

public class Tablero {
	private HashBiMap<Escaque, Trebejo> escaques;
	private Multimap<Color, Trebejo> trebejosComidos;
	
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

	public Trebejo getTrebejo(Escaque escaque) {
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

	//TODO: Refactor de nombre para aclarar que retorna trebejo comido o pensar si va aca el comer
	public Trebejo moverTrebejoAEscaque(Trebejo trebejo, Escaque escaque){
		Escaque origen = escaques.inverse().get(trebejo);
		escaques.remove(origen);
		
		//TODO: Soportar peon al paso
		Trebejo trebejoComido = escaques.get(escaque);
		if(trebejoComido != null) {
			trebejosComidos.put(trebejo.getColor(), trebejo);
		}
		
		escaques.put(escaque, trebejo);
		
		return trebejoComido;
	}
}