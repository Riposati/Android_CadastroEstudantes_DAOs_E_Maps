package br.com.mobilita.listagem_Estudantes.task;

import java.io.IOException;
import java.util.List;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;
import br.com.mobilita.cadastro.modelo.Aluno;
import br.com.mobilita.dao.AlunoDAO;
import br.com.mobilita.listagem_Estudantes.converter.AlunoConverter;
import br.com.mobilita.listagem_Estudantes.util.WebClient;

public class EnviaAlunosTask extends AsyncTask<Integer, Double, String> {

    private final Activity context;
    private ProgressDialog progress;

    public EnviaAlunosTask(final Activity context) {
        this.context = context;
    }

    @Override
    protected void onPreExecute() {

        this.progress =
                        ProgressDialog.show(this.context, "Aguarde...",
                                        "Enviando dados pra web...", true, true);
    }

    @Override
    protected String doInBackground(final Integer... params) {

        // faz trabalho pesado

        final String urlServidor = "http://www.caelum.com.br/mobile";

        final AlunoDAO dao = new AlunoDAO(this.context);
        final List<Aluno> listaAlunos = dao.getLista();
        dao.close();

        final String dadosJson = new AlunoConverter().toJson(listaAlunos);

        final WebClient client = new WebClient(urlServidor);

        String respostaJson = null;
        try {
            respostaJson = client.post(dadosJson);
        } catch (final IOException e) {
            respostaJson = "Problemas na conexão";
            Log.e("ERRO", "" + e.getMessage() + " na: " + this.getClass());
            e.printStackTrace();
        }

        // Log.i("resposta do servidor da caellum : ", respostaJson);

        return respostaJson;
    }


    @Override
    protected void onPostExecute(final String result) {
        this.progress.dismiss();
        Toast.makeText(this.context, result, Toast.LENGTH_LONG).show();
    }



}
