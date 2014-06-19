package com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.trebejos;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.movimientos.TipoMovimiento;
import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.preconditions.movimiento.CaminoLibrePrecondition;
import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.preconditions.movimiento.DestinoOcupablePrecondition;
import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.preconditions.movimiento.MovimientoPrecondition;
import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.preconditions.movimiento.ReySeguroPrecondition;
import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.preconditions.movimiento.TipoMovimientoCorrectoPrecondition;

public class Torre extends Trebejo {
	private static final List<TipoMovimiento> TIPOS_ESPERADOS = Arrays.asList(TipoMovimiento.Vertical, TipoMovimiento.Horizontal);
	
	private static List<MovimientoPrecondition> preconditions;

	public Torre(Color color) {
		super(color);
		
		synchronized (this) {
			if(preconditions == null) {
				preconditions = new ArrayList<MovimientoPrecondition>();				
				preconditions.add(new TipoMovimientoCorrectoPrecondition(TIPOS_ESPERADOS));
				preconditions.add(new CaminoLibrePrecondition());
				preconditions.add(new DestinoOcupablePrecondition());
				preconditions.add(new ReySeguroPrecondition());
			}
		}
	}

	@Override
	protected List<MovimientoPrecondition> getMovimientoPreconditions() {
		return Torre.preconditions;
	}

}
