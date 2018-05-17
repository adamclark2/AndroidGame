package adam.com.androidgame.Model.DataAccess.DAO;

import android.util.Log;

import java.io.InputStream;
import java.util.Scanner;

public class StringClasspathDao {
    protected String getStringFromClasspath(String name){
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
            Log.e("Not Found", name, e);
            return null;
        }
    }
}
