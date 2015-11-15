package com.comp.mt;

import java.io.File;

import com.comp.mt.utils.Mensajes;
import com.example.comp_compl_mt_app.R;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.NumberPicker;

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

public class MainActivity extends Activity {

	private NumberPicker picker;
	private EditText editText;
	private EditText editLenguje;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		picker = (NumberPicker) findViewById(R.id.numberPicker1);
		editText = (EditText) findViewById(R.id.editPrefijo);
		editLenguje = (EditText) findViewById(R.id.editLenguaje);

		picker.setMaxValue(10);
		picker.setMinValue(2);
	}

	public void nextScreen(View v) {
		try {
			String prefijo = editText.getText().toString().trim();
			String lenguaje = editLenguje.getText().toString().trim();
			String languagePattern = ".,.*[^,]";
			int cantidad = picker.getValue();
			
			if (prefijo.length() <= 0 || lenguaje.length() <= 0) {
				Mensajes.showDialogMessage(this, "Datos requeridos", "Ingrese el lenguaje y el prefijo de los estados");
			} else {
				if (lenguaje.length() > 0) {
					Log.i(MainActivity.class.getCanonicalName(), "Lenguaje " + lenguaje);
					if (lenguaje.matches(languagePattern)) {
						Bundle mBundle = new Bundle();
						mBundle.putString("prefijo", prefijo);
						mBundle.putInt("estados", cantidad);
						mBundle.putString("lenguaje", lenguaje);

						Intent npIntent = new Intent(MainActivity.this, EstadosActivity.class);
						npIntent.putExtras(mBundle);
						startActivity(npIntent);
					} else {
						Mensajes.showDialogMessage(this, "Datos requeridos",
								"Ingrese los caracteres del lenguaje separados por coma (,)");
					}
				}
			}
		} catch (Exception e) {
			Mensajes.showDialogMessage(this, "Error", "Interno " + e.getLocalizedMessage());
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		if (id == R.id.action_open) {
			
			File f = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/turingApp");
			final String[] archivos = f.list();
			
			if(archivos == null || archivos.length <= 0) {
				Mensajes.showDialogMessage(this, "Hey!", "No has guardado ninguna configuración");
			}else {
				AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
				builder.setTitle("Abrir configuración").setItems(archivos, new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						Intent i = new Intent(MainActivity.this, RunActivity.class);
						i.putExtra("file", archivos[which]);
						startActivity(i);
					}
				});

				AlertDialog dialog = builder.create();
				dialog.show();
			}
			
			return true;
		}
		
		if (id == R.id.action_about) {
			Mensajes.showDialogMessage(this, "Sobre mí", "Desarrollado por Rosendo Peña\nPara Comp. y Complejidad de Algoritmos 2015.\nUniversidad de Panamá\n@ropherpanama\nropherpanama@gmail.com");
		}
		
		return super.onOptionsItemSelected(item);
	}
}
