/**
 *
 */
package br.com.mobilita.cadastro_caelum;

import java.util.Arrays;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import br.com.mobilita.cadastro.modelo.Prova;
import br.com.mobilita.cadastrocaelum.R;

/**
 * @author mobilita
 *
 */
public class Provas extends Activity {

    private Prova provaClicada;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.listagem_provas);

        final ListView listagem_provas = (ListView) findViewById(R.id.provas);

        final Prova prova1 = new Prova("24/11/2014", "matemática");
        prova1.setTopicos(Arrays.asList("Calculo 1", "Estatistica", "Probabilidade"));

        final Prova prova2 = new Prova("30/11/2014", "português");
        prova2.setTopicos(Arrays.asList("gramática", "interpretação", "orações subordinadas"));

        final Prova prova3 = new Prova("05/12/2014", "Física");
        prova3.setTopicos(Arrays.asList("Mecânica", "leis de Gauss", "leis de Newton"));

        final Prova prova4 = new Prova("07/12/2014", "Estrutura de dados");
        prova4.setTopicos(Arrays.asList("Listas encadeadas", "alocação dinâmica", "pilhas e filas"));

        final Prova prova5 = new Prova("30/11/2014", "Desenvolvimento Android");
        prova5.setTopicos(Arrays.asList("XML", "Interfaces simples e intuitivas",
                        "Java e Orientação a objetos", "Java para Android", "Java e OO SOLID",
                        "Activities"));

        final List<Prova> provas = Arrays.asList(prova1, prova2, prova3, prova4, prova5);

        final int layout = android.R.layout.simple_list_item_1;

        final ArrayAdapter<Prova> adapter = new ArrayAdapter<Prova>(this, layout, provas);

        listagem_provas.setAdapter(adapter);

        listagem_provas.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(final AdapterView<?> parent, final View view,
                            final int position, final long id) {

                final Intent irPraTelaDetalheProvas = new Intent(Provas.this, ProvasDetalhes.class);

                irPraTelaDetalheProvas.putExtra("provaSelecionada",
                    (Prova) listagem_provas.getItemAtPosition(position));

                startActivity(irPraTelaDetalheProvas);

            }

        });

    }

}
