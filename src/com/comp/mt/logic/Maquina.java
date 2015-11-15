package com.comp.mt.logic;

import com.comp.mt.domain.Estado;
import com.comp.mt.domain.Problema;
import com.comp.mt.domain.Transicion;
import com.comp.mt.utils.CommonJson;
import com.comp.mt.utils.Utils;
import com.example.comp_compl_mt_app.R;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.TextView;

/**
 * Universidad de Panamá Facultad de Informática, Electrónica y Comunicación
 * 
 * @author ropherpanama@gmail.com Desarrollado por Rosendo Peña para la
 *         asignatura Computabilidad y Complejidad, segundo semestre año 2015 A
 *         consideración del profesor Ajax Mendoza
 * 
 *         El código de esta aplicación puede ser usado para fines de educación
 *         y/o consulta, siempre y cuando se respete esta documentación y el
 *         trabajo de su autor
 * 
 */

public class Maquina extends AsyncTask<Void, String, Boolean> {
	private Problema p;
	private TextView monitor;
	private TextView resultado;
	private Context context;
	private String loadingVar = "";

	public Maquina(Problema p, TextView monitor, TextView resultado, Context context) {
		this.p = p;
		this.monitor = monitor;
		this.resultado = resultado;
		this.context = context;
	}

	public boolean evaluarCadena() {
		Estado current = p.getInitial();
		String eval = p.getEval() + "B";
		boolean accepted = false;
		boolean continuar = true;
		int contador = 0;
		char c;

		while (continuar) {

			c = eval.charAt(contador);

			Transicion movement = findByStateAndInput(current, c);

			if (movement == null) {
				if (c == 'B') {
					if (current.isTerminal())
						accepted = true;
					break;
				} else
					break;
			}

			if (contador == 0 && movement.getDireccion().equals("L")) {
				publishProgress("El movimiento está fuera de la cinta");
				accepted = false;
				break;
			}

//			Log.i(RunActivity.class.getCanonicalName(), "Valor de caracter actual " + c);
			
			eval = Utils.changeCharAt(eval, contador, movement.getSalida().charAt(0));
			String publish = eval.replace("B", "");

			publishProgress(publish);

			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			current = CommonJson.estadosFindByLabel(context, movement.getDestino());

			if (current != null) {
				if (current.isTerminal()) {
					if (contador == (eval.length() - 1)) {
						accepted = true;
						continuar = false;
					} else {
						accepted = false;
					}
				}
			} else {
				accepted = false;
				continuar = false;
			}

			if (movement.getDireccion().equals("R"))
				contador++;
			else if (movement.getDireccion().equals("L"))
				contador--;
		}
		return accepted;
	}

	public Transicion findByStateAndInput(Estado state, char input) {
		Transicion retorno = null;

		// if destiny state doesn't exist return null, not accepted string
		if (state != null) {
			for (Transicion t : state.getTransiciones()) {
				if (t.getEntrada().equals(String.valueOf(input)))
					retorno = t;
			}
		}

		return retorno;
	}

	@Override
	protected void onPreExecute() {
		super.onPreExecute();
		resultado.setText("En espera ...");
		loadingVar = "";
	}

	@Override
	protected Boolean doInBackground(Void... params) {
		return evaluarCadena();
	}

	@Override
	protected void onPostExecute(Boolean result) {
		super.onPostExecute(result);
		if (result) {
			resultado.setText("Aceptada");
			resultado.setTextColor(context.getResources().getColor(R.color.verde));
		} else {
			resultado.setText("No Aceptada");
			resultado.setTextColor(context.getResources().getColor(R.color.rojo));
		}
	}

	@Override
	protected void onProgressUpdate(String... values) {
		super.onProgressUpdate(values);
		if (values.length > 0) {
			loadingVar = values[0];
			monitor.setText(loadingVar);
		}
	}
}
