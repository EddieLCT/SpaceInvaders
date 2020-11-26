import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Alien_Green extends Alien{
	Image img = new Image("Enemy4.png");
	public Alien_Green(int px, int py) {
		super(px, py);
	}
	@Override
	public void start() {
		setDirH(1);
		setSpeed(4);
	}
	@Override
	public void Draw(GraphicsContext graphicsContext) {
		graphicsContext.drawImage(img, getX(), getY(), 32, 32);
	}

	@Override
	public int killPoints() {
		// TODO Auto-generated method stub
		return 15;
	}
}
