package com.dahua.oz.fasttraffic;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.dahua.oz.t.core.app.Traffic;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toast.makeText(Traffic.getApplicationContet(), "传入Context了", Toast.LENGTH_LONG).show();
    }
}
