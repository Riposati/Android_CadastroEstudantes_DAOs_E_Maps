package br.com.mobilita.Listagem_Estudantes_Adapter;

import java.util.List;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import br.com.mobilita.cadastro.modelo.Aluno;
import br.com.mobilita.cadastrocaelum.R;

public class LinhaAlunosAdapter extends BaseAdapter {

    private final List<Aluno> alunos;
    private final Activity activity;

    public LinhaAlunosAdapter(final List<Aluno> alunos, final Activity activity) {

        this.alunos = alunos;
        this.activity = activity;
    }

    @Override
    public int getCount() {
        return this.alunos.size();
    }

    @Override
    public Object getItem(final int position) {

        return this.alunos.get(position);
    }

    @Override
    public long getItemId(final int position) {

        return this.alunos.get(position).getId();
    }

    @Override
    public View getView(final int position, final View convertView, final ViewGroup parent) {

        this.alunos.get(position);

        final LayoutInflater inflater = this.activity.getLayoutInflater();
        final View linha = inflater.inflate(R.layout.linha_listagem, null);



        final TextView nome = (TextView) linha.findViewById(R.id.nome);
        final TextView telefone = (TextView) linha.findViewById(R.id.telefone);
        final TextView site = (TextView) linha.findViewById(R.id.site);

        final Drawable semFoto;

        nome.setText(this.alunos.get(position).getNome());


        final ImageView imagem = (ImageView) linha.findViewById(R.id.foto);

        if (this.alunos.get(position).getFoto() != null) {

            final Bitmap foto = BitmapFactory.decodeFile(this.alunos.get(position).getFoto());

            if (foto == null) {

                this.alunos.get(position).setFoto("");
                semFoto = this.activity.getResources().getDrawable(R.drawable.sem_imagem);
                imagem.setImageDrawable(semFoto);

            } else {

                final Bitmap imagemReduzida = Bitmap.createScaledBitmap(foto, 220, 220, true);
                imagem.setImageBitmap(imagemReduzida);
                final Matrix matrix = new Matrix();
                matrix.postRotate(-90);
                final Bitmap rotated =
                                Bitmap.createBitmap(imagemReduzida, 0, 0,
                                    imagemReduzida.getWidth(),
                                    imagemReduzida.getHeight(), matrix, true); // aqui
                // sera
                // rotacionada
                // a
                // imagem
                // para
                // ser
                // organizada
                imagem.setImageBitmap(rotated);
            }

        }

        else {
            semFoto = this.activity.getResources().getDrawable(R.drawable.sem_imagem);
            imagem.setImageDrawable(semFoto);
        }

        if (telefone != null) {
            telefone.setText(this.alunos.get(position).getTelefone());
        }

        if (site != null) {
            site.setText(this.alunos.get(position).getSite());
        }

        return linha;
    }


}
