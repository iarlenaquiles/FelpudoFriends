package com.tecnologia.aquiles.felpudofriends;

import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    String [] listaNomes = {"Felpudo", "Fofura", "Lesmo", "Bugado", "Uruca", "Racing", "iOS", "Android", "RealidadeAumentada",
    "Sound FX", "3D Studio Max", "Games"};

    int[] listaIcones = {R.drawable.felpudo, R.drawable.fofura, R.drawable.lesmo, R.drawable.bugado, R.drawable.uruca, R.drawable.carrinho, R.drawable.ios, R.drawable.android, R.drawable.realidade_aumentada,
            R.drawable.sound_fx, R.drawable.max, R.drawable.games};

    String[] listaDescricao = {"","","","","","","","","","","",""};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView minhaLista = findViewById(R.id.minhaLista);
        final MeuAdaptador meuAdaptador = new MeuAdaptador(getApplicationContext(), R.layout.minha_celula);
        int i = 0;

        for(String nome: listaNomes) {
            DadosPersonagem dadosPersonagem = new DadosPersonagem(listaIcones[i], nome, listaDescricao[i]);
            meuAdaptador.add(dadosPersonagem);
            i++;
        }
        minhaLista.setAdapter(meuAdaptador);

        minhaLista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                DadosPersonagem dadosPersonagem = (DadosPersonagem) meuAdaptador.getItem(position);
                criaAlerta(dadosPersonagem);
            }
        });
    }

    void criaAlerta(DadosPersonagem dadosPersonagem) {
        AlertDialog.Builder meuAlerta = new AlertDialog.Builder(MainActivity.this);
        meuAlerta.setTitle(dadosPersonagem.getTitulo());
        meuAlerta.setMessage(dadosPersonagem.getDescricao());
        meuAlerta.setCancelable(true);
        meuAlerta.setIcon(dadosPersonagem.getIcone());

        meuAlerta.setPositiveButton("Confirma", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(MainActivity.this, "Clicou em confirma", Toast.LENGTH_SHORT).show();
            }
        });
        meuAlerta.setNegativeButton("Cancela", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(MainActivity.this, "Clicou em cancelar", Toast.LENGTH_SHORT).show();
            }
        });
        meuAlerta.create();
        meuAlerta.show();
    }
}

class DadosPersonagem {
    private int icone;
    private String titulo;
    private String descricao;

    public DadosPersonagem(int icone, String titulo, String descricao) {
        this.icone = icone;
        this.titulo = titulo;
        this.descricao = descricao;
    }

    public int getIcone() {
        return icone;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescricao() {
        return descricao;
    }
}

class ViewPersonagem{
    ImageView icone;
    TextView titulo;
    TextView descricao;
}

class MeuAdaptador extends ArrayAdapter{
    public MeuAdaptador(Context context, int resource) {
        super(context, resource);
    }

    @Override
    public void add(@Nullable Object object) {
        super.add(object);
    }

    @Override
    public int getCount() {
        return super.getCount();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View minhaView;
        minhaView = convertView;
        ViewPersonagem viewPersonagem;

        if(convertView == null) {
            LayoutInflater inflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            minhaView = inflater.inflate(R.layout.minha_celula, parent, false);

            viewPersonagem = new ViewPersonagem();
            viewPersonagem.icone = (ImageView) minhaView.findViewById(R.id.meuIcone);
            viewPersonagem.titulo = (TextView) minhaView.findViewById(R.id.meuTitulo);
            viewPersonagem.descricao = (TextView) minhaView.findViewById(R.id.descricao);

            minhaView.setTag(viewPersonagem);
        } else {
            viewPersonagem = (ViewPersonagem) minhaView.getTag();
        }

        DadosPersonagem dadosPersonagem;
        dadosPersonagem = (DadosPersonagem) this.getItem(position);
        viewPersonagem.icone.setImageResource(dadosPersonagem.getIcone());
        viewPersonagem.titulo.setText(dadosPersonagem.getTitulo());
        viewPersonagem.descricao.setText(dadosPersonagem.getDescricao());

        return minhaView;
    }
}