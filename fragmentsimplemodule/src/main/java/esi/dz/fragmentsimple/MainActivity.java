package esi.dz.fragmentsimple;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
          listView = (ListView)findViewById(R.id.listView);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                showDetail(listView.getAdapter().getItem(position).toString());
            }
        });
    }

    public boolean isTwoPane() {
        View view = findViewById(R.id.frameLayout);
        return (view != null);
    }

    public void showDetail(String item) {
        if (isTwoPane()) {
            DetailFragment detailFragment = new DetailFragment();
            Bundle bundle = new Bundle();
            bundle.putString("item",item);
            detailFragment.setArguments(bundle);
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            ft.replace(R.id.frameLayout,detailFragment);
            ft.commit();

        }
        else {
            Intent intent = new Intent(this,DetailActivity.class);
            intent.putExtra("item",item);
            startActivity(intent);
        }

    }
}
