package com.comp.mt.adapters;

import java.util.List;

import com.comp.mt.domain.Estado;
import com.example.comp_compl_mt_app.R;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

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

public class EstadosArrayAdapter extends ArrayAdapter<Estado> {
	private LayoutInflater inflater;
	@SuppressWarnings("unused")
	private Context context;

	public EstadosArrayAdapter(Context context, List<Estado> estados) {
		super(context, R.layout.estado_item, estados);
		this.context = context;
		inflater = LayoutInflater.from(context);
	}

	@SuppressLint({ "DefaultLocale", "InflateParams" })
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		Estado p = (Estado) this.getItem(position);
		TextView textView;

		if (convertView == null) {
			convertView = inflater.inflate(R.layout.estado_item, null);
			textView = (TextView) convertView.findViewById(R.id.nombre_estado);
			convertView.setTag(new EstadosViewHolder(textView));
		} else {
			EstadosViewHolder viewHolder = (EstadosViewHolder) convertView.getTag();
			textView = viewHolder.getTextView();
		}

		textView.setText("Estado " + p.getLabel());

		return convertView;
	}
}
