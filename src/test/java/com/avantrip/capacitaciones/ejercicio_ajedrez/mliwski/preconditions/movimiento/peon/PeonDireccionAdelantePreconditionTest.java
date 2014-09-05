package com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.preconditions.movimiento.peon;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;

import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.Escaque;
import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.exceptions.MovimientoIlegalException;
import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.movimientos.Movimiento;
import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.preconditions.movimiento.MovimientoPrecondition;
import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.preconditions.movimiento.peon.PeonDireccionAdelantePrecondition;
import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.tableros.TableroSnapshot;
import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.trebejos.Color;
import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.trebejos.Peon;

public class PeonDireccionAdelantePreconditionTest {

	private TableroSnapshot tableroSnapshot;
	private Movimiento movimiento;
	
	private Escaque origen;
	private Peon trebejoOrigen;
	
	private Escaque destino;

	private MovimientoPrecondition direccionAdelantePrecondition;

	@Before
	public void beforeEveryTest() {
		trebejoOrigen = spy(new Peon(Color.Blanco));
		tableroSnapshot = mock(TableroSnapshot.class);
		movimiento = mock(Movimiento.class);
		
		origen = mock(Escaque.class);
		when(tableroSnapshot.getTrebejo(origen)).thenReturn(trebejoOrigen);

		destino = mock(Escaque.class);
		
		when(movimiento.getOrigen()).thenReturn(origen);
		when(movimiento.getDestino()).thenReturn(destino);
		
		direccionAdelantePrecondition = new PeonDireccionAdelantePrecondition();
	}

	@Test(expected=MovimientoIlegalException.class)
	public void shouldThrowsMovimientoIlegalBecauseDireccionAtrasIlegal() {
		when(trebejoOrigen.getColor()).thenReturn(Color.Blanco);
		when(origen.getDistanciaNumero(destino)).thenReturn(-5);
		
		direccionAdelantePrecondition.check(tableroSnapshot, movimiento);
	}
	
	@Test(expected=MovimientoIlegalException.class)
	public void shouldThrowsMovimientoIlegalBecauseDireccionAdelanteIlegal() {
		when(trebejoOrigen.getColor()).thenReturn(Color.Negro);
		when(origen.getDistanciaNumero(destino)).thenReturn(2);
		
		direccionAdelantePrecondition.check(tableroSnapshot, movimiento);
	}
	
	@Test
	public void shouldNotThrowsExceptionOnDireccionAdelante() {
		when(trebejoOrigen.getColor()).thenReturn(Color.Blanco);
		when(origen.getDistanciaNumero(destino)).thenReturn(5);
		
		direccionAdelantePrecondition.check(tableroSnapshot, movimiento);
	}
	
	@Test
	public void shouldNotThrowsExceptionOnDireccionAtras() {
		when(trebejoOrigen.getColor()).thenReturn(Color.Negro);
		when(origen.getDistanciaNumero(destino)).thenReturn(-2);
		
		direccionAdelantePrecondition.check(tableroSnapshot, movimiento);
	}
}