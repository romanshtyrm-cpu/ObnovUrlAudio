package com.zaeb.app;

import android.content.Context;

import java.io.IOException;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;

public class AudioHttpServer {

    private final int port;
    private final Context context;
    private SimpleWebServer server;

    public AudioHttpServer(int port, Context context) {
        this.port = port;
        this.context = context;
    }

    public void startServer() {
        if (server == null) {
            server = new SimpleWebServer(port, context);
            try {
                server.start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void stopServer() {
        if (server != null) {
            server.stop();
        }
    }

    // ► Получение локального IP
    public static String getIpAddress() {
        try {
            for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces();
                 en.hasMoreElements();) {

                NetworkInterface intf = en.nextElement();
                for (Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses();
                     enumIpAddr.hasMoreElements();) {

                    InetAddress inetAddress = enumIpAddr.nextElement();

                    if (!inetAddress.isLoopbackAddress()
                            && inetAddress instanceof Inet4Address) {

                        return inetAddress.getHostAddress();
                    }
                }
            }
        } catch (Exception ignored) { }

        return "0.0.0.0";
    }
}