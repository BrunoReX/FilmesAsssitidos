package localhost.filmesassistidos.activity;

import localhost.filmesassistidos.R;
import localhost.filmesassistidos.dao.FilmeDAO;
import localhost.filmesassistidos.model.Filme;
import localhost.filmesassistidos.util.Constantes;
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
	private FilmeDAO filmeDAO;
	
	private EditText edtNome;
	private EditText edtAno;
	private EditText edtDiretor;
	private Spinner spnGenero;
	private Spinner spnNacionalidade;
	private RadioButton rbNao;
	private Button btnAdicionar;
	private Button btnVoltar;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tela_cadastro);
		
		util = new Util(this);
		filmeDAO = FilmeDAO.obterInstancia(this);
		
		edtNome = (EditText) findViewById(R.id.edtNomeCad);
		edtAno = (EditText) findViewById(R.id.edtAnoCad);
		edtDiretor = (EditText) findViewById(R.id.edtDiretorCad);
		spnGenero = (Spinner) findViewById(R.id.spnGeneroCad);
		spnGenero.setSelection(0);
		spnNacionalidade = (Spinner) findViewById(R.id.spnNacionalidadeCad);
		spnNacionalidade.setSelection(0);
		rbNao = (RadioButton) findViewById(R.id.rbNaoCad);
		btnAdicionar = (Button) findViewById(R.id.btnAdicionarCad);
		btnVoltar = (Button) findViewById(R.id.btnVoltarCad);
		
		btnVoltar.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				util.voltar();
			}
		});
		
		btnAdicionar.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Filme f = new Filme();
				
				f.setNomeFilme(edtNome.getText().toString());
				f.setAnoFilme(edtAno.getText().toString());
				f.setNomeDiretor(edtDiretor.getText().toString());
				f.setCodigoGenero(spnGenero.getSelectedItemPosition());
				f.setCodigoNacionalidade(spnNacionalidade.getSelectedItemPosition());
				
				if (rbNao.isChecked()) {
					f.setFilmeAssistido(Constantes.FILME_NAO);
				} else {
					f.setFilmeAssistido(Constantes.FILME_SIM);
				}
				
				if (!util.validarCampos(f)) {
					return;
				}
				
				filmeDAO.novoRegistro(f);
				util.mostrarMensagem(getResources().getString(R.string.msg_filme_cadastrado));
			}
		});
	}
}
