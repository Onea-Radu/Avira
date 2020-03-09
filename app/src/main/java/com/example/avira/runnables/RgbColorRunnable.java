package com.example.avira.runnables;

import android.graphics.Color;
import android.os.Message;

import com.example.avira.activities.MainActivity;

public class RgbColorRunnable implements Runnable
{
    int r = 255, g = 0, b = 0;
    @Override
    public void run() {
        while (true)
        {
            if(r == 255 && g < 255 && b == 0)
                g+=5;
            if(r > 0 && g == 255 && b == 0)
                r-=5;
            if(r == 0 && g == 255 && b < 255)
                b+=5;
            if(r == 0 && g > 0 && b == 255)
                g-=5;
            if(r < 255 && g == 0 && b == 255)
                r+=5;
            if(r == 255 && g == 0 && b > 0)
                b-=5;
            Message message = MainActivity.handler.obtainMessage();
            message.what = 1;
            message.obj = Color.rgb(r, g, b);
            message.sendToTarget();
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
