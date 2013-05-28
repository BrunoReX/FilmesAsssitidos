package localhost.filmesassistidos.helper;

import android.content.Context;

public class FilmeHelper extends GenericoHelper {
	public FilmeHelper(Context context) {
		super(context);
	}

	@Override
	public void configurarParametros() {
		NOME_TABELA = "FILME";
		COLUNAS_TABELA = new String[]{ "FIL_ID", "FIL_NOME", "FIL_ANO", "FIL_DIR",
				"FIL_GEN", "FIL_NACIONALIDADE", "FIL_ASSISTIDO" };
		TIPOS_COLUNAS = new String[]{ "INTEGER PRIMARY KEY AUTOINCREMENT", "VARCHAR", "VARCHAR",
				"VARCHAR", "INTEGER", "INTEGER", "INTEGER" };
		SQL_BD = criarTabela();
	}
	
}