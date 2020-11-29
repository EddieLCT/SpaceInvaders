import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Paint;

/**
 * Represents the basic game character
 * @author Bernardo Copstein and Rafael Copstein
 */
public interface Character {
    int getX();
    int getY();
    int getAltura();
    int getLargura();
    int getScore();
    
    void testaColisao(Character c);
    boolean jaColidiu();
    void setColidiu();
    void setScore(int score);
    public void contaScore(int score);
    
    void start();
    boolean isActive();
    boolean getNextPhase();
    void setNextPhase(boolean b);
    void Update();
    void Draw(GraphicsContext graphicsContext);
}
