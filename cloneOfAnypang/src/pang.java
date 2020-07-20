import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;

import javax.swing.ImageIcon;

public class pang extends ImageIcon {
	int pX;				// ĳ���� �̹����� ��ǥ
	int pY;				// ĳ���� �̹����� y��ǥ
	int width;			// ĳ���� �̹����� ����
	int height;	        // ĳ���� �̹����� ����
	int number;         // ĳ���� �̹����� ����
	int moveSpeed;
	boolean change,visuable;     // ĳ������ ��������
	      
	
	private ImageIcon img;
	//ĳ���� �̹����� ������ �޾��� ������
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
	
	//���� ��ǥ�� ��������� ������
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

