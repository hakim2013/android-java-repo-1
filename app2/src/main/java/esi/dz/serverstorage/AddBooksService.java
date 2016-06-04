package esi.dz.serverstorage;

import android.app.IntentService;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by pc on 01/06/2016.
 */
public class AddBooksService extends IntentService {


    public AddBooksService() {
        super("name");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        String result="";
        try {

            URL url = new URL("http://10.0.2.41:8080/addbooks");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            conn.setConnectTimeout(5000);
            OutputStream outputStream = conn.getOutputStream();
            // Ecrire les données de la requete
 outputStream.write(intent.getStringExtra("books").getBytes("UTF-8"));
            if (conn.getResponseCode() == 200) {
                // Lire la réponse
                InputStream is = conn.getInputStream();
                BufferedReader reader =
                        new BufferedReader
                                (new InputStreamReader(is, "UTF-8"));
                result = reader.readLine();
                if(result.equals("1"))  {
                sendNotification("Data inserted",this);
                    // annuler l'alarme
                }
            }
            else {
                // relancer l'alarme
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            // relancer l'alarme
        }

    }

    private void sendNotification(String message,Context ctx) {

        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(ctx)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("Notification")
                .setContentText(message)
                .setAutoCancel(false)
                .setSound(defaultSoundUri);

        NotificationManager notificationManager =
                (NotificationManager) ctx.getSystemService(Context.NOTIFICATION_SERVICE);

        notificationManager.notify(0 , notificationBuilder.build());
    }
}
