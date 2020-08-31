package Pos;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.Vector;
import java.util.*;


public class salesChart extends JFrame{
	Container c = getContentPane();
	JPanel left = new JPanel();
	JPanel right = new JPanel();
	JPanel rightRight = new JPanel();
	JPanel rightLift = new JPanel();
	MyPanel myPanel = new MyPanel();
	Calendar cal = Calendar.getInstance();
	int mon = cal.get(cal.MONTH)+1;
	int og;
	int r;
	Vector<Integer> yearhap = new Vector<Integer>(12);//11월30일 연매출 정산 벡터
	JLabel[] text = {
			new JLabel("1월 평균"), new JLabel("2월 평균"), 
			new JLabel("3월 평균"), new JLabel("4월 평균"),
			new JLabel("5월 평균"), new JLabel("6월 평균"),
			new JLabel("7월 평균"), new JLabel("8월 평균"), 
			new JLabel("9월 평균"), new JLabel("10월 평균"),
			new JLabel("11월 평균"), new JLabel("12월 평균")
	};
	JTextField gapText1 = new JTextField(15);
	JTextField gapText2 = new JTextField(15);
	JTextField gapText3 = new JTextField(15);
	JTextField gapText4 = new JTextField(15);
	JTextField gapText5 = new JTextField(15);
	JTextField gapText6 = new JTextField(15);
	JTextField gapText7 = new JTextField(15);
	JTextField gapText8 = new JTextField(15);
	JTextField gapText9 = new JTextField(15);
	JTextField gapText10 = new JTextField(15);
	JTextField gapText11 = new JTextField(15);
	JTextField gapText12 = new JTextField(15);
	
	
	String[] gapText = new String[6];
	JButton btn1 = new JButton("평균계산하기");
	
	
	public salesChart() {
		setTitle("월별 평균 매출");
		
		
		c.setLayout(new BorderLayout());
		c.add(right, BorderLayout.EAST);
		c.add(myPanel, BorderLayout.CENTER);
		right.setLayout(new BorderLayout());
		right.add(rightRight, BorderLayout.CENTER);
		right.add(rightLift, BorderLayout.WEST);
		rightLift.setLayout(new GridLayout(13,1));
		rightRight.setLayout(new GridLayout(13,1));
		for(int i=0; i<text.length; i++) {
			rightLift.add(text[i]);
		}
		gapText1.setText("0");
		gapText2.setText("0");
		gapText3.setText("0");
		gapText4.setText("0");
		gapText5.setText("0");
		gapText6.setText("0");
		gapText7.setText("0");
		gapText8.setText("0");
		gapText9.setText("0");
		gapText10.setText("0");
		gapText11.setText("0");
		gapText12.setText("0");
		
		rightLift.add(new JLabel("10만 단위"));
		rightRight.add(gapText1);
		rightRight.add(gapText2);
		rightRight.add(gapText3);
		rightRight.add(gapText4);
		rightRight.add(gapText5);
		rightRight.add(gapText6);
		rightRight.add(gapText7);
		rightRight.add(gapText8);
		rightRight.add(gapText9);
		rightRight.add(gapText10);
		rightRight.add(gapText11);
		rightRight.add(gapText12);
		
		rightLift.setBackground(Color.PINK);
		rightRight.add(btn1);
		btn1.addActionListener(new DrawActionListener
	          (gapText1, gapText2, gapText3, gapText4, gapText5, 
			   gapText6, gapText7, gapText8, gapText9, gapText10,
			   gapText11, gapText12, myPanel));
		
		pack();
		setSize(720,400);
		
		
	}
    class MyPanel extends JPanel {

		int gapInt1,gapInt2,gapInt3,gapInt4,gapInt5,gapInt6,
			gapInt7,gapInt8,gapInt9,gapInt10,gapInt11,gapInt12;
		public void paint(Graphics g) {
	        g.clearRect(0,0,getWidth(),getHeight());
	        g.drawLine(50,250,450,250);
	        for(int cnt = 1 ;cnt<11;cnt++) {
	         g.drawString(cnt *100 +"만",1,255-20*cnt);
	         g.drawLine(50, 250-20*cnt, 450,250-20*cnt);
	        }
	        //11월 30일 필드에 값넘기는 역할을 함------------------
	        for(int i=0; i<yearhap.size(); i++) {
		        r = yearhap.elementAt(i);
		        int tg = r/100000;
		        System.out.println("이것이 바로 r값 : " +tg);
		        String gg = Integer.toString(tg);
		       	System.out.println("스트링으로 변환된 "+ gg);
		       switch (mon) {
			case 1:
				gapText1.setText(gg);
				break;
			case 2:
				gapText2.setText(gg);
				break;
			case 3:
				gapText3.setText(gg);
				break;
			case 4:
				gapText4.setText(gg);
				break;
			case 5:
				gapText5.setText(gg);
				break;
			case 6:
				gapText6.setText(gg);
				break;
			case 7:
				gapText7.setText(gg);
				break;
			case 8:
				gapText8.setText(gg);
				break;
			case 9:
				gapText9.setText(gg);
				break;
			case 10:
				gapText10.setText(gg);
				break;
			case 11:
				gapText11.setText(gg);
				break;
			case 12:
				gapText12.setText(gg);
				break;
			default:
				break;
			}
		        tg=0;
		    }
	        //-------------------------------------------
	        
	        g.drawLine(50,20,50,250);
	        g.drawString("1월",55,270);
	        g.drawString("2월",85,270);
	        g.drawString("3월",115,270);
	        g.drawString("4월",145,270);
	        g.drawString("5월",175,270);
	        g.drawString("6월",205,270);
	        g.drawString("7월",235,270);
	        g.drawString("8월",265,270);
	        g.drawString("9월",295,270);
	        g.drawString("10월",325,270);
	        g.drawString("11월",362,270);
	        g.drawString("12월",400,270);
	        g.setColor(Color.RED);
	      
	        if(gapInt1>0) //1월달
	        	g.fillRect(55,250-gapInt1*2,10,gapInt1*2);
	        if(gapInt2>0) //2월달
	        	g.fillRect(85,250-gapInt2*2,10,gapInt2*2);
	        if(gapInt3>0) //3월달
	        	g.fillRect(115,250-gapInt3*2,10,gapInt3*2);
	        if(gapInt4>0) //4월달
		         g.fillRect(145,250-gapInt4*2,10,gapInt4*2);
	        if(gapInt5>0) //5월달
		         g.fillRect(175,250-gapInt5*2,10,gapInt5*2);
	        if(gapInt6>0) //6월달
		         g.fillRect(205,250-gapInt6*2,10,gapInt6*2);
	        if(gapInt7>0) //7월달
		         g.fillRect(235,250-gapInt7*2,10,gapInt7*2);
	        if(gapInt8>0) //8월달
		         g.fillRect(265,250-gapInt8*2,10,gapInt8*2);
	        if(gapInt9>0) //9월달
		         g.fillRect(295,250-gapInt9*2,10,gapInt9*2);
	        if(gapInt10>0)//10월달
		         g.fillRect(325,250-gapInt10*2,10,gapInt10*2);
	        if(gapInt11>0)//11월달
		         g.fillRect(362,250-gapInt11*2,10,gapInt11*2);
	        if(gapInt12>0)//12월달
		         g.fillRect(400,250-gapInt12*2,10,gapInt12*2);
			}
			  void setScores( int gap1, int gap2, int gap3, int gap4
							 ,int gap5, int gap6, int gap7, int gap8
							 ,int gap9, int gap10, int gap11, int gap12 ) {
			  this.gapInt1=gap1; this.gapInt4=gap4; this.gapInt7=gap7; this.gapInt10=gap10;
			  this.gapInt2=gap2; this.gapInt5=gap5; this.gapInt8=gap8; this.gapInt11=gap11;
			  this.gapInt3=gap3; this.gapInt6=gap6; this.gapInt9=gap9; this.gapInt12=gap12;
			 }
		}
	class DrawActionListener implements ActionListener {
	 JTextField text1,text2,text3,text4,text5,
	 text6,text7,text8,text9,text10,text11,text12;
	 MyPanel myPanel;
	 DrawActionListener(JTextField text1, JTextField text2, JTextField text3, JTextField text4, 
						JTextField text5, JTextField text6, JTextField text7, 
						JTextField text8, JTextField text9, JTextField text10, 
						JTextField text11, JTextField text12, MyPanel myPanel) {
						this.text1=text1; this.text2=text2; this.text3=text3; this.text4=text4;
						this.text5=text5; this.text6=text6; this.text7=text7; this.text8=text8;
						this.text9=text9; this.text10=text10; this.text11=text11; this.text12=text12;
						this.myPanel=myPanel;
	 }

	public void actionPerformed(ActionEvent e) {
	  try
	  {
	   int gap1 = Integer.parseInt(text1.getText());
	   int gap2 = Integer.parseInt(text2.getText());
	   int gap3 = Integer.parseInt(text3.getText());
	   int gap4 = Integer.parseInt(text4.getText());
	   int gap5 = Integer.parseInt(text5.getText());
	   int gap6 = Integer.parseInt(text6.getText());
	   int gap7 = Integer.parseInt(text7.getText());
	   int gap8 = Integer.parseInt(text8.getText());
	   int gap9 = Integer.parseInt(text9.getText());
	   int gap10 = Integer.parseInt(text10.getText());
	   int gap11 = Integer.parseInt(text11.getText());
	   int gap12 = Integer.parseInt(text12.getText());
	   myPanel.setScores(gap1, gap2, gap3, gap4, 
			             gap5, gap6, gap7, gap8, 
			             gap9, gap10, gap11, gap12);
	   myPanel.repaint();
	  }
	  catch (NumberFormatException nfe){
	   JOptionPane.showMessageDialog(myPanel,"잘못된 숫자 입력입니다","에러메시지",JOptionPane.ERROR_MESSAGE);
	  }
	 }
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new salesChart();
	}
}
