package adam.com.androidgame.Model.DataAccess;

import android.opengl.GLES20;

import java.util.HashMap;
import java.util.Map;

import adam.com.androidgame.Model.DataAccess.DAO.ShaderClassPathDao;

/**
 * Created by Adam Clark on 5/16/2018.
 */

public class AssetRepository {
    private static AssetRepository assetRepository;

    /// Cache
    private Map<String, String> assetCache = new HashMap<>();

    /// DAO's
    private ShaderClassPathDao dao = new ShaderClassPathDao();

    private AssetRepository(){

    }

    public String loadShaderByName(String name){
        String retVal = null;
        retVal = assetCache.get(name);
        if(retVal == null){
            retVal = dao.getShaderByName(name);
            assetCache.put(name, retVal);
        }
        return retVal;
    }

    public static AssetRepository getInstance(){
        if(assetRepository == null){
            assetRepository = new AssetRepository();
        }

        return assetRepository;
    }
}
