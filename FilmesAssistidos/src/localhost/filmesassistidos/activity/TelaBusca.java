package localhost.filmesassistidos.activity;

import localhost.filmesassistidos.R;
import localhost.filmesassistidos.util.Util;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class TelaBusca extends Activity {
	private Util util;
	
	private Spinner spnTipoBusca;
	private EditText edtTermoBusca;
	private Button btnBuscar;
	private Button btnVoltar;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tela_busca);
		
		util = new Util(this);
		
		spnTipoBusca = (Spinner) findViewById(R.id.spnTipoBusca);
		edtTermoBusca = (EditText) findViewById(R.id.edtTermoBusca);
		btnBuscar = (Button) findViewById(R.id.btnBuscar);
		btnVoltar = (Button) findViewById(R.id.btnVoltarBus);
		
		btnVoltar.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				util.voltar();
			}
		});
	}
}
