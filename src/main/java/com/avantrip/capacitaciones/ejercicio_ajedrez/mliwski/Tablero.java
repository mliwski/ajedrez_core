package com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski;

import java.util.List;

import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.commands.Command;
import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.movimientos.Movimiento;
import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.notifications.Notification;
import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.trebejos.Alfil;
import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.trebejos.Caballo;
import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.trebejos.Color;
import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.trebejos.Peon;
import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.trebejos.Reina;
import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.trebejos.Rey;
import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.trebejos.Torre;
import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.trebejos.Trebejo;
import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import com.google.common.collect.Multimap;

public class Tablero {
	private HashBiMap<Escaque, Trebejo> escaques;
	private Multimap<Color, Trebejo> trebejosComidos;
	
	private static Tablero instance;
	
	private Tablero() {
		escaques = HashBiMap.create();
	}

	//TODO: Revisar costo y el problema de no poder llamarlo en el constructor por el singleton
	//TODO: Crear un abstract factory para hacerlo mas prolijo
	public void inicializar() {
		escaques = HashBiMap.create();
		
		posicionarTorres(Color.Blanco);
		posicionarCaballos(Color.Blanco);
		posicionarAlfiles(Color.Blanco);
		posicionarReinaRey(Color.Blanco);
		posicionarPeones(Color.Blanco);

		posicionarTorres(Color.Negro);
		posicionarCaballos(Color.Negro);
		posicionarAlfiles(Color.Negro);
		posicionarReinaRey(Color.Negro);
		posicionarPeones(Color.Negro);

	}
	
	private void posicionarTorres(Color color) {
		Integer numero = color.equals(Color.Blanco) ? 1 : 8;
		Character letra = 'a';
		Escaque escaque = new Escaque(letra, numero);
		escaques.put(escaque, new Torre(color));
		
		letra = (char)((int)letra + 7);
		escaque = new Escaque(letra, numero);
		escaques.put(escaque, new Torre(color));
	}

	private void posicionarCaballos(Color color) {
		Integer numero = color.equals(Color.Blanco) ? 1 : 8;
		Character letra = 'b';
		Escaque escaque = new Escaque(letra, numero);
		escaques.put(escaque, new Caballo(color));
		
		letra = (char)((int)letra + 5);
		escaque = new Escaque(letra, numero);
		escaques.put(escaque, new Caballo(color));
	}
	
	private void posicionarAlfiles(Color color) {
		Integer numero = color.equals(Color.Blanco) ? 1 : 8;
		Character letra = 'c';
		Escaque escaque = new Escaque(letra, numero);
		escaques.put(escaque, new Alfil(color));
		
		letra = (char)((int)letra + 3);
		escaque = new Escaque(letra, numero);
		escaques.put(escaque, new Alfil(color));
	}
	
	private void posicionarReinaRey(Color color) {
		Integer numero = color.equals(Color.Blanco) ? 1 : 8;
		Character letra = 'd';
		Escaque escaque = new Escaque(letra, numero);
		escaques.put(escaque, new Reina(color));
		
		letra = (char)((int)letra + 1);
		escaque = new Escaque(letra, numero);
		escaques.put(escaque, new Rey(color));
	}

	private void posicionarPeones(Color color) {
		Integer numero = color.equals(Color.Blanco) ? 2 : 7;
		Character letra = 'a';

		for(int i=1; i<=8; i++){
			Escaque escaque = new Escaque(letra, numero);
			escaques.put(escaque, new Peon(color));
			letra = (char)((int)letra + 1);
		}
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
		BiMap<Trebejo, Escaque> trebejos = escaques.inverse();
		Escaque origen = trebejos.get(escaque);
		escaques.remove(origen);
		
		//TODO: Soportar peon al paso
		Trebejo trebejoComido = escaques.get(escaque);
		if(trebejoComido != null) {
			trebejosComidos.put(trebejo.getColor(), trebejo);
		}
		
		escaques.put(escaque, trebejo);
		
		return trebejoComido;
	}

	public boolean isAmenazado(Escaque destino) {
		// TODO Auto-generated method stub
		return false;
	}
}