package com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.movimientos;

import java.util.ArrayList;
import java.util.List;

import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.Escaque;
import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.exceptions.TipoMovimientoDesconocidoException;

public abstract class MovimientoProcessor {
	private MovimientoProcessor next;
	
	public MovimientoProcessor(MovimientoProcessor next) {
		this.next = next;
	}

	public DatosMovimiento detectar(Escaque origen, Escaque destino) {
		DatosMovimiento datosMovimiento = null;
		if(isTipoDetectado(origen, destino)) {
			datosMovimiento = getDatosMovimiento(origen, destino);
  		} else if(this.next != null) {
  			datosMovimiento = this.next.detectar(origen, destino);
		} else {
			throw new TipoMovimientoDesconocidoException("El tipo de movimiento que se desea realizar es desconocido en el ajedrez");
		}
		
		return datosMovimiento;
	}
	protected abstract boolean isTipoDetectado(Escaque origen, Escaque destino);
	protected abstract DatosMovimiento getDatosMovimiento(Escaque origen, Escaque destino);
	
	protected final List<Escaque> getCamino(Escaque origen, Escaque destino) {
		List<Escaque> camino = new ArrayList<Escaque>();
		
		Integer incrementoLetra = getIncrementoLetra(origen, destino);
		Integer incrementoNumero = getIncrementoNumero(origen, destino);
		
		Escaque escaqueSiguiente = getEscaqueSiguiente(origen, incrementoLetra, incrementoNumero);
		while (destino.equals(escaqueSiguiente) == false) {
			camino.add(escaqueSiguiente);
			escaqueSiguiente = getEscaqueSiguiente(escaqueSiguiente, incrementoLetra, incrementoNumero);
		}
		
		return camino;
	}
	
	private Integer getIncrementoLetra(Escaque origen, Escaque destino) {
		Integer distanciaLetra = origen.getDistanciaLetra(destino);
		Integer normaLetra = distanciaLetra == 0 ? 0 : distanciaLetra/Math.abs(distanciaLetra);
		return normaLetra;
	}
	

	private Integer getIncrementoNumero(Escaque origen, Escaque destino) {
		Integer distanciaNumero = origen.getDistanciaNumero(destino);
		Integer normaNumero = distanciaNumero == 0 ? 0 : distanciaNumero/Math.abs(distanciaNumero);
		return normaNumero;
	}
	protected abstract Escaque getEscaqueSiguiente(Escaque escaque, Integer incrementoLetra, Integer incrementoNumero);
	
	protected final Character getLetraSiguiente(Escaque nextEscaque, Integer incremento) {
		return (char)((int)nextEscaque.getLetra() + incremento);
	}
	
	protected final Integer getNumeroSiguiente(Escaque nextEscaque, Integer incremento) {
		return nextEscaque.getNumero() + incremento;
	}
}
