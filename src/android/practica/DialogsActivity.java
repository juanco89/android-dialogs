package android.practica;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


/**
 * Creación de Diálogos básicos para una actividad en android 2.2.
 * 
 * Diálogos encontrados en esta clase:
 * *AlertDialog (Sencillo, Lista, RadioButton y CheckBox)
 * *ProgressDialog
 * *Diálogo personalizado
 * 
 * @author Juan Carlos Orozco
 * 
 */
public class DialogsActivity extends Activity {
    
	//Definicion de identificadores para los dialogs
	private static final int ALERT_DIALOG = 0;
	private static final int PROGRESS_DIALOG = 1;
	private static final int CUSTOM_DIALOG = 2;
	private static final int ALERT_LIST = 3;
	private static final int ALERT_RADIO = 4;
	private static final int ALERT_CHECK = 5;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        // Modificando elementos de la vista
        // Agregando eventos a los botones que lanzan los Dialogs
        Button progress = (Button) findViewById(R.id.progress_d);
        progress.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				DialogsActivity.this.showDialog(PROGRESS_DIALOG);
			}
		});
        Button alert = (Button) findViewById(R.id.alert_d);
        alert.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				DialogsActivity.this.showDialog(ALERT_DIALOG);
			}
		});
        Button custom = (Button)findViewById(R.id.custom_d);
        custom.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				DialogsActivity.this.showDialog(CUSTOM_DIALOG);
			}
		});
        Button alert_list = (Button)findViewById(R.id.alert_list_d);
        alert_list.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				DialogsActivity.this.showDialog(ALERT_LIST);
			}
		});
        Button alert_radio = (Button)findViewById(R.id.alert_radio_d);
        alert_radio.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				DialogsActivity.this.showDialog(ALERT_RADIO);
			}
		});
        Button alert_check = (Button)findViewById(R.id.alert_check_d);
        alert_check.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				DialogsActivity.this.showDialog(ALERT_CHECK);
			}
		});
    }
    
    /**
     * Redefinición del método onCreateDialog en el cual se crearán
     * cada uno de los dialogs dependiendo del id pasado en el momento
     * de llamar showDialog(int id) de la actividad.
     * Se retorna el objetod dialog, el sistema se encarga de llamar el
     * método dialog.show() y de establecer a esta actividad como la clase
     * padre del diálogo lanzado.
     */
    @Override
    protected Dialog onCreateDialog(int id) {
    	final String[] lista = {"Primero", "Segundo", "Tercero"};
    	final boolean[] checks = {false, true, true};
    	Dialog dialog = null;
    	
    	switch(id){
    	case ALERT_DIALOG:
    		//Alert Dialog sencillo
    		AlertDialog.Builder builder = new AlertDialog.Builder(this);
    		builder.setTitle("Alert Dialog")
    			.setMessage("Cerrar este diálogo ?")
    			.setCancelable(false)
    			.setPositiveButton("Si", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						dialog.cancel();
					}
				});
    		dialog = builder.create();
    		break;
    	case ALERT_LIST:
    		//Otras Opciones con Alert Dialog
    		AlertDialog.Builder build_list = new AlertDialog.Builder(this);
    		build_list.setTitle("Alert List");
    		build_list.setItems(lista , new DialogInterface.OnClickListener() {
				
				public void onClick(DialogInterface dialog, int which) {
					Toast.makeText(getApplicationContext(), lista[which], Toast.LENGTH_SHORT).show();
				}
			});
    		dialog = build_list.create();
    		break;
    	case ALERT_RADIO:
    		AlertDialog.Builder build_radio = new AlertDialog.Builder(this);
    		build_radio.setTitle("Alert RadioButton");
    		build_radio.setSingleChoiceItems(lista, -1, new DialogInterface.OnClickListener() {
    			//Cual será el item chequeado por defecto, -1 ninguno.
    			public void onClick(DialogInterface dialog, int which) {
					Toast.makeText(getApplicationContext(), "Radio: " + lista[which], Toast.LENGTH_SHORT).show();
					dialog.cancel();
				}
    		});
    		dialog = build_radio.create();
    		break;
    	case ALERT_CHECK:
    		AlertDialog.Builder build_check = new AlertDialog.Builder(this);
    		build_check.setTitle("Alert CheckBox");
    		build_check.setMultiChoiceItems(lista, checks , new DialogInterface.OnMultiChoiceClickListener() {
				
				public void onClick(DialogInterface dialog, int which, boolean isChecked) {
					Toast.makeText(getApplicationContext(), "Check: " + lista[which], Toast.LENGTH_SHORT).show();
					checks[which] = isChecked;
					dialog.cancel();
				}
    		});
    		dialog = build_check.create();
    		break;
    	case PROGRESS_DIALOG:
    		ProgressDialog progress = new ProgressDialog(this);
    		progress.setTitle("Progress Dialog");
    		progress.setMessage("Cargando...");
    		progress.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
    		progress.setMax(10);
    		progress.setProgress(5);
    		//progress.setCancelable(false);
    		dialog = progress;
    		break;
    	case CUSTOM_DIALOG:
    		//Clase Dialog con un Layout personalizado
    		dialog = new Dialog(this);
    		dialog.setContentView(R.layout.custom_dialog);
    		dialog.setTitle("Dialog Personalizado");
    		
    		TextView texto = (TextView) dialog.findViewById(R.id.texto);
    		texto.setText("Mensaje personalizado");
    		break;
    		default:
    			dialog = null;    			
    	}
    	return dialog;
    }
    
}