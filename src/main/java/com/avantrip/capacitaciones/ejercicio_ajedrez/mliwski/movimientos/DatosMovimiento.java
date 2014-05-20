package com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.movimientos;

import java.util.List;

import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.Escaque;

public class DatosMovimiento {
	private TipoMovimiento tipo;
	private List<DireccionMovimiento> direccion;
	private Integer cantidad;
	private List<Escaque> camino;
	
	public DatosMovimiento() {
	}

	public TipoMovimiento getTipo() {
		return tipo;
	}
	public List<DireccionMovimiento> getDireccion() {
		return direccion;
	}
	public Integer getCantidad() {
		return cantidad;
	}
	public List<Escaque> getCamino() {
		return camino;
	}
	public void setTipo(TipoMovimiento tipo) {
		this.tipo = tipo;
	}
	public void setDireccion(List<DireccionMovimiento> direccion) {
		this.direccion = direccion;
	}
	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}
	public void setCamino(List<Escaque> camino) {
		this.camino = camino;
	}
	
	
}
