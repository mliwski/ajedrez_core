package com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski;

import java.util.Map;

import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.trebejos.Color;
import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.trebejos.Rey;
import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.trebejos.Trebejo;
import com.google.common.collect.HashBiMap;
import com.google.common.collect.Multimap;

public class TableroSnapshot extends Tablero {
	public TableroSnapshot(HashBiMap<Escaque, Trebejo> escaques, Multimap<Color, Trebejo> trebejosComidos, Map<Color, Rey> reyes) {
		
	}
}
