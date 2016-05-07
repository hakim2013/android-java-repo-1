package dz.esi.bookfragments.activity;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import dz.esi.bookfragments.R;
import dz.esi.bookfragments.fragment.DetailFragment;
import dz.esi.bookfragments.model.Book;

public class MainActivity extends AppCompatActivity {
     ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (ListView) findViewById(R.id.listView);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                showView((Book) listView.getAdapter().getItem(position));
            }
        });


    }

    public boolean isTwoPane() {

        View v  = findViewById(R.id.frameLayout);
        return (v!=null);

    }

    public void showView (Book book) {
        if (isTwoPane()) {
           DetailFragment detailFragment = new DetailFragment();
           Bundle bundle = new Bundle();
            bundle.putSerializable("book", book);
            detailFragment.setArguments(bundle);
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            ft.replace(R.id.frameLayout,detailFragment);
            ft.commit();

        }
        else {
            Intent intent = new Intent(this,DetailActivity.class);
            intent.putExtra("book",book);
            startActivity(intent);
        }

    }


}
