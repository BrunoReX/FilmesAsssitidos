package localhost.filmesassistidos.activity;

import localhost.filmesassistidos.R;
import localhost.filmesassistidos.dao.FilmeDAO;
import localhost.filmesassistidos.model.Filme;
import localhost.filmesassistidos.util.Constantes;
import localhost.filmesassistidos.util.Util;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;

public class TelaEdicao extends Activity {
	private Util util;
	private FilmeDAO filmeDAO;
	private Intent it;
	private Filme filme;
	private int codigo;
	private int tela;

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
		
		util = new Util(this);
		filmeDAO = FilmeDAO.obterInstancia(this);
		it = getIntent();
		codigo = Integer.parseInt(it.getStringExtra("codigo"));
		tela = Integer.parseInt(it.getStringExtra("tela"));

		filme = filmeDAO.obterDados(codigo);
		
		edtNome.setText(filme.getNomeFilme());
		edtAno.setText(filme.getAnoFilme());
		edtDiretor.setText(filme.getNomeDiretor());
		spnGenero.setSelection(filme.getCodigoGenero() == null ?
				0 : filme.getCodigoGenero());
		
		spnNacionalidade.setSelection(filme.getCodigoNacionalidade() == null ?
				0 : filme.getCodigoNacionalidade());
		
		if (filme.getFilmeAssistido() == Constantes.FILME_SIM) {
			rbSim.setChecked(true);
		} else {
			rbNao.setChecked(true);
		}
		

		btnVoltar.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (tela == Constantes.EDITAR_LISTAR) {
					util.voltar(TelaListagem.class);
				} else {
					util.voltar(TelaBusca.class);
				}
			}
		});

		btnGravar.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Filme f = new Filme();
				
				f.setCodigoFilme(codigo);
				f.setNomeFilme(edtNome.getText().toString());
				f.setAnoFilme(edtAno.getText().toString());
				f.setNomeDiretor(edtDiretor.getText().toString());
				f.setCodigoGenero(spnGenero.getSelectedItemPosition());
				f.setCodigoNacionalidade(spnNacionalidade
						.getSelectedItemPosition());

				if (rbNao.isSelected()) {
					f.setFilmeAssistido(Constantes.FILME_NAO);
				} else {
					f.setFilmeAssistido(Constantes.FILME_SIM);
				}
				
				if (!util.validarCampos(f)) {
					return;
				}
				
				filmeDAO.editarRegistro(f);
				util.mostrarMensagem(getResources().getString(R.string.msg_filme_alterado));
			}
		});
		
		btnExcluir.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Filme f = new Filme();
				f.setCodigoFilme(codigo);
				filmeDAO.excluirRegistro(f);
				util.mostrarMensagem(getResources().getString(R.string.msg_filme_excluido));
				
				if (tela == Constantes.EDITAR_LISTAR) {
					util.voltar(TelaListagem.class);
				} else {
					util.voltar(TelaBusca.class);
				}
			}
		});
	}
}
