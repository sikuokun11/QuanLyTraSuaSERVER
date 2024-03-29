package com.example.serversideapp.Common;


import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;


import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;

import com.example.serversideapp.Model.Request;

import com.example.serversideapp.Model.User;
import com.example.serversideapp.Remote.IGeoCoordinates;
import com.example.serversideapp.Remote.RetrofitClient;

public class Common {
    public static User currunent_User;
    public static Request currunent_Request;

    public static final String UPDATE = "Update";
    public static final String DELETE = "Delete";

    public  static String baseUrl = "https://maps.googlepis.com";



    public static final int PICK_IMAGE_REQUEST = 71;

    public static boolean isConnectedToInterner(Context context)
    {

        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        if(connectivityManager != null)
        {
            NetworkInfo[] info = connectivityManager.getAllNetworkInfo();
            if(info != null)
            {
                for(int i = 0; i<info.length;i++)
                {
                    if(info[i].getState() == NetworkInfo.State.CONNECTED)
                        return true;
                }

            }

        }

        return false;
    }

    public static String convertCodeToStatus(String code){
        if(code.equals("0"))
            return "Placed";
        else if(code.equals("1"))
            return "On My Way";
        else
            return "Shipped";
    }


    public static IGeoCoordinates getGeoCodeService(){
        return RetrofitClient.getClient(baseUrl).create(IGeoCoordinates.class);
    }

    public static Bitmap scaleBitmap(Bitmap bitmap, int newWidth, int newHeight)
    {
        Bitmap scaledBitmap = Bitmap.createBitmap(newWidth, newHeight, Bitmap.Config.ARGB_8888);

        float scaleX = newWidth/(float)bitmap.getWidth();
        float scaleY = newHeight/(float)bitmap.getHeight();
        float pivotX = 0, pivotY = 0;

        Matrix scaleMatrix = new Matrix();
        scaleMatrix.setScale(scaleX,scaleY,pivotX,pivotY);

        Canvas canvas = new Canvas(scaledBitmap);
        canvas.setMatrix(scaleMatrix);
        canvas.drawBitmap(bitmap, 0,0, new Paint(Paint.FILTER_BITMAP_FLAG));

        return scaledBitmap;


    }



}


