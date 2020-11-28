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
		setSpeed(8);
	}
	
	@Override
    public void Update(){
		if (jaColidiu()){
			deactivate();
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
	
	public boolean nextPhase() {
		if(contaKills() == 5) {
			return true;
		}
		return false;
	}
}
