package br.com.mobilita.cadastro_caelum.fragments;

import java.util.List;

import android.support.v4.app.FragmentActivity;
import br.com.mobilita.cadastro.modelo.Aluno;
import br.com.mobilita.cadastro_caelum.mapas.Localizador;
import br.com.mobilita.dao.AlunoDAO;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapaFragment extends SupportMapFragment {

    private MarkerOptions marcadores;
    private MarkerOptions marcadorLugarAtual;
    private GoogleMap mapa = getMap();

    @Override
    public void onResume() {
        final FragmentActivity contexto = getActivity();

        final LatLng localA =
                        new Localizador(contexto)
        .getCoordenada("R. Cândida Mendonça Bilharinho, 631 - Mercês MG");

        this.marcadorLugarAtual = new MarkerOptions().title("Mobilità Sistemas").position(localA);

        super.onResume();

        final AlunoDAO dao = new AlunoDAO(contexto);

        final List<Aluno> listaAlunos = dao.getLista();

        dao.close();

        this.mapa = getMap();

        for (int i = 0; i < listaAlunos.size(); i++) {

            final LatLng localAluno =
                            new Localizador(contexto).getCoordenada(listaAlunos.get(i)
                                .getEndereco());

            localizaRegiao(localAluno);

            this.marcadores =
                            new MarkerOptions().title(listaAlunos.get(i).getNome()).position(
                                localAluno);
            this.mapa.addMarker(this.marcadores);

            this.mapa.addMarker(this.marcadorLugarAtual);


        }
    }

    public void localizaRegiao(final LatLng local) {

        final CameraUpdate update = CameraUpdateFactory.newLatLngZoom(local, 15);

        this.mapa.animateCamera(update);

    }

}
