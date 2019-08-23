package com.changan.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;

public class MyService extends Service {
    @Override
    public IBinder onBind(Intent intent) {
        return new Mybind();
    }

    class Mybind extends IMyAidlInterface.Stub{

        @Override
        public String getHelloInfo() throws RemoteException {
            return "Hello Server!";
        }
    }
}
