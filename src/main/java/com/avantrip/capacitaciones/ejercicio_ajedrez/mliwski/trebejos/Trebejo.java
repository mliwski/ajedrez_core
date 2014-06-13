package com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.trebejos;

import java.util.ArrayList;
import java.util.List;

import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.TableroSnapshot;
import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.capturas.CapturaStrategy;
import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.capturas.CapturaStrategyDefault;
import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.movimientos.Movimiento;
import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.preconditions.movimiento.MovimientoPrecondition;

public abstract class Trebejo {
	private Color color;
	private List<Movimiento> movimientos = new ArrayList<Movimiento>();
	private CapturaStrategy capturaStrategy;
	
	public Trebejo(Color color) {
		checkArgumentPreconditions(color);
		this.color = color;
		this.capturaStrategy = new CapturaStrategyDefault();
	}

	private void checkArgumentPreconditions(Color color) {
		if(color==null) {
			throw new IllegalArgumentException("Para construir el trebejo se necesita saber el color");
		}
	}

	public final void addMovimiento(Movimiento movimiento) {
		movimientos.add(movimiento);
	}
	
	public final void checkPreconditions(TableroSnapshot tableroSnapshot, Movimiento movimiento) {
		List<MovimientoPrecondition> movimientoPreconditions = getMovimientoPreconditions();
		for (MovimientoPrecondition precondition : movimientoPreconditions) {
			precondition.check(tableroSnapshot, movimiento);
		}
	}; 
	protected abstract List<MovimientoPrecondition> getMovimientoPreconditions();

	public CapturaStrategy getCapturaStrategy() {
		return capturaStrategy;
	}
	
	public Color getColor() {
		return color;
	}

	public boolean isTrebejoMovido() {
		return movimientos.isEmpty() == false;
	}
	
}
