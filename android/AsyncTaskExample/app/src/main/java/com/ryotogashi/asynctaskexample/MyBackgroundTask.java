package com.ryotogashi.asynctaskexample;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;

import java.lang.ref.WeakReference;

public class MyBackgroundTask extends AsyncTask<Void, Integer, String> {
    // AsyncTask<p1, p2, p3>
    // p1: the data type of the parameters sent to the task upon executing the doInBackground() method.
    // p2: Progress = the data type of the progress units published using onProgressUpdated() method
    // p3: Result - The data type of the result delivered by the onPostExecute() method

    private WeakReference<TextView> mTextView;
    private int seconds;

    public MyBackgroundTask(TextView textView, int seconds) {
        mTextView = new WeakReference<>(textView);
        this.seconds = seconds;
    }

    @Override
    protected String doInBackground(Void... voids) {
        // tasks you want to do in the background
        for(int i = 0; i < this.seconds; i++){
            try{
                Thread.sleep(1000);
                // this method can be called from doInBackground() to publish
                // updates on the MainThread while the background work is still running.
                // each call to this method will trigger the execution of onProgressUpdate()
                // on the mainThread.
                publishProgress((i + 1) * 100 / seconds);
            } catch(InterruptedException e){
                e.printStackTrace();
            }

        }
        return "I am back from a "+ this.seconds + " sec nap!";
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);

//        Log.d("MyBackgroundTask", "onProgressUpdate: " + values[0]);
        mTextView.get().setText("Completed: " + values[0] + "%");
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        // returns the value back to the main thread
        // we want to display returned text
        mTextView.get().setText(s);
    }
}
