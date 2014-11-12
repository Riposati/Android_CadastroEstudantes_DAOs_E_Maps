package br.com.mobilita.cadastrocaelum;

import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MenuItem.OnMenuItemClickListener;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import br.com.mobilita.cadastro.modelo.Aluno;
import br.com.mobilita.dao.AlunoDAO;


public class ListaAlunos extends ActionBarActivity {

    private ListView lista;
    private Aluno aluno;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listagemalunos);

        this.lista = (ListView) findViewById(R.id.lista);

        registerForContextMenu(this.lista); // para mostrar no menu quando se é dado clique longo

        /*
         * 
         * Clique curto usa-se este método
         */
        this.lista.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(final AdapterView<?> adapter, final View view,
                            final int position, final long id) {

                // Toast.makeText(ListaAlunos.this, "Posição clicada = " + (id + 1) + "",
                // Toast.LENGTH_SHORT).show();

                final Aluno alunoClicado = (Aluno) adapter.getItemAtPosition(position);
                final Intent irPraTelaFormulario = new Intent(ListaAlunos.this, Formulario.class);

                irPraTelaFormulario.putExtra("alunoSelecionado", alunoClicado);

                startActivity(irPraTelaFormulario);
            }
        });

        /*
         * Efeito de clique longo usa-se este método
         */


        this.lista.setOnItemLongClickListener(new OnItemLongClickListener() {

            @Override
            public boolean onItemLongClick(final AdapterView<?> adapter, final View view,
                            final int posicao, final long id) {
                //
                // Toast.makeText(ListaAlunos.this,
                // "Clique longo em = " + adapter.getItemAtPosition(posicao),
                // Toast.LENGTH_SHORT).show();

                ListaAlunos.this.aluno = (Aluno) adapter.getItemAtPosition(posicao);

                return false;

            }

        });


    }

    @Override
    public void onCreateContextMenu(final ContextMenu menu, final View v,
                                    final ContextMenuInfo menuInfo) {

        super.onCreateContextMenu(menu, v, menuInfo);

        menu.add("SMS");
        menu.add("Navegar no site");
        menu.add("Ligar");
        final MenuItem deletar = menu.add("Deletar"); // ao clicar longo uma opção do menu pode
        // responder ao clique através de u evento
        deletar.setOnMenuItemClickListener(new OnMenuItemClickListener() {

            @Override
            public boolean onMenuItemClick(final MenuItem item) {

                final AlunoDAO aluno = new AlunoDAO(ListaAlunos.this);

                aluno.deletar(ListaAlunos.this.aluno);

                // Toast.makeText(ListaAlunos.this, ListaAlunos.this.aluno.getNome(),
                // Toast.LENGTH_SHORT).show();

                aluno.close(); // sempre ao usar o DAO lembrar de fechar

                carregaLista();

                return false;
            }
        });

        menu.add("Ver no mapa");
        menu.add("Enviar e-mail");
    }

    @Override
    protected void onResume() {

        carregaLista();
    }

    private void carregaLista() {
        super.onResume();

        final AlunoDAO alunoDao = new AlunoDAO(this);

        final List<Aluno> alunos = alunoDao.getLista();

        alunoDao.close();

        final int layout = android.R.layout.simple_list_item_1;

        final ArrayAdapter<Aluno> adapter = new ArrayAdapter<Aluno>(this, layout, alunos);

        this.lista.setAdapter(adapter);
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
