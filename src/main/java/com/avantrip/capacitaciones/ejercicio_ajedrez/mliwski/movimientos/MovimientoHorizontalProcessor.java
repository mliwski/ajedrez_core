package com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.movimientos;

import java.util.List;

import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.Escaque;

public class MovimientoHorizontalProcessor extends MovimientoProcessor {

	public MovimientoHorizontalProcessor() {
		this(null);
	}
	
	public MovimientoHorizontalProcessor(MovimientoProcessor next){
		super(next);
	};

	@Override
	protected boolean isTipoDetectado(Escaque origen, Escaque destino) {
		return origen.getNumero().equals(destino.getNumero());
	}

	@Override
	protected DatosMovimiento getDatosMovimiento(Escaque origen, Escaque destino) {
		DatosMovimiento datosMovimiento = new DatosMovimiento();
		
		datosMovimiento.setTipo(TipoMovimiento.Horizontal);
		
		Integer cantidad = Math.abs(origen.getDistanciaLetra(destino));
		datosMovimiento.setCantidad(cantidad);
		
		List<Escaque> camino = super.getCamino(origen, destino);
		datosMovimiento.setCamino(camino);
		
		return datosMovimiento;
	}
}
