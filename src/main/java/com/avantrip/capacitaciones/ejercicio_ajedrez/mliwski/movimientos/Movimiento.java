package com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.movimientos;

import java.util.List;

import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.Escaque;

public class Movimiento {
	private static MovimientoProcessor datosProcessor = buildTipoMovimientoDetectorChain();

	private Escaque origen;
	private Escaque destino;
	
	private TipoMovimiento tipo;
	private Integer cantidad;
	private List<Escaque> camino;
	
	
	public Movimiento(Escaque origen, Escaque destino) {
		checkArgumentsPreconditions(origen, destino);
		
		this.origen = origen;
		this.destino = destino;
		
		DatosMovimiento datosMovimiento = datosProcessor.detectar(origen, destino);
		this.tipo = datosMovimiento.getTipo();
		this.cantidad = datosMovimiento.getCantidad();
		this.camino = datosMovimiento.getCamino();
		
	}

	private void checkArgumentsPreconditions(Escaque origen, Escaque destino) {
		if(origen == null || destino == null ) {
			throw new IllegalArgumentException("Para construir el escaque movimiento se debe conocer el origen y el destino.");
		}
		if(origen.equals(destino)) {
			throw new IllegalArgumentException("El origen de un movimiento debe ser distinto del destino");
		}
	}
	
	public Escaque getOrigen() {
		return origen;
	}

	public Escaque getDestino() {
		return destino;
	}

	public TipoMovimiento getTipo() {
		return this.tipo;
	}
	
	public Integer getCantidad() {
		return this.cantidad;
	}

	public List<Escaque> getCamino() {
		return this.camino;
	}
	
	private static MovimientoProcessor buildTipoMovimientoDetectorChain(){
		MovimientoProcessor eleProcessor = new MovimientoEleProcessor();
		MovimientoProcessor horizontalProcessor = new MovimientoHorizontalProcessor(eleProcessor);
		MovimientoProcessor diagonalProcessor = new MovimientoDiagonalProcessor(horizontalProcessor);
		MovimientoProcessor verticalProcessor = new MovimientoVerticalProcessor(diagonalProcessor);
		return verticalProcessor;
	}

	@Override
	public String toString() {
		return origen + " -> " + destino + ", " + this.tipo + " (" + this.cantidad + ")";
	}
}
