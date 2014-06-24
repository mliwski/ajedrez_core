package com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski;

import java.util.List;
import java.util.Map;

import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.movimientos.Movimiento;
import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.trebejos.Color;
import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.trebejos.Trebejo;
import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;

public abstract class Tablero {
	private HashBiMap<Escaque, Trebejo> escaquesTrebejosMap;
	private List<Trebejo> trebejosCapturados;
	
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
			trebejosCapturados.add(trebejoCapturado);
		}
	}

	private void checkejecutarMovimientoPreconditions(Trebejo trebejo) {
		if(trebejo == null) {
			throw new IllegalStateException("No se puede mover si en el origen no hay un trebejo.");
		}
	}

	public boolean isEscaqueAmenazadoPorColor(Escaque destino, Color color) {
		//TODO: Implementar
		return false;
	}
	
	protected void setEscaquesTrebejosMap(Map<Escaque, Trebejo> escaquesTrebejosMap) {
		this.escaquesTrebejosMap = HashBiMap.create(escaquesTrebejosMap);
	}
	
	protected Map<Escaque, Trebejo> getEscaquesTrebejosMap() {
		return this.escaquesTrebejosMap;
	}
	
	protected void setTrebejosCapturados(List<Trebejo> trebejosCapturados) {
		this.trebejosCapturados = trebejosCapturados;
	}
	
	protected List<Trebejo> getTrebejosCapturados() {
		return this.trebejosCapturados;
	}; 
}