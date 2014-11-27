package br.com.mobilita.cadastro_caelum;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import br.com.mobilita.cadastro.modelo.Aluno;
import br.com.mobilita.cadastrocaelum.R;

public class formularioHelper {

    private final EditText et_nome;
    private final EditText et_site;
    private final EditText et_endereco;
    private final EditText et_telefone;
    private final RatingBar rb_nota;
    private final ImageView iv_foto;
    private Aluno aluno;


    public formularioHelper(final Formulario formulario) {

        this.et_nome = (EditText) formulario.findViewById(R.id.et_nome);
        this.et_site = (EditText) formulario.findViewById(R.id.et_site);
        this.et_endereco = (EditText) formulario.findViewById(R.id.et_endereco);
        this.et_telefone = (EditText) formulario.findViewById(R.id.et_telefone);
        this.rb_nota = (RatingBar) formulario.findViewById(R.id.rb_nota);
        this.iv_foto = (ImageView) formulario.findViewById(R.id.foto);
        this.aluno = new Aluno();

    }

    public Aluno pegaAlunoFormulario() {

        this.aluno.setNome(this.et_nome.getText().toString());
        this.aluno.setSite(this.et_site.getText().toString());
        this.aluno.setEndereco(this.et_endereco.getText().toString());
        this.aluno.setTelefone(this.et_telefone.getText().toString());
        this.aluno.setNotas(Double.valueOf(this.rb_nota.getRating()));
        return this.aluno;
    }

    public void colocaAlunoParaSerAlterado(final Aluno alunoParaSerAlterado,
                                           final String caminhoArquivo) {
        this.aluno = alunoParaSerAlterado;
        this.et_nome.setText(alunoParaSerAlterado.getNome());
        this.et_endereco.setText(alunoParaSerAlterado.getEndereco());
        this.et_site.setText(alunoParaSerAlterado.getSite());
        this.et_telefone.setText(alunoParaSerAlterado.getTelefone());
        this.rb_nota.setRating(alunoParaSerAlterado.getNotas().floatValue());

        if ((caminhoArquivo != null)) {

            carregaImagem(alunoParaSerAlterado.getFoto());
        }
    }

    public ImageView getFoto() {

        return this.iv_foto;
    }

    public void carregaImagem(final String caminhoArquivo) {

        this.aluno.setFoto(caminhoArquivo);

        final Bitmap imagem = BitmapFactory.decodeFile(caminhoArquivo);

        final Bitmap imagemReduzida = Bitmap.createScaledBitmap(imagem, 400, 400, true);

        final Matrix matrix = new Matrix();
        matrix.postRotate(-90);
        final Bitmap rotated =
                        Bitmap.createBitmap(imagemReduzida, 0, 0, imagemReduzida.getWidth(),
                                        imagemReduzida.getHeight(), matrix, true); // aqui sera
        // rotacionada a
        // imagem para
        // ser organizada

        this.iv_foto.setImageBitmap(rotated);
    }

}
