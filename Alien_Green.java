import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Alien_Green extends Alien{
	Image img = new Image("Enemy4.gif");
	public Alien_Green(int px, int py) {
		super(px, py);
	}
	
	@Override
	public void start() {
		setDirH(1);
		setSpeed(5);
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
       
        	setPosX(getX() + getDirH() * getSpeed()); 	
        	// Se chegou no lado direito da tela ...	            
            if (getX() >= getLMaxH()){
                // Reposiciona no lado esquerdo e ...
                setPosX(getLMinH());
                setPosY(getY() + 70);
                // MANTEM O CONTROLE DE GRUPO                
               // setSpeed(Params.getInstance().nextInt(5)+1);            
        }
    }
	
	@Override
	public void Draw(GraphicsContext graphicsContext) {
		graphicsContext.drawImage(img, getX(), getY(), 62, 20);
	}


	@Override
	public int killPoints() {
		// TODO Auto-generated method stub
		return 10;
	}

}
