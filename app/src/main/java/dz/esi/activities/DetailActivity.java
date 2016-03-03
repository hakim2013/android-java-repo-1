package dz.esi.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import dz.esi.model.Book;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Intent intent = getIntent();
        Book book = (Book) intent.getSerializableExtra("book");
        ImageView coverImage = (ImageView) findViewById(R.id.coverImage);
        TextView textSummary = (TextView) findViewById(R.id.summary);
        TextView textYear = (TextView) findViewById(R.id.yearText);
        TextView textTitle = (TextView) findViewById(R.id.textTitle);
        TextView textEditor = (TextView) findViewById(R.id.editorText);
        TextView textAuthor = (TextView) findViewById(R.id.textAuthor);
        coverImage.setImageResource(book.getCover());
        textSummary.setText(book.getSummary());
        textTitle.setText("Titre: "+book.getTitle());
        textYear.setText("Année d'édition: "+book.getYear());
        textEditor.setText("Editeur: "+book.getEditor());
        textAuthor.setText("Auteur(s): "+book.getAuthors().get(0));
        if(book.getAuthors().size()>1) {
            //textAuthorLabel.setText("Auteurs: ");
            for(int i=1;i<book.getAuthors().size();i++) {
                textAuthor.setText(textAuthor.getText()+", "+book.getAuthors().get(i));
            }
        }
    }
}
