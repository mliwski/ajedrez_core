package com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.movimientos;

import java.util.ArrayList;
import java.util.List;

import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.DireccionAtaque;
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
		Integer distanciaNumero = origen.getDistanciaNumero(destino);
		Integer distanciaLetra = origen.getDistanciaLetra(destino);
		return distanciaNumero - distanciaLetra == 0;
	}
	
	@Override
	protected DatosMovimiento getDatosMovimiento(Escaque origen, Escaque destino, DireccionAtaque direccionAtaque) {
		DatosMovimiento datosMovimiento = new DatosMovimiento();
		
		datosMovimiento.setTipo(TipoMovimiento.Diagonal);
		
		Integer cantidad = origen.getDistanciaNumero(destino);
		datosMovimiento.setCantidad(cantidad);
		
		List<DireccionMovimiento> direccion = super.getDireccion(origen, destino, direccionAtaque);
		datosMovimiento.setDireccion(direccion);
		
		List<Escaque> camino = getCamino(origen, destino, direccionAtaque, direccion);
		datosMovimiento.setCamino(camino);
		
		return datosMovimiento;
	}

	private List<Escaque> getCamino(Escaque origen, Escaque destino, DireccionAtaque direccionAtaque, List<DireccionMovimiento> direccion) {
		List<Escaque> camino = new ArrayList<Escaque>();
		Escaque nextEscaque = getNextEscaque(origen, direccionAtaque, direccion);

		while (destino.equals(nextEscaque) == false) {
			camino.add(nextEscaque);
			nextEscaque = getNextEscaque(nextEscaque, direccionAtaque, direccion);
		}
		return camino;
	}
	
	private Escaque getNextEscaque(Escaque escaqueActual, DireccionAtaque direccionAtaque, List<DireccionMovimiento> direccion) {
		Integer numeroOrigen = escaqueActual.getNumero();
		Character letraOrigen = escaqueActual.getLetra();
		
		Integer nextNumero = getNextEscaqueNumero(direccionAtaque, direccion, numeroOrigen);
		Character nextLetra = getNextEscaqueLetra(direccionAtaque, direccion, letraOrigen);
		Escaque nextEscaque = new Escaque(nextLetra, nextNumero);
		
		return nextEscaque;
	}
}
