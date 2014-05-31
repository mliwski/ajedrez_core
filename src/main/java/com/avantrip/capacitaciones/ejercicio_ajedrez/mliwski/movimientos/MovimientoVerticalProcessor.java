package com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.movimientos;

import java.util.List;

import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.Escaque;

public class MovimientoVerticalProcessor extends MovimientoProcessor {

	public MovimientoVerticalProcessor() {
		this(null);
	}
	
	public MovimientoVerticalProcessor(MovimientoProcessor next){
		super(next);
	};

	@Override
	protected boolean isTipoDetectado(Escaque origen, Escaque destino) {
		return origen.getLetra().equals(destino.getLetra());
	}
	
	@Override
	protected DatosMovimiento getDatosMovimiento(Escaque origen, Escaque destino) {
		DatosMovimiento datosMovimiento = new DatosMovimiento();
		
		datosMovimiento.setTipo(TipoMovimiento.Vertical);
		
		Integer cantidad = Math.abs(origen.getDistanciaNumero(destino));
		datosMovimiento.setCantidad(cantidad);
		
		List<Escaque> camino = super.getCamino(origen, destino);
		datosMovimiento.setCamino(camino);
		
		return datosMovimiento;
	}
}
