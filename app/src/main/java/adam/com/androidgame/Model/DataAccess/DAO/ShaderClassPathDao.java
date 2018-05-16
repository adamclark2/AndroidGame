package adam.com.androidgame.Model.DataAccess.DAO;

import android.util.Log;

import java.io.File;
import java.io.InputStream;
import java.util.Scanner;

/**
 * Created by Adam Clark on 5/16/2018.
 */

public class ShaderClassPathDao {
    public String getShaderByName(String name){
        InputStream is = this.getClass().getClassLoader().getResourceAsStream(name);

        try {
            Scanner sc = new Scanner(is);

            StringBuilder bldr = new StringBuilder();
            while (sc.hasNextLine()){
                bldr.append(sc.nextLine());
                bldr.append("\n");
            }

            sc.close();
            is.close();
            return bldr.toString();

        }catch (Exception e){
            Log.e("Shader Not Found", name, e);
            return null;
        }
    }
}
