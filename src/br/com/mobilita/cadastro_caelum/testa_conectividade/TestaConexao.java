package br.com.mobilita.cadastro_caelum.testa_conectividade;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class TestaConexao {


    private final Context _context;

    public TestaConexao(final Context context) {
        this._context = context;
    }

    public boolean existeConexao() {
        final ConnectivityManager connectivity =
                        (ConnectivityManager) this._context
                        .getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivity != null) {
            final NetworkInfo netInfo = connectivity.getActiveNetworkInfo();

            // Se não existe nenhum tipo de conexão retorna false
            if (netInfo == null) {
                return false;
            }

            final int netType = netInfo.getType();

            // Verifica se a conexão é do tipo WiFi ou Mobile e
            // retorna true se estiver conectado ou false em
            // caso contrário
            if ((netType == ConnectivityManager.TYPE_WIFI)
                            || (netType == ConnectivityManager.TYPE_MOBILE)) {
                return netInfo.isConnected();

            } else {
                return false;
            }
        } else {
            return false;
        }
    }

}
