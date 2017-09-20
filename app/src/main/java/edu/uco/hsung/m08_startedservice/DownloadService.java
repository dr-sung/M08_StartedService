package edu.uco.hsung.m08_startedservice;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Message;
import android.os.Process;
import android.widget.Toast;

public class DownloadService extends Service {

    private Handler serviceHandler;

    @Override
    public void onCreate() {
        // Startup the thread running the service
        // background priority so CPU-intensive work will not discrupt UI
        HandlerThread thread = new HandlerThread("ServiceStartArguments",
                Process.THREAD_PRIORITY_BACKGROUND);
        thread.start();

        serviceHandler = new Handler(thread.getLooper()) {
            @Override
            public void handleMessage(Message msg) {
                // do work (simulation 5 sec sleep)
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                }

                stopSelf(msg.arg1);
            }
        };
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Toast.makeText(this, "download service started at " + new java.util.Date(), Toast.LENGTH_SHORT).show();

        Message msg = serviceHandler.obtainMessage();
        msg.arg1 = startId;
        serviceHandler.sendMessage(msg);

        // if killed, restart
        return START_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy() {
        Toast.makeText(this, "download service done at "+new java.util.Date(), Toast.LENGTH_SHORT).show();
    }
}
