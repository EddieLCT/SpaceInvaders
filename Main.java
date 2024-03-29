//Caroline Lewandowski Rodrigues - 20102626
//Eduardo Lucchese Costa de Toledo - 18200096-8
//https://github.com/EddieLCT/SpaceInvaders

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.value.ChangeListener;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * Handles window initialization and primary game setup
 * @author Bernardo Copstein, Rafael Copstein
 */

public class Main extends Application {
	private double escRate = 300;
    protected static double nextRate = 0;
	private boolean pause = false;
    @Override
    public void start(Stage stage) throws Exception {
        // Initialize Window
        stage.setTitle("SPACE INVADERS");
 
        GridPane root = new GridPane();
        Scene scene = new Scene( root, Color.BLACK);
        stage.setScene( scene );        
        
        Canvas canvas = new Canvas(Params.WINDOW_WIDTH, Params.WINDOW_HEIGHT );
        root.getChildren().add( canvas );
        root.setAlignment(Pos.CENTER);
        root.setVgap(10);
        root.setHgap(10);  
       
        //scoreAtual.setOnInputMethodTextChanged(((event))) 
        
        // Setup Game object        
        Game.getInstance().Start();
        
        
        // Register User Input Handler
        scene.setOnKeyPressed((KeyEvent event) -> {
            Game.getInstance().OnInput(event.getCode(), true);
        });
        
        scene.setOnKeyReleased((KeyEvent event) -> {
            Game.getInstance().OnInput(event.getCode(), false);
        });
        
        // Register Game Loop       
        final GraphicsContext gc = canvas.getGraphicsContext2D();

        new AnimationTimer()
        {
            long lastNanoTime = System.nanoTime();
            
            @Override
            public void handle(long currentNanoTime)
            {
                long deltaTime = currentNanoTime - lastNanoTime;                
                Game.getInstance().Update(currentNanoTime, deltaTime);
                gc.clearRect(0, 0, Params.WINDOW_WIDTH, Params.WINDOW_HEIGHT);
                Game.getInstance().Draw(gc);
                lastNanoTime = currentNanoTime;
                
                gc.setFill( Color.WHITE );
                //MOSTRAR PONTUA��O
                String score = "SCORE: " + Game.getInstance().score();                
                gc.fillText(score, 700, 36);
                gc.strokeText(score, 700, 36);                
                gc.setFont(Font.font("Arcade Classic"));
                gc.setStroke(Color.WHITE);
                
                //MOSTRAR FASES
                String fase = "FASE: " + Game.getInstance().getFase();
                gc.fillText(fase, 700, 20);
                gc.strokeText(fase, 700, 20);
                
                //MOSTRAR VIDAS
                String vidas = "VIDAS: " + Game.getInstance().getVidas();
                gc.fillText(vidas, 15, 26);
                gc.strokeText(vidas, 15, 26);                
                
                
                stage.addEventHandler(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent> () {

					@Override
					public void handle(KeyEvent t) {
						if (t.getCode() == KeyCode.ESCAPE && !pause && System.currentTimeMillis() > nextRate) {
							nextRate = System.currentTimeMillis() + escRate;
							pause = true;
		                    if (pause) {
		                    	stop();
		                    }
						}
						else if (t.getCode() == KeyCode.ESCAPE && pause && System.currentTimeMillis() > nextRate) {
							nextRate = System.currentTimeMillis() + escRate;
							pause = false;
							if (!pause) {
								start();
		                    }
						}
					}
            });
            }
        }.start();

        // Show window
        stage.show();
    }
    
    public static void main(String args[]) {
        launch();
    }
}