package dz.esi.bookfragments.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import dz.esi.bookfragments.R;
import dz.esi.bookfragments.service.GetBookTask;
import dz.esi.bookfragments.util.UtilService;


public class MainFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.main_fragment,null);
        // tester si le dispositif est connecté
        // ne pas effecuter ce test si le serveur est connecté au point d'accès du dispositif
        if(new UtilService().checkNetwork(getActivity())) {
            new GetBookTask(getActivity()).execute(getScreenDensity());
        }
        else {
            Toast.makeText(getActivity(),"Aucune connexion", Toast.LENGTH_SHORT).show();
        }
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
