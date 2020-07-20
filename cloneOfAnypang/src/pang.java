import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;

import javax.swing.ImageIcon;

public class pang extends ImageIcon {
	int pX;				// 캐릭터 이미지의 좌표
	int pY;				// 캐릭터 이미지의 y좌표
	int width;			// 캐릭터 이미지의 가로
	int height;	        // 캐릭터 이미지의 높이
	int number;         // 캐릭터 이미지의 종류
	int moveSpeed;
	boolean change,visuable;     // 캐릭터의 생존유무
	      
	
	private ImageIcon img;
	//캐릭터 이미지의 정보를 받아줄 생성자
	public pang( String k,int n,int x, int y, int width, int height,int speed,boolean f,boolean C) {
		super(k);
		number=n;
		pX=x;
		pY=y;
		this.width = width;
		this.height = height;
		moveSpeed=speed;
		visuable=f;
		change=C;
	}
	
	//고정 좌표를 만들기위한 생성자
	public pang(int x,int y) {
		pX=x;
		pY=y;
	}
	public void changeImage(String r) {
		img=new ImageIcon(r);
		this.setImage(img.getImage());
	}
	public void draw(Graphics g) {
		g.drawImage(this.getImage(),pX-50, pY-50, width, height,null);
	}
	public void draw_boom(Graphics g) {
		//g.drawImage(boomGif.getImage(),pX-50, pY-50, width, height,null);
	}
	public void moveXplus() {
		pX+=moveSpeed;
	}
	public void moveXMinus() {
		pX-=moveSpeed;
	}
	public void moveYplus() {
		pY+=moveSpeed;
	}
	public void moveYMinus() {
		pY-=moveSpeed;
	}
}

