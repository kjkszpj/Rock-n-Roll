package com.example.you.rocknroll;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class ItemFile extends Service {
    private Context context;
    public ItemFile(Context context) {
        super();
        this.context = context;
    }

    public void save(String fileName, String content) throws Exception {
        try {
            content += '\t';
            FileOutputStream outputStream = context.openFileOutput(fileName, context.MODE_APPEND);
            outputStream.write(content.getBytes());
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String load(String fileName) throws Exception {
        byte[] buffer = new byte[4096];

        int len;

        FileInputStream inputStream = context.openFileInput(fileName);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        try {
            while ((len = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, len);
            }
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        byte[] data = outputStream.toByteArray();
        return new String(data);
    }

    public void delete(String fileName) {
        context.deleteFile(fileName);
        /*
        ------also
        File file = new File(context.getFilesDir(), fileName);
        file.delete();
        */
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
