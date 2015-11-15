package com.comp.mt.domain;

import java.io.Serializable;

/**
 * Universidad de Panam�
 * Facultad de Inform�tica, Electr�nica y Comunicaci�n
 * 
 * @author ropherpanama@gmail.com
 * Desarrollado por Rosendo Pe�a para la asignatura
 * Computabilidad y Complejidad, segundo semestre a�o 2015
 * A consideraci�n del profesor Ajax Mendoza
 * 
 * El c�digo de esta aplicaci�n puede ser usado para fines
 * de educaci�n y/o consulta, siempre y cuando se respete
 * esta documentaci�n y el trabajo de su autor
 * 
 */

public class Transicion implements Serializable {
	private static final long serialVersionUID = 1L;
	private String destino;
	private String entrada;
	private String salida;
	private String direccion;

	public Transicion(String destino, String entrada, String salida, String direccion) {
		super();
		this.destino = destino;
		this.entrada = entrada;
		this.salida = salida;
		this.direccion = direccion;
	}

	public String getDestino() {
		return destino;
	}

	public void setDestino(String destino) {
		this.destino = destino;
	}

	public String getEntrada() {
		return entrada;
	}

	public void setEntrada(String entrada) {
		this.entrada = entrada;
	}

	public String getSalida() {
		return salida;
	}

	public void setSalida(String salida) {
		this.salida = salida;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	@Override
	public String toString() {
		return "{" + getEntrada() + " | " + getSalida() + " | " + getDireccion() + "}";
	}
}
