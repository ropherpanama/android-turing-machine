package com.comp.mt.domain;

import java.util.List;

/**
 * Universidad de Panamá
 * Facultad de Informática, Electrónica y Comunicación
 * 
 * @author ropherpanama@gmail.com
 * Desarrollado por Rosendo Peña para la asignatura
 * Computabilidad y Complejidad, segundo semestre año 2015
 * A consideración del profesor Ajax Mendoza
 * 
 * El código de esta aplicación puede ser usado para fines
 * de educación y/o consulta, siempre y cuando se respete
 * esta documentación y el trabajo de su autor
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
