package br.com.mobilita.listagem_Estudantes;

import java.util.List;

import android.content.Intent;
import android.net.Uri;
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
import android.widget.ListView;
import android.widget.Toast;
import br.com.mobilita.Listagem_Estudantes_Adapter.LinhaAlunosAdapter;
import br.com.mobilita.cadastro.modelo.Aluno;
import br.com.mobilita.cadastro_caelum.Formulario;
import br.com.mobilita.cadastrocaelum.R;
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

        menu.add("Enviar mensagem");
        final MenuItem navegar = menu.add("Navegar no site");

        navegar.setOnMenuItemClickListener(new OnMenuItemClickListener() {

            @Override
            public boolean onMenuItemClick(final MenuItem item) {

                if (ListaAlunos.this.aluno.getSite() != null) {

                    final Intent site = new Intent(Intent.ACTION_VIEW);

                    final Uri dados;
                    dados = Uri.parse("http://" + ListaAlunos.this.aluno.getSite());
                    site.setData(dados);
                    startActivity(site);
                } else {
                    Toast.makeText(ListaAlunos.this, "Estudante nao possui site", Toast.LENGTH_LONG)
                    .show();
                }

                return false;
            }
        });

        final MenuItem ligar = menu.add("Ligar");

        ligar.setOnMenuItemClickListener(new OnMenuItemClickListener() {

            @Override
            public boolean onMenuItemClick(final MenuItem item) {

                final Intent it = new Intent(Intent.ACTION_CALL);

                final Uri discagem = Uri.parse("tel:" + ListaAlunos.this.aluno.getTelefone());

                it.setData(discagem);

                startActivity(it);

                return false;
            }
        });


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

        menu.add("Visualizar localizacao");
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

        final LinhaAlunosAdapter adapter = new LinhaAlunosAdapter(alunos, ListaAlunos.this);

        // LayoutInflater layoutInflater = getLayoutInflater(); // pegar série de elementos XML e
        // transformar em objetos no java

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
