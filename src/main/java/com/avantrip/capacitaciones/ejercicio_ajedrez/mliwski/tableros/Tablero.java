package com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.tableros;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.Escaque;
import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.movimientos.Movimiento;
import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.trebejos.Trebejo;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Multimap;
import com.google.common.collect.Multimaps;
import com.google.common.collect.SetMultimap;

public abstract class Tablero {
	private Map<Escaque, Trebejo> escaquesTrebejosMap;
	private List<Trebejo> trebejosCapturados;

	//FIXME: Todo esto tiene que estar encapsulado en el comando mover
	// Para eso crear los metodos
	// - capturarTrebejo(Trebejo, Escaque) ???
	// - moverTrebejo(Movimiento)
	public void ejecutarMovimiento(Movimiento movimiento) {
		//TODO: Mejorar niveles de abstraccion para claridad de codigo
		checkEjecutarMovimientoPreconditions(movimiento);
		
		Escaque origen = movimiento.getOrigen();
		Escaque destino = movimiento.getDestino();
		Trebejo trebejo = getTrebejo(origen);

		//TODO: Me suena raro enviarme a mi ... puedo hacer lo que quiero => revisar
		Trebejo trebejoCapturado = trebejo.getTrebejoCapturado(this, movimiento);
		
		escaquesTrebejosMap.remove(origen);
		escaquesTrebejosMap.put(destino, trebejo);
		
		if(trebejoCapturado != null){
			trebejosCapturados.add(trebejoCapturado);
		}
	}

	private void checkEjecutarMovimientoPreconditions(Movimiento movimiento) {
		if(movimiento == null) {
			throw new IllegalArgumentException("No se puede mover si no se conoce el movimiento.");
		}

		Escaque origen = movimiento.getOrigen();
		Trebejo trebejoOrigen = getTrebejo(origen);

		if(trebejoOrigen == null) {
			throw new IllegalStateException("No se puede mover si en el origen no hay un trebejo.");
		}
	}

	public Trebejo getTrebejo(Escaque escaque) {
		return escaquesTrebejosMap.get(escaque);
	}
	
	public Multimap<Trebejo,Escaque> getTrebejosEscaques() {
		SetMultimap<Escaque, Trebejo> escaquesTrebejosMultimap = Multimaps.forMap(escaquesTrebejosMap);
		Multimap<Trebejo, Escaque> trebejosEscaques = Multimaps.invertFrom(escaquesTrebejosMultimap, HashMultimap.<Trebejo, Escaque> create());
		return trebejosEscaques;
	}
	
	public Map<Escaque, Trebejo> getEscaquesTrebejosMap() {
		return Maps.newHashMap(this.escaquesTrebejosMap);
	}
	
	public List<Trebejo> getTrebejosCapturados() {
		return Lists.newArrayList(this.trebejosCapturados);
	}
	
	protected void setEscaquesTrebejosMap(Map<Escaque, Trebejo> escaquesTrebejosMap) {
		this.escaquesTrebejosMap = new HashMap<Escaque, Trebejo>(escaquesTrebejosMap);
	}
	
	protected void setTrebejosCapturados(List<Trebejo> trebejosCapturados) {
		this.trebejosCapturados = trebejosCapturados;
	}
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}