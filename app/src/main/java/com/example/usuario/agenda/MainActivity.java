package com.example.usuario.agenda;
import java.util.List;
import com.example.usuario.agenda.dao.AlunoDAO;
import com.example.usuario.agenda.modelo.Aluno;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    private ListView listaAlunos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listaAlunos = (ListView) findViewById(R.id.lista_alunos);

        listaAlunos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int positon, long l) {
                Aluno aluno = (Aluno) listaAlunos.getItemAtPosition(positon);

                Intent intentvaiproformulario = new Intent(MainActivity.this, FormularioActivity.class);
                intentvaiproformulario.putExtra("aluno", aluno);
                startActivity(intentvaiproformulario);
            }
        });
        Button novoaluno = (Button) findViewById(R.id.novo_aluno);
        novoaluno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent vaiproformulario = new Intent(MainActivity.this, FormularioActivity.class);
                startActivity(vaiproformulario);
            }
        });
        registerForContextMenu(listaAlunos);
    }

    private void carregalista() {
        //isso busca do banco
        AlunoDAO dao = new AlunoDAO(this);
        List<Aluno> alunos = dao.buscaalunos();
        dao.close();

        //isso joga na lista
        ArrayAdapter<Aluno> adapter = new ArrayAdapter<Aluno>(this, android.R.layout.simple_list_item_1, alunos);
        listaAlunos.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        carregalista();

    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, final ContextMenu.ContextMenuInfo menuInfo) {
        MenuItem deletar = menu.add("Deletar");
        deletar.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener(){
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
                Aluno aluno = (Aluno) listaAlunos.getItemAtPosition(info.position);

                AlunoDAO dao = new AlunoDAO(MainActivity.this);
                dao.deleta(aluno);
                dao.close();
                carregalista();
                return false;
            }
        });
    }

}
