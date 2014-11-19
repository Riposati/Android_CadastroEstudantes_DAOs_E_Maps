package br.com.mobilita.listagem_Estudantes.util;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

public class WebClient {

    String url;

    public WebClient(final String url) {

        this.url = url;
    }

    public String post(final String dados) {

        // Log.i("JSON", "" + dados);

        try {

            final HttpClient cliente = new DefaultHttpClient();
            final HttpPost post = new HttpPost(this.url);

            post.setEntity(new StringEntity(dados));
            post.setHeader("Content-type", "application/json");
            post.setHeader("Accept", "application/json");

            final HttpResponse response = cliente.execute(post);

            final HttpEntity resposta = response.getEntity();


            final String respostaJson = EntityUtils.toString(resposta);

            // Log.i("RES", "" + respostaJson);

            return respostaJson;

        } catch (final Exception e) {
            throw new RuntimeException(e);
        }

    }
}
