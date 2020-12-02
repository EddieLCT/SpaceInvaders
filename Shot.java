import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Paint;

/**
 * Represents a shot that crosses the screen from bottom to up and then dismiss
 * @author Bernardo Copstein and Rafael Copstein
 */
public abstract class Shot extends BasicElement{
    public Shot(int px,int py){
        super(px,py);
    }
    
    @Override
    public void start(){
        setDirV(-1);
        setSpeed(5);
    }
            
    @Override
    public void testaColisao(Character outro) {
        // Não verifica colisão de um tiro com outro tiro
        if (outro instanceof Shot){
            return;          
            
        }else{
            super.testaColisao(outro);
        }
    }
                
    @Override
    public abstract void Update();

    public abstract void Draw(GraphicsContext graphicsContext);
    
}

