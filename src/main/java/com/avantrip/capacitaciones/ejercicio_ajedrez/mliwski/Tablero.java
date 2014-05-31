package com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski;

import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.exceptions.MovimientoIlegalException;
import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.movimientos.Movimiento;
import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.trebejos.Alfil;
import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.trebejos.Caballo;
import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.trebejos.Color;
import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.trebejos.Peon;
import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.trebejos.Reina;
import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.trebejos.Rey;
import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.trebejos.Torre;
import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.trebejos.Trebejo;
import com.google.common.collect.HashBiMap;
import com.google.common.collect.Multimap;

public class Tablero {
	private HashBiMap<Escaque, Trebejo> escaques;
	private Multimap<Color, Trebejo> trebejosComidos;
	
	//TODO: Agregar pila para que contemple el rollback
	
	private static Tablero instance;
	
	private Tablero() {
		escaques = HashBiMap.create();
	}

	//TODO: Revisar costo y el problema de no poder llamarlo en el constructor por el singleton
	//TODO: Crear un chain para hacerlo mas prolijo
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
	
	//TODO: Eliminar o refactorear o algo
	public Escaque getEscaqueRey(Color color) {
		Rey rey = new Rey(color);
		return escaques.inverse().get(rey);
	}

	public void moverTrebejo(Movimiento movimiento){
		Escaque origen = movimiento.getOrigen();
		Escaque destino = movimiento.getDestino();
		Trebejo trebejo = escaques.get(origen);
		checkMoverTrebejoPreconditions(trebejo);
		
		escaques.remove(origen);
		escaques.put(destino, trebejo);
	}

	private void checkMoverTrebejoPreconditions(Trebejo trebejo) {
		if(trebejo == null) {
			throw new IllegalStateException("No se puede mover si en el origen no hay un trebejo.");
		}
	}

	public boolean isEscaqueAmenazadoPorColor(Escaque destino, Color color) {
		DireccionAtaque direccionAtaque = color.getContrincante().getDireccionAtaque();
		
		//FIXME: Refactorear POR DIOS!!!!
//		for (Trebejo trebejo : escaques.values()) {
//			if(trebejo.getColor().equals(color)) {
//				Movimiento movimiento = new Movimiento(escaques.inverse().get(trebejo), destino, direccionAtaque);
//				try {
//					trebejo.checkPreconditions(movimiento);
//					return true;
//				} catch(MovimientoIlegalException movimientoIlegalException){
//					
//				}
//			}
//		}
		return false;
	}

	//TODO: Implementar rollback aca con pila de movimientos
	public void rollback() {
		// TODO Auto-generated method stub
		
	}
//	
//	public TableroSnapshot getSnapshot() {
//		return null;
//	}
}