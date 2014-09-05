package com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski;

import java.util.HashMap;
import java.util.Map;

import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.tableros.TableroSnapshot.TableroSnapshotBuilder;
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
	public static synchronized TableroSnapshotBuilder getWorkingTableroSnapshotBuilder() {
		TableroSnapshotBuilder tableroSnapshotBuilder = new TableroSnapshotBuilder();
		Map<Escaque, Trebejo> reyesDefault = getReyesDefault();
		tableroSnapshotBuilder.with(reyesDefault);
		return tableroSnapshotBuilder;
	}

	private static Map<Escaque, Trebejo> getReyesDefault() {
		Map<Escaque, Trebejo> escaquesTrebejos = new HashMap<Escaque, Trebejo>();
		
		Rey reyBlanco = new Rey(Color.Blanco);
		Escaque escaqueReyBlanco = new Escaque('e', 1);
		
		Rey reyNegro = new Rey(Color.Negro);
		Escaque escaqueReyNegro = new Escaque('e', 8);
		
		escaquesTrebejos.put(escaqueReyBlanco, reyBlanco);
		escaquesTrebejos.put(escaqueReyNegro, reyNegro);
		
		return escaquesTrebejos;
	}
}
