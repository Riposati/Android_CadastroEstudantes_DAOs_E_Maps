package br.com.mobilita.dao;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import br.com.mobilita.cadastro.modelo.Aluno;

public class AlunoDAO extends SQLiteOpenHelper {

    private static final String DATABASE = "bancoAndroid";
    private static final int versao = 1;

    public AlunoDAO(final Context context) {
        super(context, DATABASE, null, versao);

    }


    public void salva(final Aluno aluno) {

        final ContentValues content = new ContentValues();

        content.put("nome", aluno.getNome());
        content.put("site", aluno.getSite());
        content.put("telefone", aluno.getTelefone());
        content.put("endereco", aluno.getEndereco());
        content.put("foto", aluno.getFoto());
        content.put("nota", aluno.getNotas());

        getWritableDatabase().insert("Alunos", null, content);
    }

    @Override
    public void onCreate(final SQLiteDatabase db) {

        final String ddl =
                        "create table Alunos(id primary key," + "nome text unique not null,"
                                        + "telefone text, endereco text,"
                                        + "site text,foto text, nota real);";
        db.execSQL(ddl);
    }

    @Override
    public void onUpgrade(final SQLiteDatabase db, final int oldVersion, final int newVersion) {

        final String ddl = "drop table alunos if exists"; // / usar com cuidado para não perder os
        // dados
        db.execSQL(ddl);
        this.onCreate(db);
    }


    public List<Aluno> getLista() {

        final String colunas[] = {"id", "nome", "site", "telefone", "endereco", "foto", "nota"};
        final Cursor cursor =
                        getWritableDatabase()
                        .query("alunos", colunas, null, null, null, null, null); // select
        // do
        // android

        final ArrayList<Aluno> alunos = new ArrayList<Aluno>();
        //
        // final Aluno alunoTes = new Aluno();
        // alunoTes.setNome("Gustavo");
        // alunos.add(alunoTes);

        while (cursor.moveToNext()) {

            final Aluno aluno = new Aluno();

            aluno.setId(cursor.getLong(0));
            aluno.setNome(cursor.getString(1));
            aluno.setSite(cursor.getString(2));
            aluno.setTelefone(cursor.getString(3));
            aluno.setEndereco(cursor.getString(4));
            aluno.setFoto(cursor.getString(5));
            aluno.setNotas(cursor.getDouble(6));

            alunos.add(aluno);
        }

        return alunos;
    }


    public void deletar(final Aluno aluno) {

        final String[] args = {aluno.getNome()};
        getWritableDatabase().delete("Alunos", "nome=?", args);
    }

    public void atualizar(final Aluno aluno) {

        final String args[] = {aluno.getNome()};

        final ContentValues content = new ContentValues();

        content.put("nome", aluno.getNome());
        content.put("site", aluno.getSite());
        content.put("telefone", aluno.getTelefone());
        content.put("endereco", aluno.getEndereco());
        content.put("foto", aluno.getFoto());
        content.put("nota", aluno.getNotas());

        getWritableDatabase().update("alunos", content, "nome=?", args);

    }


}
