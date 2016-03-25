package nilson.formandlistviewswipe;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    FrameLayout grupo;
    Button bt1, bt2;
    ListView lista;
    EditText etTitulo, etTexto;
    ScrollView form;


    ArrayList<String> titulo_array = new ArrayList<>();
    ArrayList<String> texto_array = new ArrayList<>();
    MyAdapter adapter;

    final long TEMPO = 500;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        grupo = (FrameLayout) findViewById(R.id.grupo);
        lista = (ListView) findViewById(R.id.lista);
        form = (ScrollView) findViewById(R.id.scroll);

        bt1 = (Button) findViewById(R.id.button1);
        bt2 = (Button) findViewById(R.id.button2);

        etTitulo = (EditText) findViewById(R.id.etTitulo);
        etTexto = (EditText) findViewById(R.id.etTexto);


        adapter = new MyAdapter(this, titulo_array,texto_array);

        lista.setAdapter(adapter);

        int indexLista = grupo.indexOfChild(lista);
        int indexForm = grupo.indexOfChild(form);

        lista.setAlpha(0f);
        if(indexLista<indexForm)
            form.bringToFront();


        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int indexLista = grupo.indexOfChild(lista);
                int indexForm = grupo.indexOfChild(form);
                if(indexLista<indexForm){
                    Log.e("VV","Passando a lista pra frente");
                    exibirLista();
                }else{
                    Log.e("VV","Passando o form pra frente");
                    exibirForm();
                }

            }
        });

    }

    private void exibirLista(){
        lista.bringToFront();
        form.animate()
                .translationY(-form.getHeight())
                .alpha(0)
                .setDuration(TEMPO)
                .setListener(null).setStartDelay(0);
        lista.animate()
                .translationX(0)
                .alpha(1)
                .setDuration(TEMPO)
                .setListener(null).setStartDelay(TEMPO-TEMPO/3);
    }

    private void exibirForm(){
        form.bringToFront();
        lista.animate()
                .translationY(0)
                .alpha(0)
                .setDuration(TEMPO)
                .setListener(null).setStartDelay(0);


        form.animate()
                .translationY(0)
                .alpha(1)
                .setDuration(TEMPO)
                .setListener(null).setStartDelay(TEMPO-TEMPO/3);
    }

    public void criar_registro(View v){
        titulo_array.add(etTitulo.getText().toString());
        texto_array.add(etTexto.getText().toString());
        adapter.notifyDataSetChanged();
        etTitulo.setText("");
        etTexto.setText("");

        exibirLista();
    }
}
