package dz.esi.bookfragments.service;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import dz.esi.bookfragments.R;
import dz.esi.bookfragments.adapter.CustomAdapter;
import dz.esi.bookfragments.model.Author;
import dz.esi.bookfragments.model.Book;


public class GetBookTask extends AsyncTask<String,Void,String> {
    private Context context;
    ProgressDialog pd;


    public GetBookTask(Context context) {
        this.context = context;
    }

    @Override
    protected void onPreExecute() {
        pd = new ProgressDialog(context);
        pd.setTitle("Please Wait..");
        pd.setMessage("Loading...");
        pd.show();
    }

    @Override
    protected String doInBackground(String... params) {
        StringBuilder result = new StringBuilder();
        String data;
        try {
            URL url = new URL("http://192.168.1.4:8080/getbooks?density="+params[0]);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            // Attendre 5 secondes pour établir la connexion
            conn.setConnectTimeout(5000);
            // Aattendre 1 minute pour lire les données
            conn.setReadTimeout(60000);
            if (conn.getResponseCode()==200) {
                InputStream is = conn.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
                while ((data = reader.readLine()) != null) {
                    result.append(data);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result.toString();
    }

    @Override
    protected void onPostExecute(String s) {
      pd.dismiss();
        if (!s.equals("")) {
            List<Book> listBook = new ArrayList<>();

            try {
                JSONArray jsonArray = new JSONArray(s);
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    Book book = new Book();
                    book.setIsbn(jsonObject.getInt("isbn"));
                    book.setTitle(jsonObject.get("title").toString());
                    book.setEditor(jsonObject.get("editor").toString());
                    book.setYear(jsonObject.get("year").toString());
                    Author[] authorsList = new Gson().fromJson(jsonObject.get("listAuthors").toString(),Author[].class);
                    book.setListAuthors(authorsList);
                    book.setSummary(jsonObject.get("summary").toString());
                    book.setCover(jsonObject.getString("cover"));
                    book.setIconCover(jsonObject.getString("iconCover"));
                    listBook.add(book);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }


            ListView listView = (ListView) ((Activity)context).findViewById(R.id.listView);
            CustomAdapter cutomAdapter = new CustomAdapter(context,listBook);
            listView.setAdapter(cutomAdapter);
        }
        else {
            Toast.makeText(context, "une erreure s'est produite", Toast.LENGTH_SHORT).show();
        }
    }


}
