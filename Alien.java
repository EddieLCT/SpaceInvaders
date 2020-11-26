import java.util.Random;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Paint;

public abstract class Alien extends BasicElement{
	
	private Random random = new Random();
	  public Alien(int px,int py){
	        super(px,py);
	    }
	    
	    @Override
	    public abstract void start();
	        
	    @Override
	    public void Update(){
	        if (jaColidiu()){
	            deactivate();
	        }else{
	        	int aux = random.nextInt(500);
	        	if (getX() == aux) {
	        		setPosX(aux);
	            	Game.getInstance().addChar(new EnemyShot(getX()+16,getY()+34));
	        	}
	        	setPosX(getX() + getDirH() * getSpeed()); 	
	        	// Se chegou no lado direito da tela ...	            
	            if (getX() >= getLMaxH()){	
	                // Reposiciona no lado esquerdo e ...
	                setPosX(getLMinH());
	                // Sorteia o passo de avanço [1,5]	                
	                setSpeed(Params.getInstance().nextInt(5)+1);  
	            }
	        }
	    }
	    
	    public abstract void Draw(GraphicsContext graphicsContext);
	    public abstract int killPoints();
}
