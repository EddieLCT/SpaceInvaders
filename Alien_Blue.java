import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Paint;

public class Alien_Blue extends Alien{
	private int hit = 0;
	private int contadorkills = 0;
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
			setContaPontos(killPoints());
			System.out.println(getContaPontos());
			hit = 0;
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
                setSpeed(Params.getInstance().nextInt(2)+1);            
        }            
    }
	
	
	@Override
	public void Draw(GraphicsContext graphicsContext) {
		graphicsContext.drawImage(img, getX(), getY(), 32, 52);  
	}

	@Override
	public int killPoints() {
		return 15;
	}

	@Override
	public boolean nextPhase() {
		if(contaKills() == 5) {
			return true;
		}
		return true;
	}

}
