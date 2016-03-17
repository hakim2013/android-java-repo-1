package esi.dz.booksearchtitle;

import android.app.SearchManager;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ResultSearchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_search);
        handleIntent(getIntent());
    }

    @Override
    protected void onNewIntent(Intent intent) {

        handleIntent(intent);
    }

    private void handleIntent(Intent intent) {
        Book book = null;

        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);
            List<Book> bookList = getBookList();

                for (int i = 0; i < bookList.size(); i++) {
                    if (bookList.get(i).getTitle().toLowerCase().equals(query.toLowerCase())) {
                        book= bookList.get(i);
                        displayBook(book);
                        break;
                }
            }
            if (book==null) {
                Toast.makeText(this,"Le livre n''existe pas",Toast.LENGTH_SHORT).show();
                finish();
            }


        }
    }

    public void displayBook(Book book) {
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


    public List<Book> getBookList() {
        String[] listSummary = getResources().getStringArray(R.array.summary);
        List<Book> bookList = new ArrayList<Book>();
        // le 1er livre
        Book book = new Book();
        book.setTitle("Design Patterns in Java");
        List authors = new ArrayList();
        authors.add("Steven John Metsker");
        authors.add("William C. Wake");
        book.setAuthors(authors);
        book.setEditor("Addison-Wesley Professional");
        book.setIconCover(R.drawable.ic_dpjava);
        book.setCover(R.drawable.ic_dpjavacover);
        book.setYear("2006");
        book.setSummary(listSummary[0]);
        bookList.add(book);
        // le 2eme livre
        book = new Book();
        //book.setTitle("UML 2.0 in a Nutshell");
        book.setTitle("UML 2.0 in a Nutshell UML 2.0");
        authors = new ArrayList();
        authors.add("Dan Pilone");
        authors.add("Neil Pitman");
        book.setAuthors(authors);
        book.setEditor("O\'Reilly");
        book.setIconCover(R.drawable.ic_uml_2);
        book.setCover(R.drawable.ic_uml_2cover);
        book.setYear("2005");
        book.setSummary(listSummary[1]);
        bookList.add(book);
        // le 3eme livre
        book = new Book();
        book.setTitle("Microsoft SQL Server 2012 Pocket Consultant");
        authors = new ArrayList();
        authors.add("William R. Stanek");
        book.setAuthors(authors);
        book.setEditor("Microsoft Press");
        book.setIconCover(R.drawable.ic_sqlpc);
        book.setCover(R.drawable.ic_sqlpc_cover);
        book.setYear("2012");
        book.setSummary(listSummary[2]);
        bookList.add(book);
        // le 4ème livre
        book = new Book();
        book.setTitle("Android UI Fundamentals: Develop & Design");
        authors = new ArrayList();
        authors.add("Jason Ostrander");
        book.setAuthors(authors);
        book.setEditor("Peachpit Press");
        book.setIconCover(R.drawable.ic_androidfd);
        book.setCover(R.drawable.ic_androidfdcover);
        book.setYear("2012");
        book.setSummary(listSummary[3]);
        bookList.add(book);
        // le 5ème livre
        book = new Book();
        book.setTitle("Programming in Objective-C");
        authors = new ArrayList();
        authors.add("Stephen Kochan");
        book.setAuthors(authors);
        book.setEditor("Developer's Library");
        book.setIconCover(R.drawable.ic_objectivec);
        book.setCover(R.drawable.ic_objectivecover);
        book.setYear("2012");
        book.setSummary(listSummary[4]);
        bookList.add(book);
        // le 6 ème livre
        book = new Book();
        book.setTitle("Learning Agile");
        authors = new ArrayList();
        authors.add("Andrew Stellman");
        authors.add("Jennifer Greene");
        book.setAuthors(authors);
        book.setEditor("Kindle Edition");
        book.setIconCover(R.drawable.ic_agile);
        book.setCover(R.drawable.ic_agilecovrer);
        book.setYear("2014");
        book.setSummary(listSummary[5]);
        bookList.add(book);
        // le 7 ème livre
        book = new Book();
        book.setTitle("Learning the UNIX Operating System");
        authors = new ArrayList();
        authors.add("Jerry Peek");
        authors.add("Grace T-Gonguet");
        authors.add("John Strang");
        book.setAuthors(authors);
        book.setEditor("O'Reilly Media, Inc.");
        book.setIconCover(R.drawable.ic_unixicon);
        book.setCover(R.drawable.ic_unixicover);
        book.setYear("2002");
        book.setSummary(listSummary[6]);
        bookList.add(book);
        return bookList;


    }
}
