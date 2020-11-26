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
	public void Draw(GraphicsContext graphicsContext) {
		graphicsContext.drawImage(img, getX(), getY(), 48, 32);
	}

	@Override
	public int killPoints() {
		return 5;
	}
}
