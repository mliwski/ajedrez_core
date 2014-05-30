package com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski;


public class Escaque implements Comparable<Escaque> {
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
		if(LETRA_MINIMA.compareTo(letra) > 0 || LETRA_MAXIMA.compareTo(letra) < 0) {
			throw new IllegalArgumentException("En el ajedrez la letra de los escaques debe estar entre " + LETRA_MINIMA + " y " + LETRA_MAXIMA + "( recibido = " + letra + ")");
		}
		if(numero < NUMERO_MINIMO || numero > NUMERO_MAXIMO) {
			throw new IllegalArgumentException("En el ajedrez el numero de los escaques debe estar entre " + NUMERO_MINIMO + " y " + NUMERO_MAXIMO + "( recibido = " + numero + ")");
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
	
	public Integer getDistanciaNumero(Escaque otroEscaque){
		if(otroEscaque == null) {
			throw new IllegalArgumentException("No se puede medir la distancia a un escaque inexistente");
		}
		return this.numero - otroEscaque.getNumero();
	}
	
	public Integer getDistanciaLetra(Escaque otroEscaque){
		if(otroEscaque == null) {
			throw new IllegalArgumentException("No se puede medir la distancia a un escaque inexistente");
		}
		return this.letra.compareTo(otroEscaque.getLetra());
	}

	public int compareTo(Escaque otroEscaque) {
		Integer result = 0;
		boolean isDifferentEscaque = this.equals(otroEscaque) == false;
		
		if(isDifferentEscaque) {
			Integer distanciaLetra = this.getDistanciaLetra(otroEscaque);
			Integer normaLetra = distanciaLetra == 0 ? 0 : distanciaLetra/Math.abs(distanciaLetra);
		
			Integer distanciaNumero = this.getDistanciaNumero(otroEscaque);
			Integer normaNumero = distanciaNumero == 0 ? 0 : distanciaNumero/Math.abs(distanciaNumero);
			
			result = normaNumero + normaLetra >= 0 ? 1 : -1;
		}
		return result;
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
