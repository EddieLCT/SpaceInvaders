//Caroline Lewandowski Rodrigues - 20102626
//Eduardo Lucchese Costa de Toledo - 18200096-8
//https://github.com/EddieLCT/SpaceInvaders
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
    
    void testaColisao(Character c);
    boolean jaColidiu();    
    void setColidiu(boolean aux);    
    
    void start();
    boolean isActive();    
    void Update();
    void Draw(GraphicsContext graphicsContext);
    
    //MÉTODOS PARA PONTUAÇÃO
    int contaHits();
    void setScore(int score);
    int getScore();
    public void contaScore(int score);
    
    //MÉTODOS PARA VIDA DO PLAYER
    void setCanhaoColidiu(boolean b);
    boolean getCanhaoColidiu();
    void setContaVidas(int vidas);
    int getContaVidas();
    int contaVidas();
    
    //MÉTODOS PARA FASES
    boolean getNextPhase();
    void setNextPhase(boolean b);
    
    //MÉTODO PARA GAME-OVER
    boolean getCanhaoMorreu();
	void setCanhaoMorreu(boolean b);
	}