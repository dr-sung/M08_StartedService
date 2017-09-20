package edu.uco.hsung.m08_startedservice;


import android.app.IntentService;
import android.content.Intent;
import android.widget.Toast;

public class DownloadIntentService extends IntentService {
    /**
     * A constructor is required, and must call the super IntentService(String)
     * constructor with a name for the worker thread.
     */
    public DownloadIntentService() {
        super("DownloadIntentService");
    }

    /**
     * The IntentService calls this method from the default worker thread with
     * the intent that started the service. When this method returns, IntentService
     * stops the service, as appropriate.
     */
    @Override
    protected void onHandleIntent(Intent intent) {
        // Do work. For our sample, we just sleep for 5 seconds.

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
        }

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        Toast.makeText(this, "download service started at " + new java.util.Date(), Toast.LENGTH_SHORT).show();

        // if you override onStartCommand(), be sure to return the default implementation
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        Toast.makeText(this, "download service done at "+new java.util.Date(), Toast.LENGTH_SHORT).show();

        // if you override onDestroy(), be sure to call the default implementation
        super.onDestroy();
    }
}
