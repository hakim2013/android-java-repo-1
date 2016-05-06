package esi.dz.bookfragments;

import android.app.Fragment;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pc on 04/03/2016.
 */
public class MainFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.main_fragment,null);
        new GetBookTask(getActivity()).execute(getScreenDensity());
        return v;
    }

    public String getScreenDensity() {
          DisplayMetrics metrics = new DisplayMetrics();
          getActivity().getWindowManager().getDefaultDisplay().getMetrics(metrics);
          String density ="";
          switch(metrics.densityDpi){
               case DisplayMetrics.DENSITY_LOW:
                density="ldpi";
               break;
               case DisplayMetrics.DENSITY_MEDIUM:
                    density= "mdpi";
                    break;
               case DisplayMetrics.DENSITY_HIGH:
                    density="hdpi";
                    break;
               case DisplayMetrics.DENSITY_XHIGH:
                    density= "xhdpi";
               break;
               case DisplayMetrics.DENSITY_XXHIGH:
                    density= "xxhdpi";
               case DisplayMetrics.DENSITY_XXXHIGH:
                    density= "xxxhdpi";
               break;
          }

     return density;

     }




}
