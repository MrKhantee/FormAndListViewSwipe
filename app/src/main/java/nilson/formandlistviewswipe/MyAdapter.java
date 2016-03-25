package nilson.formandlistviewswipe;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Nilson on 16/10/2015.
 */
public class MyAdapter extends BaseAdapter {

    Context context;
    ArrayList<String> titulo_array;
    ArrayList<String> texto_array;
    ArrayList<Bitmap> imagem_array;
    private static LayoutInflater inflater = null;

    public MyAdapter(Context context) {
        this.context = context;
        titulo_array = new ArrayList<>();
        texto_array = new ArrayList<>();
        imagem_array = new ArrayList<>();

        inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public void add(String titulo, String texto, Bitmap imagem){
        titulo_array.add(titulo);
        texto_array.add(texto);
        imagem_array.add(imagem);

        this.notifyDataSetChanged();
    }

    public void remove(int pos){
        titulo_array.remove(pos);
        texto_array.remove(pos);
        imagem_array.remove(pos);

        this.notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return titulo_array.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return titulo_array.get(position);
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        View vi = convertView;
        if (vi == null)
            vi = inflater.inflate(R.layout.adapter_my, null);



        TextView tvTitulo = (TextView) vi.findViewById(R.id.tvTitulo);
        tvTitulo.setText(titulo_array.get(position));

        TextView tvTexto = (TextView) vi.findViewById(R.id.tvTexto);
        tvTexto.setText(texto_array.get(position));

        ImageView ivImagem = (ImageView) vi.findViewById(R.id.ivImagem);

        ivImagem.setImageBitmap(imagem_array.get(position));


        return vi;
    }
}