package com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski;

import java.util.List;
import java.util.Map;

import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.trebejos.Trebejo;

public class TableroSnapshot extends Tablero {
	private Map<Escaque, Trebejo> originalEscaquesTrebejosMap;
	private List<Trebejo> originalTrebejosCapturados;
		
	public TableroSnapshot(Map<Escaque, Trebejo> escaquesTrebejosMap, List<Trebejo> trebejosCapturados) {
		originalEscaquesTrebejosMap = escaquesTrebejosMap;
		originalTrebejosCapturados = trebejosCapturados;

		this.restart();
	}

	public void restart() {
		setEscaquesTrebejosMap(originalEscaquesTrebejosMap);
		setTrebejosCapturados(originalTrebejosCapturados);
	}
}
