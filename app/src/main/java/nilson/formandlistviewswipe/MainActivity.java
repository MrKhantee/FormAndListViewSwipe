package nilson.formandlistviewswipe;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Shader;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.ScrollView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    FrameLayout grupo;
    Button bt1, bt2;
    ListView lista;
    EditText etTitulo, etTexto;
    ScrollView form;


    ArrayList<String> titulo_array = new ArrayList<>();
    ArrayList<String> texto_array = new ArrayList<>();
    ArrayList<Integer> imagem_array = new ArrayList<>();
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


        //adapter = new MyAdapter(this, titulo_array, texto_array, imagem_array);
        adapter = new MyAdapter(this);

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
                    exibirLista();
                }else{
                    exibirForm();
                }

            }
        });

        SwipeListViewTouchListener touchListener =
                new SwipeListViewTouchListener(
                        lista,
                        new SwipeListViewTouchListener.OnSwipeCallback() {

                            @Override
                            public void onSwipeLeft(ListView listView, int pos) {
                                // TODO : YOUR CODE HERE FOR RIGHT ACTION
                            }

                            @Override
                            public void onSwipeRight(ListView listView, int pos) {
                                // TODO : YOUR CODE HERE FOR RIGHT ACTION

                                adapter.remove(pos);
                            }
                        },
                        true, // example : left action = dismiss
                        false); // example : right action without dismiss animation
        lista.setOnTouchListener(touchListener);
        // Setting this scroll listener is required to ensure that during ListView scrolling,
        // we don't look for swipes.
        lista.setOnScrollListener(touchListener.makeScrollListener());

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

        adapter.add(etTitulo.getText().toString(),etTexto.getText().toString(),circleImage());

        etTitulo.setText("");
        etTexto.setText("");

        exibirLista();
    }

    private Bitmap circleImage(){
        Random r = new Random();
        int i1 = r.nextInt(25 - 1) + 1;//de 1 a 24
        int res_id = this.getResources().getIdentifier("p"+i1, "drawable", this.getPackageName());
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), res_id);
        Bitmap circleBitmap = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        BitmapShader shader = new BitmapShader (bitmap,  Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
        Paint paint = new Paint();
        paint.setFlags(Paint.ANTI_ALIAS_FLAG);
        paint.setShader(shader);
        Canvas c = new Canvas(circleBitmap);
        c.drawCircle(bitmap.getWidth()/2, bitmap.getHeight()/2, bitmap.getWidth()/2, paint);
        return circleBitmap;
    }
}
