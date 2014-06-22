package com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski;

import java.util.List;
import java.util.Map;

import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.movimientos.Movimiento;
import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.trebejos.Color;
import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.trebejos.Trebejo;
import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;

public class TableroSnapshot implements Tablero {
	private HashBiMap<Escaque, Trebejo> originalEscaquesTrebejosMap;
	private Multimap<Color, Trebejo> originalTrebejosCapturados;
	
	private HashBiMap<Escaque, Trebejo> escaquesTrebejosMap;
	private Multimap<Color, Trebejo> trebejosCapturados;
	
	public TableroSnapshot(Map<Escaque, Trebejo> escaquesTrebejosMap, List<Trebejo> trebejosCapturados) {
		
		loadEscaqueTrebejoMap(escaquesTrebejosMap);
		loadTrebejosCapturados(trebejosCapturados);

		this.rollback();
	}

	private void loadEscaqueTrebejoMap(Map<Escaque, Trebejo> escaquesTrebejosMap) {
		originalEscaquesTrebejosMap = HashBiMap.create(escaquesTrebejosMap.size());
		originalEscaquesTrebejosMap.putAll(escaquesTrebejosMap);
	}
	
	private void loadTrebejosCapturados(List<Trebejo> trebejosCapturados) {
		originalTrebejosCapturados = HashMultimap.create();
		for (Trebejo trebejo : trebejosCapturados) {
			Color color = trebejo.getColor();
			originalTrebejosCapturados.put(color, trebejo);
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

	public void ejecutarMovimiento(Movimiento movimiento) {
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
		// TODO Auto-generated method stub
		return false;
	}

	public void rollback() {
		escaquesTrebejosMap = HashBiMap.create(originalEscaquesTrebejosMap.size());
		escaquesTrebejosMap.putAll(originalEscaquesTrebejosMap);
		
		trebejosCapturados = HashMultimap.create();
		for (Trebejo trebejo : originalTrebejosCapturados.values()) {
			Color color = trebejo.getColor();
			trebejosCapturados.put(color, trebejo);
		}
	}


}
