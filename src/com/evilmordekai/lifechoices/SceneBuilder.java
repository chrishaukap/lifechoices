
package com.evilmordekai.lifechoices;

import java.io.IOException;
import java.io.InputStream;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

class SceneBuilder {

    private GameDriver mGame = null;
    public SceneBuilder(GameDriver game) {
        mGame = game;
    }
    
    // caller should recycle the returned bitmap
    private Bitmap buildSceneForEvent(Context context, Event event)
    {
        InputStream is = context.getResources().openRawResource(event.getImageID());
        Bitmap bitmap;
        try {
            bitmap = BitmapFactory.decodeStream(is);
        } finally {
            try {
                is.close();
            } catch(IOException e) {
                // Ignore.
            }
        }
        return bitmap;
    }
}


