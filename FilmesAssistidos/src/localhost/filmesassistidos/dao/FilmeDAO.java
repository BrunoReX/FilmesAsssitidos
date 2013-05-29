package localhost.filmesassistidos.dao;

import localhost.filmesassistidos.helper.FilmeHelper;
import localhost.filmesassistidos.model.Filme;
import localhost.filmesassistidos.util.Constantes;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class FilmeDAO {
	private static FilmeDAO filmeDAO;
	private FilmeHelper filmeHelper;
	private SQLiteDatabase banco;
	
	public static FilmeDAO obterInstancia(Context context) {
		if (filmeDAO == null) {
			filmeDAO = new FilmeDAO(context);
		}
		
		return filmeDAO;
	}
	
	public FilmeDAO(Context context) {
		filmeHelper = new FilmeHelper(context);
		banco = filmeHelper.getWritableDatabase();
	}
	
	public Cursor listarFilmes(int filtro) {
		String[] colunas = filmeHelper.obterColunasTabela();
		Cursor cursor = null;
		
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT ");
		
		for (int i = 0; i < colunas.length; i++) {
			if (i == 0) {
				sql.append(colunas[i] + " as _id");
			} else {
				sql.append(colunas[i]);
			}
			
			if (i < (colunas.length-1)) {
				sql.append(", ");
			} else {
				sql.append(" ");
			}
		}
		
		sql.append("FROM " + filmeHelper.obterNomeTabela()  + " ");
		
		switch (filtro) {
		case Constantes.LISTAR_NAO:
			sql.append("WHERE FIL_ASSISTIDO=0 ");
			break;
			
		case Constantes.LISTAR_SIM:
			sql.append("WHERE FIL_ASSISTIDO=1 ");
			break;
			
		case Constantes.LISTAR_TODOS:
			break;

		default:
			break;
		}
		
		sql.append("ORDER BY FIL_NOME;");
		
		cursor = banco.rawQuery(sql.toString(), null);
		
		return cursor;		
	}
	
	public int obterTotal(int filtro) {
		Cursor cursor = listarFilmes(filtro);
		int total = 0;
		
		if (cursor != null) {
			total = cursor.getCount();
		}		
		
		cursor.close();		
		return total;
	}
	
	public long novoRegistro(Filme f) {
		String[] colunas = filmeHelper.obterColunasTabela();
		
		ContentValues valores = new ContentValues();
		valores.put(colunas[1], f.getNomeFilme());
		valores.put(colunas[2], f.getAnoFilme());
		valores.put(colunas[3], f.getNomeDiretor());
		valores.put(colunas[4], f.getCodigoGenero());
		valores.put(colunas[5], f.getCodigoNacionalidade());
		valores.put(colunas[6], !f.isFilmeAssistido() ? 1 : 0);

		return banco.insert(filmeHelper.obterNomeTabela(), null, valores);
	}
	
	public int editarRegistro(Filme f) {
		String[] colunas = filmeHelper.obterColunasTabela();
		
		ContentValues valores = new ContentValues();
		valores.put(colunas[1], f.getNomeFilme());
		valores.put(colunas[2], f.getAnoFilme());
		valores.put(colunas[3], f.getNomeDiretor());
		valores.put(colunas[4], f.getCodigoGenero());
		valores.put(colunas[5], f.getCodigoNacionalidade());
		valores.put(colunas[6], f.isFilmeAssistido());
		
		String registro = colunas[0] + "=" + f.getCodigoFilme();
		
		return banco.update(filmeHelper.obterNomeTabela(), valores, registro, null);
	}
	
	public void excluirRegistro(Filme f) {
		String[] colunas = filmeHelper.obterColunasTabela();
		StringBuilder sql = new StringBuilder();
		
		sql.append("DELETE FROM " + filmeHelper.obterNomeTabela() + " ");
		sql.append("WHERE " + colunas[0] + "=" + f.getCodigoFilme() + ";");
		
		banco.execSQL(sql.toString());
	}
}
