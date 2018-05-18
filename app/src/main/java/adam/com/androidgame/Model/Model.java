package adam.com.androidgame.Model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class Model {
    private Triangle[] triangles;
    private Random r = new Random();

    public Model(Triangle[] triangles){
        this.triangles = triangles;
    }

    public Model(){
        List<Triangle> tri = new ArrayList<>();
        for(int i = 0; i < 10;i++){
            Triangle t = new Triangle();
            float color[] = {r.nextFloat(), r.nextFloat(), r.nextFloat(), 1.0f};
            t.setColor(color);
            tri.add(t);
        }
        triangles = tri.toArray(new Triangle[0]);
    }

    public void draw(){
        for(Triangle t : triangles){
            t.draw();
        }
    }
}
