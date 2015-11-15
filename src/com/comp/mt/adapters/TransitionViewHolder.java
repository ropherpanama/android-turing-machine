package com.comp.mt.adapters;

import android.widget.TextView;

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

public class TransitionViewHolder {
	private TextView textEstado;
	private TextView textDestino;
	private TextView textEntrada;
	private TextView textSalida;
	private TextView textDireccion;

	public TransitionViewHolder() {
	}

	public TransitionViewHolder(TextView textEstado, TextView textDestino, TextView textEntrada, TextView textSalida, TextView textDireccion) {
		super();
		this.textEstado = textEstado;
		this.textDestino = textDestino;
		this.textEntrada = textEntrada;
		this.textSalida = textSalida;
		this.textDireccion = textDireccion;
	}

	public TextView getTextEstado() {
		return textEstado;
	}

	public void setTextEstado(TextView textEstado) {
		this.textEstado = textEstado;
	}

	public TextView getTextEntrada() {
		return textEntrada;
	}

	public void setTextEntrada(TextView textEntrada) {
		this.textEntrada = textEntrada;
	}

	public TextView getTextDestino() {
		return textDestino;
	}

	public void setTextDestino(TextView textDestino) {
		this.textDestino = textDestino;
	}

	public TextView getTextSalida() {
		return textSalida;
	}

	public void setTextSalida(TextView textSalida) {
		this.textSalida = textSalida;
	}

	public TextView getTextDireccion() {
		return textDireccion;
	}

	public void setTextDireccion(TextView textDireccion) {
		this.textDireccion = textDireccion;
	}
}
