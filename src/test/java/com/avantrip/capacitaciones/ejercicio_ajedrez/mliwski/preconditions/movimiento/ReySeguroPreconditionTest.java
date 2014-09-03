package com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.preconditions.movimiento;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.Escaque;
import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.TableroSnapshot;
import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.TestUtils;
import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.exceptions.MovimientoIlegalException;
import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.movimientos.Movimiento;
import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.trebejos.Color;
import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.trebejos.Torre;
import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.trebejos.Trebejo;

public class ReySeguroPreconditionTest {

	private MovimientoPrecondition reySeguroPrecondition;
	private Map<Escaque, Trebejo> escaquesTrebejos;
	private Movimiento movimiento;
	
	@Before
	public void beforeEveryTest() {
		Torre torreBlanca = new Torre(Color.Blanco);
		Escaque escaqueTorreBlanca = new Escaque('c', 1);
		
		escaquesTrebejos = new HashMap<Escaque, Trebejo>();
		escaquesTrebejos.put(escaqueTorreBlanca, torreBlanca);

		reySeguroPrecondition = new ReySeguroPrecondition();
		
		movimiento = new Movimiento(escaqueTorreBlanca, new Escaque('c',4));
	}
	
	@Test(expected=MovimientoIlegalException.class)
	public void shouldThrowsMovimientoIlegalException() {
		Torre torreNegra = new Torre(Color.Negro);
		Escaque escaqueTorreNegra = new Escaque('a', 1);
		escaquesTrebejos.put(escaqueTorreNegra, torreNegra);
		TableroSnapshot tableroSnapshot = TestUtils.buildWorkingTableroSnapshotWith(escaquesTrebejos);
		
		reySeguroPrecondition.check(tableroSnapshot, movimiento);
	}
	
	@Test
	public void shouldNotThrowsException() {
		TableroSnapshot tableroSnapshot = TestUtils.buildWorkingTableroSnapshotWith(escaquesTrebejos);

		reySeguroPrecondition.check(tableroSnapshot, movimiento);
	}
}