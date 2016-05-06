package esi.dz.bookfragments;

import android.app.Fragment;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by pc on 04/03/2016.
 */
public class DetailFragment extends Fragment {



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.detail_fragment,null);
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
            coverImage.setImageBitmap(getImageByte(book.getCover()));
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

    public Bitmap getImageByte(String  image) {
        byte[] imgbytes = Base64.decode(image, Base64.DEFAULT);
        Bitmap bitmap = BitmapFactory.decodeByteArray(imgbytes, 0,
                imgbytes.length);
        return bitmap;
    }
}
