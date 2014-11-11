package br.com.mobilita.cadastrocaelum;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;


public class ListaAlunos extends ActionBarActivity {

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listagemalunos);

        final ListView lista = (ListView) findViewById(R.id.lista);
        final String nomes[] = {"Gustavo", "Andre", "Fabiana"};
        final int layout = android.R.layout.simple_list_item_1;
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, layout, nomes);

        lista.setAdapter(adapter);

        /*
         * 
         * Clique curto usa-se este método
         */
        lista.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(final AdapterView<?> adapter, final View view,
                                    final int position, final long id) {

                Toast.makeText(ListaAlunos.this, "Posição clicada = " + (id + 1) + "",
                    Toast.LENGTH_SHORT).show();

            }
        });

        /*
         * Efeito de clique longo usa-se este método
         */


        lista.setOnItemLongClickListener(new OnItemLongClickListener() {

            @Override
            public boolean onItemLongClick(final AdapterView<?> adapter, final View view,
                                           final int posicao, final long id) {

                Toast.makeText(ListaAlunos.this,
                    "Clique longo em = " + adapter.getItemAtPosition(posicao),
                    Toast.LENGTH_SHORT).show();
                return true;
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(final Menu menu) {

        final MenuInflater inflater = getMenuInflater();

        inflater.inflate(R.menu.lista_alunos, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(final MenuItem item) {

        final int itemClicado = item.getItemId();

        switch (itemClicado) {
            case R.id.novo:
                final Intent chamaTelaFormAluno = new Intent(this, Formulario.class);
                startActivity(chamaTelaFormAluno);
                break;

            default:
                break;
        }

        return super.onOptionsItemSelected(item);
    }

}
