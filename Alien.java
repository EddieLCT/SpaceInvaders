//Caroline Lewandowski Rodrigues - 20102626
//Eduardo Lucchese Costa de Toledo - 18200096-8
//https://github.com/EddieLCT/SpaceInvaders
import java.util.Random;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Paint;

public abstract class Alien extends BasicElement{	
	private boolean endPhase = false;
	
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
	        	setPosX(getX() + getDirH() * getSpeed()); 	
	        	// Se chegou no lado direito da tela ...	            
	            if (getX() >= getLMaxH()){	
	                // Reposiciona no lado esquerdo e ...
	                setPosX(getLMinH());
	                setPosY(getY() + 70);
	                // Sorteia o passo de avanço [1,5]	                
	                setSpeed(Params.getInstance().nextInt(5)+1);  
	            }
	        }
	    } 
	    
	    @Override
	    public void setNextPhase(boolean b) {
	    	endPhase = b;
	    }
	    
	    @Override
	    public boolean getNextPhase() {
	    	return endPhase;
	    }
	    
	    public abstract void Draw(GraphicsContext graphicsContext);
	    public abstract int killPoints();
}