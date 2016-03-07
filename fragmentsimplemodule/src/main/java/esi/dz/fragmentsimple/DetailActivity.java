package esi.dz.fragmentsimple;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        DetailFragment detailFragment = new DetailFragment();
        Intent intent = getIntent();
        Bundle bundle = new Bundle();
        bundle.putString("item",intent.getStringExtra("item"));
        detailFragment.setArguments(bundle);
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.frameLayout, detailFragment);
        ft.commit();
    }
}
