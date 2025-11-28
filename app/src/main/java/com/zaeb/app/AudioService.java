package com.zaeb.app;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class AudioService extends Service {

    private AudioHttpServer server;

    @Override
    public void onCreate() {
        super.onCreate();
        server = new AudioHttpServer(8080, this);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        server.startServer();
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        server.stopServer();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}