package dz.esi.bookfragments.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import dz.esi.bookfragments.R;
import dz.esi.bookfragments.model.Author;
import dz.esi.bookfragments.model.Book;
import dz.esi.bookfragments.util.UtilService;


public class DetailFragment extends Fragment {



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.detail_fragment, null);
        Bundle bundle = getArguments();
        if (bundle != null) {
            Book book = (Book) bundle.getSerializable("book");
            ImageView coverImage = (ImageView) v.findViewById(R.id.coverImage);
            TextView textSummary = (TextView) v.findViewById(R.id.summary);
            TextView textYear = (TextView) v.findViewById(R.id.yearText);
            TextView textTitle = (TextView) v.findViewById(R.id.textTitle);
            TextView textEditor = (TextView) v.findViewById(R.id.editorText);
            TextView textAuthor = (TextView) v.findViewById(R.id.textAuthor);
            ((TextView) v.findViewById(R.id.textView)).setVisibility(v.VISIBLE);
            // convertir en bitmap
            coverImage.setImageBitmap(new UtilService().getImageByte(book.getCover()));
            textSummary.setText(book.getSummary());
            textTitle.setText("Titre: "+book.getTitle());
            textYear.setText("Année d'édition: "+book.getYear());
            textEditor.setText("Editeur: "+book.getEditor());
            Author[] authors = book.getListAuthors();
            //Affichage auteurs
            String authorsName = authors[0].getFirstName()
                    +" " +authors[0].getLastName();
            for (int i = 1; i < authors.length; i++) {
                authorsName= authorsName+","+
                        authors[i].getFirstName()+" "
                        +authors[i].getLastName();
            }
            textAuthor.setText("Auteur(s): "+authorsName);
        }
        return v;
    }


}
