package com.example.roundprogressbar;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.circlepregress.R;

/**
 * Created by Administrator on 2014/8/22.
 */
public class TestActivity extends Activity {
    CircleProgressBar progressbar = null;
    TextView progress_tv = null;
    int progress = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.test);
        progressbar = (CircleProgressBar) findViewById(R.id.progressbar);
        progress_tv = (TextView) findViewById(R.id.progress_tv);

        ((Button) findViewById(R.id.btn)).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                new SetProgres().execute();
            }
        });

    }

    class SetProgres extends AsyncTask<Void, Integer, Void>{
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progress = 0;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
//            super.onProgressUpdate(values);
            if (progress_tv != null)
                progress_tv.setText(values[0]+"%");
        }

        @Override
        protected Void doInBackground(Void... params) {
            while(progress <= 100){
                progress += 3;
                if (progress > 100)
                    progress = 100;
                publishProgress(progress);
                progressbar.setProgress(progress);
                try {
                    Thread.sleep(400);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }
    }
}
