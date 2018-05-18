package adam.com.androidgame.Model;

import java.nio.FloatBuffer;

/**
 * Created by Adam Clark on 5/17/2018.
 */

public interface Program {
    public int getProgramId();
    public void setParameters(String paramName, float[] value);
    public void setVertices(FloatBuffer vertices);
}
