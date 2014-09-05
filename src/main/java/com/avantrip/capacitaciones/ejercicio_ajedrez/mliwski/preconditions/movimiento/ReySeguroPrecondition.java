package com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.preconditions.movimiento;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.Escaque;
import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.exceptions.MovimientoIlegalException;
import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.exceptions.ReyAmenazadoException;
import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.movimientos.Movimiento;
import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.tableros.TableroSnapshot;
import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.trebejos.Color;
import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.trebejos.Rey;
import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.trebejos.Trebejo;
import com.google.common.collect.Multimap;

public class ReySeguroPrecondition extends MovimientoPrecondition {

	//TODO: Mejorar el codigo es poco claro por el nivel de abstraccion y ademas es poco performante
	@Override
	protected void checkMovimientoPreconditions(TableroSnapshot tableroSnapshot, Movimiento movimiento) {
		Escaque origen = movimiento.getOrigen();
		Trebejo trebejo = tableroSnapshot.getTrebejo(origen);
		Color color = trebejo.getColor();
		Rey reyPropio = new Rey(color);

		tableroSnapshot.ejecutarMovimiento(movimiento);

		List<Escaque> ataquesEncontrados = getAtaquesEncontrados(tableroSnapshot, reyPropio);

		if (ataquesEncontrados.size() > 0) {
			throw new ReyAmenazadoException("El movimiento que se intenta realizar deja al rey en jaque desde los siguientes escaques: " + ataquesEncontrados);
		}

	}

	private List<Escaque> getAtaquesEncontrados(TableroSnapshot tableroSnapshot, Rey reyPropio) {
		List<Escaque> ataquesEncontrados = new ArrayList<Escaque>();
		Escaque escaqueReyPropio = getEscaqueReyPropio(tableroSnapshot, reyPropio);

		Map<Escaque, Trebejo> escaquesTrebejosMap = tableroSnapshot.getEscaquesTrebejosMap();
		
		for (Entry<Escaque, Trebejo> escaqueTrebejo : escaquesTrebejosMap.entrySet()) {
			Trebejo trebejo = escaqueTrebejo.getValue();
			
			if(trebejo.getColor().equals(reyPropio.getColor()) == false) {
				Escaque escaque = escaqueTrebejo.getKey();
				//TODO: Revisar en un test como se comporta ante una doble exposicion de reyes
				//FIXME: Esta parte en particular esta muy mal implementada
				try {
					Movimiento ataqueAlRey = new Movimiento(escaque, escaqueReyPropio);
					TableroSnapshot tableroSnapshotTemp = new TableroSnapshot(tableroSnapshot.getEscaquesTrebejosMap(), tableroSnapshot.getTrebejosCapturados());
					trebejo.checkPreconditions(tableroSnapshotTemp,	ataqueAlRey);
					ataquesEncontrados.add(escaque);
				} catch (MovimientoIlegalException exception) {
				}
			}
					
		}
		return ataquesEncontrados;
	}

	private Escaque getEscaqueReyPropio(TableroSnapshot tableroSnapshot, Rey reyPropio) {
		Multimap<Trebejo, Escaque> trebejosEscaques = tableroSnapshot.getTrebejosEscaques();
		
		Collection<Escaque> escaquesReyPropio = trebejosEscaques.get(reyPropio);
		ArrayList<Escaque> arrayList = new ArrayList<Escaque>(escaquesReyPropio);
		Escaque escaqueReyPropio = arrayList.get(0);
		return escaqueReyPropio;
	}

}
