package adam.com.androidgame.Model.DataAccess;

import java.util.HashMap;
import java.util.Map;

import adam.com.androidgame.Model.Program;

/**
 * Created by Adam Clark on 5/17/2018.
 */

public class ProgramRepository {
    private static ProgramRepository programRepository;

    private Map<String, Program> programs = new HashMap<>();

    private ProgramRepository(){

    }

    public Program getProgramByName(String name){
        if(programs.get(name) == null){
            // Create Program
            // todo
        }

        return programs.get(name);
    }

    public void registerProgram(String name, Program program){
        programs.put(name, program);
    }

    public static ProgramRepository getInstance(){
        if(programRepository == null){
            programRepository = new ProgramRepository();
        }

        return programRepository;
    }
}
