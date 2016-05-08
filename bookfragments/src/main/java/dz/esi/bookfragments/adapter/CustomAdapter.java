package dz.esi.bookfragments.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.List;

import dz.esi.bookfragments.R;
import dz.esi.bookfragments.model.Author;
import dz.esi.bookfragments.model.Book;
import dz.esi.bookfragments.util.UtilService;


public class CustomAdapter extends BaseAdapter  {
    private Context context;
    private List<Book> bookList;


    public CustomAdapter(Context context, List<Book> bookList) {
        this.context = context;
        this.bookList = bookList;
    }

    @Override
    public int getCount() {
        return bookList.size();
    }

    @Override
    public Object getItem(int position) {
        return bookList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = parent.inflate(context, R.layout.list_items, null);
        }
        ImageView coverIcon = (ImageView) convertView.findViewById(R.id.coverIcon);
        TextView textTitle = (TextView) convertView.findViewById(R.id.title);
        TextView textAuthors = (TextView) convertView.findViewById(R.id.authors);
        TextView textEditor = (TextView) convertView.findViewById(R.id.editor);
        // convertir en bitmap
        coverIcon.setImageBitmap(new UtilService().getImageByte(bookList.get(position).getIconCover()));
        textTitle.setText(bookList.get(position).getTitle());
        // Récupérer la liste des auteurs
        Author[] bookAuthors = bookList.get(position).getListAuthors();
        // Séparer la liste des auteurs par une virgule
        String authors = bookAuthors[0].getFirstName()+" "+bookAuthors[0].getLastName();
        int tabLenght = bookAuthors.length;
            for (int i = 1; i < tabLenght; i++) {
                authors = authors + ", " + bookAuthors[i].getFirstName()+" "+bookAuthors[i].getLastName();
        }
        textAuthors.setText("Auteur(s): "+ authors);
        textEditor.setText("Editeur: " + bookList.get(position).getEditor());

        return convertView;
    }



}
