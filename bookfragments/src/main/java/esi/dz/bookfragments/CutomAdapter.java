package esi.dz.bookfragments;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.List;



public class CutomAdapter extends BaseAdapter  {
    private Context context;
    private List<Book> bookList;


    public CutomAdapter(Context context, List<Book> bookList) {
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
        // mettre le layout une seule fois lorsque convertView est null
        if (convertView == null) {
            convertView = parent.inflate(context, R.layout.list_items, null);
        }
        ImageView coverIcon = (ImageView) convertView.findViewById(R.id.coverIcon);
        TextView textTitle = (TextView) convertView.findViewById(R.id.title);
        TextView textAuthors = (TextView) convertView.findViewById(R.id.authors);
        TextView textEditor = (TextView) convertView.findViewById(R.id.editor);
        coverIcon.setImageResource(bookList.get(position).getIconCover());
        textTitle.setText(bookList.get(position).getTitle());
        // Récupérer la liste des auteurs
        List<String> bookAuthors = bookList.get(position).getAuthors();
        // Séparer la liste des auteurs par une virgule
        String authors = bookAuthors.get(0);
        int listSize = bookAuthors.size();
        if (listSize > 1) {
            for (int i = 1; i < listSize; i++) {
                authors = authors + ", " + bookAuthors.get(i);
            }
            textAuthors.setText("Auteur(s): "+ authors);
        }
        textEditor.setText("Editeur: " + bookList.get(position).getEditor());

        return convertView;
    }


}
