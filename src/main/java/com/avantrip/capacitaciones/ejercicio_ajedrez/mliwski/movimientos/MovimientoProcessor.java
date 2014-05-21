package com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.movimientos;

import java.util.ArrayList;
import java.util.List;

import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.DireccionAtaque;
import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.Escaque;
import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.exceptions.TipoMovimientoDesconocidoException;

public abstract class MovimientoProcessor {
	private MovimientoProcessor next;
	
	public MovimientoProcessor(MovimientoProcessor next) {
		this.next = next;
	}

	public DatosMovimiento detectar(Escaque origen, Escaque destino, DireccionAtaque direccionAtaque) {
		DatosMovimiento datosMovimiento = null;
		if(isTipoDetectado(origen, destino)) {
			datosMovimiento = getDatosMovimiento(origen, destino, direccionAtaque);
  		} else if(this.next != null) {
  			datosMovimiento = this.next.detectar(origen, destino, direccionAtaque);
		} else {
			throw new TipoMovimientoDesconocidoException("El tipo de movimiento que se desea realizar es desconocido en el ajedrez");
		}
		
		return datosMovimiento;
	}
	protected abstract boolean isTipoDetectado(Escaque origen, Escaque destino);
	protected abstract DatosMovimiento getDatosMovimiento(Escaque origen, Escaque destino, DireccionAtaque direccionAtaque);
	
	// FIXME: Explotar en 4 metodos y dejar la implementacion del detector en cada processor
	protected ArrayList<DireccionMovimiento> getDireccion(Escaque origen, Escaque destino,
			DireccionAtaque direccionAtaque) {
		ArrayList<DireccionMovimiento> direccion = new ArrayList<DireccionMovimiento>();
		
		Integer distanciaLetra = origen.getDistanciaLetra(destino);
		Integer distanciaNumero = origen.getDistanciaNumero(destino);
		
		if(distanciaLetra >= distanciaNumero) {
			DireccionMovimiento direccionHorizontal = getDireccionHorizontal(origen, destino, direccionAtaque);
			direccion.add(direccionHorizontal);
			
			DireccionMovimiento direccionVertical = getDireccionVertical(origen, destino, direccionAtaque);
			if(direccionVertical != null){
				direccion.add(direccionVertical);
			}
		} else {
			
			DireccionMovimiento direccionVertical = getDireccionVertical(origen, destino, direccionAtaque);
			direccion.add(direccionVertical);

			DireccionMovimiento direccionHorizontal = getDireccionHorizontal(origen, destino, direccionAtaque);
			if(direccionHorizontal != null){
				direccion.add(direccionHorizontal);
			}
		}
		return direccion;
	}

	private DireccionMovimiento getDireccionHorizontal(Escaque origen, Escaque destino, DireccionAtaque direccionAtaque) {
		DireccionMovimiento direccion = null;

		if(destino.getLetra().compareTo(origen.getLetra()) * direccionAtaque.getSigno() > 0){
			direccion = DireccionMovimiento.Derecha;
		} else if(origen.getLetra().compareTo(destino.getLetra()) * direccionAtaque.getSigno() > 0) {
			direccion = DireccionMovimiento.Izquierda;
		}
		
		return direccion;
	}
	
	private DireccionMovimiento getDireccionVertical(Escaque origen, Escaque destino, DireccionAtaque direccionAtaque) {
		DireccionMovimiento direccion = null;
		
		Integer xxx = direccionAtaque.getSigno();
		Integer yyy = destino.getNumero() - origen.getNumero();
		
		if(xxx * yyy > 0){
			direccion = DireccionMovimiento.Adelante;
		} else if(xxx * yyy < 0) {
			direccion = DireccionMovimiento.Atras;
		}
		
		return direccion;
	}
	
	
	//FIXME: Manera de obtener la direccion ... con el refactor de pasar la direccion de ataque al trebejo no va a ser necesario porque la direccion no va a importar
	protected Integer getNextEscaqueNumero(DireccionAtaque direccionAtaque, List<DireccionMovimiento> direccion, Integer numero) {
		DireccionMovimiento direccionMovimiento = direccion.get(0).equals(DireccionMovimiento.Adelante) || direccion.get(0).equals(DireccionMovimiento.Atras) ? direccion.get(0) : direccion.get(1);
		Integer nextEscaqueNumero = numero + (direccionAtaque.getSigno() * direccionMovimiento.getSigno());
		return nextEscaqueNumero;
	}
	//FIXME: Manera de obtener la direccion ... con el refactor de pasar la direccion de ataque al trebejo no va a ser necesario porque la direccion no va a importar
	protected Character getNextEscaqueLetra(DireccionAtaque direccionAtaque, List<DireccionMovimiento> direccion, Character letra) {
		DireccionMovimiento direccionMovimiento = direccion.get(0).equals(DireccionMovimiento.Derecha) || direccion.get(0).equals(DireccionMovimiento.Izquierda) ? direccion.get(0) : direccion.get(1);
		Integer letraNumericValue = (int)letra;
		
		Character nextEscaqueLetra = (char)(letraNumericValue + (direccionAtaque.getSigno() * direccionMovimiento.getSigno()));
		return nextEscaqueLetra;
	}
}
