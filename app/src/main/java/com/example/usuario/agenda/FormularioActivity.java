package com.example.usuario.agenda;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.example.usuario.agenda.dao.AlunoDAO;
import com.example.usuario.agenda.modelo.Aluno;

public class FormularioActivity extends AppCompatActivity {
    private Formulariohelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario);
        helper = new Formulariohelper(this);

        Intent intent = getIntent();
        Aluno aluno = (Aluno) intent.getSerializableExtra("aluno");

        if (aluno!=null){
            helper.preencherformulario(aluno);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_formulario_ok:
                Aluno aluno = helper.pegaaluno();
                AlunoDAO dao = new AlunoDAO(this);
                if (aluno.getId()!=null){
                    dao.altera(aluno);
                }else{
                    dao.insere(aluno);
                }
                dao.close();
                Toast.makeText(FormularioActivity.this, "Aluno "+ aluno.getNome()+ " Salvo!", Toast.LENGTH_SHORT).show();
                finish();
            break;
        }
        return super.onOptionsItemSelected(item);
    }
}
