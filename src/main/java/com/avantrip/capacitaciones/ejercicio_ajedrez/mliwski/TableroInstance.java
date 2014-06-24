package com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.trebejos.Alfil;
import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.trebejos.Caballo;
import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.trebejos.Color;
import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.trebejos.Peon;
import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.trebejos.Reina;
import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.trebejos.Rey;
import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.trebejos.Torre;
import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.trebejos.Trebejo;
import com.google.common.collect.HashBiMap;

public class TableroInstance extends Tablero {
	private Map<Escaque, Trebejo> escaquesTrebejosMap;
	
	public TableroInstance() {
		setTrebejosCapturados(new ArrayList<Trebejo>(30));
		escaquesTrebejosMap = HashBiMap.create(32);
		
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
		
		setEscaquesTrebejosMap(escaquesTrebejosMap);
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
	
	public TableroSnapshot getSnapshot() {
		List<Trebejo> trebejosCapturados = getTrebejosCapturados();
		Map<Escaque, Trebejo> escaquesTrebejosMap = getEscaquesTrebejosMap();
		
		TableroSnapshot tableroSnapshot = new TableroSnapshot(escaquesTrebejosMap, trebejosCapturados);
		
		return tableroSnapshot;
	}
}