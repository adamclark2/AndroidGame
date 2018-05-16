package adam.com.androidgame.View;

import android.opengl.GLSurfaceView;
import android.util.Log;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

/**
 * Created by Adam Clark on 5/16/2018.
 */

public class Renderer implements GLSurfaceView.Renderer {
    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        Log.e("Debug", "Cool ce");
    }

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {
        Log.e("Debug", "Cool ch");
    }

    @Override
    public void onDrawFrame(GL10 gl) {
        Log.e("Debug", "Cool draw!");
    }
}