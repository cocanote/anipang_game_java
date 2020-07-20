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
	titleLabel =new JLabel("설정");	
	titlePanel.add(titleLabel);
	
	 group = new ButtonGroup();
	green= new JRadioButton("초록색");
	pink=new JRadioButton("핑크색");
	blue=new JRadioButton("파랑색");
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
	text1= new JLabel("배경색");
	text1Panel.add(text1);
	
	this.add(titlePanel, BorderLayout.NORTH);  //이 패널이 가장 위로 간다
	//this.add(titlePanel);
	//this.add(text1Panel);
	//this.add(titlePanel);
	this.add(text1Panel, BorderLayout.CENTER); //이 패널이 한가운데로 간다
	this.add(radioPanel, BorderLayout.SOUTH); //이 패널이 가장 밑으로 간다
    

}
public void actionPerformed(ActionEvent a) {//a는 ActionEvent를 실행하기 위한 객체이름으로, 다른걸로 변경해도 무관 
	  if(a.getSource()==green) { //getSource는 object자체를 리턴해주는 기능. 
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
