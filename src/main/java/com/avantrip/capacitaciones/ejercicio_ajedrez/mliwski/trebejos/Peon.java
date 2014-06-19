package com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.trebejos;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.movimientos.TipoMovimiento;
import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.preconditions.movimiento.MovimientoPrecondition;
import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.preconditions.movimiento.ReySeguroPrecondition;
import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.preconditions.movimiento.TipoMovimientoCorrectoPrecondition;
import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.preconditions.movimiento.peon.PeonCantidadPrecondition;
import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.preconditions.movimiento.peon.PeonDestinoOcupablePrecondition;
import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.preconditions.movimiento.peon.PeonDireccionAdelantePrecondition;

//TODO: Soportar peon al paso
public class Peon extends Trebejo {
	private static final List<TipoMovimiento> TIPOS_ESPERADOS = Arrays.asList(TipoMovimiento.Vertical, TipoMovimiento.Diagonal);
	
	private static List<MovimientoPrecondition> preconditions;
	
	public Peon(Color color) {
		super(color);

		synchronized (this) {
			if(preconditions == null){
				preconditions = new ArrayList<MovimientoPrecondition>();
				preconditions.add(new TipoMovimientoCorrectoPrecondition(TIPOS_ESPERADOS));
				preconditions.add(new PeonCantidadPrecondition());
				preconditions.add(new PeonDestinoOcupablePrecondition());
				preconditions.add(new PeonDireccionAdelantePrecondition());
				preconditions.add(new ReySeguroPrecondition());
			}
		}
	}

	@Override
	protected List<MovimientoPrecondition> getMovimientoPreconditions() {
		return Peon.preconditions;
	}
}