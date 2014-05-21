package com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.preconditions;

import java.util.List;

import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.exceptions.MovimientoIlegalException;
import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.movimientos.Movimiento;
import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.movimientos.TipoMovimiento;

public class TipoMovimientoCorrectoPrecondition extends MovimientoPrecondition{
	private List<TipoMovimiento> tiposEsperados;

	public TipoMovimientoCorrectoPrecondition(List<TipoMovimiento> tiposEsperados) {
		super();
		this.tiposEsperados = tiposEsperados;
	}

	@Override
	public void check(Movimiento movimiento) {
		TipoMovimiento tipo = movimiento.getTipo();
		if(tiposEsperados.contains(tipo) == false) {
			throw new MovimientoIlegalException("El trebejo que desea mover solo puede moverse en " + tiposEsperados + " (movimiento deseado=" + tipo + ")");
		}
	}
}
