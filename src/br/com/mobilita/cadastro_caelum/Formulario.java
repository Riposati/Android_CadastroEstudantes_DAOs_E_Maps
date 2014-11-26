package br.com.mobilita.cadastro_caelum;

import java.io.File;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.Toast;
import br.com.mobilita.cadastro.modelo.Aluno;
import br.com.mobilita.cadastrocaelum.R;
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
    private String caminhoArquivo;
    private EditText et_email;

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
        this.et_email = (EditText) findViewById(R.id.et_email);

        final Intent it = getIntent();

        final Aluno alunoSelecionado = (Aluno) it.getSerializableExtra("alunoSelecionado");

        if (alunoSelecionado != null) {

            // aqui e pra alterar

            this.bt_gravar.setText("Alterar");

            this.et_nome.setEnabled(false);

            this.bt_gravar.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(final View v) {

                    Formulario.this.aluno = new Aluno();

                    Formulario.this.aluno = Formulario.this.helper.pegaAlunoFormulario();

                    Toast.makeText(Formulario.this, "Aluno alterado com sucesso!",
                        Toast.LENGTH_LONG).show();

                    final AlunoDAO dao = new AlunoDAO(Formulario.this);

                    dao.atualizar(Formulario.this.aluno);

                    dao.close();

                    finish(); // / faz o papel do botao voltar
                }
            });

            this.helper.colocaAlunoParaSerAlterado(alunoSelecionado);
        } else {

            // aqui e pra guardar

            this.bt_gravar.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(final View v) {

                    Formulario.this.aluno = new Aluno();

                    Formulario.this.aluno = Formulario.this.helper.pegaAlunoFormulario();

                    if (Formulario.this.aluno.getNome().isEmpty()) {
                        Toast.makeText(Formulario.this, "Informe um nome!", Toast.LENGTH_LONG)
                        .show();
                    } else {

                        final AlunoDAO dao = new AlunoDAO(Formulario.this);
                        dao.salva(Formulario.this.aluno);
                        dao.close(); // sempre usar para fechar a conexão com a base de dados!
                        Toast.makeText(Formulario.this, "Aluno salvo!", Toast.LENGTH_LONG).show();
                        finish(); // / faz o papel do botao voltar
                    }
                }
            });

        }

        /*
         *
         * Parte da imagem do estudante e daqui pra baixo
         */

        final ImageView foto = this.helper.getFoto();

        foto.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(final View v) {

                final Intent irPraCamera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE); // media
                // store e
                // onde as
                // constantes
                // de midia
                // estao

                Formulario.this.caminhoArquivo =
                                Environment.getExternalStorageDirectory().toString() + "/"
                                                + System.currentTimeMillis() + ".png"; // get
                // External
                // Storage
                // retorna o
                // caminho
                // para o SD
                // do seu
                // aparelho


                final File arquivo = new File(Formulario.this.caminhoArquivo);

                final Uri localImagem = Uri.fromFile(arquivo);

                irPraCamera.putExtra(MediaStore.EXTRA_OUTPUT, localImagem);

                startActivityForResult(irPraCamera, 123);
            }
        });

    }

    @Override
    protected void onActivityResult(final int requestCode, final int resultCode,
                                    final Intent intencao) {

        if (requestCode == 123) {

            if (resultCode == Activity.RESULT_OK) {
                // Toast.makeText(Formulario.this, "Vim aqui!", Toast.LENGTH_LONG).show();
                this.helper.carregaImagem(this.caminhoArquivo);

            } else {

                this.caminhoArquivo = null;
                Toast.makeText(this, "Imagem nao salva!", Toast.LENGTH_LONG).show();

            }

        }

    }
}
