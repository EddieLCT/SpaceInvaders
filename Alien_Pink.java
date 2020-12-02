//Caroline Lewandowski Rodrigues - 20102626
//Eduardo Lucchese Costa de Toledo - 18200096-8
//https://github.com/EddieLCT/SpaceInvaders
import java.util.Random;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Paint;

public class Alien_Pink extends Alien{
	private Random random = new Random();
	Image img = new Image("Enemy3.gif");
	//Image img = new Image(getClass().getResourceAsStream("\SpaceInvaders\Imagens\Enemy3.gif"));
	public Alien_Pink(int px, int py) {
		super(px, py);
	}
	@Override
	public void start() {
		setDirH(1);
		setSpeed(6);
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
                setPosY(getY() + 70);
                // Sorteia o passo de avanço [1,5]	                
                setSpeed(Params.getInstance().nextInt(6)+3);  
            }
        }
    }

	@Override
	public void Draw(GraphicsContext graphicsContext) {
		/*graphicsContext.setFill(Paint.valueOf("#FF1493"));
        graphicsContext.fillOval(getX(), getY(), 32, 32);*/
		graphicsContext.drawImage(img, getX(), getY(), 30, 52);
	}

	@Override
	public int killPoints() {
		return 15;
	}	
	}