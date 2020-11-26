import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Paint;

public class Alien_Pink extends Alien{
	Image img = new Image("Enemy3.gif");
	//Image img = new Image(getClass().getResourceAsStream("\SpaceInvaders\Imagens\Enemy3.gif"));
	public Alien_Pink(int px, int py) {
		super(px, py);
	}
	@Override
	public void start() {
		setDirH(1);
		setSpeed(8);
	}

	@Override
	public void Draw(GraphicsContext graphicsContext) {
		/*graphicsContext.setFill(Paint.valueOf("#FF1493"));
        graphicsContext.fillOval(getX(), getY(), 32, 32);*/
		graphicsContext.drawImage(img, getX(), getY(), 32, 52);
	}

	@Override
	public int killPoints() {
		return 20;
	}
}
