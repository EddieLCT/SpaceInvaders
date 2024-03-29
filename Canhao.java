//Caroline Lewandowski Rodrigues - 20102626
//Eduardo Lucchese Costa de Toledo - 18200096-8
//https://github.com/EddieLCT/SpaceInvaders
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Paint;

/**
 * Represents the game Gun
 * @author Bernardo Copstein, Rafael Copstein
 */
public class Canhao extends BasicElement implements KeyboardCtrl{
	Image img = new Image("spaceship.png");
	private double shotRate = 300;
    public Canhao(int px,int py){
        super(px,py);
    }
    
    @Override
    public void start() {
        setLimH(20,Params.WINDOW_WIDTH-20);
        setLimV(Params.WINDOW_HEIGHT-100,Params.WINDOW_HEIGHT);
    }
    
     @Override
    public void Update() {
    	 if (jaColidiu()) {
    		 setCanhaoColidiu(true);    		
    	 }
    	 
    	 if(contaHits() == 3) {
    		 //deactivate();
    		 setContaVidas(0);
    		 setCanhaoMorreu(true);
    	 }
    	 setPosX(getX() + getDirH() * getSpeed());    	
    }

    @Override
    public void OnInput(KeyCode keyCode, boolean isPressed) {
        if (keyCode == KeyCode.LEFT){
            int dh = isPressed ? -1 : 0;
            setDirH(dh);
        }
        if (keyCode == KeyCode.RIGHT){
            int dh = isPressed ? 1 : 0;
            setDirH(dh);
        }
        if (keyCode == KeyCode.SPACE && System.currentTimeMillis() > nextShot){
            nextShot = System.currentTimeMillis() + shotRate;
            Game.getInstance().addChar(new CanhaoShot(getX()+16,getY()-32));
        }
        //if (keyCode == KeyCode.UP) do nothing
        //if (keyCode == KeyCode.DOWN) do nothing
    }
    
    @Override
    public void Draw(GraphicsContext graphicsContext) {
    	graphicsContext.drawImage(img, getX(), getY(), 42, 42);
    }
}