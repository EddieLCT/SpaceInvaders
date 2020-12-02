//Caroline Lewandowski Rodrigues - 20102626
//Eduardo Lucchese Costa de Toledo - 18200096-8
//https://github.com/EddieLCT/SpaceInvaders
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Paint;

public class EnemyShot extends Shot{
	Image img = new Image("EnemyShot.png");
	public EnemyShot(int px, int py) {
		super(px, py);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void Update() {
		  if (jaColidiu()){
	            deactivate();
	        }else{
	            setPosY(getY() - getDirV() * getSpeed());
	            // Se chegou na parte inferior da tela ...
	            if (getY() <= getLMinV()){
	                // Desaparece
	                deactivate();
	            }		
	}

}

	@Override
	public void Draw(GraphicsContext graphicsContext) {
		graphicsContext.drawImage(img, getX(), getY(), 8, 16);
        /*graphicsContext.setFill(Paint.valueOf("#FFFFFF"));
        graphicsContext.fillOval(getX(), getY(), 8, 16);*/		
	}
}