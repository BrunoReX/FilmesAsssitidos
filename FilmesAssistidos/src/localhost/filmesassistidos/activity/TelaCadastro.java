package localhost.filmesassistidos.activity;

import localhost.filmesassistidos.R;
import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;

public class TelaCadastro extends Activity {
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
		
		edtNome = (EditText) findViewById(R.id.edtNomeCad);
		edtAno = (EditText) findViewById(R.id.edtAnoCad);
		edtDiretor = (EditText) findViewById(R.id.edtDiretorCad);
		spnGenero = (Spinner) findViewById(R.id.spnGeneroCad);
		spnNacionalidade = (Spinner) findViewById(R.id.spnNacionalidadeCad);
		rbSim = (RadioButton) findViewById(R.id.rbSimCad);
		rbNao = (RadioButton) findViewById(R.id.rbNaoCad);
		btnAdicionar = (Button) findViewById(R.id.btnAdicionarCad);
		btnVoltar = (Button) findViewById(R.id.btnVoltarCad);
	}
}
