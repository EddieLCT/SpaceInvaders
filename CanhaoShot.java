import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Paint;

public class CanhaoShot extends Shot{
	Image img = new Image("Shot.png");
	public CanhaoShot(int px, int py) {
		super(px, py);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void Update() {
		  if (jaColidiu()){
	            deactivate();
	        }else{
	            setPosY(getY() + getDirV() * getSpeed());
	            // Se chegou na parte superior da tela ...
	            if (getY() <= getLMinV()){
	                // Desaparece
	                deactivate();
	            }		
	}

}

	@Override
	public void Draw(GraphicsContext graphicsContext) {
		graphicsContext.drawImage(img, getX(), getY(), 3, 16);
		/*graphicsContext.setFill(Paint.valueOf("#00FF00"));
        graphicsContext.fillOval(getX(), getY(), 8, 16);*/		
	}

	/*@Override
	public void testaColisao(Character outro) {
        // Não verifica colisão de um tiro com outro tiro
        if (outro instanceof Shot){
            return;
        }else{
            testaColisao(outro);
        }		
	}*/
}
