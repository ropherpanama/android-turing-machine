package com.comp.mt.utils;

import java.io.File;
import java.io.PrintStream;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import android.os.Environment;
import android.os.SystemClock;

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

public class Logit {
	private PrintStream printStr;
	private static String fileName = "Log";
	private String programName = "";
	private String message = "";
	private static Logit log;
	private GregorianCalendar g = new GregorianCalendar();
	private Date date = new Date();

	public static Logit getInstance() {
		if (log == null)
			log = new Logit();
		return log;
	}

	public static Logit getInstance(String fileName) {
		if (log == null)
			log = new Logit();
		log.setFileName(fileName);
		return log;
	}

	private PrintStream createLogFile() {
		Calendar calendar = Calendar.getInstance();
		g.setTime(date);
		String name = fileName + "_" + calendar.get(Calendar.YEAR) + (calendar.get(Calendar.MONTH) + 1 < 10 ? "0" : "")
				+ (calendar.get(Calendar.MONTH) + 1) + (calendar.get(Calendar.DATE) < 10 ? "0" : "")
				+ calendar.get(Calendar.DATE) + ".log";
		File file = new File(Environment.getExternalStorageDirectory(), name);
		FileOutputStream out;
		PrintStream ps = null;

		try {
			out = new FileOutputStream(file, true);
			ps = new PrintStream(out);
			printStr = ps;
		} catch (Exception x) {
			x.printStackTrace();
		}
		return ps;
	}

	public String write(String text) {
		message = text;

		if (printStr == null)
			printStr = createLogFile();
		printStr.println(new Date() + " " + SystemClock.currentThreadTimeMillis() + " -- " + programName
				+ " -- [MSG] : " + message);
		printStr.flush();
		return message;
	}

	public String error(String text) {
		message = text;

		if (printStr == null)
			printStr = createLogFile();
		printStr.println(new Date() + " " + SystemClock.currentThreadTimeMillis() + " -- " + programName
				+ " -- [ERR] : " + message);
		printStr.flush();
		return message;
	}

	public static String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		Logit.fileName = fileName;
	}

	public String getProgramName() {
		return programName;
	}

	public void setProgramName(String programName) {
		this.programName = programName;
	}

	public static String stringStackTrace(Exception exception) {
		Writer writer = new StringWriter();
		PrintWriter printWriter = new PrintWriter(writer);
		exception.printStackTrace(printWriter);
		return writer.toString();
	}
}
