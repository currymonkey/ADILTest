package com.changan.adiltest;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.changan.service.IMyAidlInterface;

public class MainActivity extends AppCompatActivity {

    Button bindBtn, unbindBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    void initView() {
        bindBtn = findViewById(R.id.bindBtn);
        unbindBtn = findViewById(R.id.unBindBtn);

        bindBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mIntent = new Intent();
                mIntent.setAction("com.changan.service.MyService");
                mIntent.setPackage("com.changan.service");
                Log.e("curry", "bind");
               bindService(mIntent, mServiceConnection, BIND_AUTO_CREATE);
            }
        });

        unbindBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("curry", "unbind:"+mIMyAidlInterface);
                    unbindService(mServiceConnection);

            }
        });
    }

    IMyAidlInterface mIMyAidlInterface;
    ServiceConnection mServiceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mIMyAidlInterface = IMyAidlInterface.Stub.asInterface(service);
            try {
                Log.e("curry", mIMyAidlInterface.getHelloInfo()+"");
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            mIMyAidlInterface = null;
            Log.e("curry", "onServiceDisconnected");
        }
    };
}
