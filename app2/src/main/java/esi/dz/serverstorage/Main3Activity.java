package esi.dz.serverstorage;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import static android.content.Context.ALARM_SERVICE;

public class Main3Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

    }
    public void ajouter(View v) {

        List<Book> bookList  = new ArrayList<>();
        Book book = new Book();
        book.setAuthor("");
        book.setCategory("");
        book.setTitle("Java8");
        bookList.add(book);
        book = new Book();
        book.setAuthor("");
        book.setCategory("");
        book.setTitle("Java6");
        bookList.add(book);
        book = new Book();
        book.setAuthor("");
        book.setCategory("");
        book.setTitle("Java7");
        bookList.add(book);
        AlarmManager alarmManager =
                (AlarmManager)
                getSystemService(Context.ALARM_SERVICE);
        Intent intent =
                new Intent(this,AddBooksServices.class);

     intent.putExtra("books", new Gson().toJson(bookList));
        PendingIntent pendingIntent = PendingIntent.
                getService(this,0,intent,0);
        alarmManager.
                set(AlarmManager.ELAPSED_REALTIME_WAKEUP,
                SystemClock.elapsedRealtime() + 1000 * 5,
                        pendingIntent);


    }
}
