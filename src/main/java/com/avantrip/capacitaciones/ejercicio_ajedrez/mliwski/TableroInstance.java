package com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;

public class TableroInstance implements Tablero {
	private HashBiMap<Escaque, Trebejo> escaquesTrebejosMap;
	private Multimap<Color, Trebejo> trebejosCapturados;
	
	private Map<Color, Rey> reyes;
	
	public TableroInstance() {
		trebejosCapturados = HashMultimap.create();
		escaquesTrebejosMap = HashBiMap.create(32);
		//TODO: Ver como hacerlo sin mapa ... son solo 2 (quizas enum interno)
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
		escaquesTrebejosMap.put(escaque, new Torre(color));
		
		letra = (char)((int)letra + 7);
		escaque = new Escaque(letra, numero);
		escaquesTrebejosMap.put(escaque, new Torre(color));
	}

	private void posicionarCaballos(Color color) {
		Integer numero = color.equals(Color.Blanco) ? 1 : 8;
		Character letra = 'b';
		Escaque escaque = new Escaque(letra, numero);
		escaquesTrebejosMap.put(escaque, new Caballo(color));
		
		letra = (char)((int)letra + 5);
		escaque = new Escaque(letra, numero);
		escaquesTrebejosMap.put(escaque, new Caballo(color));
	}
	
	private void posicionarAlfiles(Color color) {
		Integer numero = color.equals(Color.Blanco) ? 1 : 8;
		Character letra = 'c';
		Escaque escaque = new Escaque(letra, numero);
		escaquesTrebejosMap.put(escaque, new Alfil(color));
		
		letra = (char)((int)letra + 3);
		escaque = new Escaque(letra, numero);
		escaquesTrebejosMap.put(escaque, new Alfil(color));
	}
	
	private void posicionarReinaRey(Color color) {
		Integer numero = color.equals(Color.Blanco) ? 1 : 8;
		Character letra = 'd';
		Escaque escaque = new Escaque(letra, numero);
		escaquesTrebejosMap.put(escaque, new Reina(color));
		
		letra = (char)((int)letra + 1);
		escaque = new Escaque(letra, numero);
		Rey rey = new Rey(color);
		escaquesTrebejosMap.put(escaque, rey);
		reyes.put(color, rey);
	}

	private void posicionarPeones(Color color) {
		Integer numero = color.equals(Color.Blanco) ? 2 : 7;
		Character letra = 'a';

		for(int i=1; i<=8; i++){
			Escaque escaque = new Escaque(letra, numero);
			escaquesTrebejosMap.put(escaque, new Peon(color));
			letra = (char)((int)letra + 1);
		}
	}

	public Trebejo getTrebejo(Escaque escaque) {
		return escaquesTrebejosMap.get(escaque);
	}
	
	public Escaque getEscaque(Trebejo trebejo) {
		BiMap<Trebejo, Escaque> trebejosEscaquesMap = escaquesTrebejosMap.inverse();
		Escaque escaque = trebejosEscaquesMap.get(trebejo);
		return escaque;
	}
	
	//TODO: Eliminar o refactorear o algo
	public Escaque getEscaqueDelRey(Color color) {
		Rey rey = reyes.get(color);
		return escaquesTrebejosMap.inverse().get(rey);
	}

	//TODO: Ver si se puede mejorar la legibilidad y el nivel de abstraccion
	public void ejecutarMovimiento(Movimiento movimiento){
		Escaque origen = movimiento.getOrigen();
		Escaque destino = movimiento.getDestino();
		Trebejo trebejo = escaquesTrebejosMap.get(origen);
		Trebejo trebejoCapturado = escaquesTrebejosMap.get(destino);
		
		checkejecutarMovimientoPreconditions(trebejo);
		
		escaquesTrebejosMap.remove(origen);
		escaquesTrebejosMap.put(destino, trebejo);
		
		if(trebejoCapturado != null){
			Color colorTrebejoCapturado = trebejoCapturado.getColor();
			trebejosCapturados.put(colorTrebejoCapturado, trebejoCapturado);
		}
	}

	private void checkejecutarMovimientoPreconditions(Trebejo trebejo) {
		if(trebejo == null) {
			throw new IllegalStateException("No se puede mover si en el origen no hay un trebejo.");
		}
	}

	public boolean isEscaqueAmenazadoPorColor(Escaque destino, Color color) {
		//FIXME: Refactorear POR DIOS!!!!
//		for (Trebejo trebejo : escaquesTrebejosMap.values()) {
//			if(trebejo.getColor().equals(color)) {
//				Movimiento movimiento = new Movimiento(escaquesTrebejosMap.inverse().get(trebejo), destino, direccionAtaque);
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
	
	public TableroSnapshot getSnapshot() {
		ArrayList<Trebejo> trebejosCapturadosList = getTrebejosCapturadosList();
//		getEscaquesMap();
		TableroSnapshot tableroSnapshot = new TableroSnapshot(escaquesTrebejosMap, trebejosCapturadosList);
		
		return tableroSnapshot;
	}

	private ArrayList<Trebejo> getTrebejosCapturadosList() {
		List<Trebejo> trebejosCapturadosBlancos = (List<Trebejo>) this.trebejosCapturados.get(Color.Blanco);
		List<Trebejo> trebejosCapturadosNegros = (List<Trebejo>) this.trebejosCapturados.get(Color.Negro);
		
		ArrayList<Trebejo> trebejosCapturados = new ArrayList<Trebejo>();
		trebejosCapturados.addAll(trebejosCapturadosBlancos);
		trebejosCapturados.addAll(trebejosCapturadosNegros);
		
		return trebejosCapturados;
	}
	
//	private void getEscaquesMap() {
//		Map<Escaque, Trebejo> escaquesMap = new HashMap<Escaque, Trebejo>(escaquesTrebejosMap.size());
//		for(Escaque escaque : escaquesTrebejosMap.keySet()){
//			Trebejo trebejo = escaquesTrebejosMap.get(escaque);
//			escaquesMap.put(escaque, trebejo);
//		}
//	}
}