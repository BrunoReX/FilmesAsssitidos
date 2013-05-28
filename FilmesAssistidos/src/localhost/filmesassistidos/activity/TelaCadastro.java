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

public class TelaCadastro extends Activity {
	private Util util;
	
	private EditText edtNome;
	private EditText edtAno;
	private EditText edtDiretor;
	private Spinner spnGenero;
	private Spinner spnNacionalidade;
	private RadioButton rbSim;
	private RadioButton rbNao;
	private Button btnAdicionar;
	private Button btnVoltar;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tela_cadastro);
		
		util = new Util(this);
		
		edtNome = (EditText) findViewById(R.id.edtNomeCad);
		edtAno = (EditText) findViewById(R.id.edtAnoCad);
		edtDiretor = (EditText) findViewById(R.id.edtDiretorCad);
		spnGenero = (Spinner) findViewById(R.id.spnGeneroCad);
		spnNacionalidade = (Spinner) findViewById(R.id.spnNacionalidadeCad);
		rbSim = (RadioButton) findViewById(R.id.rbSimCad);
		rbNao = (RadioButton) findViewById(R.id.rbNaoCad);
		btnAdicionar = (Button) findViewById(R.id.btnAdicionarCad);
		btnVoltar = (Button) findViewById(R.id.btnVoltarCad);
		
		btnVoltar.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				util.voltar();
			}
		});
	}
}
