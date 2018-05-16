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
        doRender();
    }

    public GLView(Context context, AttributeSet attrs) {
        super(context, attrs);
        doRender();
    }

    private void doRender(){
        this.setRenderer(new adam.com.androidgame.View.Renderer());
    }
}
