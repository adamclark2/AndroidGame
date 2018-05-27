package adam.com.androidgame.Model.DataAccess.DAO;

import android.opengl.GLES20;
import android.util.Log;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

import adam.com.androidgame.Model.DataAccess.ProgramRepository;
import adam.com.androidgame.Model.DrawableThing;
import adam.com.androidgame.Model.Program;

/**
 * Created by Adam Clark on 5/26/2018.
 */

public class OBJClassPathDAO {
    private StringClasspathDao dao = new StringClasspathDao();

    public DrawableThing loadModel(String name){
        String modelText = dao.getStringFromClasspath(name);

        final List<Float[]> vertices = new ArrayList<>();
        final List<List<Integer>> faces = new ArrayList<>();
        final Map<Integer, String> faceToMaterial = new HashMap<>();

        Scanner sc = new Scanner(modelText);
        String material = "default";
        while (sc.hasNextLine()){
            String line = sc.nextLine();
            Scanner lnSc = new Scanner(line);
            String next = lnSc.next();
            switch (next){
                case "v":
                    Float[] arr = new Float[3];
                    arr[0] = lnSc.nextFloat();
                    arr[1] = lnSc.nextFloat();
                    arr[2] = lnSc.nextFloat();
                    vertices.add(arr);
                    Log.e("Loading", "Loading v");
                    break;

                case "f":
                    List<Integer> face = new ArrayList<>();
                    while (lnSc.hasNext()) {
                        Scanner tmp = new Scanner(lnSc.next());
                        tmp.useDelimiter("/");
                        face.add(tmp.nextInt());
                        tmp.close();
                    }
                    faces.add(face);
                    faceToMaterial.put(faces.size() - 1, material);
                    Log.e("Loading", "Loading f");
                    break;

                case "usemtl":
                    material = lnSc.next();
                    break;

            }
            lnSc.close();
        }
        sc.close();

        final float[] val = new float[1];
        val[0] = 0;
        return new DrawableThing() {
            @Override
            public void draw() {
                Random r = new Random(123);
                int index = 0;
                for (List<Integer> face : faces) {
                    List<Float> corrd = new ArrayList<>();
                    for (int ii : face) {
                        corrd.add(vertices.get(ii - 1)[0]);
                        corrd.add(vertices.get(ii - 1)[1]);
                        corrd.add(vertices.get(ii - 1)[2]);
                    }


                    int offset = 0;
                    while((face.size() + offset) % 3 != 0) {
                        corrd.add(vertices.get(face.get(offset) - 1)[0]);
                        corrd.add(vertices.get(face.get(offset) - 1)[1]);
                        corrd.add(vertices.get(face.get(offset) - 1)[2]);

                        offset++;
                    }

                    Program p = ProgramRepository.getInstance().getProgramByName("flatColor");
                    int program = p.getProgramId();
                    GLES20.glUseProgram(program);

                    p.setVertices(toFloatBuffer(corrd));
                    int xOff = GLES20.glGetAttribLocation(program, "xOff");
                    GLES20.glVertexAttrib1f(xOff, val[0]);
                    p.setParameters("color", loadMaterial(r, faceToMaterial.get(index)));

                    // Let's Draw!
                    GLES20.glDrawArrays(GLES20.GL_TRIANGLE_FAN, 0, corrd.size() / 3);

                    int err = GLES20.glGetError();
                    if (err != GLES20.GL_NO_ERROR) {
                        Log.e("GL ERR", "NUMBER " + err);
                    }

                    if(val[0] > 1){
                        val[0] = -1;
                    }
                    val[0] += 0.002f;
                    index++;
                }
            }
        };
    }

    protected static FloatBuffer toFloatBuffer(List<Float> corrd){
        int sizeOfFloat = 4;
        ByteBuffer bb = ByteBuffer.allocateDirect(sizeOfFloat * corrd.size());
        bb.order(ByteOrder.nativeOrder());
        FloatBuffer floatBuffer = bb.asFloatBuffer();

        float arr[] = new float[corrd.size()];
        for(int i = 0; i < arr.length;i++){
            arr[i] = corrd.get(i);
        }
        floatBuffer.put(arr);

        floatBuffer.position(0);
        return floatBuffer;
    }

    protected float[] getColor(Random r){
        float[] f = {r.nextFloat(), r.nextFloat(), r.nextFloat(), 1.0f};
        return f;
    }

    protected float[] loadMaterial(Random r, String material){
        float[] red = {1.0f, 0.0f, 0.0f, 1.0f};
        float[] green = {0.0f, 1.0f, 0.0f, 1.0f};

        switch (material){
            case "RED":
                return red;

            case "GREEN":
                return green;

            default:
                return getColor(r);
        }
    }
}
