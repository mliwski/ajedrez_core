package com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.trebejos.Color;
import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.trebejos.Rey;
import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.trebejos.Trebejo;

public class TestUtils {
	/*
	 * Utilidad para poder testear con un tablero snapshot funcional para los casos mas complejos.
	 * 
	 * Construye el mismo con el Rey Blanco (e1) y el Rey Negro (e8), mas los trebejos que se le pasen en cada escaque.
	 * 
	 * La lista de trebejos capturados esta vacia.
	 */
	public static synchronized TableroSnapshot buildWorkingTableroSnapshotWith(Map<Escaque, Trebejo> escaquesTrebejos) {

		Map<Escaque, Trebejo> escaquesTrebejosMap = new HashMap<Escaque, Trebejo>(escaquesTrebejos);
		
		escaquesTrebejosMap = agregarReyesSiFaltan(escaquesTrebejosMap);
		
		List<Trebejo> trebejosCapturados = new ArrayList<Trebejo>();
		
		TableroSnapshot tableroSnapshot = new TableroSnapshot(escaquesTrebejosMap, trebejosCapturados);
		
		return tableroSnapshot;
	}

	private static Map<Escaque, Trebejo> agregarReyesSiFaltan(Map<Escaque, Trebejo> escaquesTrebejos) {
		Rey reyBlanco = new Rey(Color.Blanco);
		Escaque escaqueReyBlanco = new Escaque('e', 1);
		
		Rey reyNegro = new Rey(Color.Negro);
		Escaque escaqueReyNegro = new Escaque('e', 8);
		
		if(escaquesTrebejos.values().contains(reyBlanco) == false)  {
			escaquesTrebejos.put(escaqueReyBlanco, reyBlanco);
		}
		if(escaquesTrebejos.values().contains(reyNegro) == false)  {
			escaquesTrebejos.put(escaqueReyNegro, reyNegro);
		}
		
		return escaquesTrebejos;
	}
}
