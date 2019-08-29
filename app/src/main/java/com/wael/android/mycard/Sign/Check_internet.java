package com.wael.android.mycard.Sign;

import android.app.Service;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.widget.Toast;

import com.wael.android.mycard.Sign.View.SplashActivity;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;


public class Check_internet extends AsyncTask<String, Void, Integer> {
    Context context;
        private SplashActivity activity;
    public Check_internet(SplashActivity activity) {

        this.activity=activity;

    }



    public  boolean isConnected()
    {
        ConnectivityManager connectivityManager=(ConnectivityManager)activity.getBaseContext().getSystemService(Service.CONNECTIVITY_SERVICE);

        if (connectivityManager!=null)
        {
            NetworkInfo info=connectivityManager.getActiveNetworkInfo();
            if (info!=null)
            {
                if (info.getState()== NetworkInfo.State.CONNECTED)
                {
                    return true;
                }
            }
        }
        return false;
    }



    @Override
    protected Integer doInBackground(String... params) {

        Integer result=0;
        try {
            Socket socket=new Socket();
            SocketAddress socketAddress=new InetSocketAddress("8.8.8.8",53);
            socket.connect(socketAddress,1500);
            socket.close();
            result=1;
        } catch (IOException e) {
            e.printStackTrace();
            result=0;
        }

        return result;
    }

    @Override
    protected void onPostExecute(Integer result) {
        if (isConnected())
        {
            if (result==1)
            {
                Toast.makeText(activity.getBaseContext(), "  internet available ", Toast.LENGTH_SHORT).show();
            }

            if(result==0)
            {
                Toast.makeText(activity.getBaseContext(), " No internet available ", Toast.LENGTH_SHORT).show();
            }
        }
        else
        {
            Toast.makeText(activity.getBaseContext(), " No internet available ", Toast.LENGTH_SHORT).show();
        }
        super.onPostExecute(result);
        activity.setWhat(result);
    }
}
