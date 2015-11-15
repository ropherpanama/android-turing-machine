package com.comp.mt.adapters;

import java.util.List;

import com.comp.mt.domain.Transicion;
import com.example.comp_compl_mt_app.R;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
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

public class TransitionArrayAdapter extends ArrayAdapter<Transicion>{
	private LayoutInflater inflater;
	@SuppressWarnings("unused")
	private Context context;
	private String estado;

	public TransitionArrayAdapter(Context context, List<Transicion> transiciones, String estado) {
		super(context, R.layout.transition_item, transiciones);
		this.context = context;
		inflater = LayoutInflater.from(context);
		this.estado = estado;
	}

	@SuppressLint({ "DefaultLocale", "InflateParams" })
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		Transicion t = (Transicion) this.getItem(position);
		TextView textEstado;
		TextView textDestino;
		TextView textEntrada;
		TextView textSalida;
		TextView textDireccion;

		if (convertView == null) {
			convertView = inflater.inflate(R.layout.transition_item, null);
			textEstado = (TextView) convertView.findViewById(R.id.txtEstado);
			textDestino = (TextView) convertView.findViewById(R.id.txtEstadoDestino);
			textEntrada = (TextView) convertView.findViewById(R.id.txtEntrada);
			textSalida = (TextView) convertView.findViewById(R.id.txtSalida);
			textDireccion = (TextView) convertView.findViewById(R.id.txtDireccion);
			
			convertView.setTag(new TransitionViewHolder(textEstado, textDestino, textEntrada, textSalida, textDireccion));
		} else {
			TransitionViewHolder viewHolder = (TransitionViewHolder) convertView.getTag();
			textDestino = viewHolder.getTextDestino();
			textDireccion = viewHolder.getTextDireccion();
			textEstado = viewHolder.getTextEstado();
			textEntrada = viewHolder.getTextEntrada();
			textSalida = viewHolder.getTextSalida();
		}

		textDestino.setText(t.getDestino());
		textDireccion.setText(t.getDireccion());
		textEntrada.setText(t.getEntrada());
		textSalida.setText(t.getSalida());
		textEstado.setText(estado); 

		return convertView;
	}
}
