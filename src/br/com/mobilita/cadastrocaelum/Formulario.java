package br.com.mobilita.cadastrocaelum;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import br.com.mobilita.cadastro.modelo.Aluno;
import br.com.mobilita.dao.AlunoDAO;

public class Formulario extends ActionBarActivity {

    private formularioHelper helper;
    private Button bt_gravar;
    private EditText et_nome;
    private EditText et_site;
    private EditText et_endereco;
    private EditText et_telefone;
    private RatingBar rb_nota;
    private Aluno aluno;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.formulario);

        Formulario.this.helper = new formularioHelper(Formulario.this);

        /*
         *
         * essas duas chamadas servem para organizar a classe Formulário
         */

        this.et_nome = (EditText) findViewById(R.id.et_nome);
        this.et_site = (EditText) findViewById(R.id.et_site);
        this.et_endereco = (EditText) findViewById(R.id.et_endereco);
        this.et_telefone = (EditText) findViewById(R.id.et_telefone);
        this.rb_nota = (RatingBar) findViewById(R.id.rb_nota);

        this.bt_gravar = (Button) findViewById(R.id.bt_gravar);


        final Intent it = getIntent();

        final Aluno alunoSelecionado = (Aluno) it.getSerializableExtra("alunoSelecionado");

        if (alunoSelecionado != null) {
            this.bt_gravar.setText("Alterar");
            this.helper.colocaAlunoParaSerAlterado(alunoSelecionado);
            // this.et_nome.setText(alunoSelecionado.getNome());
            // this.et_endereco.setText(alunoSelecionado.getEndereco());
            // this.et_site.setText(alunoSelecionado.getSite());
            // this.et_telefone.setText(alunoSelecionado.getTelefone());
            // this.rb_nota.setRating(alunoSelecionado.getNotas().floatValue());
        }

        this.bt_gravar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(final View v) {

                Formulario.this.aluno = new Aluno();

                Formulario.this.aluno = Formulario.this.helper.pegaAlunoFormulario();

                // Toast.makeText(Formulario.this, Formulario.this.aluno.getNome(),
                // Toast.LENGTH_LONG)
                // .show();
                final AlunoDAO dao = new AlunoDAO(Formulario.this);
                dao.salva(Formulario.this.aluno);
                dao.close(); // sempre usar para fechar a conexão com a base de dados!
                // Toast.makeText(Formulario.this, "Aluno salvo!", Toast.LENGTH_LONG).show();
                finish(); // / faz o papel do botao voltar
            }
        });


    }
}
