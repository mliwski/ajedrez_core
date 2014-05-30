package com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.movimientos;

import java.util.List;

import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.Escaque;

public class MovimientoDiagonalProcessor extends MovimientoProcessor {
	
	public MovimientoDiagonalProcessor() {
		this(null);
	}
	
	public MovimientoDiagonalProcessor(MovimientoProcessor next){
		super(next);
	};

	@Override
	protected boolean isTipoDetectado(Escaque origen, Escaque destino) {
		Integer distanciaNumero = Math.abs(origen.getDistanciaNumero(destino));
		Integer distanciaLetra = Math.abs(origen.getDistanciaLetra(destino));
		return distanciaNumero - distanciaLetra == 0;
	}
	
	@Override
	protected DatosMovimiento getDatosMovimiento(Escaque origen, Escaque destino) {
		DatosMovimiento datosMovimiento = new DatosMovimiento();
		
		datosMovimiento.setTipo(TipoMovimiento.Diagonal);
		
		Integer cantidad = Math.abs(origen.getDistanciaNumero(destino));
		datosMovimiento.setCantidad(cantidad);
		
		List<Escaque> camino = super.getCamino(origen, destino);
		datosMovimiento.setCamino(camino);
		
		return datosMovimiento;
	}

	@Override
	protected Escaque getEscaqueSiguiente(Escaque escaque, Integer incrementoLetra, Integer incrementoNumero) {
		return new Escaque(super.getLetraSiguiente(escaque,incrementoLetra), super.getNumeroSiguiente(escaque,incrementoNumero));
	}
}
