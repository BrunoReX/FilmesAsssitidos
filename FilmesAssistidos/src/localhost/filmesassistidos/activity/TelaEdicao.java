package localhost.filmesassistidos.activity;

import localhost.filmesassistidos.R;
import localhost.filmesassistidos.util.Util;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;

public class TelaEdicao extends Activity {
	private Util util;
	
	private EditText edtNome;
	private EditText edtAno;
	private EditText edtDiretor;
	private Spinner spnGenero;
	private Spinner spnNacionalidade;
	private RadioButton rbSim;
	private RadioButton rbNao;
	private Button btnGravar;
	private Button btnExcluir;
	private Button btnVoltar;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tela_edicao);
		
		util = new Util(this);
		
		edtNome = (EditText) findViewById(R.id.edtNomeEdi);
		edtAno = (EditText) findViewById(R.id.edtAnoEdi);
		edtDiretor = (EditText) findViewById(R.id.edtDiretorEdi);
		spnGenero = (Spinner) findViewById(R.id.spnGeneroEdi);
		spnNacionalidade = (Spinner) findViewById(R.id.spnNacionalidadeEdi);
		rbSim = (RadioButton) findViewById(R.id.rbSimEdi);
		rbNao = (RadioButton) findViewById(R.id.rbNaoEdi);
		btnGravar = (Button) findViewById(R.id.btnGravarEdi);
		btnExcluir = (Button) findViewById(R.id.btnExcluirEdi);
		btnVoltar = (Button) findViewById(R.id.btnVoltarEdi);
		
		btnVoltar.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				util.voltar(TelaListagem.class);
			}
		});
	}
}
