package adam.com.androidgame.Model.DataAccess.DAO;

import adam.com.androidgame.Model.Model;

public class ModelClassPathDao {
    private StringClasspathDao dao = new StringClasspathDao();

    public Model getModel(String name){
        // String modelText = dao.getStringFromClasspath(name);

        return new Model();
    }
}
