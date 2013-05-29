package localhost.filmesassistidos.activity;

import localhost.filmesassistidos.R;
import localhost.filmesassistidos.dao.FilmeDAO;
import localhost.filmesassistidos.util.Constantes;
import localhost.filmesassistidos.util.Util;
import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.widget.SimpleCursorAdapter;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

public class TelaBusca extends Activity {
	private SimpleCursorAdapter cursorAdapter;
	private Util util;
	private FilmeDAO filmeDAO;
	private Cursor cursor;
	
	private Spinner spnTipoBusca;
	private EditText edtTermoBusca;
	private ListView lviResultadoBusca;
	private Button btnVoltar;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tela_busca);
		
		util = new Util(this);
		filmeDAO = FilmeDAO.obterInstancia(this);
		
		spnTipoBusca = (Spinner) findViewById(R.id.spnTipoBusca);
		edtTermoBusca = (EditText) findViewById(R.id.edtTermoBusca);
		lviResultadoBusca = (ListView) findViewById(R.id.lviResultadoBusca);
		btnVoltar = (Button) findViewById(R.id.btnVoltarBus);
		atualizarBusca();
		
		btnVoltar.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				cursor.close();
				util.voltar();
			}
		});
		
		edtTermoBusca.addTextChangedListener(new TextWatcher() {
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {}
			
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
			
			@Override
			public void afterTextChanged(Editable s) {
				atualizarBusca();
			}
		});
		
		spnTipoBusca.setOnItemSelectedListener(new OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int index, long arg3) {
				atualizarBusca();
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
			}
		});
	}
	
	
	public void atualizarBusca() {
		String termo = edtTermoBusca.getText().toString();
		
		int idx = spnTipoBusca.getSelectedItemPosition();
		cursor = filmeDAO.buscarFilmes(idx, termo);
		
		String[] coluna = null;
		
		coluna = new String[] { "FIL_NOME" };
		
		cursorAdapter = util.criarCursorAdapter(coluna, cursor);
		lviResultadoBusca.setAdapter(cursorAdapter);
		util.listaViewEditarItem(cursor, lviResultadoBusca, String.valueOf(Constantes.EDITAR_BUSCAR));
	}
}
