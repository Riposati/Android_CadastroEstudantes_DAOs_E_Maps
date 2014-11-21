package br.com.mobilita.cadastro_caelum.mapas;

import java.io.IOException;
import java.util.List;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;

import com.google.android.gms.maps.model.LatLng;

public class Localizador {

    private final Context contexto;

    public Localizador(final Context contexto) {

        this.contexto = contexto;
    }

    public LatLng getCoordenada(final String endereco) {

        final Geocoder geocoder = new Geocoder(this.contexto);

        try {
            final List<Address> enderecos = geocoder.getFromLocationName(endereco, 1);

            if (!enderecos.isEmpty()) {

                final Address localizacao = enderecos.get(0);

                final double latitude = localizacao.getLatitude();
                final double longitude = localizacao.getLongitude();



                return new LatLng(latitude, longitude);



            } else {
                return null;
            }

        } catch (final IOException e) {

            return null;
        }

    }

}
