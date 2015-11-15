package com.comp.mt;

import java.util.ArrayList;
import java.util.List;

import com.comp.mt.adapters.EstadosArrayAdapter;
import com.comp.mt.domain.Estado;
import com.comp.mt.domain.Transicion;
import com.comp.mt.utils.CommonJson;
import com.comp.mt.utils.Logit;
import com.comp.mt.utils.Mensajes;
import com.example.comp_compl_mt_app.R;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

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

public class EstadosActivity extends Activity {
	
	private List<Estado> estados;
	private ListView listViewEstados;
	private ArrayAdapter<Estado> adapter;
	private String lenguaje = "";
	private final Context context = this;
	private final Logit l = Logit.getInstance();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_estados);
		
		ActionBar actionBar = getActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);
		
		l.setFileName("TEST_LOG");
		l.setProgramName(EstadosActivity.class.getCanonicalName());
		
		listViewEstados = (ListView) findViewById(R.id.listEstados);
		
		CommonJson.parseObjectToJson(getApplicationContext(), "nada", "estados");
		
		//this behavior lets set the initial and terminal states
		listViewEstados.setOnItemLongClickListener(new OnItemLongClickListener() {
			public boolean onItemLongClick(AdapterView<?> arg0, View arg1, final int pos, long id) {
				
				AlertDialog.Builder builder = new AlertDialog.Builder(context);
				final String[] types = getResources().getStringArray(R.array.estados_tipos);
				builder.setTitle("Estados especiales").setItems(types, new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						
						for(Estado e : estados) {
							if(e.getLabel().equals(listViewEstados.getItemAtPosition(pos).toString())) {
								if(types[which].equals("Terminal")) {
									e.setTerminal(true);
									e.setInitial(false);
									Mensajes.toast(EstadosActivity.this, e.getLabel() + " estado terminal");
								}else if(types[which].equals("Inicial")) {
									e.setInitial(true);
									e.setTerminal(false);
									Mensajes.toast(EstadosActivity.this, e.getLabel() + " estado inicial");
								}
							}
						}
						
						l.write("SAVE BY SET SPECIAL STATE");
						CommonJson.parseObjectToJson(getApplicationContext(), estados, "estados"); 
					}
				});
				AlertDialog dialog = builder.create();
				dialog.show();
				return true;
			}
		}); 
		
		listViewEstados.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				String estadosExtra = "";
				
				l.write("RECHARGING ESTADOS FROM JSON AND SENDING");
				estados = CommonJson.estadosFindAll(getApplicationContext(), "estados", new Estado());
				
				for(int i = 0; i < estados.size(); i++) {
					if(i == estados.size() - 1)
						estadosExtra += estados.get(i).getLabel();
					else
						estadosExtra += estados.get(i).getLabel() + ",";
				}
				
				ArrayList<Estado> serializable = new ArrayList<Estado>();
				
				for(Estado e : estados) {
					serializable.add(e);
				}
				
				Intent i = new Intent(EstadosActivity.this, TransitionsActivity.class);
				i.putExtra("lenguaje", lenguaje);
				i.putExtra("estado", listViewEstados.getItemAtPosition(position).toString());
				i.putExtra("serializable", serializable);
				i.putExtra("estadosArray", estadosExtra);
				startActivity(i);
			}
		});
		
		Bundle b = getIntent().getExtras();
		int cantidad = b.getInt("estados");
		String prefijo = b.getString("prefijo");
		lenguaje = b.getString("lenguaje");
		lenguaje += ",B";//agrego el BLANK
	
		estados = new ArrayList<Estado>();
		
		for(int i = 0; i < cantidad; i++) {
			estados.add(new Estado(prefijo + i, false, false, new ArrayList<Transicion>()));
		}
		
		CommonJson.parseObjectToJson(getApplicationContext(), estados, "estados");
		adapter = new EstadosArrayAdapter(this, estados);
		listViewEstados.setAdapter(adapter); 
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.estados, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		
		if (id == R.id.action_settings) {
			List<Estado> data = CommonJson.estadosFindAll(getApplicationContext(), "estados", new Estado()); 
			
			ArrayList<Estado> serializable = new ArrayList<Estado>();
			
			for(Estado e : data) {
				serializable.add(e);
			}
			
			data = null;
			Intent i = new Intent(EstadosActivity.this, VerifyActivity.class);
			i.putExtra("serializable", serializable);
			i.putExtra("lenguaje", lenguaje);
//			i.putExtra("reemplazo", reemplazo);
			startActivity(i);
			return true;
		}
		
		if (id == android.R.id.home) {
			onBackPressed();
		}
		
		return super.onOptionsItemSelected(item);
	}
}
