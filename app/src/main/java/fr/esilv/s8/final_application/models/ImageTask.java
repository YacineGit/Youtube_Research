package fr.esilv.s8.final_application.models;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.widget.ImageView;

import java.io.InputStream;

public class ImageTask {

    ImageView image;

    public ImageTask(ImageView image) {
        this.image = image;
    }

    protected Bitmap doInBackground(String... urls){
        String urldisplay = urls[0];
        Bitmap mIcon = null;
        try {
            InputStream in = new java.net.URL(urldisplay).openStream();
            mIcon = BitmapFactory.decodeStream(in);
        } catch (Exception e) {
            Log.e("Error", e.getMessage());
            e.printStackTrace();
        }
        return mIcon;
    }

    protected void onPostExecute(Bitmap result) {
        image.setImageBitmap(result);
    }
}
