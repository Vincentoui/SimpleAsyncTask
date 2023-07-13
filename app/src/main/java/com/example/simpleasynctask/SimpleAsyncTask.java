package com.example.simpleasynctask;

import android.os.AsyncTask;
import android.widget.TextView;

import java.lang.ref.WeakReference;

public class SimpleAsyncTask extends AsyncTask<Void, Void, String> {

    private WeakReference<TextView> mTextView;


    SimpleAsyncTask(TextView tv) {mTextView = new WeakReference<>(tv);}

    protected String doInBackground(Integer temps, Void... voids) {
        try {
            Thread.sleep(temps);

        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }

        return "Enfin réveillé après avoir dormi pendant " + temps + " millisecondes !";

    }

    @Override
    protected String doInBackground(Void... voids) {
        return null;
    }
}