package esi.dz.serverstorage;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by pc on 22/04/2016.
 */
public class Traitement extends AsyncTask<Void, Void, String>  {
private Context context ;
   // ProgressDialog pd = null;
    ProgressBar pg;
    public Traitement(Context context) {
        this.context = context;
    }

    @Override
    protected void onPreExecute() {
      //  pd = ProgressDialog.show(context, "title", "In Progress...",true);
        pg = (ProgressBar) ((Activity)(context)).findViewById(R.id.progressBar);
        pg.setVisibility(View.VISIBLE);

    }

    @Override
    protected String doInBackground(Void... params) {

        StringBuilder result = new StringBuilder();
        String data;
        try {
            //String adr = URLEncoder.encode("http://10.0.2.2:8080/getbooks","UTF-8");
            //URL url = new URL("http://192.168.43.101:8080/getbooks");
            URL url = new URL("http://10.0.2.2:8080/getbooks");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setConnectTimeout(5000);
            //conn.setReadTimeout(2000);
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

    public Bitmap getImageByte(String  image) {
        byte[] imgbytes = Base64.decode(image, Base64.DEFAULT);
        Bitmap bitmap = BitmapFactory.decodeByteArray(imgbytes, 0,
                imgbytes.length);
        return bitmap;
    }
    @Override
    protected void onPostExecute(String s) {
        pg.setVisibility(View.INVISIBLE);
     //   pd.cancel();
        Gson gson = new Gson();
        if (!s.equals("")) {
            List<Book> list = new ArrayList<>();
//            Book[] books = gson.fromJson(s,Book[].class);
  //          list = Arrays.asList(books);
            try {
                JSONArray jsonArray = new JSONArray(s);
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    Book book = new Book();
                    book.setTitle(jsonObject.get("title").toString());
                    book.setAuthor(jsonObject.get("author").toString());
                    book.setCategory(jsonObject.get("category").toString());
                    list.add(book);
                    String image = jsonObject.getString("cover");
                   // book.setCover(getImageByte(image));
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }


            ImageView imageView = (ImageView) ((Activity)context).findViewById(R.id.imageView);
            ImageView imageView1 = (ImageView) ((Activity)context).findViewById(R.id.imageView1);
            ImageView imageView2 = (ImageView) ((Activity)context).findViewById(R.id.imageView2);
            ImageView imageView3 = (ImageView) ((Activity)context).findViewById(R.id.imageView3);

        }
        else {
            Toast.makeText(context,"Network Error",Toast.LENGTH_SHORT).show();
        }
    }

}
