

public class Mode {
	public int MAX ;  
	public  int IMAGE_SIZE; 
    public  int TERM ;
    public  int SPEED;
    public  int start_X;
    public  int start_Y;
    public  Mode(int max,int size,int term,int speed,int x,int y){
    	MAX=max;
    	IMAGE_SIZE=size;
    	TERM=term;
    	SPEED=speed;
    	start_X=x;
    	start_Y=y;
    }

	public int getMAX() {
		return MAX;
	}

	public void setMAX(int mAX) {
		MAX = mAX;
	}

	public int getIMAGE_SIZE() {
		return IMAGE_SIZE;
	}

	public void setIMAGE_SIZE(int iMAGE_SIZE) {
		IMAGE_SIZE = iMAGE_SIZE;
	}

	public int getTERM() {
		return TERM;
	}

	public void setTERM(int tERM) {
		TERM = tERM;
	}
}
