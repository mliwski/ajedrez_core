package com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.trebejos.Trebejo;
import com.google.common.collect.HashBiMap;

public class TableroSnapshot extends Tablero {
	private HashBiMap<Escaque, Trebejo> originalEscaquesTrebejosMap;
	private List<Trebejo> originalTrebejosCapturados;
		
	public TableroSnapshot(Map<Escaque, Trebejo> escaquesTrebejosMap, List<Trebejo> trebejosCapturados) {
		originalEscaquesTrebejosMap = HashBiMap.create(escaquesTrebejosMap);
		originalTrebejosCapturados = new ArrayList<Trebejo>(trebejosCapturados);

		this.restart();
	}

	public void restart() {
		setEscaquesTrebejosMap(originalEscaquesTrebejosMap);
		setTrebejosCapturados(originalTrebejosCapturados);
	}
}
