package com.example.mapapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentProviderClient;
import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Uri uri = Uri.parse("content://"+DbContract.AUTHORITY+"/"+DbContract.PATH+"/latlong");
        ContentResolver resolver = getContentResolver();
        ArrayList<LatLng> latLngArrayList = new ArrayList();

        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor cursor = resolver.query(uri, null,null,null,null);

                if(cursor.moveToFirst()){
                    do{
                        LatLng latLng = new LatLng(cursor.getDouble(0), cursor.getDouble(1));
                        latLngArrayList.add(latLng);
                    }while(cursor.moveToNext());
                }
                Intent intent = new Intent(MainActivity.this, MapsActivity.class);
                intent.putExtra("array",latLngArrayList);
                startActivity(intent);
            }
        });
    }
}