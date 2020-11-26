import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Paint;

public class Alien_Blue extends Alien{
	Image img = new Image("Enemy2.png");
	public Alien_Blue(int px, int py) {
		super(px, py);
	}
	
	@Override
	public void start() {
		setDirH(1);
		setSpeed(6);
	}

	@Override
	public void Draw(GraphicsContext graphicsContext) {
		graphicsContext.drawImage(img, getX(), getY(), 32, 32);
	}

	@Override
	public int killPoints() {
		return 10;
	}
}
