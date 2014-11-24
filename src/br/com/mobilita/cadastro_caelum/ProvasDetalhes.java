/**
 *
 */
package br.com.mobilita.cadastro_caelum;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import br.com.mobilita.cadastro.modelo.Prova;
import br.com.mobilita.cadastrocaelum.R;

/**
 * @author mobilita
 *
 */
public class ProvasDetalhes extends Activity {


    private TextView topicos;
    private ListView listaProvas;
    private Prova prova;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.provas_detalhes);

        this.topicos = (TextView) findViewById(R.id.topicos);
        this.listaProvas = (ListView) findViewById(R.id.detalhesProvas);

        final Intent it = getIntent();

        final Prova provaSelecionada = (Prova) it.getSerializableExtra("provaSelecionada");

        this.prova = provaSelecionada;

        if (this.prova != null) {

            this.topicos.setText("Tópicos : ");

            if (this.prova.getTopicos() != null) {
                final int layout = android.R.layout.simple_expandable_list_item_1;

                final ArrayAdapter<String> adapter =
                                new ArrayAdapter<String>(this, layout, this.prova.getTopicos());

                this.listaProvas.setAdapter(adapter);
            }

        }

    }

}
