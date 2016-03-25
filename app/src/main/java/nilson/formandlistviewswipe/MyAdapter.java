package nilson.formandlistviewswipe;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Nilson on 16/10/2015.
 */
public class MyAdapter extends BaseAdapter {

    Context context;
    ArrayList<String> titulo_array;
    ArrayList<String> texto_array;
    private static LayoutInflater inflater = null;

    public MyAdapter(Context context,
                     ArrayList<String> titulo, ArrayList<String> texto) {
        // TODO Auto-generated constructor stub
        this.context = context;

        titulo_array =titulo;
        texto_array =texto;

        inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
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


        Random r = new Random();
        int i1 = r.nextInt(22 - 1) + 1;//de 1 a 4
        int res_id = context.getResources().getIdentifier("p"+i1, "drawable", context.getPackageName());
        ivImagem.setImageResource(res_id);


        return vi;
    }
}