package br.com.mobilita.cadastro_caelum;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import br.com.mobilita.cadastro_caelum.fragments.MapaFragment;
import br.com.mobilita.cadastro_caelum.mapas.AtualizaPosicao;
import br.com.mobilita.cadastrocaelum.R;

public class MostraAlunosProximos extends FragmentActivity {


    AtualizaPosicao atualizar;

    @Override
    protected void onCreate(final Bundle arg0) {
        super.onCreate(arg0);

        setContentView(R.layout.map_layout);

        final FragmentManager manager = getSupportFragmentManager();

        final FragmentTransaction transaction = manager.beginTransaction();

        final MapaFragment novoMapa = new MapaFragment();

        transaction.replace(R.id.mapa, novoMapa);

        transaction.commit();

        this.atualizar = new AtualizaPosicao(this, novoMapa);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        this.atualizar.destruir();

    }
}
