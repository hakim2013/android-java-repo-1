package esi.dz.fragmentsimple;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * Created by pc on 06/03/2016.
 */
public class MainFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main,null);
        ListView listView = (ListView) view.findViewById(R.id.listView);
        String [] items = {"item1","item2","item3","item4","item5","item6"};
        ArrayAdapter arrayAdapter =
                new ArrayAdapter(getActivity(),android.R.layout.simple_list_item_1,items);
        listView.setAdapter(arrayAdapter);
        return view;
    }


}
