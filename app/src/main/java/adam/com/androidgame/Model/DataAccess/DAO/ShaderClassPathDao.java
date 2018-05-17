package adam.com.androidgame.Model.DataAccess.DAO;

import android.util.Log;

import java.io.File;
import java.io.InputStream;
import java.util.Scanner;

/**
 * Created by Adam Clark on 5/16/2018.
 */

public class ShaderClassPathDao {
    private StringClasspathDao dao = new StringClasspathDao();

    public String getShaderByName(String name){
        return dao.getStringFromClasspath(name);
    }
}
