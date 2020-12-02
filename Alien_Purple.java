import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Paint;

public class Alien_Purple extends Alien{
	Image img = new Image("Enemy1.gif");

	public Alien_Purple(int px, int py) {
		super(px, py);
	}
	
	@Override
	public void start() {
		setDirH(1);
		setSpeed(2);
	}

	 @Override
	 public void Update(){
	    if (jaColidiu()){
	        deactivate();
	        contaScore(killPoints());
	    }
	    if(getContaKills() == 5) {
			setNextPhase(true);
			setContaKills(0);
		}
	    else{	        	
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
	public void Draw(GraphicsContext graphicsContext) {
		graphicsContext.drawImage(img, getX(), getY(), 48, 32);
	}

	@Override
	public int killPoints() {
		return 5;
	}	
}
