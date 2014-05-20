package com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski;

import com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski.exceptions.EscaqueException;

public class Escaque implements Cloneable{
	public static final Character LETRA_MINIMA = 'a';
	public static final Character LETRA_MAXIMA = 'h';
	public static final Integer NUMERO_MINIMO = 1;
	public static final Integer NUMERO_MAXIMO = 8;
	
	private Character letra;
	private Integer numero;
	
	@SuppressWarnings("unused")
	private Escaque() {
		//Avoid unuseful escaque creation
	}
	
	public Escaque(Character letra, Integer numero){
		if(Math.abs(LETRA_MINIMA.compareTo(letra)) < 0 || Math.abs(LETRA_MAXIMA.compareTo(letra)) > 7) {
			throw new EscaqueException("En el ajedrez la letra de los escaques debe estar entre " + LETRA_MINIMA + " y " + LETRA_MAXIMA + "( recibido = " + letra + ")");
		}
		if(numero < NUMERO_MINIMO || numero > NUMERO_MAXIMO) {
			throw new EscaqueException("En el ajedrez el numero de los escaques debe estar entre " + NUMERO_MINIMO + " y " + NUMERO_MAXIMO + "( recibido = " + numero + ")");
		}
			
		this.letra = letra;
		this.numero = numero;
	}
	
	public Integer getNumero() {
		return numero;
	}
	public Character getLetra() {
		return letra;
	}
	
	public Integer getDistanciaNumero(Escaque escaque){
		if(escaque == null) {
			throw new IllegalArgumentException("No se puede medir la distancia a un escaque inexistente");
		}
		return Math.abs(escaque.getNumero() - this.numero);
	}
	
	public Integer getDistanciaLetra(Escaque escaque){
		if(escaque == null) {
			throw new IllegalArgumentException("No se puede medir la distancia a un escaque inexistente");
		}
		return Math.abs(escaque.getLetra().compareTo(this.letra));
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((letra == null) ? 0 : letra.hashCode());
		result = prime * result + ((numero == null) ? 0 : numero.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Escaque other = (Escaque) obj;
		if (letra == null) {
			if (other.letra != null)
				return false;
		} else if (!letra.equals(other.letra))
			return false;
		if (numero == null) {
			if (other.numero != null)
				return false;
		} else if (!numero.equals(other.numero))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return letra + numero.toString();
	}
}
