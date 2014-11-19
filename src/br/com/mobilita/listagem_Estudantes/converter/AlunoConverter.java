package br.com.mobilita.listagem_Estudantes.converter;

import java.util.List;

import org.json.JSONException;
import org.json.JSONStringer;

import br.com.mobilita.cadastro.modelo.Aluno;

public class AlunoConverter {

    public String toJson(final List<Aluno> listaAlunos) {

        try {
            /*
             * JSON: {"list": [[{"alunos": [{"nome": "Gustavo","nota": 10},{"nome": "Maria","nota":
             * 10}]}]]}
             */

            final JSONStringer json = new JSONStringer();

            json.object().key("list").array();
            json.object().key("aluno").array();

            for (final Aluno aluno : listaAlunos) {

                json.object();
                json.key("nome").value(aluno.getNome());
                json.key("nota").value(aluno.getNotas());
                json.endObject();
            }

            json.endArray().endObject();
            json.endArray().endObject();

            return json.toString();

        } catch (final JSONException e) {
            throw new RuntimeException();
        }


    }
}
