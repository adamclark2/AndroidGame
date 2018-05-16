package adam.com.androidgame.View;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.util.AttributeSet;

/**
 * Created by Adam Clark on 5/16/2018.
 */

public class GLView extends GLSurfaceView {
    public GLView(Context context) {
        super(context);
        doInit();
    }

    public GLView(Context context, AttributeSet attrs) {
        super(context, attrs);
        doInit();
    }

    private void doInit(){
        // Use GLES 2.0
        setEGLContextClientVersion(2);
        this.setRenderer(new adam.com.androidgame.View.Renderer());
        setRenderMode(GLSurfaceView.RENDERMODE_WHEN_DIRTY);
    }
}
