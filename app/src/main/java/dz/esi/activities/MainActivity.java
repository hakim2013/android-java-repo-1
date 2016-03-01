package dz.esi.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import dz.esi.adapter.CutomAdapter;
import dz.esi.model.Book;

public class MainActivity extends AppCompatActivity {
    CutomAdapter cutomAdapter;
    ListView listView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView) findViewById(R.id.listView);
        cutomAdapter = new CutomAdapter(this,getBookList());
        listView.setAdapter(cutomAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), DetailActivity.class);
                intent.putExtra("book", (Book) cutomAdapter.getItem(position));
                startActivity(intent);
            }
        });


        SearchView searchView = (SearchView) findViewById(R.id.searchView);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                cutomAdapter.getFilter().filter(newText);
                return false;
            }
        });

    }



    private List<Book> getBookList() {
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
        book.setTitle("UML 2.0 in a Nutshell");
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
        // le 4eme livre
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
        return bookList;


    }
}
