package com.comp.mt;

import java.util.ArrayList;
import java.util.List;

import com.comp.mt.adapters.TransitionArrayAdapter;
import com.comp.mt.domain.Estado;
import com.comp.mt.domain.Transicion;
import com.comp.mt.utils.CommonJson;
import com.comp.mt.utils.Logit;
import com.comp.mt.utils.Mensajes;
import com.example.comp_compl_mt_app.R;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
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
public class TransitionsActivity extends Activity {

	private String lenguaje = "";
	private String destino;
	private String entrada;
	private String salida;
	private String direccion;
	private String estadosArray;
	private Estado e;
	private TextView textLabel;
	private ListView listTransiciones;
	private ArrayAdapter<Transicion> adapter;
	private List<Estado> serializable;
	private Logit l = Logit.getInstance();

	@SuppressWarnings("unchecked")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_transitions);
		
		ActionBar actionBar = getActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);
		
		l.setFileName("TEST_LOG");
		l.setProgramName(TransitionsActivity.class.getCanonicalName()); 
		
		Bundle bundle = getIntent().getExtras();
		lenguaje = bundle.getString("lenguaje");
		estadosArray = bundle.getString("estadosArray");
		String estado = bundle.getString("estado");
		serializable = (List<Estado>) bundle.get("serializable");
		
		//initialize data
		e = CommonJson.estadosFindByLabel(getApplicationContext(), estado);
		textLabel = (TextView) findViewById(R.id.textLabel);
		textLabel.setText(textLabel.getText() + " " + estado); 
		
		listTransiciones = (ListView) findViewById(R.id.listTransiciones);
		
		ArrayList<Transicion> adapterData = new ArrayList<Transicion>(e.getTransiciones());
		
		adapter = new TransitionArrayAdapter(getApplicationContext(), adapterData, estado);
		listTransiciones.setAdapter(adapter); 
		adapterData = null;
	}

	public void definirDestino(View v) {
		AlertDialog.Builder builder = new AlertDialog.Builder(TransitionsActivity.this);
		final String[] destinos = estadosArray.split(",");

		builder.setTitle("Estado destino").setItems(destinos, new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				setDestino(destinos[which]);
			}
		});

		AlertDialog dialog = builder.create();
		dialog.show();
	}

	public void definirEntrada(View v) {
		AlertDialog.Builder builder = new AlertDialog.Builder(TransitionsActivity.this);
		final String[] caracteres = lenguaje.split(",");

		builder.setTitle("Caracter de entrada").setItems(caracteres, new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				setEntrada(caracteres[which]);
			}
		});

		AlertDialog dialog = builder.create();
		dialog.show();
	}
	
	public void definirReemplazo(View v) {
		AlertDialog.Builder builder = new AlertDialog.Builder(TransitionsActivity.this);
		final String[] caracteres = lenguaje.split(",");

		builder.setTitle("Caracter de reemplazo").setItems(caracteres, new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				setSalida(caracteres[which]);
			}
		});

		AlertDialog dialog = builder.create();
		dialog.show();
	}

	public void definirDireccion(View v) {
		final String[] direcciones = getResources().getStringArray(R.array.direcciones);
		AlertDialog.Builder builder = new AlertDialog.Builder(TransitionsActivity.this);
		builder.setTitle("Dirección").setItems(direcciones, new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				setDireccion(direcciones[which]);
			}
		});

		AlertDialog dialog = builder.create();
		dialog.show();
	}

	public void addMore(View v) {
		if(destino == null || entrada == null || direccion == null || salida == null) {
			Mensajes.showDialogMessage(TransitionsActivity.this, "Datos incompletos", "Todos los datos son requeridos");
		}else {
			l.write("BUSCANDO ESTADO DESTINO DE LA TRANSICION");
			//i don't want circular reference, so i save the destiny state without their transitions
			Estado estadoDestino = CommonJson.estadosFindByLabel(TransitionsActivity.this, destino);
			e.getTransiciones().add(new Transicion(estadoDestino.getLabel(), entrada, salida, direccion));
			//adapter reload
			adapter.clear();
			adapter.addAll(e.getTransiciones()); 
			listTransiciones.setAdapter(adapter);
			destino = entrada = direccion = null;
			
			//update the data in the json
			int index = -1;
			
			for(int i = 0; i < serializable.size(); i++) {
				if(e.getLabel().equals(serializable.get(i).getLabel())) {
					index = i;
					break;
				}
			}
			//first, remove the modified item from the original structure
			serializable.remove(index);

			//adding the new modified item
			serializable.add(e);
			l.write("ADDING NEW ITEM MODIFIED");
			CommonJson.parseObjectToJson(getApplicationContext(), serializable, "estados");
		}
	}

	public String getDestino() {
		return destino;
	}

	public void setDestino(String destino) {
		this.destino = destino;
	}

	public String getEntrada() {
		return entrada;
	}

	public void setEntrada(String entrada) {
		this.entrada = entrada;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getSalida() {
		return salida;
	}

	public void setSalida(String salida) {
		this.salida = salida;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		if (id == android.R.id.home) {
			onBackPressed();
		}
		return super.onOptionsItemSelected(item);
	}
}
