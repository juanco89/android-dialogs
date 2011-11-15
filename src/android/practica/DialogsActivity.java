package android.practica;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class DialogsActivity extends Activity {
    
	//Definicion de identificadores para los dialogs
	private static final int ALERT_DIALOG = 0;
	private static final int PROGRESS_DIALOG = 1;
	private static final int CUSTOM_DIALOG = 2;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        // Modificando elementos de la vista
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
    }
    
    /**
     * Creación de diálogos para la clase DialogsActivity
     */
    @Override
    protected Dialog onCreateDialog(int id) {
    	Dialog dialog = null;
    	try{
    	switch(id){
    	case ALERT_DIALOG:
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
    		
    		break;
    		default:
    			dialog = null;    			
    	}
    	}catch(Exception e){
    		Log.v("DIALOG_CREATION", e.getMessage());
    	}
    	return dialog;
    }
    
}