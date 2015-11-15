package com.comp.mt.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import com.comp.mt.domain.Estado;
import com.comp.mt.domain.Problema;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import android.content.Context;
import android.os.Environment;

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

public class CommonJson {

	public static String objectToJsonString(Object obj) {
		Logit l = Logit.getInstance();
		l.setFileName("TEST_LOG");
		l.setProgramName(CommonJson.class.getCanonicalName());
		try {
			Gson gson = new GsonBuilder().setPrettyPrinting().setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ").create();
			return gson.toJson(obj);
		}catch(Exception e) {
			l.error(Logit.stringStackTrace(e));
			return null;
		}
	}
	
	public static void parseObjectToJson(Context context, Object obj, String name) {
		try {
			Logit l = Logit.getInstance();
			l.setFileName("TEST_LOG");
			l.setProgramName(CommonJson.class.getCanonicalName());
			
			Gson gson = new GsonBuilder().setPrettyPrinting().setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ").create();
			StringBuilder bigStr = new StringBuilder(gson.toJson(obj));
			FileWriter Filewriter = new FileWriter(context.getApplicationInfo().dataDir + "/" + name + ".json");
			Filewriter.write(bigStr.toString());
			Filewriter.close();
			l.write("PARSE TO JSON " + bigStr.toString());
			l = null;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static List<Estado> estadosFindAll(Context context, String fileName, Estado object) {
		Logit l = Logit.getInstance();
		try {
			l.setFileName("TEST_LOG");
			l.setProgramName(CommonJson.class.getCanonicalName());
			
			Gson gson = new GsonBuilder().setPrettyPrinting().setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ").create();
			Type listType = new TypeToken<ArrayList<Estado>>() {
			}.getType();
			BufferedReader br = new BufferedReader(
					new FileReader(context.getApplicationInfo().dataDir + "/" + fileName + ".json"));
			return gson.fromJson(br, listType);
		} catch (Exception e) {
			l.error(Logit.stringStackTrace(e));
			return null;
		}
	}

	public static Estado estadosFindByLabel(Context context, String label) {
		Logit l = Logit.getInstance();
		try {
			l.setFileName("TEST_LOG");
			l.setProgramName(CommonJson.class.getCanonicalName());
			
			Estado retorno = null;
			List<Estado> list = estadosFindAll(context, "estados", new Estado());

			for (Estado e : list) {
				if (label.equals(e.getLabel())) {
					l.write("FINDED " + e.toString());
					return e;
				}
			}
			
			list = null;
			return retorno;
		} catch (Exception e) {
			l.error(Logit.stringStackTrace(e));
			return null;
		}
	}
	
	public static Estado getInitialState(Context context) {
		Logit l = Logit.getInstance();
		try {
			l.setFileName("TEST_LOG");
			l.setProgramName(CommonJson.class.getCanonicalName());
			
			Estado retorno = null;
			List<Estado> list = estadosFindAll(context, "estados", new Estado());

			for (Estado e : list) {
				if (e.isInitial()) {
					l.write("FINDED " + e.toString());
					return e;
				}
			}
			
			list = null;
			return retorno;
		}catch(Exception e) {
			l.write(Logit.stringStackTrace(e));
			return null;
		}
	}
	
	public static Estado getFinalState(Context context) {
		Logit l = Logit.getInstance();
		try {
			l.setFileName("TEST_LOG");
			l.setProgramName(CommonJson.class.getCanonicalName());
			
			Estado retorno = null;
			List<Estado> list = estadosFindAll(context, "estados", new Estado());

			for (Estado e : list) {
				if (e.isTerminal()) {
					l.write("FINDED " + e.toString());
					return e;
				}
			}
			
			list = null;
			return retorno;
		}catch(Exception e) {
			l.write(Logit.stringStackTrace(e));
			return null;
		}
	}
	
	public static Object openProblem(String file) {
		try {
			Gson gson = new GsonBuilder().setPrettyPrinting().setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ").create();
			BufferedReader br = new BufferedReader(new FileReader(Environment.getExternalStorageDirectory().getAbsolutePath() + "/turingApp/" + file));
			return gson.fromJson(br, Problema.class);
		}catch(Exception e) {
			e.printStackTrace(); return null;
		}
	}
}
