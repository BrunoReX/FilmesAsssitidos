package localhost.filmesassistidos.activity;

import localhost.filmesassistidos.R;
import localhost.filmesassistidos.util.Util;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class TelaInformacoes extends Activity {
	private Util util;
	
	private TextView txtTotalFilmes;
	private TextView txtTotalAssistido;
	private TextView txtTotalNaoAssistido;
	private Button btnVoltar;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tela_informacoes);
		
		util = new Util(this);
		
		txtTotalFilmes = (TextView) findViewById(R.id.txtTotalFilmes);
		txtTotalAssistido = (TextView) findViewById(R.id.txtTotalAssistido);
		txtTotalNaoAssistido = (TextView) findViewById(R.id.txtTotalNaoAssistido);
		btnVoltar = (Button) findViewById(R.id.btnVoltarInf);
		
		btnVoltar.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				util.voltar();
			}
		});
	}
}
