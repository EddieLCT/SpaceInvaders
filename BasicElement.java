import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Paint;

/**
 * Represents the basic game character
 * @author Bernardo Copstein and Rafael Copstein
 */
public abstract class BasicElement implements Character{
    private int posX, posY;
    private int largura, altura;
    private int lminH,lmaxH;
    private int lminV,lmaxV;
    private int speed;
    private boolean active;
    private boolean colidiu;
    private int direction_horizontal, direction_vertical;
    private int hit = 0;
    private static int contadorkills = 0;
    private boolean endPhase;
    private static int contaPontos = 0;
    private boolean canhaoColidiu = false;
    private static int vidas = 3;
    private boolean canhaoMorreu = false;
    
    public BasicElement(int startX,int startY){
        posX = startX;
        posY = startY;
        largura = 32;
        altura = 32;
        direction_horizontal = 0;
        direction_vertical = 0;
        active = true;
        colidiu = false;
        speed = 2;
        lminH = (int)(Params.WINDOW_WIDTH * 0.1);
        lmaxH = (int)(Params.WINDOW_WIDTH * 0.9);
        lminV = (int)(Params.WINDOW_HEIGHT * 0.1);
        lmaxV = (int)(Params.WINDOW_HEIGHT * 0.8);
    }
    
    @Override
    public int getX(){
        return(posX);
    }
    
    @Override
    public int getY(){
        return(posY);
    }
    
    @Override
    public int getAltura(){
        return(altura);
    }
    
    @Override
    public int getLargura(){
        return(largura);
    }

    @Override
    public void testaColisao(Character outro){
        if (colidiu){
            return;
        }
        // Monta pontos
        int p1x = this.getX();
        int p1y = this.getY();
        int p2x = p1x+this.getLargura();
        int p2y = p1y+this.getAltura();
        
        int op1x = outro.getX();
        int op1y = outro.getY();
        int op2x = op1x+outro.getLargura();
        int op2y = op1y+outro.getAltura();        
        
        // Verifica colis√£o
        if ( ((p1x <= op1x && p2x >= op1x) && (p1y <= op1y && p2y >= op1y)) ||
             ((p1x <= op2x && p2x >= op2x) && (p1y <= op2y && p2y >= op2y)) ){        	
            colidiu = true;
        }
    }
    
    public int getDirH(){
        return(direction_horizontal);
    }
    
    public int getDirV(){
        return(direction_vertical);
    }
    
    public int getLMinH(){
        return(lminH);
    }

    public int getLMaxH(){
        return(lmaxH);
    }
    
    public int getLMinV(){
        return(lminV);
    }

    public int getLMaxV(){
        return(lmaxV);
    }
    
    public int getSpeed(){
        return(speed);
    }
    
    public void setColidiu(Boolean aux) {
    	colidiu = aux;
    }
    
    public void setPosX(int p){
        posX = p;
    }

    public void setPosY(int p){
        posY = p;
    }
    
    public void setLargAlt(int l,int a){
        largura = l;
        altura = a;
    }
    
    public void setDirH(int dirH){
        direction_horizontal = dirH;
    }
    
    public void setDirV(int dirV){
        direction_vertical = dirV;
    }
    
    public void setLimH(int min,int max){
        lminH = min;
        lmaxH = max;
    }
    
    public void setLimV(int min,int max){
        lminV = min;
        lmaxV = max;
    }

    public void setSpeed(int s){
        speed = s;
    }
        
    public void deactivate(){
        active = false;
        Game.getInstance().eliminate(this);
    }
    
    @Override
    public boolean jaColidiu(){
        return(colidiu);
    }
    
    @Override
    public void setColidiu(boolean aux){
        colidiu = aux;
    }
    
    @Override
    public  boolean isActive(){
        return(active);
    }
    
    @Override
    public boolean getNextPhase() {
    	return(endPhase);
    }
    @Override
    public void setNextPhase(boolean b) {
    	endPhase = b;
    }
    
    @Override
    public int contaHits() {
		if (jaColidiu()) {
			hit++;
			setColidiu(false);
		}
		return hit;
	}
    
    //VIDA DO PLAYER
    @Override
    public void setCanhaoColidiu(boolean b) {
    	this.canhaoColidiu = b;
    }
    
    @Override
    public boolean getCanhaoColidiu() {
    	return (canhaoColidiu);
    }
    
    @Override
    public int contaVidas() {
    	if(canhaoColidiu == true) {    		
    		vidas--;
    		setCanhaoColidiu(false);
    		setColidiu(false);
    		return vidas;
    	}
    	return vidas;
    }
    
    public int getContaKills() {
    	if(!isActive()) {
			contadorkills++;
    	}
    	return contadorkills;
    }
    
    @Override
    public void setContaVidas(int vidas) {
    	BasicElement.vidas = vidas;
    }
    
    @Override
    public int getContaVidas() {
    	return vidas;
    }
    
    //GAME-OVER
    public void setCanhaoMorreu(boolean b) {
    	canhaoMorreu = b;
    }
    public boolean getCanhaoMorreu() {
    	return canhaoMorreu;
    }
    
    //PONTUA«√O
    public void setContaKills(int kills) {
    	contadorkills = kills;
    }
    
    @Override
    public void setScore(int pontos) {
    	contaPontos = pontos;
    }
    
    @Override
    public void contaScore(int score) {
    	contaPontos = contaPontos + score;
    }
    
    @Override
    public int getScore() {
    	return contaPontos;
    }
    
    @Override
    public abstract void Update();
    
    @Override
    public abstract void Draw(GraphicsContext graphicsContext);
}
