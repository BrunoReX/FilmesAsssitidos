package localhost.filmesassistidos.helper;

import localhost.filmesassistidos.util.Constantes;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public abstract class GenericoHelper extends SQLiteOpenHelper {
	protected String NOME_TABELA;
	protected String[] COLUNAS_TABELA;
	protected String[] TIPOS_COLUNAS;
	protected String SQL_EXTRA;
	protected String SQL_BD;
	
	public String obterNomeTabela() {
		return NOME_TABELA;
	}
	
	public String[] obterColunasTabela() {
		return COLUNAS_TABELA;
	}
	
	public GenericoHelper(Context context, String name, CursorFactory factory,
			int version) {
		super(context, name, factory, version);
	}

	public GenericoHelper(Context context) {
		super(context, Constantes.NOME_BD, null, Constantes.VERSAO_BD);
		configurarParametros();
	}

	@Override
	public void onCreate(SQLiteDatabase database) {
		database.execSQL(SQL_BD);
	}

	@Override
	public void onUpgrade(SQLiteDatabase database, int oldVersion,
			int newVersion) {
		database.execSQL("DROP TABLE IF EXISTS " + NOME_TABELA);
		onCreate(database);
	}
	
	public abstract void configurarParametros();
	
	public Cursor consultarRegistros() {
		Cursor mCursor = getReadableDatabase().query(true, NOME_TABELA, COLUNAS_TABELA,
				null, null, null, null, null, null);
		
		if (mCursor != null) {
			mCursor.moveToFirst();
		}
		
		return mCursor;
	}
	
	public void limparTabela() {
		String sql = "DELETE FROM " + obterNomeTabela();
		getReadableDatabase().execSQL(sql);
	}
	
	public String criarTabela() {
		StringBuilder sql = new StringBuilder();
		sql.append("CREATE TABLE IF NOT EXISTS ");
		sql.append(NOME_TABELA + "(");

		for (int i = 0; i < COLUNAS_TABELA.length; i++) {
			boolean ultimaColuna = i == COLUNAS_TABELA.length-1 ? true : false;
			sql.append(COLUNAS_TABELA[i] + " " + TIPOS_COLUNAS[i] + (ultimaColuna ? " " : ", "));
		}
		
		
		sql.append(");");
		
		return sql.toString();
	}
}