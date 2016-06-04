package esi.dz.serverstorage;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

/**
 * Created by pc on 29/04/2016.
 */
public class AddBookTask extends
        AsyncTask<String,Void,String> {

    private Context context;
  //  ProgressDialog pd;

    public AddBookTask(Context context) {
        this.context = context;
    }

    /*@Override
    protected void onPreExecute() {
        // Création et affichage du ProgressDialog
        pd = new ProgressDialog(context);
        pd.setTitle("Please Wait..");
        pd.setMessage("Loading...");
        pd.show();
    }*/
    @Override
    protected String doInBackground(String... params) {
        String result="" ;
            try {
                URL url = new URL("http://10.0.2.41:8080/addbook");
                HttpURLConnection  conn = (HttpURLConnection) url.openConnection();
                conn.setDoOutput(true);
                conn.setRequestMethod("POST");
                OutputStream outputStream = conn.getOutputStream();
                // Ecrire les données de la requete
                outputStream.write(params[0].getBytes("UTF-8"));
                if (conn.getResponseCode() == 200) {
                // Lire la réponse
                    InputStream is = conn.getInputStream();
                    BufferedReader reader =
                         new BufferedReader
                                 (new InputStreamReader(is, "UTF-8"));
                       result = reader.readLine();
                    }
                }
            catch (Exception e) {
                e.printStackTrace();
            }
        return result;
        }

    @Override
    protected void onPostExecute(String s) {
           // pd.dismiss();
        if(s.equals("1")) {
            Toast.makeText(context, "sucess", Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(context, "error", Toast.LENGTH_SHORT).show();
        }
    }
}




