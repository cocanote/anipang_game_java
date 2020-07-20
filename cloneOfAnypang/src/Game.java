import java.applet.AudioClip;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Game extends Frame implements ActionListener {

	DrawPanel drawPanel;	//������ ����Ǵ� �г�
	StartPanel firstPanel;  //�ʱ�ȭ���� �������� �г�
	JPanel mainPanel;  //������ ���� �ö󰡴� �г�
	EndPanel endPanel;  //������ȭ���� �������� �г�
	ExplainPanel explainPanel;

	private final int BLUE_E = 0;   //�̹����� ���� ���ڰ�
	private final int BROWN_E = 1;
	private final int GREEN_E = 2;
	private final int RED_E = 3;
	private final int WHITE_E = 4;
	private final int ORANG_E = 5;

	private AudioClip background;  //�Ҹ�
	private AudioClip boomsound;
	private AudioClip boomsound2;
	private AudioClip change;

	private pang[][] Model ;  //ĳ������ ������ ����ִ� ��ü�� 2���� �迭
	private pang[][] Fix ;    //������ ��ġ���� ����� ��ü �迭

	private int imgX, imgY;  //ĳ���� �̹����� ��ǥ.
	private int bar ;   
	private int score=0;
	private int time =60;
	private int item=0;
	int measureTime=0;
	int comboCount=0;
	boolean compare_1,compare_2,compare_3,compare_4;
	boolean combo;
	private String[] Character = {"src/BLUE.png","src/BROWN.png","src/GREEN.png","src/RED.png"
			,"src/WHITE.png","src/ORANGE.png","src/horizon_item.png","src/vertical_item.png","src/vh_item.png"};

	private String[] modeStr = {"7*7���Ӹ��","10*10���Ӹ��","15*15���Ӹ��"};
	private final String BACKGOUND_SOUND = "/res/backgroundsound.wav";
	private final String BOOM_SOUND = "/res/boom.wav";
	private final String BOOM_SOUND2 = "/res/boom2.wav";
	private final String CHANGE_SOUND = "/res/CHANGE.wav";

	private ImageIcon backGround_GREEN,backGround_PINK,backGround_BLUE ;
	private ImageIcon firstimage;
	private ImageIcon startbutton_image;
	private ImageIcon endimage;
	private ImageIcon boomGif;
	private ImageIcon exitbutton_image;
	private ImageIcon repalybutton_image;	
	private ImageIcon optionB_img;
	private ImageIcon explane_img;
	private ImageIcon explaneB_img;
	private ImageIcon backB_img;
	private ImageIcon frameIcon_img;

	Timer allFunctionT;  // ���� ���� ����Ǵ� Ÿ�̸�
	Timer pangT;  //3���̻� ������ ������ Ÿ�̸�
	Timer backT;  //���콺�� �̵��� ������ 3���̻� ������ ���ϸ� �ٽ� ������� ������ Ÿ�̸�
	Timer gameT;  //���� �ð� Ÿ�̸�
	Timer moveT; 
	Timer collectPointT;
	Timer resetT;
	Timer measureT;

	JButton start_Button;  //���۹�ư
	JButton exit_Button;  //�����ư
	JButton replay_Button;  //����� ��ư
	JButton option_Button;
	JButton explain_Button;
	JButton back_Button;

	JLabel scoreLabel=new JLabel(score+" ");  //������ �����ִ� ��
	JLabel comaLabel =new JLabel();  // ,�� �����ִ� ��
	JLabel timeLabel =new JLabel(time+"");  //�ð��� �����ִ� ��
	JLabel EndScoreLabel =new JLabel(score+" ");  //���������� �����ִ� ��
	JLabel EndComaLabel =new JLabel("");  //���������� ,�� �����ִ� ��
	JLabel comboLabel = new JLabel(comboCount+"");

	JComboBox modeSelect;
	String modeSTR = modeStr[0];
	Point p,f,s;  //
	CardLayout card;
	Set_Up setting;
	Mode mode;
	public Game() {
		card =new CardLayout(0,0);

		firstimage =new ImageIcon("src/FIRSTIMAGE.jpg");
		startbutton_image =new ImageIcon("src/startB.png");
		boomGif  =new ImageIcon("src/run.gif");
		endimage = new ImageIcon("src/ENDING.jpg");
		exitbutton_image =new ImageIcon("src/exitB.png");
		repalybutton_image = new ImageIcon("src/repalyB.png");
		optionB_img =new ImageIcon("src/option.png");
		explane_img=new ImageIcon("src/EXPLANE.jpg");
		explaneB_img=new ImageIcon("src/explaneB.png");
		backB_img=new ImageIcon("src/backB.png");
		backGround_GREEN =new ImageIcon("src/BACKGROUND_GREEN.jpg");
		backGround_PINK =new ImageIcon("src/BACKGROUND_PINK.jpg");
		backGround_BLUE =new ImageIcon("src/BACKGROUND_BLUE.jpg");
		frameIcon_img= new ImageIcon("src/BROWN.png");
		//Model(ĳ���� �̹��� ��ü)�� �������� �̹����� �����ϰ� ��ġ�� ������������ ����
		mainPanel=new JPanel();
		drawPanel=new DrawPanel();
		firstPanel=new StartPanel();
		endPanel=new EndPanel();
		explainPanel=new ExplainPanel();

		start_Button=new JButton("����"); start_Button.addActionListener(this);		
		exit_Button=new JButton("������"); exit_Button.addActionListener(this);
		replay_Button =new JButton("�ѹ���"); replay_Button.addActionListener(this);
		option_Button =new JButton("����");option_Button.addActionListener(this);
		explain_Button =new JButton("����");explain_Button.addActionListener(this);
		back_Button =new JButton("�ڷ�");back_Button.addActionListener(this);

		scoreLabel.setLocation(130,240);
		scoreLabel.setSize(100, 100);
		scoreLabel.setFont(new Font("���",Font.BOLD,30));

		timeLabel.setLocation(98,470);
		timeLabel.setSize(100, 100);
		timeLabel.setFont(new Font("���",Font.BOLD,80));

		comaLabel.setLocation(143,240);
		comaLabel.setSize(100, 100);
		comaLabel.setFont(new Font("���",Font.BOLD,30));

		comboLabel.setLocation(1080,220);
		comboLabel.setSize(100, 100);
		comboLabel.setFont(new Font("���",Font.BOLD,30));

		EndComaLabel.setLocation(527,120);
		EndComaLabel.setSize(500, 200);
		EndComaLabel.setFont(new Font("���",Font.BOLD,60));

		EndScoreLabel.setLocation(500,120);
		EndScoreLabel.setSize(500,200);
		EndScoreLabel.setFont(new Font("���",Font.BOLD,60));
		start_Button.setLocation(600, 650);
		start_Button.setSize(250,170);
		start_Button.setIcon(startbutton_image);
		start_Button.setContentAreaFilled(false);
		start_Button.setBorderPainted(false);

		replay_Button.setLocation(880, 650);
		replay_Button.setSize(250,170);
		replay_Button.setIcon(repalybutton_image);
		replay_Button.setContentAreaFilled(false);
		replay_Button.setBorderPainted(false);

		exit_Button.setLocation(870, 650);
		exit_Button.setSize(250,170);
		exit_Button.setIcon(exitbutton_image);
		exit_Button.setContentAreaFilled(false);
		exit_Button.setBorderPainted(false);

		option_Button.setLocation(1100, 900);
		option_Button.setSize(100,50);

		option_Button.setIcon(optionB_img);
		option_Button.setContentAreaFilled(false);
		option_Button.setBorderPainted(false);
		option_Button.setContentAreaFilled(false);

		explain_Button.setLocation(330, 650);
		explain_Button.setSize(250,170);
		explain_Button.setIcon(explaneB_img);		
		explain_Button.setContentAreaFilled(false);
		explain_Button.setBorderPainted(false);

		back_Button.setLocation(50, 750);
		back_Button.setSize(250,170);
		back_Button.setIcon(backB_img);
		back_Button.setContentAreaFilled(false);
		back_Button.setBorderPainted(false);

		modeSelect=new JComboBox(modeStr);
		modeSelect.setBounds(670,600,100,30);
		modeSelect.addActionListener(this);
		firstPanel.add(modeSelect);

		mainPanel.setLayout(card);//���� �гο� card���̾ƿ��� ����
		drawPanel.setLayout(null);
		firstPanel.setLayout(null);
		endPanel.setLayout(null);
		explainPanel.setLayout(null);

		//�гε��� ������ �ű��
		mainPanel.add("a1",firstPanel);
		mainPanel.add("a2",drawPanel);
		mainPanel.add("a3",endPanel);
		mainPanel.add("a4",explainPanel);

		firstPanel.add(explain_Button);
		firstPanel.add(start_Button);
		firstPanel.add(exit_Button);
		explainPanel.add(back_Button);
		endPanel.add(replay_Button);
		endPanel.add(EndScoreLabel);
		endPanel.add(EndComaLabel);
		drawPanel.add(scoreLabel);
		drawPanel.add(comaLabel);
		drawPanel.add(timeLabel);
		drawPanel.add(option_Button);
		drawPanel.add(comboLabel);

		add(mainPanel); //���� �г��� �������� �־��ش�
		setTitle("������ ����!");
		setIconImage(frameIcon_img.getImage());
	}

	public static void main(String[] args) {
		Game css =new Game();
		css.setVisible(true);
		css.setSize(1200,1000);
		css.addWindowListener(new WindowAdapter(){
			public void windowClosing (WindowEvent  e) {
				System.exit(1);
			}
		});

	}
	//Model ����Ʈ�� ä���ְ� timer�� �����ϴ� �޼ҵ� ���ӿ��� ���� �߿��� �κ�
	public void go(){

		try {
			background = JApplet.newAudioClip(getClass().getResource(BACKGOUND_SOUND));
			boomsound = JApplet.newAudioClip(getClass().getResource(BOOM_SOUND));
			boomsound2 = JApplet.newAudioClip(getClass().getResource(BOOM_SOUND2));
			change = JApplet.newAudioClip(getClass().getResource(CHANGE_SOUND));
		}
		catch(Exception e){
			System.out.println("���� ���� �ε� ����");
		}
		imgY=mode.start_Y;
		imgX=mode.start_X;
		bar=score=0;
		setting=new Set_Up();
		setting.SetUp() ;
		Model = new pang[mode.MAX][mode.MAX+1]; 
		Fix = new pang[mode.MAX][mode.MAX+1];   
		int eggNumber;
		for(int i=0;i<mode.MAX;i++) {
			for(int j=1;j<mode.MAX+1;j++) {
				eggNumber=(int)(Math.random()*6);
				Model[i][j]=(new pang(Character[eggNumber],eggNumber,imgX,imgY,mode.IMAGE_SIZE,mode.IMAGE_SIZE,mode.SPEED,true,true));
				Fix[i][j]=(new pang(imgX,imgY));
				imgY+=mode.TERM;
			}
			imgY=mode.start_Y;
			imgX+=mode.TERM;
		}
		mainPanel.addMouseListener(new MyMouseListener());
		allFunctionT= new Timer(50,new  TimerListener());  
		pangT= new Timer(400,new  PangListener());
		backT= new Timer(200,new  BackListener());
		gameT =new Timer(1000,new GameTimerListener());
		moveT=new Timer(20,new MoveTimerListener());
		collectPointT = new Timer(10,new CollectPointTimerListener());
		resetT=new Timer(1000,new ResetTimerListener());
		measureT =new Timer (1000,new MeasureTimerListener());

		allFunctionT.start();
		gameT.start();
		moveT.start();
		background.play();
	}
	//ĳ���� �̹������� �׷����� �г� Ŭ����
	class DrawPanel extends JPanel {
		public void paintComponent(Graphics g) {	
			if(setting.g)
				g.drawImage(backGround_GREEN.getImage(),0, 0, 1200, 1000,null);
			if(setting.p)
				g.drawImage(backGround_PINK.getImage(),0, 0, 1200, 1000,null);
			if(setting.b)
				g.drawImage(backGround_BLUE.getImage(),0, 0, 1200, 1000,null);

			for(int i=0;i<mode.MAX;i++) {
				for(int j=1;j<mode.MAX+1;j++) {
					if(Model[i][j].visuable)
						Model[i][j].draw(g);
				}
			}
			g.setColor(new Color(92,209,229));
			g.fillRect(364,41, bar, 49);
		}
	}
	//������ ���� �г� Ŭ����
	class ExplainPanel extends JPanel {
		public void paintComponent(Graphics g) {	
			super.paintComponent (g);
		g.drawImage(explane_img.getImage(),0, 0, 1200, 1000,null);
		}
	}
	//�ʱ�ȭ���� �׷����� �г� Ŭ����
	class StartPanel extends JPanel {
		public void paintComponent(Graphics g) {	
			super.paintComponent (g);
			g.drawImage(firstimage.getImage(),0, 0, 1200, 1000,null);
		}
	}
	//������ ȭ���� �׷����� �г� Ŭ����
	class EndPanel extends JPanel {
		public void paintComponent(Graphics g) {	
			super.paintComponent (g);
			g.drawImage(endimage.getImage(),0, 0, 1200, 1000,null);
		}
	}
	//backtimer�� �׼Ǹ�����
	private class BackListener implements ActionListener{
		public void actionPerformed(ActionEvent event) {
			pang[][] tmp = new pang[1][1];
			tmp[0][0]=Model[f.x][f.y];
			Model[f.x][f.y]=Model[s.x][s.y];
			Model[s.x][s.y]=tmp[0][0];
			backT.stop();	
		}
	}
	//pangtimer�� �׼Ǹ�����
	private class PangListener implements ActionListener{
		public void actionPerformed(ActionEvent event) {		
			analyzeChange();
			analyzePang();
			mainPanel.repaint();
			pangT.stop();
		}
	}
	//allFunctiontimer�� �׼Ǹ�����
	private class TimerListener implements ActionListener{
		public void actionPerformed(ActionEvent event) {
			comboLabel.setText(comboCount+"");
			checkUnderAndMoov(); 
			if(probability_1()&&probability_2()&&probability_3()&&probability_4()) {
				//collectPoint();
				System.out.println("���� �� �ִ� �� �� �����ϴ�."+probability_1()+probability_2()
				+probability_3()+probability_4());
				collectPointT.start();
			}

			fill();	
			for(int i=0;i<mode.MAX;i++) 
				for(int j=2;j<mode.MAX;j++) 
					if(Model[i][j].number==Model[i][j-1].number&&Model[i][j].number==Model[i][j+1].number) {
						pangT.start();
					}
			for(int i=1;i<mode.MAX-1;i++) 
				for(int j=1;j<mode.MAX+1;j++) 
					if(Model[i][j].number==Model[i-1][j].number&&Model[i][j].number==Model[i+1][j].number) {
						pangT.start();
					}
			if(bar>=550) {
				score+=2500;
				scoreLabel.setText(score+"");
				bar=0;
				if(time==0) {
					allFunctionT.stop();
				}
			}
			if(score>10000&&score<11000)
				scoreLabel.setLocation(115,240);
		}
	}
	//gametimer�� �׼Ǹ�����
	private class GameTimerListener implements ActionListener{
		public void actionPerformed(ActionEvent event) {
			if(time>0) {
				time--;
				timeLabel.setText(time+"");
			}
			else {
				EndScoreLabel.setText(score+"");
				if(score>10000)
					EndComaLabel.setLocation(560,120);		
				if(score>1000)
					EndComaLabel.setText(",");	
				card.show(mainPanel,"a3");
				gameT.stop();
				background.stop();
			}
		}
	}
	//ĳ���Ͱ� ��ġ�� �ٲ�� �����̴� �׼Ǹ�����
	private class MoveTimerListener implements ActionListener{
		public void actionPerformed(ActionEvent event) {
			move();
			move();
			move();
			move();
		}
	}
	//��� ĳ���͸� �߾����� ������ �׼Ǹ�����
	private class CollectPointTimerListener implements ActionListener{
		public void actionPerformed(ActionEvent event) {
			moveT.stop();
			collectPoint();
			collectPoint();
			collectPoint();
			if(Model[0][1].pX==Fix[mode.MAX-4][mode.MAX-3].pX&&Model[0][1].pY==Fix[mode.MAX-4][mode.MAX-3].pY) {			
				resetT.start();
				collectPointT.stop();
				allFunctionT.stop();
			}
		}
	}
	//��ü�� ���ο� ĳ���ͷ� ���½�Ű�� �׼Ǹ�����
	private class ResetTimerListener implements ActionListener{
		public void actionPerformed(ActionEvent event) {
			resetAll();
			moveT.start();
			resetT.stop();
			allFunctionT.start();
		}
	}
	//�ð��� �����ϴ� �׼Ǹ�����
	private class MeasureTimerListener implements ActionListener{
		public void actionPerformed(ActionEvent event) {
			measureTime++;
			if(measureTime==3) {
				measureT.stop();
				comboCount=0;
			}
		}
	}

	private class MyMouseListener implements MouseListener{
		@Override
		public void mouseClicked(MouseEvent e) {

		}

		@Override
		public void mouseEntered(MouseEvent arg0) {
			// TODO Auto-generated method stub
		}

		@Override
		public void mouseExited(MouseEvent arg0) {
			// TODO Auto-generated method stub
		}
		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			p=e.getPoint();
			f=new Point(pick_X(p),pick_Y(p));	
			if(Model[f.x][f.y].number==6) {
				item_Horizon(f.y);
				score+=861;
				scoreLabel.setText(score+" ");
				measureTime=0;
			}
			else if(Model[f.x][f.y].number==7) {
				item_Vertical(f.x);
				score+=861;
				scoreLabel.setText(score+" ");
				measureTime=0;
			}
			else if(Model[f.x][f.y].number==8) {
				item_HV(f.x,f.y);
				score+=861;
				scoreLabel.setText(score+" ");
				measureTime=0;
			}
		}
		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			p=e.getPoint();
			s=new Point(pick_X(p),pick_Y(p));
			//�𼭸� �� ĳ���� �̹����� �ٲܶ� �δ콺 ���� ����� �Ϳ� ������ ���� ���� ó��
			try {
				if(Model[f.x][f.y]==Model[s.x+1][s.y]
						||Model[f.x][f.y]==Model[s.x][s.y+1]
								||Model[f.x][f.y]==Model[s.x-1][s.y]
										||Model[f.x][f.y]==Model[s.x][s.y-1]) {
					mouse();
				}	
			} 
			catch(ArrayIndexOutOfBoundsException x) {
				if(s.x==0&&s.y==mode.MAX) {
					if(Model[f.x][f.y]==Model[s.x+1][s.y]
							||Model[f.x][f.y]==Model[s.x][s.y-1])											
						mouse();
				}
				else if(s.x==0&&s.y!=mode.MAX)
					if(Model[f.x][f.y]==Model[s.x+1][s.y]
							||Model[f.x][f.y]==Model[s.x][s.y+1]
									||Model[f.x][f.y]==Model[s.x][s.y-1]) 
						mouse();
				if(s.x==6&&s.y==mode.MAX) {
					if(Model[f.x][f.y]==Model[s.x-1][s.y]
							||Model[f.x][f.y]==Model[s.x][s.y-1])											
						mouse();
				}
				else if(s.x!=0&&s.y==mode.MAX) {
					if(Model[f.x][f.y]==Model[s.x+1][s.y]
							||Model[f.x][f.y]==Model[s.x][s.y-1]
									||Model[f.x][f.y]==Model[s.x-1][s.y]) 
						mouse();
				}
				else if(s.x==mode.MAX-1)
					if(Model[f.x][f.y]==Model[s.x][s.y-1]
							||Model[f.x][f.y]==Model[s.x][s.y+1]
									||Model[f.x][f.y]==Model[s.x-1][s.y]) 
						mouse();
			}				
		}
		//������ ĳ���� �ΰ��� ��ġ�� ��ȯ�ϴ� �żҵ�
		private void mouse() {
			change.play();  //ĳ���� ��ġ��ȯ ����
			pang[][] tmp = new pang[1][1];
			tmp[0][0]=Model[f.x][f.y];
			Model[f.x][f.y]=Model[s.x][s.y];
			Model[s.x][s.y]=tmp[0][0];
			analyzeChange();
			if((Model[f.x][f.y].change==Model[f.x][f.y].visuable&&Model[s.x][s.y].change==Model[s.x][s.y].visuable)) {
				change.play();
				backT.start();
			}
			else {
				comboCount++;
				measureTime=0;
				System.out.println("combo!"+comboCount);
				measureT.start();
			}
		}
	}
	//�����̹����� 3���̻� �����ؼ� ������ ������� �ϴ� �޼ҵ�
	private void analyzePang() {
		for(int i=0;i<mode.MAX;i++) {
			for(int j=2;j<mode.MAX;j++) {

				if(Model[i][j].number==Model[i][j-1].number&&Model[i][j].number==Model[i][j+1].number) {
					Model[i][j].visuable=Model[i][j-1].visuable=Model[i][j+1].visuable=false;
					boomsound.play();
					bar+=25;
					score+=123;
					item++;

					if(score>1000)
						comaLabel.setText(",");
					scoreLabel.setText(score+" ");

				}
			}
		}
		for(int i=1;i<mode.MAX-1;i++) {
			for(int j=1;j<mode.MAX+1;j++) {
				if(Model[i][j].number==Model[i-1][j].number&&Model[i][j].number==Model[i+1][j].number) {
					Model[i][j].visuable=Model[i-1][j].visuable=Model[i+1][j].visuable=false;
					boomsound.play();
					bar+=25;
					score+=123;
					item++;
					if(score>1000)
						comaLabel.setText(",");
					scoreLabel.setText(score+" ");

				}
			}
		}
	}
	//���� �̹����� 3���̻� �����ؼ� �������� Ȯ���ϴ� �޼ҵ� �̹����� ��������� ���� 3���̻� �������� ������ �̵���Ų ĳ���͸� �������·� ������ ����	
	private void analyzeChange() {
		for(int i=0;i<mode.MAX;i++) {
			for(int j=2;j<mode.MAX;j++) {
				if(Model[i][j].number==Model[i][j-1].number&&Model[i][j].number==Model[i][j+1].number)
					Model[i][j].change=Model[i][j-1].change=Model[i][j+1].change=false;
			}
		}
		for(int i=1;i<mode.MAX-1;i++) {
			for(int j=1;j<mode.MAX+1;j++) {
				if(Model[i][j].number==Model[i-1][j].number&&Model[i][j].number==Model[i+1][j].number)
					Model[i][j].change=Model[i-1][j].change=Model[i+1][j].change=false;
			}
		}
	}


	// ���� ������ �Ʒ� ���� visuable ���� false�� ��ġ �ٲٱ� �׿��� visuable�� ��� true�� �ɶ�����
	private void checkUnderAndMoov() {
		pang[][] tmp = new pang[1][1];
		int count;
		for(int i=0;i<mode.MAX;i++) {
			for(int k=0;k<countFalse(i);k++)
				for(int j=mode.MAX-1;j-k>=1;j--) {
					if(!Model[i][j+1].visuable) {
						tmp[0][0]=Model[i][j+1];
						Model[i][j+1]=Model[i][j];
						Model[i][j]=tmp[0][0];
						{
						}
					}
				}
		}
	}
	//���ϴ� ���� �ִ� ��� ���� visuable ���� false�� ���� ����
	private int countFalse(int k) {
		int C=0;
		for(int i=0;i<mode.MAX;i++) {
			for(int j=1;j<mode.MAX+1;j++) {
				if(Model[i][j].visuable==false&&i==k)
					C++;
			}			
		}
		return C;
	}
	// visuable�� false�� ������ŭ ���ο� ������ ä���
	private void fill() {  
		int eggNumber;
		for(int k=0;k<mode.MAX;k++)
			if(Model[k][1].visuable==false) {
				for(int i=0;i<mode.MAX;i++) {
					for(int j=0;j<countFalse(i);j++) {
						if(item>2) {
							eggNumber=(int)(Math.random()*3+6);

							item=0;
						}
						else
							eggNumber=(int)(Math.random()*6);

						Model[i][0]=(new pang(Character[eggNumber],eggNumber,((i*mode.TERM)+mode.start_X),mode.start_Y-50,mode.IMAGE_SIZE,mode.IMAGE_SIZE,mode.SPEED,true,true));
						Model[i][j+1]=Model[i][j]; 
						checkUnderAndMoov();	
					}
				}
			}
	}
	//���콺�� ��ǥ�� ���� �迭�� ���� �� ��ȯ
	private int pick_X(Point k) {
		for(int i=1;i<mode.MAX;i++) {
			if(k.x<(mode.start_X-50)+(mode.TERM*i)&&k.x>mode.start_X-(mode.TERM*i)) 
				return i-1;
		}
		return mode.MAX-1;
	}
	//���콺�� ��ǥ�� ���� �迭�� ���� �� ��ȯ
	private int pick_Y(Point k) {
		for(int i=1;i<mode.MAX;i++) {
			if(k.y<(mode.start_Y-50)+(mode.TERM*i)&&k.y>mode.start_Y-(mode.TERM*i)) 
				return i;
		}
		return mode.MAX;
	}
	//Model(ĳ���� �̹���)�� ��ǥ�� Fix(������)�� ��ǥ�� ���Ͽ� �̵������� 
	private void move() {
		for(int i=0;i<mode.MAX;i++) {
			for(int j=mode.MAX;j>=1;j--) {
				if(Fix[i][j].pY-Model[i][j].pY>0) 
					Model[i][j].moveYplus();
				mainPanel.repaint();	
			}
		}
		for(int i=0;i<mode.MAX;i++) {
			for(int j=mode.MAX;j>=1;j--) {
				if(Fix[i][j].pY-Model[i][j].pY<0) 
					Model[i][j].moveYMinus();
				mainPanel.repaint();	
			}
		}
		for(int i=0;i<mode.MAX;i++) {
			for(int j=mode.MAX;j>=1;j--) {
				if(Fix[i][j].pX-Model[i][j].pX>0) 
					Model[i][j].moveXplus();
				mainPanel.repaint();	
			}
		}
		for(int i=0;i<mode.MAX;i++) {
			for(int j=mode.MAX;j>=1;j--) {
				if(Fix[i][j].pX-Model[i][j].pX<0) 
					Model[i][j].moveXMinus();
				mainPanel.repaint();	
			}
		}
	}
	//�������� ������ ������ ������ 
	public void item_Horizon(int y) {
		for(int i=0;i<mode.MAX;i++) {
			Model[i][y].visuable=false;
			Model[i][y].change=false;
		}
		try {
			Thread.sleep(300);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	//�������� ������ ������ ������
	public void item_Vertical(int x) {
		for(int i=1;i<mode.MAX+1;i++) {
			Model[x][i].visuable=false;
			Model[x][i].change=false;
		}
		try {
			Thread.sleep(300);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	//����� ������ ��� ������ ������
	public void item_HV(int x,int y) {
		for(int i=0;i<mode.MAX;i++) {
			Model[i][y].visuable=false;
			Model[i][y].change=false;
		}
		for(int i=1;i<mode.MAX+1;i++) {
			Model[x][i].visuable=false;
			Model[x][i].change=false;
		}
		try {
			Thread.sleep(300);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	//��� ��ġ�� ��� ĳ���͸� ����ִ� �޼ҵ�
	public void collectPoint() {
		for(int i=0;i<mode.MAX;i++) {
			for(int j=mode.MAX;j>=1;j--) {
				if(Fix[mode.MAX-4][mode.MAX-3].pY-Model[i][j].pY>0) 
					Model[i][j].moveYplus();
				mainPanel.repaint();	
			}
		}
		for(int i=0;i<mode.MAX;i++) {
			for(int j=mode.MAX;j>=1;j--) {
				if(Fix[mode.MAX-4][mode.MAX-3].pY-Model[i][j].pY<0) 
					Model[i][j].moveYMinus();
				mainPanel.repaint();	
			}
		}
		for(int i=0;i<mode.MAX;i++) {
			for(int j=mode.MAX;j>=1;j--) {
				if(Fix[mode.MAX-4][mode.MAX-3].pX-Model[i][j].pX>0) 
					Model[i][j].moveXplus();
				mainPanel.repaint();	
			}
		}
		for(int i=0;i<mode.MAX;i++) {
			for(int j=mode.MAX;j>=1;j--) {
				if(Fix[mode.MAX-4][mode.MAX-3].pX-Model[i][j].pX<0) 
					Model[i][j].moveXMinus();
				mainPanel.repaint();	
			}
		}
	}
	//��� ĳ���͸� �ٸ� �̹����� �����ϴ� �޼ҵ�
	public void resetAll() {
		int eggNumber;
		for(int i=0;i<mode.MAX;i++) {
			for(int j=1;j<mode.MAX+1;j++) {
				eggNumber=(int)(Math.random()*6);
				Model[i][j].changeImage(Character[eggNumber]);
				Model[i][j].number=eggNumber;
			}
		}

		for(int i=0;i<mode.MAX;i++) {
			for(int j=mode.MAX;j>=1;j--) {
				if(Fix[i][j].pY-Model[i][j].pY>0) 
					Model[i][j].moveYplus();
				mainPanel.repaint();	
			}
		}
		for(int i=0;i<mode.MAX;i++) {
			for(int j=mode.MAX;j>=1;j--) {
				if(Fix[i][j].pY-Model[i][j].pY<0) 
					Model[i][j].moveYMinus();
				mainPanel.repaint();	
			}
		}
		for(int i=0;i<mode.MAX;i++) {
			for(int j=mode.MAX;j>=1;j--) {
				if(Fix[i][j].pX-Model[i][j].pX>0) 
					Model[i][j].moveXplus();
				mainPanel.repaint();	
			}
		}
		for(int i=0;i<mode.MAX;i++) {
			for(int j=mode.MAX;j>=1;j--) {
				if(Fix[i][j].pX-Model[i][j].pX<0) 
					Model[i][j].moveXMinus();
				mainPanel.repaint();	
			}
		}
	}
	//2*3ĭ ���� ���ɼ��� �ִ��� �����ϴ� �޼ҵ�
	public boolean probability_1() {
		int [][] check=new int [2][3];
		int [] three=new int [6];
		int same = 0;
		int m=0;

		for(int i=1;i<mode.MAX;i++) {
			for(int j=3;j<mode.MAX+1;j++) {
				check[0][0]=Model[i-1][j-2].number;check[1][0]=Model[i][j-2].number;
				check[0][1]=Model[i-1][j-1].number;check[1][1]=Model[i][j-1].number;
				check[0][2]=Model[i-1][j].number;  check[1][2]=Model[i][j].number;
				for(int n=0;n<6;n++)
					three[n]=0;

				for(int k=0;k<2;k++) {
					for(int l=0;l<3;l++) {
						for(int n=0;n<6;n++) {
							if(check[k][l]==m)
								three[n]++;
							m++;
						}
						m=0;
					}
				}
				for(int n=0;n<6;n++) {
					if(three[n]<3)
						compare_1=true;
					else if(three[n]==3) {
						same=n;
						if(check[0][0]==same&&check[0][0]==check[1][0]
								||check[0][1]==same&&check[0][1]==check[1][1]
										||check[0][2]==same&&check[0][2]==check[1][2])
							compare_1=true;
						else
							return false;
					}
					else if(three[n]==4) {

						if(check[0][0]!=check[1][0]
								||check[0][1]!=check[1][1]
										||check[0][2]!=check[1][2])		
							compare_1=true;
						else
							return false;
					}
					else
						return false;
				}
			}
		}
		return compare_1;
	}
	//3*2ĭ ���� ���ɼ��� �ִ��� �����ϴ� �޼ҵ�
	public boolean probability_2() {

		int [][] check=new int [3][2];
		int [] three=new int [6];
		int same = 0;
		int m=0;

		for(int i=2;i<mode.MAX;i++) {
			for(int j=2;j<mode.MAX+1;j++) {
				check[0][0]=Model[i-2][j-1].number;check[1][0]=Model[i-1][j-1].number;check[2][0]=Model[i][j-1].number;
				check[0][1]=Model[i-2][j].number;check[1][1]=Model[i-1][j].number;check[2][1]=Model[i][j].number;
				for(int n=0;n<6;n++)
					three[n]=0;
				for(int k=0;k<3;k++) {
					for(int l=0;l<2;l++) {
						for(int n=0;n<6;n++) {
							if(check[k][l]==m)
								three[n]++;
							m++;
						}
						m=0;
					}
				}
				for(int n=0;n<6;n++) {
					if(three[n]<3) 
						compare_2=true;

					else if(three[n]==3) {
						same=n;
						if(check[0][0]==same&&check[0][0]==check[0][1]
								||check[1][0]==same&&check[1][0]==check[1][1]
										||check[2][0]==same&&check[2][0]==check[2][1])
							compare_2=true;
						else
							return false;
					}
					else if(three[n]==4) {

						if(check[0][0]!=check[0][1]
								||check[1][0]!=check[1][1]
										||check[2][0]!=check[2][1])		
							compare_2=true;
						else
							return false;
					}
					else
						return false;
				}
			}
		}
		return 	compare_2;
	}
	//4�� ������ ���� ���ɼ��� �ִ��� �����ϴ� �޼ҵ�
	public boolean probability_3() {
		int [][] check=new int [1][4];
		int [] three=new int [6];
		int m=0;

		for(int i=0;i<mode.MAX;i++) {
			for(int j=1;j<mode.MAX-2;j++) {
				check[0][0]=Model[i][j].number;
				check[0][1]=Model[i][j+1].number;
				check[0][2]=Model[i][j+2].number; 
				check[0][3]=Model[i][j+3].number;				
				for(int n=0;n<6;n++)
					three[n]=0;
				for(int l=0;l<4;l++) {
					for(int n=0;n<6;n++) {
						if(check[0][l]==m)
							three[n]++;
						m++;
					}
					m=0;
				}
				for(int n=0;n<6;n++) {
					if(three[n]==3) {
						return false;
					}
					else
						compare_3=true;
				}
			}
		}
		return compare_3;
	}
	////4�� ������ ���� ���ɼ��� �ִ��� �����ϴ� �޼ҵ�
	public boolean probability_4() {
		int [][] check=new int [4][1];
		int [] three=new int [6];
		int same = 0;
		int m=0;
		for(int i=0;i<mode.MAX-3;i++) {
			for(int j=1;j<mode.MAX+1;j++) {
				check[0][0]=Model[i][j].number;
				check[1][0]=Model[i+1][j].number;
				check[2][0]=Model[i+2][j].number; 
				check[3][0]=Model[i+3][j].number;

				for(int n=0;n<6;n++)
					three[n]=0;
				for(int l=0;l<4;l++) {
					for(int n=0;n<6;n++) {
						if(check[l][0]==m)
							three[n]++;
						m++;
					}
					m=0;
				}
				for(int n=0;n<6;n++) {
					if(three[n]==3)
						return false;
					else
						compare_4=true;
				}
			}
		}
		return compare_4;
	}

	@Override
	// mainPanel���� ������ �г��� �����ϴ� ������ �޼ҵ�
	public void actionPerformed(ActionEvent e) {


		if(e.getSource()==modeSelect) {
			JComboBox jb = (JComboBox)e.getSource();
			modeSTR = (String)jb.getSelectedItem();
		}
		if(e.getSource()==start_Button) {
			if(modeSTR==modeStr[0]) {	
				mode=new Mode(7,70,100,5,355,205);				
				go();
				card.show(mainPanel,"a2");
			}
			else if(modeSTR==modeStr[1]) {
				mode=new Mode(10,55,70,5,345,195);		
				go();	
				card.show(mainPanel, "a2");
			}
			else if(modeSTR==modeStr[2]) {
				mode=new Mode(15,35,45,5,355,205);
				go();
				card.show(mainPanel, "a2");
			}
		}
		if(e.getSource()==exit_Button) {
			System.exit(1);
		}
		if(e.getSource()==explain_Button) {
			card.show(mainPanel, "a4");

		}
		if(e.getSource()==back_Button) {
			card.show(mainPanel, "a1");
		}
		if(e.getSource()==replay_Button) {
			card.show(mainPanel, "a2");
			time=60;
			score=0;
			comaLabel.setText("");
			EndComaLabel.setText("");
			scoreLabel.setText(score+"");
			gameT.start();
			background.play();
			imgX=mode.start_X;
			int eggNumber;
			for(int i=0;i<mode.MAX;i++) {
				for(int j=1;j<mode.MAX+1;j++) {
					eggNumber=(int)(Math.random()*6);
					Model[i][j]=(new pang(Character[eggNumber],eggNumber,imgX,imgY,mode.IMAGE_SIZE,mode.IMAGE_SIZE,mode.SPEED,true,true));
					Fix[i][j]=(new pang(imgX,imgY));
					imgY+=mode.TERM;
				}
				imgY=mode.start_Y;
				imgX+=mode.TERM;
			}
		}
		if(e.getSource()==option_Button) {
			setting.setVisible(true);
			setting.setSize(300, 150);
		}
	}
}


