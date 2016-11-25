package com.example.usuario.agenda;

import android.widget.EditText;
import android.widget.RatingBar;

import com.example.usuario.agenda.modelo.Aluno;

/**
 * Created by Usuario on 24/11/2016.
 */

public class Formulariohelper {

    private final EditText camponome;
    private final EditText campotelefone;
    private final EditText campoendereco;
    private final EditText camposite;
    private final RatingBar camponota;
    private Aluno aluno;

    public Formulariohelper (FormularioActivity activity){
         camponome = (EditText) activity.findViewById(R.id.formulario_nome);
         campoendereco = (EditText) activity.findViewById(R.id.formulario_endereco);
         campotelefone = (EditText) activity.findViewById(R.id.formulario_telefone);
         camposite = (EditText) activity.findViewById(R.id.formulario_site);
         camponota = (RatingBar) activity.findViewById(R.id.formulario_nota);
        aluno = new Aluno();
    }

    public Aluno pegaaluno() {
        aluno.setNome(camponome.getText().toString());
        aluno.setEndereco(campoendereco.getText().toString());
        aluno.setTelefone(campotelefone.getText().toString());
        aluno.setSite(camposite.getText().toString());
        aluno.setNota(Double.valueOf(camponota.getProgress()));
        return aluno;
    }

    public void preencherformulario(Aluno aluno) {
        camponome.setText(aluno.getNome());
        campoendereco.setText(aluno.getEndereco());
        campotelefone.setText(aluno.getTelefone());
        camposite.setText(aluno.getSite());
        camponota.setProgress(aluno.getNota().intValue());

        this.aluno = aluno;
    }
}
