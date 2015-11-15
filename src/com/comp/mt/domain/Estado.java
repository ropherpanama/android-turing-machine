package com.comp.mt.domain;

import java.io.Serializable;
import java.util.List;

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

public class Estado implements Serializable{
	private static final long serialVersionUID = 1L;
	private String label;
	private boolean terminal;
	private boolean initial;
	private List<Transicion> transiciones;

	public Estado(String label, boolean terminal, boolean initial, List<Transicion> transiciones) {
		super();
		this.label = label;
		this.terminal = terminal;
		this.initial = initial;
		this.transiciones = transiciones;
	}

	public Estado() {
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public boolean isTerminal() {
		return terminal;
	}

	public void setTerminal(boolean terminal) {
		this.terminal = terminal;
	}

	public boolean isInitial() {
		return initial;
	}

	public void setInitial(boolean initial) {
		this.initial = initial;
	}

	public List<Transicion> getTransiciones() {
		return transiciones;
	}

	public void setTransiciones(List<Transicion> transiciones) {
		this.transiciones = transiciones;
	}
	
	@Override
	public String toString() {
		return getLabel();
	}
	
	public String stringRepr() {
		return "Etiqueta : " + getLabel() + "\n" + 
				"Final : " + isTerminal() + "\n" + 
				"Inicial : " + isInitial() + "\n" + 
				"Transiciones: " + getTransiciones().toString();
	}
}
