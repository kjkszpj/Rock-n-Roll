package com.example.you.rocknroll;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Random;

import static java.lang.Math.abs;


public class main extends Activity {

    private ItemFile service;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        service = new ItemFile(getApplicationContext());
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        switch (id) {
            case R.id.action_add_item:
                addItem();
                return true;
            case R.id.action_settings:
                //----------------------------------------------------------------------------------TODO setting
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void addItem() {
        Intent intent = new Intent(main.this, add.class);
        startActivity(intent);
    }

    public void add(View view) {addItem();}

    public void clear(View view) {
        service.delete(this.getString(R.string.NAME_dataFile));
    }

    public void rock(View view) throws Exception{
        ArrayList<String> items = new ArrayList<String>();
        String content;
        Random random = new Random(System.currentTimeMillis());

        //obtain data
        try {
            String s = service.load(this.getString(R.string.NAME_dataFile));
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
        String s = service.load(this.getString(R.string.NAME_dataFile));
        while (s.indexOf('\t') != -1) {
            items.add(s.substring(0, s.indexOf('\t')));
            s = s.substring(s.indexOf('\t') + 1);
        }

        //get output
        TextView output = (TextView)findViewById(R.id.output);
        if (items.size() == 0) {
            content = "No such item!";
        }
        else {
            content = items.get((int)((long)(abs(random.nextInt())) % (long)(items.size())));
        }
        output.setText(content);
    }
}
