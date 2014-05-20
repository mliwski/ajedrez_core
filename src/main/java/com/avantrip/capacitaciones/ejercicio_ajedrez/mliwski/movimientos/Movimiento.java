package com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.movimientos;

import java.util.List;

import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.DireccionAtaque;
import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.Escaque;

public class Movimiento {
	private static MovimientoProcessor datosProcessor = buildTipoMovimientoDetectorChain();

	private Escaque origen;
	private Escaque destino;
	private DireccionAtaque direccionAtaque;
	
	private TipoMovimiento tipo;
	private List<DireccionMovimiento> direccion;
	private Integer cantidad;
	private List<Escaque> camino;
	
	
	public Movimiento(Escaque origen, Escaque destino, DireccionAtaque direccionAtaque) {
		checkArgumentsPreconditions(origen, destino, direccionAtaque);
		
		this.origen = origen;
		this.destino = destino;
		this.direccionAtaque = direccionAtaque;
		
		DatosMovimiento datosMovimiento = datosProcessor.detectar(origen, destino, direccionAtaque);
		this.tipo = datosMovimiento.getTipo();
		this.cantidad = datosMovimiento.getCantidad();
		this.direccion = datosMovimiento.getDireccion();
		this.camino = datosMovimiento.getCamino();
		
	}

	private void checkArgumentsPreconditions(Escaque origen, Escaque destino, DireccionAtaque direccionAtaque) {
		if(origen.equals(destino)) {
			throw new IllegalArgumentException("El origen de un movimiento debe ser distinto del destino");
		}
		if(origen == null || destino == null || direccionAtaque == null) {
			throw new IllegalArgumentException("Para construir el escaque movimiento se debe conocer el orige, el destino y la direccion de ataque");
		}
	}
	
	public Escaque getOrigen() {
		return origen;
	}

	public Escaque getDestino() {
		return destino;
	}

	public DireccionAtaque getDireccionAtaque() {
		return direccionAtaque;
	}

	public TipoMovimiento getTipo() {
		return this.tipo;
	}
	
	public Integer getCantidad() {
		return this.cantidad;
	}
	
	public List<DireccionMovimiento> getDireccion() {
		return this.direccion;
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
		return origen + " -> " + destino + "(ataque:" + direccionAtaque +") = " + this.tipo + ", " + this.cantidad + ", " + this.direccion;
	}
}
