package com.comp.mt;

import java.io.File;
import java.io.FileWriter;
import java.util.List;

import com.comp.mt.domain.Estado;
import com.comp.mt.domain.Problema;
import com.comp.mt.logic.Maquina;
import com.comp.mt.utils.CommonJson;
import com.comp.mt.utils.Logit;
import com.comp.mt.utils.Mensajes;
import com.example.comp_compl_mt_app.R;

import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

/**
 * Universidad de Panamá Facultad de Informática, Electrónica y Comunicación
 * 
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

public class RunActivity extends Activity {

	private Logit l = Logit.getInstance();
	private TextView entrada;
	private TextView monitor;
	private TextView resultado;
	private String openFile;
	private String cadena;
	private Problema problema;
	private boolean letSave = false;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_run);
		
		ActionBar actionBar = getActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);
		
		l.setFileName("TEST_ERROR");
		l.setProgramName(RunActivity.class.getCanonicalName());
		entrada = (TextView) findViewById(R.id.entrada);
		monitor = (TextView) findViewById(R.id.monitor);
		resultado = (TextView) findViewById(R.id.resultado);

		openFile = getIntent().getExtras().getString("file");

		if (!openFile.equals("any")) {
			problema = (Problema) CommonJson.openProblem(openFile);
			Mensajes.toast(RunActivity.this, "Problema cargado");
		} else {
			initData();
			letSave = true;
		}
	}

	public void evaluarCadena(View v) {
		try {
			cadena = entrada.getText().toString().trim();

			if (cadena.length() <= 0)
				Mensajes.showDialogMessage(RunActivity.this, "Cadena inválida",
						"No ha ingresado una cadena para validar");
			else {
				if (letSave) { initData(); }
				
				problema.setEval(cadena);
				// get the machine instance
				Maquina m = new Maquina(problema, monitor, resultado, getApplicationContext());
				m.execute();
			}
		} catch (Exception e) {
			l.write(Logit.stringStackTrace(e));
		}
	}

	public void initData() {
		List<Estado> estados = CommonJson.estadosFindAll(getApplicationContext(), "estados", new Estado());
		// Set the problem variables
		problema = new Problema();
		problema.setEstados(estados);
		problema.setEval(cadena);
		problema.setInitial(CommonJson.getInitialState(getApplicationContext()));
		problema.setTerminal(CommonJson.getFinalState(getApplicationContext()));
		estados = null;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.run, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		
		if (id == android.R.id.home) {
			onBackPressed();
		}
		
		if (id == R.id.action_save) {
			try {
				if (letSave) {
					boolean saved = false;
					File f = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/turingApp");

					if (f.exists()) {
						final String[] archivos = f.list();
						if (archivos != null) {
							FileWriter Filewriter = new FileWriter(
									f.getAbsoluteFile() + "/problem_test_" + (archivos.length + 1) + ".ttm");
							Filewriter.write(CommonJson.objectToJsonString(problema));
							Filewriter.close();
							saved = true;
						}
					} else {
						if (f.mkdirs()) {
							FileWriter Filewriter = new FileWriter(f.getAbsoluteFile() + "/problem_test_1.ttm");
							Filewriter.write(CommonJson.objectToJsonString(problema));
							Filewriter.close();
							saved = true;
						}
					}

					if (saved)
						Mensajes.showDialogMessage(this, "Guardado", "Configuración guardada");
					else
						Mensajes.showDialogMessage(this, "Woops!", "No se pudo guardar el archivo");
				} else
					Mensajes.showDialogMessage(this, "Hey!", "Este problema ya esta guardado");

			} catch (Exception e) {
				l.error(Logit.stringStackTrace(e));
			}
		}
		return true;
	}
}
