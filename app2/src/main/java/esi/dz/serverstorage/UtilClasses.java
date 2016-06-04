package esi.dz.serverstorage;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by pc on 29/04/2016.
 */
public class UtilClasses {

    public boolean checkNetwork(Context ctx) {
        ConnectivityManager cm = (ConnectivityManager) ctx.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo= cm.getActiveNetworkInfo();
        return (networkInfo!=null&&networkInfo.isConnectedOrConnecting());
    }

}

