package esi.dz.fragmentsimple;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

/**
 * Created by pc on 06/03/2016.
 */
public class DetailFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detail, null);
        Bundle bundle = getArguments();
        if (bundle!=null) {
            TextView textView = (TextView) view.findViewById(R.id.textView);
            textView.setText(bundle.getString("item"));
        }


        return view;
    }

}
