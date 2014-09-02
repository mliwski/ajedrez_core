package com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.preconditions.movimiento;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.Escaque;
import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.TableroSnapshot;
import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.exceptions.MovimientoIlegalException;
import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.movimientos.Movimiento;
import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.trebejos.Color;
import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.trebejos.Rey;
import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.trebejos.Torre;
import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.trebejos.Trebejo;

public class ReySeguroPreconditionTest {

	private TableroSnapshot tableroSnapshot;
	
	private MovimientoPrecondition reySeguroPrecondition;
	private Map<Escaque, Trebejo> escaquesTrebejos;
	private Movimiento movimiento;
	
	@Before
	public void beforeEveryTest() {
		Rey reyBlanco = new Rey(Color.Blanco);
		Escaque escaqueReyBlanco = new Escaque('e', 1);

		Rey reyNegro = new Rey(Color.Negro);
		Escaque escaqueReyNegro = new Escaque('e', 8);

		Torre torreBlanca = new Torre(Color.Blanco);
		Escaque escaqueTorreBlanca = new Escaque('c', 1);
		
		escaquesTrebejos = new HashMap<Escaque, Trebejo>();
		escaquesTrebejos.put(escaqueReyBlanco, reyBlanco);
		escaquesTrebejos.put(escaqueReyNegro, reyNegro);
		escaquesTrebejos.put(escaqueTorreBlanca, torreBlanca);

		reySeguroPrecondition = new ReySeguroPrecondition();
		
		movimiento = new Movimiento(escaqueTorreBlanca, new Escaque('c',4));
	}
	
	@Test(expected=MovimientoIlegalException.class)
	public void shouldThrowsMovimientoIlegalException() {
		Torre torreNegra = new Torre(Color.Negro);
		Escaque escaqueTorreNegra = new Escaque('a', 1);
		escaquesTrebejos.put(escaqueTorreNegra, torreNegra);
		tableroSnapshot = new TableroSnapshot(escaquesTrebejos, new ArrayList<Trebejo>());
		
		reySeguroPrecondition.check(tableroSnapshot, movimiento);
	}
	
	@Test
	public void shouldNotThrowsException() {
		tableroSnapshot = new TableroSnapshot(escaquesTrebejos, new ArrayList<Trebejo>());

		reySeguroPrecondition.check(tableroSnapshot, movimiento);
	}
}