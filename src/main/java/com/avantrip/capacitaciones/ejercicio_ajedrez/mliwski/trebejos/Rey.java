package com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.trebejos;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.movimientos.TipoMovimiento;
import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.preconditions.CaminoLibrePrecondition;
import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.preconditions.CantidadMaximaPrecondition;
import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.preconditions.DestinoOcupablePorReyPrecondition;
import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.preconditions.DestinoOcupablePrecondition;
import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.preconditions.MovimientoPrecondition;
import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.preconditions.TipoMovimientoCorrectoPrecondition;

public class Rey extends Trebejo {
	private static final TipoMovimiento TIPO_MOVIMIENTO_1 = TipoMovimiento.Vertical;
	private static final TipoMovimiento TIPO_MOVIMIENTO_2 = TipoMovimiento.Horizontal;
	private static final TipoMovimiento TIPO_MOVIMIENTO_3 = TipoMovimiento.Diagonal;
	private static final List<TipoMovimiento> TIPOS_ESPERADOS = Arrays.asList(TIPO_MOVIMIENTO_1, TIPO_MOVIMIENTO_2, TIPO_MOVIMIENTO_3);
	private static final Integer CANTIDAD_MAXIMA_MOVIMIENTOS = 1;
	
	//TODO: Pasarla a estatica e inicializar en bloque estatico (no hay riesgo de zona critica en este punto)
	private List<MovimientoPrecondition> preconditions = new ArrayList<MovimientoPrecondition>();
	
	
	public Rey(Color color) {
		super(color);
		
		preconditions.add(new TipoMovimientoCorrectoPrecondition(TIPOS_ESPERADOS));
		preconditions.add(new CaminoLibrePrecondition());
		preconditions.add(new CantidadMaximaPrecondition(CANTIDAD_MAXIMA_MOVIMIENTOS));
		preconditions.add(new DestinoOcupablePorReyPrecondition());
	}

	@Override
	protected List<MovimientoPrecondition> getMovimientoPreconditions() {
		return this.preconditions;
	}

}
