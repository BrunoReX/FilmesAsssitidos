package localhost.filmesassistidos.activity;

import localhost.filmesassistidos.R;
import localhost.filmesassistidos.util.Util;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;

public class TelaListagem extends Activity {
	private Util util;
	
	private Spinner spnListar;
	private ListView lviFilmes;
	private Button btnVoltar;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tela_listagem);
		
		util = new Util(this);
		
		spnListar = (Spinner) findViewById(R.id.spnListar);
		lviFilmes = (ListView) findViewById(R.id.lviFilmes);
		btnVoltar = (Button) findViewById(R.id.btnVoltarLis);
		
		btnVoltar.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				util.voltar();
			}
		});
	}
}
