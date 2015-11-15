package com.comp.mt.utils;

import com.example.comp_compl_mt_app.R;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.widget.Toast;

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

public final class Mensajes {
	private static AlertDialog alertDialog;

	public static void showDialogMessage(Context ctx, String title, String message) {
		alertDialog = new AlertDialog.Builder(ctx).create();
		alertDialog.setTitle(title);
		alertDialog.setMessage(message);
		alertDialog.setButton(DialogInterface.BUTTON_POSITIVE, "Aceptar", new DialogInterface.OnClickListener() {

			public void onClick(DialogInterface dialog, int which) {
				alertDialog.dismiss();
			}
		});
		alertDialog.setIcon(R.drawable.ic_app);
		alertDialog.show();
	}

	public static void toast(Context ctx, String message) {
		Toast.makeText(ctx, message, Toast.LENGTH_SHORT).show();
	}
}
