package com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.trebejos;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.capturas.CapturaStrategy;
import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.movimientos.TipoMovimiento;
import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.preconditions.movimiento.CantidadMaximaPrecondition;
import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.preconditions.movimiento.DestinoOcupablePeonPrecondition;
import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.preconditions.movimiento.MovimientoPrecondition;
import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.preconditions.movimiento.ReySeguroPrecondition;
import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.preconditions.movimiento.TipoMovimientoCorrectoPrecondition;

public class Peon extends Trebejo {
	private static final TipoMovimiento TIPO_MOVIMIENTO_1 = TipoMovimiento.Vertical;
	private static final TipoMovimiento TIPO_MOVIMIENTO_2 = TipoMovimiento.Diagonal;
	private static final List<TipoMovimiento> TIPOS_ESPERADOS = Arrays.asList(TIPO_MOVIMIENTO_1, TIPO_MOVIMIENTO_2);
	private static final Integer CANTIDAD_MAXIMA_MOVIMIENTOS = 2;
	
	//TODO: Pasarla a estatica e inicializar en bloque estatico (no hay riesgo de zona critica en este punto)
	private List<MovimientoPrecondition> preconditions = new ArrayList<MovimientoPrecondition>();
	
	public Peon(Color color) {
		super(color);

		//TODO: Terminar de implementar
		// Mover en diagonal solo si come
		// Cantidad dos solo si primer movimiento
		// Solo hacia adelante
		// Soportar Peon al paso
		preconditions.add(new TipoMovimientoCorrectoPrecondition(TIPOS_ESPERADOS));
		preconditions.add(new CantidadMaximaPrecondition(CANTIDAD_MAXIMA_MOVIMIENTOS));
		preconditions.add(new DestinoOcupablePeonPrecondition());
		preconditions.add(new ReySeguroPrecondition());
		
	}

	@Override
	protected List<MovimientoPrecondition> getMovimientoPreconditions() {
		return this.preconditions;
	}
	
	@Override
	public CapturaStrategy getCapturaStrategy() {
		return this.getCapturaStrategy();
	}

}
