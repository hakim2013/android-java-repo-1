package esi.dz.serverstorage;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void afficher(View v) {
        if(new UtilClasses().checkNetwork(this)) {
            new Traitement(this).execute();
        }
        else {
            Toast.makeText(this, "Pas de connexion", Toast.LENGTH_SHORT).show();
        }

    }
}
