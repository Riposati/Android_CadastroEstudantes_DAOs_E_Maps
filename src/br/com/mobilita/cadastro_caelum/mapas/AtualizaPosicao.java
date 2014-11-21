package br.com.mobilita.cadastro_caelum.mapas;

import android.app.Activity;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import br.com.mobilita.cadastro_caelum.fragments.MapaFragment;

import com.google.android.gms.maps.model.LatLng;

public class AtualizaPosicao implements LocationListener {

    private final LocationManager locationManager;
    private final MapaFragment mapa;

    public AtualizaPosicao(final Activity activity, final MapaFragment mapa) {

        this.mapa = mapa;

        this.locationManager =
                        (LocationManager) activity.getSystemService(Context.LOCATION_SERVICE);

        final String provider = LocationManager.GPS_PROVIDER;

        final long tempoMinimo = 20000;

        final float distancia = 20;

        this.locationManager.requestLocationUpdates(provider, tempoMinimo, distancia, this);

    }

    public void destruir() {

        this.locationManager.removeUpdates(this);
    }

    @Override
    public void onLocationChanged(final Location novaLocalizacao) {

        final double latitude = novaLocalizacao.getLatitude();
        final double longitude = novaLocalizacao.getLongitude();

        final LatLng local = new LatLng(latitude, longitude);

        this.mapa.localizaRegiao(local);

    }

    @Override
    public void onStatusChanged(final String provider, final int status, final Bundle extras) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onProviderEnabled(final String provider) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onProviderDisabled(final String provider) {
        // TODO Auto-generated method stub

    }

}
