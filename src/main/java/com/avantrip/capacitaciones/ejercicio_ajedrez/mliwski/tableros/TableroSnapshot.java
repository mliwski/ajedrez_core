package com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.tableros;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.Escaque;
import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.trebejos.Color;
import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.trebejos.Rey;
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
	
	public static class TableroSnapshotBuilder {
		private Map<Escaque, Trebejo> escaquesTrebejosMap;
		private List<Trebejo> trebejosCapturados;
		
		public TableroSnapshotBuilder(){
			this.escaquesTrebejosMap = new HashMap<Escaque, Trebejo>(32);
			this.trebejosCapturados = new ArrayList<Trebejo>(30);
			
			Rey reyBlanco = new Rey(Color.Blanco);
			Escaque escaqueReyBlanco = new Escaque('e', 1);
			this.with(escaqueReyBlanco, reyBlanco);
			
			Rey reyNegro = new Rey(Color.Negro);
			Escaque escaqueReyNegro = new Escaque('e', 8);
			this.with(escaqueReyNegro, reyNegro);
		}
		
		/*
		 * Agrega un par escaque/trebejo al tablero.
		 *  - Si el escaque ya esta ocupado, pisa su ocupacion.
		 *  - Si el trebejo ya esta en el tablero, cambia su ubicacion al nuevo escaque.
		 */
		public TableroSnapshotBuilder with(Escaque escaque, Trebejo trebejo) {
			if(escaquesTrebejosMap.values().contains(trebejo)) {
				escaquesTrebejosMap.remove(escaque);
			}
			
			this.escaquesTrebejosMap.put(escaque, trebejo);
			return this;
		}
		
		/*
		 * Agrega los pares de escaque/trebejo al tablero.
		 *  - Si el escaque ya esta ocupado, pisa su ocupacion.
		 *  - Si el trebejo ya esta en el tablero, cambia su ubicacion al nuevo escaque.
		 */
		public TableroSnapshotBuilder with(Map<Escaque, Trebejo> escaquesTrebejos) {
			for (Entry<Escaque, Trebejo> escaqueTrebejo : escaquesTrebejos.entrySet()) {
				Escaque escaque = escaqueTrebejo.getKey();
				Trebejo trebejo = escaqueTrebejo.getValue();
				
				with(escaque, trebejo);
			}
			return this;
		}
		
		public TableroSnapshotBuilder with(List<Trebejo> trebejosCapturados) {
			this.trebejosCapturados.addAll(trebejosCapturados);
			return this;
		}
		
		public TableroSnapshot build() {
			return new TableroSnapshot(this.escaquesTrebejosMap, this.trebejosCapturados);
		}
	}
}
