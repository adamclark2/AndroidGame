package adam.com.androidgame.Model;

import android.opengl.GLES20;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

/**
 * Created by Adam Clark on 5/16/2018.
 */

public class Triangle {
    private FloatBuffer floatBuffer;
    private static final int CORDINATES_PER_VERTEX = 3;
    private static final int VERTICES = 3;
    private static final int SIZE_OF_FLOAT = 4;

    private float color[] = {1.0f, 0.0f, 0.0f, 1.0f};

    public Triangle(){
        float[] cordinates = {
                0.0f,  0.622008459f, 0.0f, // top
                -0.5f, -0.311004243f, 0.0f, // bottom left
                0.5f, -0.311004243f, 0.0f  // bottom right
        };

        ByteBuffer bb = ByteBuffer.allocateDirect(4 * 9);
        bb.order(ByteOrder.nativeOrder());
        floatBuffer = bb.asFloatBuffer();
        floatBuffer.put(cordinates);
    }

    public FloatBuffer getFloatBuffer(){
        floatBuffer.position(0);
        return floatBuffer;
    }

    public float[] getColor(){
        return color;
    }

    public void draw(int program){
        GLES20.glUseProgram(program);

        // Enable vertex shader position attribute
        int pos = GLES20.glGetAttribLocation(program, "vPosition");
        GLES20.glEnableVertexAttribArray(pos);
        GLES20.glVertexAttribPointer(
                pos,
                CORDINATES_PER_VERTEX,
                GLES20.GL_FLOAT,
                false,
                SIZE_OF_FLOAT * VERTICES,
                getFloatBuffer()
        );

        // Set Color
        int colorParam = GLES20.glGetUniformLocation(program, "vColor");
        GLES20.glUniform4fv(colorParam, 1, getColor(), 0);

        // Let's Draw!
        GLES20.glDrawArrays(GLES20.GL_TRIANGLES, 0, 3);
        GLES20.glDisableVertexAttribArray(pos);
    }
}
