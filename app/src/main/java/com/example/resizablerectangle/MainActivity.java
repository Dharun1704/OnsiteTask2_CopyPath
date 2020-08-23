package com.example.resizablerectangle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import android.graphics.Path;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    DataSender receiver1, receiver2;
    public static DataSender Receiver;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Receiver = new DataSender() {
            @Override
            public void ToSendPath(ArrayList<PathStorage> DrawnPathStorage, Path path, int id) {
                if (id == 1)
                    receiver2.ToSendPath(DrawnPathStorage,path,id);
                else
                    receiver1.ToSendPath(DrawnPathStorage,path,id);
            }
        };
        setContentView(R.layout.activity_main);

        Fragment1 firstFragment = new Fragment1();
        receiver1 = firstFragment.getReceiver();
        FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction()
                .replace(R.id.firstLayout, firstFragment, firstFragment.getTag())
                .commit();

        Fragment2 secondFragment = new Fragment2();
        receiver2 = secondFragment.getReceiver();
        FragmentManager manager2 = getSupportFragmentManager();
        manager2.beginTransaction()
                .replace(R.id.secondLayout, secondFragment, firstFragment.getTag())
                .commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.newPage) {
            Fragment1 firstFragment= new Fragment1();
            receiver1= firstFragment.getReceiver();
            FragmentManager manager= getSupportFragmentManager();
            manager.beginTransaction()
                    .replace(R.id.firstLayout,firstFragment,firstFragment.getTag())
                    .commit();

            Fragment2 secondFragment= new Fragment2();
            receiver2= secondFragment.getReceiver();
            FragmentManager manager2= getSupportFragmentManager();
            manager2.beginTransaction()
                    .replace(R.id.secondLayout,secondFragment,firstFragment.getTag())
                    .commit();
        }

        return super.onOptionsItemSelected(item);
    }
}