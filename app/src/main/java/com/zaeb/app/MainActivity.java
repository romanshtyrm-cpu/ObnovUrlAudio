package com.zaeb.app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView statusText;
    private TextView urlText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        statusText = findViewById(R.id.statusText);
        urlText = findViewById(R.id.urlText);

        // Запуск сервиса сразу при старте приложения
        Intent serviceIntent = new Intent(this, AudioService.class);
        startService(serviceIntent);

        // Статус
        statusText.setText("ON");
        statusText.setTextColor(Color.GREEN);

        // URL сервера
        String ip = AudioHttpServer.getIpAddress();
        urlText.setText("URL: http://" + ip + ":8080");
    }
}