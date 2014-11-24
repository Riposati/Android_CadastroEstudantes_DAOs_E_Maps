package br.com.mobilita.cadastro_caelum;


import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;
import br.com.mobilita.cadastrocaelum.R;

public class Preferencias extends Activity {

    TextView texto;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.preferencias);

        this.texto = (TextView) findViewById(R.id.contrucao);
    }

}
