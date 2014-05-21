package com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.preconditions;

import java.util.Map;

import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.Escaque;
import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.exceptions.MovimientoIlegalException;
import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.movimientos.Movimiento;
import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.movimientos.TipoMovimiento;
import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.trebejos.Color;
import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.trebejos.Peon;
import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.trebejos.Trebejo;

public class DestinoOcupablePrecondition extends MovimientoPrecondition{
	//TODO: Pensar como hacer para que no lo utilice un peon, para un peon faltan mas chequeos .... 
	//	o hacer compatible mediante
	
	@Override
	public void check(Movimiento movimiento) {
		Escaque origen = movimiento.getOrigen();
		Trebejo trebejoOrigen = tablero.getTrebejo(origen);
		Color colorOrigen = trebejoOrigen.getColor();
		
		Escaque destino = movimiento.getDestino();
		Trebejo trebejoDestino = tablero.getTrebejo(destino);
		Color colorDestino = trebejoDestino != null ? trebejoDestino.getColor() : null;
		
		if(colorDestino != null && colorDestino.equals(colorOrigen)){
			throw new MovimientoIlegalException("El escaque destino esta ocupado por un trebejo el mismo color");
		}
		
		//FIXME: Implementar sin instanceof ni cadena de ifs
		if(trebejoOrigen instanceof Peon) {
			if(colorDestino != null && colorDestino.equals(colorOrigen) == false && movimiento.getTipo().equals(TipoMovimiento.Diagonal) == false){
				throw new MovimientoIlegalException("El peon solo puede comer en diagonal");
			}
			//TODO: Soportar peon al paso
//			else if() {
//				
//			}	
		}
	}
	
}
