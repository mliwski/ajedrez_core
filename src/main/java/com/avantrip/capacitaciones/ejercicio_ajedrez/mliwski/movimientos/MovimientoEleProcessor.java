package com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.movimientos;

import java.util.Collections;
import java.util.List;

import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.Escaque;

public class MovimientoEleProcessor extends MovimientoProcessor {
	
	public MovimientoEleProcessor() {
		this(null);
	}

	public MovimientoEleProcessor(MovimientoProcessor next){
		super(next);
	};

	@Override
	protected boolean isTipoDetectado(Escaque origen, Escaque destino) {
		Integer distanciaNumero = origen.getDistanciaNumero(destino);
		Integer distanciaLetra = origen.getDistanciaLetra(destino);
		
		boolean eleHorizontal = distanciaNumero == 1 && distanciaLetra == 2;
		boolean eleVertical = distanciaNumero == 2 && distanciaLetra == 1;
		
		return eleHorizontal || eleVertical;
	}

	@Override
	protected DatosMovimiento getDatosMovimiento(Escaque origen, Escaque destino) {
		DatosMovimiento datosMovimiento = new DatosMovimiento();
		datosMovimiento.setTipo(TipoMovimiento.Ele);
		datosMovimiento.setCantidad(1);
		List<Escaque> camino = Collections.emptyList();
		datosMovimiento.setCamino(camino);
		
		return datosMovimiento;
	}
}
