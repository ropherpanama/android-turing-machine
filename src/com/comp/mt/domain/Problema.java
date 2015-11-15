package com.comp.mt.domain;

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

public class Problema {
	private List<Estado> estados;
	private Estado initial;
	private Estado terminal;
	private String eval;

	public List<Estado> getEstados() {
		return estados;
	}

	public void setEstados(List<Estado> estados) {
		this.estados = estados;
	}

	public Estado getInitial() {
		return initial;
	}

	public void setInitial(Estado initial) {
		this.initial = initial;
	}

	public Estado getTerminal() {
		return terminal;
	}

	public void setTerminal(Estado terminal) {
		this.terminal = terminal;
	}

	public String getEval() {
		return eval;
	}

	public void setEval(String eval) {
		this.eval = eval;
	}
}
