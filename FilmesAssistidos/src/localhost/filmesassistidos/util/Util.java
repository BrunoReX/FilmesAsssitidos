package localhost.filmesassistidos.util;

import localhost.filmesassistidos.R;
import localhost.filmesassistidos.activity.TelaOpcoes;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.widget.Toast;

public class Util {
	private Context context;
	
	public Util(Context context) {
		this.context = context;
	}
	
	public AlertDialog.Builder criarDialogo(String titulo, String mensagem) {
		// Criar modelo de caixa de diálogo
		AlertDialog.Builder alertBuilder = new AlertDialog.Builder(context);
		
		// Definir o título e o conteúdo da caixa de diálogo
		alertBuilder.setTitle(titulo);
		alertBuilder.setMessage(mensagem);
		
		return alertBuilder;
	}
	
	public AlertDialog criarDialogoMensagem(String titulo, String mensagem) {
		AlertDialog.Builder alertBuilder = criarDialogo(titulo, mensagem);
		
		// Criar uma caixa de diálogo utilizado o modelo criado anteriormente
		AlertDialog alertDialog = alertBuilder.create();
		
		return alertDialog;
	}
	
	public AlertDialog criarDialogoSaida() {
		String str_sair = obterString(R.string.str_sair);
		String str_sim = obterString(R.string.str_sim);
		String msg_sair = obterString(R.string.msg_sair);
		
		AlertDialog.Builder alertBuilder = criarDialogo(str_sair, msg_sair);
		
		// Adicionar um botão para a escolha positva
		alertBuilder.setPositiveButton(str_sim,
				new DialogInterface.OnClickListener() {			
					@Override
					public void onClick(DialogInterface dialog, int which) {
						System.exit(0);
					}
		});
		
		// Adicionar um botão para a escolha negativa
		alertBuilder.setNegativeButton(R.string.str_nao, null);
				
		// Criar uma caixa de diálogo utilizado o modelo criado anteriormente
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
	
	public void voltar(Class classe) {
		Intent it = new Intent(context, classe);
		context.startActivity(it);
		((Activity) context).finish();
	}
}
