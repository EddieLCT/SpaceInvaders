import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Paint;

public class Alien_Blue extends Alien{
	int hit = 0;
	Image img = new Image("Enemy2.gif");
	public Alien_Blue(int px, int py) {
		super(px, py);
	}
	
	@Override
	public void start() {
		setDirH(1);
		setSpeed(1);
	}
	
	@Override
    public void Update(){
		//if (jaColidiu) {
		if (contaHits() == 4){
			deactivate();
			hit = 0;
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
	
	@Override
	public void Draw(GraphicsContext graphicsContext) {
		graphicsContext.drawImage(img, getX(), getY(), 32, 52);  
	}

	@Override
	public int killPoints() {
		return 10;
	}
}
