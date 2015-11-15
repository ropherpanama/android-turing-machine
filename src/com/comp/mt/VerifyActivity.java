package com.comp.mt;

import java.util.List;

import com.comp.mt.domain.Estado;
import com.comp.mt.domain.Transicion;
import com.comp.mt.utils.Mensajes;
import com.example.comp_compl_mt_app.R;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
//import android.widget.TableLayout;
//import android.widget.TableRow;
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

public class VerifyActivity extends Activity {

	private TextView lenguaje;
	private TextView estados;
	private TextView estadoInicial;
	private TextView estadoFinal;
	private TextView transiciones;
	boolean hasInitial = false;
	boolean hasFinal = false;
	boolean hasTransition = false;

	@SuppressWarnings("unchecked")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_verify);

		ActionBar actionBar = getActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);

		lenguaje = (TextView) findViewById(R.id.lenguaje);
		estados = (TextView) findViewById(R.id.estados);
		estadoInicial = (TextView) findViewById(R.id.estadoInicial);
		estadoFinal = (TextView) findViewById(R.id.estadoFinal);
		transiciones = (TextView) findViewById(R.id.transiciones);

		String lenguajeExtra = getIntent().getExtras().getString("lenguaje");
		List<Estado> data = (List<Estado>) getIntent().getExtras().get("serializable");

		lenguaje.setText(lenguajeExtra);
		estados.setText(data.toString());

		StringBuilder strb = new StringBuilder();

		for (Estado e : data) {
			if (e.isInitial()) {
				estadoInicial.setText(e.getLabel());
				hasInitial = true;
			}

			if (e.isTerminal()) {
				estadoFinal.setText(e.getLabel());
				hasFinal = true;
			}

			if (e.getTransiciones().size() > 0) {
				hasTransition = true;
				for (Transicion t : e.getTransiciones()) {
//					strb.append(e.getLabel()).append(",").append(t.getEntrada()).append(", ").append(t.getSalida()).append(", ").append(t.getDestino()).append(", ").append(t.getDireccion()).append("\n");
					strb.append("$(").append(e.getLabel()).append(",").append(t.getEntrada()).append(") = (").append(t.getDestino()).append(",").append(t.getSalida())
					.append(",").append(t.getDireccion()).append(")").append("\n"); 
				}
			}
		}

		transiciones.setText(strb);
	}

	public void probarConfiguracion() {
		Intent i = new Intent(VerifyActivity.this, RunActivity.class);
		i.putExtra("file", "any");
		startActivity(i);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.verify, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		if (id == android.R.id.home) {
			onBackPressed();
		}

		if (id == R.id.action_run) {
			if (hasInitial && hasFinal && hasTransition) {
				probarConfiguracion();
			} else {
				Mensajes.showDialogMessage(VerifyActivity.this, "Configuración incompleta",
						"Aún no puedes ejecutar tu configuración. Asegúrate de configurar todos los datos requeridos");
			}
		}

		return super.onOptionsItemSelected(item);
	}
}
