import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class Set_Up extends JFrame implements ActionListener{

JPanel setUpPanel,titlePanel,text1Panel,radioPanel;
JLabel titleLabel,text1,text2;
JRadioButton green,pink,blue;
ButtonGroup group ;
FlowLayout flowLayout;




boolean g,p,b;
public void SetUp() {
	

//	setUpPanel.setBackground(Color.gray);
	titlePanel=new JPanel();
	titleLabel =new JLabel("����");	
	titlePanel.add(titleLabel);
	
	 group = new ButtonGroup();
	green= new JRadioButton("�ʷϻ�");
	pink=new JRadioButton("��ũ��");
	blue=new JRadioButton("�Ķ���");
	green.addActionListener(this);
	pink.addActionListener(this);
	blue.addActionListener(this);
	group.add(green);
	group.add(pink);
	group.add(blue);
	radioPanel=new JPanel();
	
	radioPanel.add(green);
	radioPanel.add(pink);
	radioPanel.add(blue);
	
	green.doClick();
	text1Panel=new JPanel();
	text1= new JLabel("����");
	text1Panel.add(text1);
	
	this.add(titlePanel, BorderLayout.NORTH);  //�� �г��� ���� ���� ����
	//this.add(titlePanel);
	//this.add(text1Panel);
	//this.add(titlePanel);
	this.add(text1Panel, BorderLayout.CENTER); //�� �г��� �Ѱ���� ����
	this.add(radioPanel, BorderLayout.SOUTH); //�� �г��� ���� ������ ����
    

}
public void actionPerformed(ActionEvent a) {//a�� ActionEvent�� �����ϱ� ���� ��ü�̸�����, �ٸ��ɷ� �����ص� ���� 
	  if(a.getSource()==green) { //getSource�� object��ü�� �������ִ� ���. 
	   g=true;
	   p=false;
	   b=false;
	  }
	  
	  if(a.getSource()==pink) {
		  g=false;
		   p=true;
		   b=false;
	  }
	  
	  if(a.getSource()==blue) {
		  g=false;
		   p=false;
		   b=true;
	  }
	 }
}
