//Caroline Lewandowski Rodrigues - 20102626
//Eduardo Lucchese Costa de Toledo - 18200096-8
//https://github.com/EddieLCT/SpaceInvaders

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Paint;

public class Alien_Blue extends Alien{
	private int contadorkills = 0;
	Image img = new Image("Enemy2.gif");
	public Alien_Blue(int px, int py) {
		super(px, py);
		getNextPhase();
	}
	
	@Override
	public void start() {
		setDirH(1);
		setSpeed(8);
	}
	
	@Override
    public void Update(){
		//if (jaColidiu) {
		if (contaHits() == 2){
			deactivate();
			contaScore(killPoints());
		}	
		if(getContaKills() == 5) {
			setNextPhase(true);
			setContaKills(0);
		}
        	setPosX(getX() + getDirH() * getSpeed()); 	
        	// Se chegou no lado direito da tela ...	            
            if (getX() >= getLMaxH()){	
                // Reposiciona no lado esquerdo e ...
                setPosX(getLMinH());
                setPosY(getY() + 70);
                if(getY() == (getLMinV() + 100)) {
                	deactivate();
                }                
                // Sorteia o passo de avanço [1,5]	                
                setSpeed(Params.getInstance().nextInt(7)+4);            
        }            
    }
	
	@Override
	public void Draw(GraphicsContext graphicsContext) {
		graphicsContext.drawImage(img, getX(), getY(), 32, 52);  
	}

	@Override
	public int killPoints() {
		return 20;
	}
}