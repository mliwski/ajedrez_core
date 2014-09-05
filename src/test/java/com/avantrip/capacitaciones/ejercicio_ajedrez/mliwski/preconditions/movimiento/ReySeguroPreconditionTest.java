package com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.preconditions.movimiento;

import org.junit.Before;
import org.junit.Test;

import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.Escaque;
import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.TestUtils;
import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.exceptions.MovimientoIlegalException;
import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.movimientos.Movimiento;
import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.tableros.TableroSnapshot;
import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.tableros.TableroSnapshot.TableroSnapshotBuilder;
import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.trebejos.Color;
import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.trebejos.Torre;

public class ReySeguroPreconditionTest {

	private TableroSnapshotBuilder tableroSnapshotBuilder;
	private Movimiento movimiento;
	private MovimientoPrecondition reySeguroPrecondition;
	
	@Before
	public void beforeEveryTest() {
		tableroSnapshotBuilder = TestUtils.getWorkingTableroSnapshotBuilder();

		Torre torreBlanca = new Torre(Color.Blanco);
		Escaque escaqueTorreBlanca = new Escaque('c', 1);
		tableroSnapshotBuilder.with(escaqueTorreBlanca, torreBlanca);
		
		reySeguroPrecondition = new ReySeguroPrecondition();
		
		movimiento = new Movimiento(escaqueTorreBlanca, new Escaque('c',4));
	}
	
	@Test(expected=MovimientoIlegalException.class)
	public void shouldThrowsMovimientoIlegalException() {
		Torre torreNegra = new Torre(Color.Negro);
		Escaque escaqueTorreNegra = new Escaque('a', 1);
		tableroSnapshotBuilder.with(escaqueTorreNegra, torreNegra);
		TableroSnapshot tableroSnapshot = tableroSnapshotBuilder.build();
		
		reySeguroPrecondition.check(tableroSnapshot, movimiento);
	}
	
	@Test
	public void shouldNotThrowsException() {
		TableroSnapshot tableroSnapshot = tableroSnapshotBuilder.build();

		reySeguroPrecondition.check(tableroSnapshot, movimiento);
	}
}