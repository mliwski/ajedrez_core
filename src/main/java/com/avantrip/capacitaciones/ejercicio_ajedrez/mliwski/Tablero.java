package com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski;

import java.util.HashMap;
import java.util.Map;

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
import com.google.common.collect.ImmutableBiMap;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;

public class Tablero {
	private HashBiMap<Escaque, Trebejo> escaques;
	private Multimap<Color, Trebejo> trebejosComidos;
	
	private Map<Color, Rey> reyes;
	
	public Tablero() {
		escaques = HashBiMap.create();
		//TODO: Ver como hacerlo sin mapa ... son solo 2
		reyes = new HashMap<Color, Rey>(2);
		
		//TODO: Crear algo para hacerlo mas prolijo
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
		Rey rey = new Rey(color);
		escaques.put(escaque, rey);
		reyes.put(color, rey);
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

	public Trebejo getTrebejo(Escaque escaque) {
		return escaques.get(escaque);
	}
	
	//TODO: Eliminar o refactorear o algo
	public Escaque getEscaqueDelRey(Color color) {
		Rey rey = reyes.get(color);
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
	
	public TableroSnapshot getSnapshot() {
//		HashBiMap<Escaque, Trebejo> copyOfEscaques = Maps.(escaques);
//		Multimap<Color, Trebejo> copyOfCapturas = ImmutableMultimap.copyOf(trebejosComidos);
//		Map<Color, Rey> copeyes = ImmutableMap.copyOf(reyes);
//		new TableroSnapshot(copyOfEscaques, trebejosComidos, reyes);

		
		return null;
	}
}