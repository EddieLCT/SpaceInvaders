import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import java.util.List;
import java.util.LinkedList;

/**
 * Handles the game lifecycle and behavior
 * @author Bernardo Copstein and Rafael Copstein
 */
public class Game {
    private static Game game = null;
    private Canhao canhao;
    private List<Character> activeChars;
    public static int score = 0;
    private int fase = 1; 
    private boolean trocouFase = false;
    
    private Game(){
    }
    
    public static Game getInstance(){
        if (game == null){
            game = new Game();
        }
        return(game);
    }
    
    public void addChar(Character c){
        activeChars.add(c);
        c.start();
    }
    
    public void eliminate(Character c){
        activeChars.remove(c);
    }
    
    public void Start() {
        // Repositório de personagens
        activeChars = new LinkedList();
        
        // Adiciona o canhao
        canhao = new Canhao(400,550);
        activeChars.add(canhao);
        
        if (fase == 1) {
        	System.out.println("FASE " + fase + ":");
        for(int i=0; i<5; i++){
            activeChars.add(new Alien_Purple(100+(i*60),60+i*40));
        }
        
        }
        if (fase == 2) {
        	System.out.println("FASE " + fase + ":");
	        for(int i=0; i<5; i++){
	            activeChars.add(new Alien_Green(100+(i*60),60+i*40));
	        }
        }
        
        if (fase == 3) {
        	System.out.println("FASE 3:");
	        for(int i=0; i<5; i++){
	            activeChars.add(new Alien_Pink(100+(i*60),60+i*40));
	        }
        }
        
        if (fase == 4) {
        	System.out.println("FASE 4:");
	        for(int i=0; i<5; i++){
	            activeChars.add(new Alien_Blue(100+(i*60),60+i*40));
	        }
        }
        
        for(Character c: activeChars) {
            c.start();
        }        
    }
    
    public void Update(long currentTime, long deltaTime) {    
    	trocarFase();
    	for(int i=0;i<activeChars.size();i++) {
            Character este = activeChars.get(i);
            este.Update();
            for(int j =0; j<activeChars.size();j++) {
                Character outro = activeChars.get(j);
                if ( este != outro){
                    este.testaColisao(outro);
                }
            }
            if (este.getNextPhase() == true) {
            	for(Character c: activeChars){
            		este.setNextPhase(false);
            		trocouFase = true;
                }
            }
        }    	
    }
    
    public void trocarFase() {
    	if (trocouFase == true) {    		
    		fase++;
    		trocouFase = false;
    		game.Start();	
    	}
    }
    
    public void OnInput(KeyCode keyCode, boolean isPressed) {
        canhao.OnInput(keyCode, isPressed);
    }
    
    public void Draw(GraphicsContext graphicsContext) {
        for(Character c:activeChars) {
            c.Draw(graphicsContext);
        }
    }
}