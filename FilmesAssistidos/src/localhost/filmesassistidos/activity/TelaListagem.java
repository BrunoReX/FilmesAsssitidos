package localhost.filmesassistidos.activity;

import localhost.filmesassistidos.R;
import localhost.filmesassistidos.dao.FilmeDAO;
import localhost.filmesassistidos.util.Constantes;
import localhost.filmesassistidos.util.Util;
import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;

public class TelaListagem extends Activity {
	// Qual coluna mostrar no ListView
	private String[] coluna = new String[] { "FIL_NOME" };
	
	private SimpleCursorAdapter cursorAdapter;
	
	private Util util;
	private FilmeDAO filmeDAO;
	private Cursor cursor;
	
	private Spinner spnListar;
	private ListView lviFilmes;
	private Button btnVoltar;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tela_listagem);
		
		util = new Util(this);
		filmeDAO = FilmeDAO.obterInstancia(this);
		cursor = filmeDAO.listarFilmes(Constantes.LISTAR_NAO);
		
		spnListar = (Spinner) findViewById(R.id.spnListar);
		spnListar.setSelection(0);
		lviFilmes = (ListView) findViewById(R.id.lviFilmes);
		btnVoltar = (Button) findViewById(R.id.btnVoltarLis);
		
		cursorAdapter = util.criarCursorAdapter(coluna, cursor);
		
		util.listaViewEditarItem(cursor, lviFilmes, String.valueOf(Constantes.EDITAR_LISTAR));
		lviFilmes.setAdapter(cursorAdapter);
		
		btnVoltar.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				cursor.close();
				util.voltar();
			}
		});
		
		spnListar.setOnItemSelectedListener(new OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int index, long arg3) {
				cursor = filmeDAO.listarFilmes(index);
				cursorAdapter = util.criarCursorAdapter(coluna, cursor);
				lviFilmes.setAdapter(cursorAdapter);
				util.listaViewEditarItem(cursor, lviFilmes, String.valueOf(Constantes.EDITAR_LISTAR));
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {}
		});
	}
}
