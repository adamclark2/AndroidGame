package adam.com.androidgame.View;

import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import android.util.Log;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import adam.com.androidgame.Model.DataAccess.AssetRepository;
import adam.com.androidgame.Model.Model;
import adam.com.androidgame.Model.Triangle;

/**
 * Created by Adam Clark on 5/16/2018.
 */
public class Renderer implements GLSurfaceView.Renderer {
    private Triangle triangle;

    private int vertexShader;
    private int colorShader;
    private int program;

    private Model m = new Model();
    private Triangle t = new Triangle();

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        Log.e("Debug", "Cool ce");
        // Model
        triangle = new Triangle();

        // GL Init Stuff
        GLES20.glClearColor(0,1,1,1);
        vertexShader = loadShader("vertex.gls", GLES20.GL_VERTEX_SHADER);
        colorShader = loadShader("fragShader.gls", GLES20.GL_FRAGMENT_SHADER);

        // Create Program
        program = GLES20.glCreateProgram();
        GLES20.glAttachShader(program, vertexShader);
        GLES20.glAttachShader(program, colorShader);
        GLES20.glLinkProgram(program);

        GLES20.glUseProgram(program);
    }

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {
        GLES20.glViewport(0, 0, width, height);
    }

    @Override
    public void onDrawFrame(GL10 gl) {
        GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT);
        m.draw(program);

        t.draw(program);
        t.addToX(.002f);
        if(t.isOffScreen()){
            t = new Triangle();
        }
    }

    private int loadShader(String name, int type){
        int shader = GLES20.glCreateShader(type);

        GLES20.glShaderSource(shader, AssetRepository.getInstance().loadShaderByName(name));
        GLES20.glCompileShader(shader);

        return shader;
    }
}
