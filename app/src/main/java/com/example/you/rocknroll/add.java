package com.example.you.rocknroll;

import android.app.Activity;
import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.example.you.rocknroll.R;

import java.util.ServiceConfigurationError;

public class add extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.add, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void add(View view) throws Exception{
        EditText etItemName = (EditText)findViewById(R.id.editTextItemName);
        ItemFile service = new ItemFile(getApplicationContext());
        try {
            service.save(this.getString(R.string.NAME_dataFile), etItemName.getText().toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        Intent intent = new Intent(add.this, main.class);
        startActivity(intent);
    }
}
