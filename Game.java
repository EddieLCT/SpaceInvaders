import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.shape.Path;

import java.util.List;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;

/**
 * Handles the game lifecycle and behavior
 * @author Bernardo Copstein and Rafael Copstein
 */
public class Game {
    private static Game game = null;
    private Canhao canhao;
    private List<Character> activeChars;
    private List<Integer> totalScore = new ArrayList<Integer>();
    private List<Integer> scores = new ArrayList<Integer>();
    private int fase = 4; 
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
        for(int i=0; i<5; i++){
            activeChars.add(new Alien_Purple(100+(i*60),60+i*40));
        }
        
        }
        if (fase == 2) {
	        for(int i=0; i<5; i++){
	            activeChars.add(new Alien_Green(100+(i*60),60+i*40));
	        }
        }
        
        if (fase == 3) {
	        for(int i=0; i<5; i++){
	            activeChars.add(new Alien_Pink(100+(i*60),60+i*40));
	        }
        }
        
        if (fase == 4) {
	        for(int i=0; i<5; i++){
	            activeChars.add(new Alien_Blue(100+(i*60),60+i*40));
	        }
        }
        
        if (fase == 5) {
        	try {
				readBestScores();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
        	for (Character c : activeChars) {
        		totalScore.add(c.getScore());
        		 writeLine(c.getScore());                
                c.setContaVidas(3);
                c.setScore(0);               
        	}        	
        	fase = 1;
        	game.Start();
        }
        
        for(Character c: activeChars) {
            c.start();
        }
    }
    
    public void Update(long currentTime, long deltaTime) {
    	trocarFase();
    	gameOver();
    	for(int i=0;i<activeChars.size();i++) {
            Character este = activeChars.get(i);
            este.Update();
            for(int j =0; j<activeChars.size();j++) {
                Character outro = activeChars.get(j);
                if (este instanceof Alien && outro instanceof Alien) {
                	este.setColidiu(false);
                }
                else if (este instanceof Alien && outro instanceof EnemyShot) {
                	este.setColidiu(false);
                }
                else if ( este != outro){
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
    
    private void trocarFase() {
    	if (trocouFase == true) {
    		fase++;
    		trocouFase = false;
    		game.Start();	
    	}
    }
    
    public int getVidas() {
    	for (Character c : activeChars) {
            return c.contaVidas();
    	}
    	return 0;
    }
    
    public int score() {
    	for (Character c : activeChars) {
            return c.getScore();
    	}
    	return 0;
    }
    
    public int getFase() {
    	return fase;
    }
    
    public void gameOver() {
    	for (Character c : activeChars) {
            if(c.getCanhaoMorreu() == true) {
            	fase = 5;
            	game.Start();
            }
    	}
    }
    
    private void readLine() throws FileNotFoundException {
    	File arquivo = new File("C:/Users/carol/eclipse-workspace/SpaceInvaders/Scores.txt");
    	if (!arquivo.exists()) {
    	 System.err.println("Arquivo não existe!!");
    	}

    	Scanner sc = new Scanner(arquivo);
    	int lineNumber = 1;    	
    	while (sc.hasNextLine()) {
	    	 //lê a proxima linha!
	    	 String linha = sc.nextLine();
	    	 System.out.println("linha " + lineNumber + ": "  + linha);
	    	 int aux = Integer.parseInt(linha);
	    	 totalScore.add(aux);
    	}
    	sc.close();
    	}              
         
    private void writeLine(int score) {
    	    File arquivo = new File("C:/Users/carol/eclipse-workspace/SpaceInvaders/Scores.txt");

    	    try{
    	        if (!arquivo.exists()) {
    	            System.out.println("Arquivo não existe. Criando arquivo...");
    	            arquivo.createNewFile();
    	        }

    	        FileWriter fw = new FileWriter(arquivo, true);
    	        BufferedWriter bw = new BufferedWriter(fw);
    	        for (Integer i : totalScore) {
                    bw.write("\n" + i);
                }
    	        bw.close();
    	        fw.close();

    	    } catch (IOException ioe) {
    	        System.err.println("Erro ao escrever no Scores.txt!!");
    	    }
    	}
    
    
    	public void readBestScores() throws FileNotFoundException {
    		int[] best = new int[10000];
    		int i = 0;
    		File arquivo = new File("C:/Users/carol/eclipse-workspace/SpaceInvaders/Scores.txt");
        	if (!arquivo.exists()) {
        	 System.err.println("Arquivo não existe!!");
        	}
        	Scanner sc = new Scanner(arquivo);
        	int lineNumber = 1;    	
        	while (sc.hasNextLine()) {
    	    	 //lê a proxima linha!
    	    	 String linha = sc.nextLine();
    	    	 int aux = Integer.parseInt(linha);
    	    	 best[i] = aux;    	    	
    	    	 i++;    	    	
                 }
        	
            for(int k = 0; k < best.length - 1; k++) {
            	for(int j = 0; j < best.length - 1 - k; j++) { 
                
            		if (best[j] > best[j+1]) {            			
            			int aux = best[j];
            			best[j] = best[j + 1];
            			best[j + 1] = aux;
            		}
            	}
             }
            
            File fArquivo = new File("C:/Users/carol/eclipse-workspace/SpaceInvaders/Top10.txt");
            fArquivo.delete();
    	    try{
    	        if (!fArquivo.exists()) {
    	            fArquivo.createNewFile();
    	        }

    	        FileWriter fw = new FileWriter(fArquivo, true);
    	        BufferedWriter bw = new BufferedWriter(fw);    	        
    	        BufferedWriter.nullWriter();
		            for (int j = 9999; j > 9988; j--) {
		            	bw.write(best[j] + "\n");
		            }
		            bw.close();
			        fw.close();
    	    } catch (IOException ioe) {
    	        System.err.println("Erro ao escrever no Scores.txt!!");
    	    }    	    
        	sc.close();
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