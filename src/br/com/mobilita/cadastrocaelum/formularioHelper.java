package br.com.mobilita.cadastrocaelum;

import android.widget.EditText;
import android.widget.RatingBar;
import br.com.mobilita.cadastro.modelo.Aluno;

public class formularioHelper {

    private final EditText et_nome;
    private final EditText et_site;
    private final EditText et_endereco;
    private final EditText et_telefone;
    private final RatingBar rb_nota;


    public formularioHelper(final Formulario formulario) {

        this.et_nome = (EditText) formulario.findViewById(R.id.et_nome);
        this.et_site = (EditText) formulario.findViewById(R.id.et_site);
        this.et_endereco = (EditText) formulario.findViewById(R.id.et_endereco);
        this.et_telefone = (EditText) formulario.findViewById(R.id.et_telefone);
        this.rb_nota = (RatingBar) formulario.findViewById(R.id.rb_nota);

    }

    public Aluno pegaAlunoFormulario() {

        final Aluno aluno = new Aluno();

        aluno.setNome(this.et_nome.getText().toString());
        aluno.setSite(this.et_site.getText().toString());
        aluno.setEndereco(this.et_endereco.getText().toString());
        aluno.setTelefone(this.et_telefone.getText().toString());
        aluno.setNotas(Double.valueOf(this.rb_nota.getRating()));
        return aluno;
    }

    public void colocaAlunoParaSerAlterado(final Aluno aluno) {

        this.et_nome.setText(aluno.getNome());
        this.et_endereco.setText(aluno.getEndereco());
        this.et_site.setText(aluno.getSite());
        this.et_telefone.setText(aluno.getTelefone());
        this.rb_nota.setRating(aluno.getNotas().floatValue());
    }

}
