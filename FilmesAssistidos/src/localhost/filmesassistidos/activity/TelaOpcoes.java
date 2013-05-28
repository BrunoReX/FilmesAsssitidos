package localhost.filmesassistidos.activity;

import localhost.filmesassistidos.R;
import localhost.filmesassistidos.util.Constantes;
import localhost.filmesassistidos.util.Util;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class TelaOpcoes extends Activity {
	private Util util;
	private ListView lviOpcoes;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tela_opcoes);
		
		util = new Util(this);
		lviOpcoes = (ListView) findViewById(R.id.lviOpcoes);
		
		ArrayAdapter<CharSequence> arrayAdapter =
				ArrayAdapter.createFromResource(this,
						R.array.str_array_opcoes, android.R.layout.simple_list_item_1);
		
		lviOpcoes.setAdapter(arrayAdapter);
		
		lviOpcoes.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> adapter,
					View view, int position, long arg) {
				Intent it = null;
				
				switch (position) {
				case Constantes.MENU_ADICIONAR:
					it = new Intent(TelaOpcoes.this, TelaCadastro.class);
					break;
					
				case Constantes.MENU_BUSCAR:
					it = new Intent(TelaOpcoes.this, TelaBusca.class);
					break;
					
				case Constantes.MENU_LISTAR:
					it = new Intent(TelaOpcoes.this, TelaListagem.class);
					break;
					
				case Constantes.MENU_INFORMACOES:
					it = new Intent(TelaOpcoes.this, TelaInformacoes.class);
					break;					
					
				case Constantes.MENU_SAIR:
					util.criarDialogoSaida().show();
					break;
					
				default:
					break;
				}
				
				if (it != null) {
					startActivity(it);
					finish();
				}
			}
		});
	}
}