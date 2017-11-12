package com.example.acer.merisaathi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

public class Menstrual_Tips extends AppCompatActivity {
    ListView lv1;
    String[] mtips={
            "sanitaion",
            "pain"
    };
   int[] imageId= {
           R.drawable.orange,
           R.drawable.cat
   };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menstrual__tips);
        lv1 = (ListView) findViewById(R.id.menstrual_Tips_lv1);
        customview adapter = new customview(Menstrual_Tips.this,mtips,imageId);
        lv1.setAdapter(adapter);
        lv1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Toast.makeText(Menstrual_Tips.this, "You clicked "+ mtips[position],  Toast.LENGTH_SHORT).show();
            }
        });



    }
}
