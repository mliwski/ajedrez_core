package com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.trebejos;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.movimientos.TipoMovimiento;
import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.preconditions.CaminoLibrePrecondition;
import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.preconditions.DestinoOcupablePrecondition;
import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.preconditions.MovimientoPrecondition;
import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.preconditions.TipoMovimientoCorrectoPrecondition;

public class Alfil extends Pieza {
	private static final TipoMovimiento TIPO_MOVIMIENTO = TipoMovimiento.Diagonal;
	
	//TODO: Pasarla a estatica e inicializar en bloque estatico (no hay riesgo de zona critica en este punto)
	private List<MovimientoPrecondition> preconditions = new ArrayList<MovimientoPrecondition>();
	
	
	public Alfil() {
		List<TipoMovimiento> tiposEsperados = Arrays.asList(TIPO_MOVIMIENTO);
		preconditions.add(new TipoMovimientoCorrectoPrecondition(tiposEsperados));

		preconditions.add(new CaminoLibrePrecondition());
		
		preconditions.add(new DestinoOcupablePrecondition());
	}

	@Override
	protected List<MovimientoPrecondition> getMovimientoPreconditions() {
		return this.preconditions;
	}

}
