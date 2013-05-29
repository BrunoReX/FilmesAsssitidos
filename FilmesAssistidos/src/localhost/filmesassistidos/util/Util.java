package localhost.filmesassistidos.util;

import localhost.filmesassistidos.R;
import localhost.filmesassistidos.activity.TelaEdicao;
import localhost.filmesassistidos.activity.TelaOpcoes;
import localhost.filmesassistidos.model.Filme;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

public class Util {
	private Context context;
	
	public Util(Context context) {
		this.context = context;
	}
	
	public AlertDialog.Builder criarDialogo(String titulo, String mensagem) {
		// Criar modelo de caixa de di�logo
		AlertDialog.Builder alertBuilder = new AlertDialog.Builder(context);
		
		// Definir o t�tulo e o conte�do da caixa de di�logo
		alertBuilder.setTitle(titulo);
		alertBuilder.setMessage(mensagem);
		
		return alertBuilder;
	}
	
	public AlertDialog criarDialogoMensagem(String titulo, String mensagem) {
		AlertDialog.Builder alertBuilder = criarDialogo(titulo, mensagem);
		
		// Criar uma caixa de di�logo utilizado o modelo criado anteriormente
		AlertDialog alertDialog = alertBuilder.create();
		
		return alertDialog;
	}
	
	public AlertDialog criarDialogoSaida() {
		String str_sair = obterString(R.string.str_sair);
		String str_sim = obterString(R.string.str_sim);
		String msg_sair = obterString(R.string.msg_sair);
		
		AlertDialog.Builder alertBuilder = criarDialogo(str_sair, msg_sair);
		
		// Adicionar um bot�o para a escolha positva
		alertBuilder.setPositiveButton(str_sim,
				new DialogInterface.OnClickListener() {			
					@Override
					public void onClick(DialogInterface dialog, int which) {
						System.exit(0);
					}
		});
		
		// Adicionar um bot�o para a escolha negativa
		alertBuilder.setNegativeButton(R.string.str_nao, null);
				
		// Criar uma caixa de di�logo utilizado o modelo criado anteriormente
		AlertDialog alertDialog = alertBuilder.create();
		
		return alertDialog;
	}
	
	public void mostrarMensagem(CharSequence msg) {
		Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
	}
	
	public String obterString(int id) {
		return context.getString(id);
	}
	
	public void voltar() {
		Intent it = new Intent(context, TelaOpcoes.class);
		context.startActivity(it);
		((Activity) context).finish();
	}
	
	@SuppressWarnings("rawtypes")
	public void voltar(Class classe) {
		Intent it = new Intent(context, classe);
		context.startActivity(it);
		((Activity) context).finish();
	}
	
	public SimpleCursorAdapter criarCursorAdapter(String[] coluna, Cursor cursor) {
		SimpleCursorAdapter cursorAdapter = new SimpleCursorAdapter(
				context,
				android.R.layout.simple_list_item_1,
				cursor,
				coluna,
				new int[] { android.R.id.text1 }, // id padr�o definido no simple_list_item_1
				0);
		
		return cursorAdapter;
	}
	
	public void listaViewEditarItem(final Cursor cursor, ListView listView, final String tela) {
		listView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> adapter, View view, int position, long arg) {
				if (position < 0) {
					return;
				}
				
				// Mover o cursor para o registro na posi��o clicada
				cursor.moveToPosition(position);
				
				// Armazenar posi��o do item na lista
				//String pos_lst = String.valueOf(position);

				// Obter chave prim�ria daquela registro na tabela
				String id_tbl = String.valueOf(cursor.getInt(cursor.getColumnIndex("_id")));
				
				Intent it = new Intent(context, TelaEdicao.class);

				// Enviar c�digo do registro na tabela para a pr�xima tela
				Bundle parametros = new Bundle();
				parametros.putString("codigo", id_tbl);
				parametros.putString("tela", tela);
				it.putExtras(parametros);
				
				context.startActivity(it);
				//cursor.close();
				((Activity) context).finish();
			}
		});
	}
	
	public boolean validarCampos(Filme f) {
		StringBuilder msg = new StringBuilder();
		msg.append(obterString(R.string.msg_dados_invalidos) + " ");
		
		int invalido = 0;
		if (f.getNomeFilme().trim().equals("")) {
			msg.append(obterString(R.string.str_nome));
			invalido++;
		}
		
		try {
			Integer.parseInt(f.getAnoFilme());
		} catch (Exception e) {
			if (invalido > 0) {
				msg.append(", ");
			}
			
			msg.append(obterString(R.string.str_ano));
			invalido++;
		}
		
		if (f.getNomeDiretor().trim().equals("")) {
			if (invalido > 0) {
				msg.append(", ");
			}
			
			msg.append(obterString(R.string.str_diretor));
			invalido++;
		}
		
		if (invalido == 0) {
			return true;
		} else {
			mostrarMensagem(msg.toString());
			return false;
		}
	}
}
