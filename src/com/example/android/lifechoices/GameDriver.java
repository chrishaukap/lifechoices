package com.example.android.basicglsurfaceview;

import android.app.Application;
import android.os.Environment;
import android.util.Log;

public class GameDriver extends Application {
    private final String TAG = this.getClass().getSimpleName();

    private static String EXTERNAL_STORAGE_DIRECTORY = Environment.getExternalStorageDirectory().toString();
    final static String TARGET_BASE_PATH = EXTERNAL_STORAGE_DIRECTORY + "/GeoViewer";

    private BasicGLSurfaceView mGLView = null;
    private CharacterInfo mCharacter = null;
    public CharacterInfo getCharacter() {return mCharacter;}
    
    @Override
    public void onCreate() {
        Log.i(TAG, "onCreate");

        super.onCreate();
        mCharacter = new CharacterInfo();
    }
    
    private boolean paused = false;
    public boolean ispaused() {return paused;}
    public void setPause(boolean _isPaused) {paused = _isPaused;}
    public void update()
    {
       mCharacter.update();
    }

    public void reinit(BasicGLSurfaceView view) {
        Log.d(TAG, "reinit");
        mGLView = view;
    }

    public BasicGLSurfaceView getGLView() {
        return mGLView;
    }

}