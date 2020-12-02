//Caroline Lewandowski Rodrigues - 20102626
//Eduardo Lucchese Costa de Toledo - 18200096-8
//https://github.com/EddieLCT/SpaceInvaders
import java.util.Random;

public class Params{
    public static final String WINDOW_TITLE = "My Game V1.0";
    public static final int WINDOW_WIDTH = 800;
    public static final int WINDOW_HEIGHT = 600;
    
    private static Params params = null;
    private Random rnd;
    
    private Params(){
        rnd = new Random();
    }
    
    public static Params getInstance(){
        if (params == null){
            params = new Params();
        }
        return(params);
    }
    
    public int nextInt(int lim){
        return(rnd.nextInt(lim));
    }
}

