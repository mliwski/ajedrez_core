package com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

import java.util.Collection;

import org.junit.Before;
import org.junit.Test;

import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.movimientos.Movimiento;
import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.trebejos.Alfil;
import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.trebejos.Caballo;
import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.trebejos.Color;
import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.trebejos.Peon;
import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.trebejos.Reina;
import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.trebejos.Rey;
import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.trebejos.Torre;
import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.trebejos.Trebejo;
import com.google.common.collect.Multimap;

public class TableroInstanceTest extends TableroTest {

	private TableroInstance tablero;
	
	@Before
	public void beforeEveryTest() {
		super.beforeEveryTest();
		tablero = new TableroInstance();
	}
	
	@Test
	public void shouldCreateTableroWithEmptyTrebejosCapturados() {
		assertThat(tablero.getTrebejosCapturados(), is(empty()));
	}
	
	@Test
	public void shouldCreateTableroWith32EscaquesOcupados() {
		assertThat(tablero.getEscaquesTrebejosMap().size(), is(32));
	}
	
	@Test
	public void shouldCreateTableroWithPeonesBlancosTestedByGetEscaques() {
		Multimap<Trebejo, Escaque> trebejosEscaques = tablero.getTrebejosEscaques();
		Peon peonBlanco = new Peon(Color.Blanco);
		Collection<Escaque> escaquesPeonesBlancos = trebejosEscaques.get(peonBlanco);

		Escaque a2 = new Escaque('a', 2);
		Escaque b2 = new Escaque('b', 2);
		Escaque c2 = new Escaque('c', 2);
		Escaque d2 = new Escaque('d', 2);
		Escaque e2 = new Escaque('e', 2);
		Escaque f2 = new Escaque('f', 2);
		Escaque g2 = new Escaque('g', 2);
		Escaque h2 = new Escaque('h', 2);
		
		assertThat(escaquesPeonesBlancos, containsInAnyOrder(a2, b2, c2, d2, e2, f2, g2, h2));
		assertThat(escaquesPeonesBlancos.size(), is(8)); 
	}
	
	@Test
	public void shouldCreateTableroWithPeonesBlancosTestedByGetTrebejo() {
		Peon peonBlanco = new Peon(Color.Blanco);
		
		Escaque a2 = new Escaque('a', 2);
		Escaque b2 = new Escaque('b', 2);
		Escaque c2 = new Escaque('c', 2);
		Escaque d2 = new Escaque('d', 2);
		Escaque e2 = new Escaque('e', 2);
		Escaque f2 = new Escaque('f', 2);
		Escaque g2 = new Escaque('g', 2);
		Escaque h2 = new Escaque('h', 2);
		
		
		Peon peon_a2 = (Peon) tablero.getTrebejo(a2);
		Peon peon_b2 = (Peon) tablero.getTrebejo(b2);
		Peon peon_c2 = (Peon) tablero.getTrebejo(c2);
		Peon peon_d2 = (Peon) tablero.getTrebejo(d2);
		Peon peon_e2 = (Peon) tablero.getTrebejo(e2);
		Peon peon_f2 = (Peon) tablero.getTrebejo(f2);
		Peon peon_g2 = (Peon) tablero.getTrebejo(g2);
		Peon peon_h2 = (Peon) tablero.getTrebejo(h2);
		
		assertThat(peon_a2, equalTo(peonBlanco));
		assertThat(peon_b2, equalTo(peonBlanco));
		assertThat(peon_c2, equalTo(peonBlanco));
		assertThat(peon_d2, equalTo(peonBlanco));
		assertThat(peon_e2, equalTo(peonBlanco));
		assertThat(peon_f2, equalTo(peonBlanco));
		assertThat(peon_g2, equalTo(peonBlanco));
		assertThat(peon_h2, equalTo(peonBlanco));
	}
	
	@Test
	public void shouldCreateTableroWithTorresBlancasTestedByGetEscaques() {
		Multimap<Trebejo, Escaque> trebejosEscaques = tablero.getTrebejosEscaques();
		Torre torreBlanca = new Torre(Color.Blanco);
		Collection<Escaque> escaquesTorresBlancas = trebejosEscaques.get(torreBlanca);

		Escaque a1 = new Escaque('a', 1);
		Escaque h1 = new Escaque('h', 1);
		
		assertThat(escaquesTorresBlancas, containsInAnyOrder(a1, h1));
		assertThat(escaquesTorresBlancas.size(), is(2)); 
	}
	
	@Test
	public void shouldCreateTableroWithTorresBlancasTestedByGetTrebejo() {
		Torre torreBlanca = new Torre(Color.Blanco);
		
		Escaque a1 = new Escaque('a', 1);
		Escaque h1 = new Escaque('h', 1);
		
		Torre torre_a1 = (Torre) tablero.getTrebejo(a1);
		Torre torre_h1 = (Torre) tablero.getTrebejo(h1);
		
		assertThat(torre_a1, equalTo(torreBlanca));
		assertThat(torre_h1, equalTo(torreBlanca));
	}
	
	@Test
	public void shouldCreateTableroWithCaballosBlancosTestedByGetEscaques() {
		Multimap<Trebejo, Escaque> trebejosEscaques = tablero.getTrebejosEscaques();
		Caballo torreBlanca = new Caballo(Color.Blanco);
		Collection<Escaque> escaquesTorresBlancas = trebejosEscaques.get(torreBlanca);
		
		Escaque b1 = new Escaque('b', 1);
		Escaque g1 = new Escaque('g', 1);
		
		assertThat(escaquesTorresBlancas, containsInAnyOrder(b1, g1));
		assertThat(escaquesTorresBlancas.size(), is(2)); 
	}
	
	@Test
	public void shouldCreateTableroWithCaballosBlancosTestedByGetTrebejo() {
		Caballo torreBlanca = new Caballo(Color.Blanco);
		
		Escaque b1 = new Escaque('b', 1);
		Escaque g1 = new Escaque('g', 1);
		
		Caballo caballo_b1 = (Caballo) tablero.getTrebejo(b1);
		Caballo caballo_g1 = (Caballo) tablero.getTrebejo(g1);
		
		assertThat(caballo_b1, equalTo(torreBlanca));
		assertThat(caballo_g1, equalTo(torreBlanca));
	}

	@Test
	public void shouldCreateTableroWithAlfilesBlancosTestedByGetEscaques() {
		Multimap<Trebejo, Escaque> trebejosEscaques = tablero.getTrebejosEscaques();
		Alfil alfilBlanco = new Alfil(Color.Blanco);
		Collection<Escaque> escaquesAlfilesBlancas = trebejosEscaques.get(alfilBlanco);
		
		Escaque c1 = new Escaque('c', 1);
		Escaque f1 = new Escaque('f', 1);
		
		assertThat(escaquesAlfilesBlancas, containsInAnyOrder(c1, f1));
		assertThat(escaquesAlfilesBlancas.size(), is(2)); 
	}
	
	@Test
	public void shouldCreateTableroWithAlfilesBlancosTestedByGetTrebejo() {
		Alfil alfilBlanco = new Alfil(Color.Blanco);
		
		Escaque c1 = new Escaque('c', 1);
		Escaque f1 = new Escaque('f', 1);
		
		Alfil alfil_c1 = (Alfil) tablero.getTrebejo(c1);
		Alfil alfil_f1 = (Alfil) tablero.getTrebejo(f1);
		
		assertThat(alfil_c1, equalTo(alfilBlanco));
		assertThat(alfil_f1, equalTo(alfilBlanco));
	}
	
	@Test
	public void shouldCreateTableroWithReyBlancoTestedByGetEscaques() {
		Multimap<Trebejo, Escaque> trebejosEscaques = tablero.getTrebejosEscaques();
		Rey reyBlanco = new Rey(Color.Blanco);
		Collection<Escaque> escaquesReyBlanco = trebejosEscaques.get(reyBlanco);
		
		Escaque e1 = new Escaque('e', 1);
		
		assertThat(escaquesReyBlanco, containsInAnyOrder(e1));
		assertThat(escaquesReyBlanco.size(), is(1)); 
	}
	
	@Test
	public void shouldCreateTableroWithReyBlancoTestedByGetTrebejo() {
		Rey reyBlanco = new Rey(Color.Blanco);
		
		Escaque e1 = new Escaque('e', 1);
		
		Rey rey_e1 = (Rey) tablero.getTrebejo(e1);

		assertThat(rey_e1, equalTo(reyBlanco));
	}
	
	@Test
	public void shouldCreateTableroWithReinaBlancaTestedByGetEscaques() {
		Multimap<Trebejo, Escaque> trebejosEscaques = tablero.getTrebejosEscaques();
		Reina reinaBlanca = new Reina(Color.Blanco);
		Collection<Escaque> escaquesReinaBlanca = trebejosEscaques.get(reinaBlanca);
		
		Escaque d1 = new Escaque('d', 1);
		
		assertThat(escaquesReinaBlanca, containsInAnyOrder(d1));
		assertThat(escaquesReinaBlanca.size(), is(1)); 
	}
	
	@Test
	public void shouldCreateTableroWithReinaBlancaTestedByGetTrebejo() {
		Reina reinaBlanca = new Reina(Color.Blanco);
		
		Escaque d1 = new Escaque('d', 1);
		
		Reina reina_d1 = (Reina) tablero.getTrebejo(d1);

		assertThat(reina_d1, equalTo(reinaBlanca));
	}
	
	@Test
	public void shouldCreateTableroWithPeonesNegrosTestedByGetEscaques() {
		Multimap<Trebejo, Escaque> trebejosEscaques = tablero.getTrebejosEscaques();
		Peon peonNegro = new Peon(Color.Negro);
		Collection<Escaque> escaquesPeonesNegros = trebejosEscaques.get(peonNegro);
		
		Escaque a2 = new Escaque('a', 7);
		Escaque b2 = new Escaque('b', 7);
		Escaque c2 = new Escaque('c', 7);
		Escaque d2 = new Escaque('d', 7);
		Escaque e2 = new Escaque('e', 7);
		Escaque f2 = new Escaque('f', 7);
		Escaque g2 = new Escaque('g', 7);
		Escaque h2 = new Escaque('h', 7);
		
		assertThat(escaquesPeonesNegros, containsInAnyOrder(a2, b2, c2, d2, e2, f2, g2, h2));
		assertThat(escaquesPeonesNegros.size(), is(8)); 
	}
	
	@Test
	public void shouldCreateTableroWithPeonesNegrosTestedByGetTrebejo() {
		Peon peonNegro = new Peon(Color.Negro);
		
		Escaque a2 = new Escaque('a', 7);
		Escaque b2 = new Escaque('b', 7);
		Escaque c2 = new Escaque('c', 7);
		Escaque d2 = new Escaque('d', 7);
		Escaque e2 = new Escaque('e', 7);
		Escaque f2 = new Escaque('f', 7);
		Escaque g2 = new Escaque('g', 7);
		Escaque h2 = new Escaque('h', 7);
		
		
		Peon peon_a2 = (Peon) tablero.getTrebejo(a2);
		Peon peon_b2 = (Peon) tablero.getTrebejo(b2);
		Peon peon_c2 = (Peon) tablero.getTrebejo(c2);
		Peon peon_d2 = (Peon) tablero.getTrebejo(d2);
		Peon peon_e2 = (Peon) tablero.getTrebejo(e2);
		Peon peon_f2 = (Peon) tablero.getTrebejo(f2);
		Peon peon_g2 = (Peon) tablero.getTrebejo(g2);
		Peon peon_h2 = (Peon) tablero.getTrebejo(h2);
		
		assertThat(peon_a2, equalTo(peonNegro));
		assertThat(peon_b2, equalTo(peonNegro));
		assertThat(peon_c2, equalTo(peonNegro));
		assertThat(peon_d2, equalTo(peonNegro));
		assertThat(peon_e2, equalTo(peonNegro));
		assertThat(peon_f2, equalTo(peonNegro));
		assertThat(peon_g2, equalTo(peonNegro));
		assertThat(peon_h2, equalTo(peonNegro));
	}
	
	@Test
	public void shouldCreateTableroWithTorresNegrasTestedByGetEscaques() {
		Multimap<Trebejo, Escaque> trebejosEscaques = tablero.getTrebejosEscaques();
		Torre torreNegra = new Torre(Color.Negro);
		Collection<Escaque> escaquesTorresNegras = trebejosEscaques.get(torreNegra);
		
		Escaque a8 = new Escaque('a', 8);
		Escaque h8 = new Escaque('h', 8);
		
		assertThat(escaquesTorresNegras, containsInAnyOrder(a8, h8));
		assertThat(escaquesTorresNegras.size(), is(2)); 
	}
	
	@Test
	public void shouldCreateTableroWithTorresNegrasTestedByGetTrebejo() {
		Torre torreNegra = new Torre(Color.Negro);
		
		Escaque a8 = new Escaque('a', 8);
		Escaque h8 = new Escaque('h', 8);
		
		Torre torre_a8 = (Torre) tablero.getTrebejo(a8);
		Torre torre_h8 = (Torre) tablero.getTrebejo(h8);
		
		assertThat(torre_a8, equalTo(torreNegra));
		assertThat(torre_h8, equalTo(torreNegra));
	}
	
	@Test
	public void shouldCreateTableroWithCaballosNegrosTestedByGetEscaques() {
		Multimap<Trebejo, Escaque> trebejosEscaques = tablero.getTrebejosEscaques();
		Caballo torreNegra = new Caballo(Color.Negro);
		Collection<Escaque> escaquesTorresNegras = trebejosEscaques.get(torreNegra);
		
		Escaque b8 = new Escaque('b', 8);
		Escaque g8 = new Escaque('g', 8);
		
		assertThat(escaquesTorresNegras, containsInAnyOrder(b8, g8));
		assertThat(escaquesTorresNegras.size(), is(2)); 
	}
	
	@Test
	public void shouldCreateTableroWithCaballosNegrosTestedByGetTrebejo() {
		Caballo torreNegra = new Caballo(Color.Negro);
		
		Escaque b8 = new Escaque('b', 8);
		Escaque g8 = new Escaque('g', 8);
		
		Caballo caballo_b8 = (Caballo) tablero.getTrebejo(b8);
		Caballo caballo_g8 = (Caballo) tablero.getTrebejo(g8);
		
		assertThat(caballo_b8, equalTo(torreNegra));
		assertThat(caballo_g8, equalTo(torreNegra));
	}
	
	@Test
	public void shouldCreateTableroWithAlfilesNegrosTestedByGetEscaques() {
		Multimap<Trebejo, Escaque> trebejosEscaques = tablero.getTrebejosEscaques();
		Alfil alfilNegro = new Alfil(Color.Negro);
		Collection<Escaque> escaquesAlfilesNegras = trebejosEscaques.get(alfilNegro);
		
		Escaque c8 = new Escaque('c', 8);
		Escaque f8 = new Escaque('f', 8);
		
		assertThat(escaquesAlfilesNegras, containsInAnyOrder(c8, f8));
		assertThat(escaquesAlfilesNegras.size(), is(2)); 
	}
	
	@Test
	public void shouldCreateTableroWithAlfilesNegrosTestedByGetTrebejo() {
		Alfil alfilNegro = new Alfil(Color.Negro);
		
		Escaque c8 = new Escaque('c', 8);
		Escaque f8 = new Escaque('f', 8);
		
		Alfil alfil_c8 = (Alfil) tablero.getTrebejo(c8);
		Alfil alfil_f8 = (Alfil) tablero.getTrebejo(f8);
		
		assertThat(alfil_c8, equalTo(alfilNegro));
		assertThat(alfil_f8, equalTo(alfilNegro));
	}
	
	@Test
	public void shouldCreateTableroWithReyNegroTestedByGetEscaques() {
		Multimap<Trebejo, Escaque> trebejosEscaques = tablero.getTrebejosEscaques();
		Rey reyNegro = new Rey(Color.Negro);
		Collection<Escaque> escaquesReyNegro = trebejosEscaques.get(reyNegro);
		
		Escaque e8 = new Escaque('e', 8);
		
		assertThat(escaquesReyNegro, containsInAnyOrder(e8));
		assertThat(escaquesReyNegro.size(), is(1)); 
	}
	
	@Test
	public void shouldCreateTableroWithReyNegroTestedByGetTrebejo() {
		Rey reyNegro = new Rey(Color.Negro);
		
		Escaque e8 = new Escaque('e', 8);
		
		Rey rey_e8 = (Rey) tablero.getTrebejo(e8);
		
		assertThat(rey_e8, equalTo(reyNegro));
	}
	
	@Test
	public void shouldCreateTableroWithReinaNegraTestedByGetEscaques() {
		Multimap<Trebejo, Escaque> trebejosEscaques = tablero.getTrebejosEscaques();
		Reina reinaNegra = new Reina(Color.Negro);
		Collection<Escaque> escaquesReinaNegra = trebejosEscaques.get(reinaNegra);
		
		Escaque d8 = new Escaque('d', 8);
		
		assertThat(escaquesReinaNegra, containsInAnyOrder(d8));
		assertThat(escaquesReinaNegra.size(), is(1)); 
	}
	
	@Test
	public void shouldCreateTableroWithReinaNegraTestedByGetTrebejo() {
		Reina reinaNegra = new Reina(Color.Negro);
		
		Escaque d8 = new Escaque('d', 8);
		
		Reina reina_d8 = (Reina) tablero.getTrebejo(d8);
		
		assertThat(reina_d8, equalTo(reinaNegra));
	}
	
	@Test
	public void shouldGetSnapshotOfInitialTablero() {
		TableroSnapshot snapshot = tablero.getSnapshot();
		
		assertThat(snapshot.getTrebejosCapturados(), is(empty()));
		assertThat(snapshot.getEscaquesTrebejosMap().size(), is(32));
	}

	@Test
	public void shouldGetSnapshotOfModifiedTablero() {
		Trebejo trebejoDestino = new Torre(Color.Negro);
		Escaque origen = new Escaque('a', 1);
		Escaque destino = new Escaque('h', 8);
		Movimiento movimiento = new Movimiento(origen, destino);
		
		tablero.ejecutarMovimiento(movimiento);
		
		TableroSnapshot snapshot = tablero.getSnapshot();
		
		assertThat(snapshot.getTrebejosCapturados(), contains(trebejoDestino));
		assertThat(snapshot.getEscaquesTrebejosMap().size(), is(31));
	}
	
	@Test
	public void shouldNotAffectInstanceBecauseOfTrebejo() {
		Trebejo trebejoDestino = new Torre(Color.Negro);
		Escaque origen = new Escaque('a', 1);
		Escaque destino = new Escaque('h', 8);
		Movimiento movimiento = new Movimiento(origen, destino);
		
		TableroSnapshot snapshot = tablero.getSnapshot();
		snapshot.ejecutarMovimiento(movimiento);
		
		assertThat(snapshot.getTrebejosCapturados(), contains(trebejoDestino));
		assertThat(snapshot.getEscaquesTrebejosMap().size(), is(31));

		assertThat(tablero.getTrebejosCapturados(), is(empty()));
		assertThat(tablero.getEscaquesTrebejosMap().size(), is(32));
	}

}
