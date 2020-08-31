package Pos;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.Calendar;
import java.util.Vector;
import javax.swing.event.*;
import javax.swing.table.DefaultTableModel;
import Pos.salesChart.DrawActionListener;

public class MainPage  extends JFrame{
	
	//외부클래스 목록
	salesChart graph = new salesChart(); // 그래프 
	TimerThread timer = new TimerThread();//타이머
	SubPage sub = new SubPage(this,"메뉴");//메뉴판연동
	Container c = getContentPane(); //컨테이너 정리
	
	//JPanel 목록
	JPanel top = new JPanel(); //상단
	JPanel topLeft = new JPanel();//상단 좌측
	JPanel topCenter = new JPanel();//상단 중앙
	JPanel topRight = new JPanel();//상단 우측
	JPanel center = new JPanel(); //테이블위치
	JPanel rightAll = new JPanel(); //우측 전체 패널
	JPanel rightTop = new JPanel(); //우측 상단
	JPanel rightbottom = new JPanel();//우측하단
	JPanel bottom = new JPanel(); //하단
	
	//Vector 목록
	Vector<Integer> hap = new Vector<Integer>(); //당일정산?
	int hap0,hap1,hap2,hap3,hap4,hap5,hap6,hap7,hap8,hap9,hap10,hap11,hap12,hap13,hap14;
	int n0,n1,n2,n3,n4,n5,n6,n7,n8,n9,n10,n11,n12,n13,n14;
	int m1,m2,m3,m4,m5,m6,m7,m8,m9,m10,m11,m12;
	JPanel[] gul = {new JPanel(),new JPanel(),
					new JPanel(),new JPanel(),
					new JPanel(),new JPanel(),
					new JPanel(),new JPanel(),
					new JPanel(),new JPanel(),
					new JPanel(),new JPanel(),
					new JPanel(),new JPanel(),
					new JPanel()}; //11월 30일 추가
	JButton[] gulBtn = {new JButton("결제"),new JButton("결제"),
						new JButton("결제"),new JButton("결제"),
						new JButton("결제"),new JButton("결제"),
						new JButton("결제"),new JButton("결제"),
						new JButton("결제"),new JButton("결제"),
						new JButton("결제"),new JButton("결제"),
						new JButton("결제"),new JButton("결제"),
						new JButton("결제")};
	JTextArea[] table = {
		new JTextArea(8,20),new JTextArea(8,20),
		new JTextArea(8,20),new JTextArea(8,20),
		new JTextArea(8,20),new JTextArea(8,20),
		new JTextArea(8,20),new JTextArea(8,20),
		new JTextArea(8,20),new JTextArea(8,20), 
		new JTextArea(8,20),new JTextArea(8,20),
		new JTextArea(8,20),new JTextArea(8,20),
		new JTextArea(8,20)
	};
	JScrollPane[] tableScroll = {
			new JScrollPane(table[0]),new JScrollPane(table[1]),
			new JScrollPane(table[2]),new JScrollPane(table[3]),
			new JScrollPane(table[4]),new JScrollPane(table[5]),
			new JScrollPane(table[6]),new JScrollPane(table[7]),
			new JScrollPane(table[8]),new JScrollPane(table[9]),
			new JScrollPane(table[10]),new JScrollPane(table[11]),
			new JScrollPane(table[12]),new JScrollPane(table[13]),
			new JScrollPane(table[14])
	};
	
	JTextField[] money = {
		new JTextField("0원",10),new JTextField("0원",10),
		new JTextField("0원",10),new JTextField("0원",10),
		new JTextField("0원",10),new JTextField("0원",10),
		new JTextField("0원",10),new JTextField("0원",10),
		new JTextField("0원",10),new JTextField("0원",10),
		new JTextField("0원",10),new JTextField("0원",10),
		new JTextField("0원",10),new JTextField("0원",10),
		new JTextField("0원",10)
	};
	JLabel[] tableNumber = {
			new JLabel("1번 테이블"),new JLabel("2번 테이블"),
			new JLabel("3번 테이블"),new JLabel("4번 테이블"),
			new JLabel("5번 테이블"),new JLabel("6번 테이블"),
			new JLabel("7번 테이블"),new JLabel("8번 테이블"),
			new JLabel("9번 테이블"),new JLabel("10번 테이블"),
			new JLabel("11번 테이블"),new JLabel("12번 테이블"),
			new JLabel("13번 테이블"),new JLabel("14번 테이블"),
			new JLabel("15번 테이블")
	}; //11월 30일 추가
	JTextField date = new JTextField(20);
	JTextField memo = new JTextField(15);
	JButton memoBtn = new JButton("입력");
	JTextArea memoArea = new JTextArea(7,20);
	JButton calculate = new JButton("당일정산");
	JButton start = new JButton("영업시작");
	JButton annualSales = new JButton("연 매출액 확인");
	JLabel clock = new JLabel();
	JLabel title = new JLabel("폴붕이흑우전문점");
	JButton exit = new JButton("종료");
	JButton btn1 = new JButton("미정");
	JButton btn2 = new JButton("미정");
	int sum = 0;
	String vetergap = "";
	String mesg = "";
	int n;
	int jk;
	int gt;
	int gtn;
	int jki;
	
	public MainPage() {
		setTitle("폴붕이흑우전문점");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//컨테이너 구역 설정
		c.setLayout(new BorderLayout());
		c.add(top, BorderLayout.NORTH);
		c.add(center, BorderLayout.CENTER);
		c.add(rightAll, BorderLayout.EAST);
		c.add(bottom, BorderLayout.SOUTH);
		title.setForeground(Color.WHITE);
		clock.setForeground(Color.WHITE);
		title.setFont(new Font("나눔스퀘어", Font.BOLD, 25));		
		clock.setFont(new Font("나눔스퀘어", Font.BOLD, 25));
		//파트별색 구분
		top.setBackground(new Color(37,75,114));
		center.setBackground(new Color(37,75,114));
		bottom.setBackground(new Color(37,75,114));
		topRight.setBackground(new Color(37,75,114));
		topCenter.setBackground(new Color(37,75,114));
		topLeft.setBackground(new Color(37,75,114));
//		top.setFont(new Font("나눔스퀘어", Font.BOLD, 25));
//		center.setFont(new Font("나눔스퀘어", Font.BOLD, 25));
//		bottom.setFont(new Font("나눔스퀘어", Font.BOLD, 25));
//		topRight.setFont(new Font("나눔스퀘어", Font.BOLD, 25));

//		top.setForeground(Color.WHITE);
//		center.setForeground(Color.WHITE);
//		bottom.setForeground(Color.WHITE);
//		topRight.setForeground(Color.WHITE);
//		topCenter.setForeground(Color.WHITE);
//		topLeft.setForeground(Color.WHITE);

		//top
		top.setLayout(new GridLayout(1,3));
		top.add(topLeft);
		top.add(topCenter);
		top.add(topRight);
		topCenter.add(title);
		topRight.add(clock);
		TimerThread th = new TimerThread(clock);
		clock.setFont(new Font("Gothic", Font.ITALIC, 12));
		th.start();
		//center
		center.setLayout(new FlowLayout(FlowLayout.LEFT, 80,100));
		//center -> tableAll 텍스트에어리어 묶음  
		// table 배열 11월 30일 수정
		
		table[0].addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				sub.setVisible(true);
				String mainTotalFld = sub.getInputTotalFld();
				String mainMenuTotal = sub.getInputMenuTotal();
				JTextArea table =(JTextArea)e.getSource();
				table.append(mainMenuTotal);
				sub.n="";
				if(mainTotalFld!= null) {
				int t0 = Integer.parseInt(mainTotalFld);
				hap0 +=t0;
				hap.add(hap0);
				sub.totalFld.setText("");
				String t0h = Integer.toString(hap0)+"원";
				money[0].setText(t0h);
				}
			}
		});
		table[1].addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				sub.setVisible(true);
				String mainTotalFld = sub.getInputTotalFld();
				String mainMenuTotal = sub.getInputMenuTotal();
				JTextArea table =(JTextArea)e.getSource();
				table.append(mainMenuTotal);
				sub.n="";
				if(mainTotalFld!= null) {
				int t1 = Integer.parseInt(mainTotalFld);
				hap1 += t1;
				hap.add(hap1);
				sub.totalFld.setText("");
				String t1h = Integer.toString(hap1)+"원";
				money[1].setText(t1h);
				}
			}
		});
		table[2].addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				sub.setVisible(true);
				String mainTotalFld = sub.getInputTotalFld();
				String mainMenuTotal = sub.getInputMenuTotal();
				JTextArea table =(JTextArea)e.getSource();
				table.append(mainMenuTotal);
				sub.n="";
				if(mainTotalFld!= null) {
				int t2 = Integer.parseInt(mainTotalFld);
				hap2 += t2;
				hap.add(hap2);
				sub.totalFld.setText("");
				String t2h = Integer.toString(hap2)+"원";
				money[2].setText(t2h);
				}
			}
		});
		table[3].addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				sub.setVisible(true);
				String mainTotalFld = sub.getInputTotalFld();
				String mainMenuTotal = sub.getInputMenuTotal();
				JTextArea table =(JTextArea)e.getSource();
				table.append(mainMenuTotal);
				sub.n="";
				if(mainTotalFld!= null) {
				int t3 = Integer.parseInt(mainTotalFld);
				hap3 += t3;
				hap.add(hap3);
				sub.totalFld.setText("");
				String t3h = Integer.toString(hap3)+"원";
				money[3].setText(t3h);
				}
			}
		});
		table[4].addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				sub.setVisible(true);
				String mainTotalFld = sub.getInputTotalFld();
				String mainMenuTotal = sub.getInputMenuTotal();
				JTextArea table =(JTextArea)e.getSource();
				table.append(mainMenuTotal);
				sub.n="";
				if(mainTotalFld!= null) {
				int t4 = Integer.parseInt(mainTotalFld);
				hap4 += t4;
				hap.add(hap4);
				sub.totalFld.setText("");
				String t4h = Integer.toString(hap4)+"원";
				money[4].setText(t4h);
				}
			}
		});
		table[5].addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				sub.setVisible(true);
				String mainTotalFld = sub.getInputTotalFld();
				String mainMenuTotal = sub.getInputMenuTotal();
				JTextArea table =(JTextArea)e.getSource();
				table.append(mainMenuTotal);
				sub.n="";
				if(mainTotalFld!= null) {
				int t5 = Integer.parseInt(mainTotalFld);
				hap5 += t5;
				hap.add(hap5);
				sub.totalFld.setText("");
				String t5h = Integer.toString(hap5)+"원";
				money[5].setText(t5h);
				}
			}
		});
		table[6].addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				sub.setVisible(true);
				String mainTotalFld = sub.getInputTotalFld();
				String mainMenuTotal = sub.getInputMenuTotal();
				JTextArea table =(JTextArea)e.getSource();
				table.append(mainMenuTotal);
				sub.n="";
				if(mainTotalFld!= null) {
				int t6 = Integer.parseInt(mainTotalFld);
				hap6 += t6;
				hap.add(hap6);
				sub.totalFld.setText("");
				String t6h = Integer.toString(hap6)+"원";
				money[6].setText(t6h);
				}
			}
		});
		table[7].addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				sub.setVisible(true);
				String mainTotalFld = sub.getInputTotalFld();
				String mainMenuTotal = sub.getInputMenuTotal();
				JTextArea table =(JTextArea)e.getSource();
				table.append(mainMenuTotal);
				sub.n="";
				if(mainTotalFld!= null) {
				int t7 = Integer.parseInt(mainTotalFld);
				hap7 += t7;
				hap.add(hap7);
				sub.totalFld.setText("");
				String t7h = Integer.toString(hap7)+"원";
				money[7].setText(t7h);
				}
			}
		});
		table[8].addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				sub.setVisible(true);
				String mainTotalFld = sub.getInputTotalFld();
				String mainMenuTotal = sub.getInputMenuTotal();
				JTextArea table =(JTextArea)e.getSource();
				table.append(mainMenuTotal);
				sub.n="";
				if(mainTotalFld!= null) {
				int t8 = Integer.parseInt(mainTotalFld);
				hap8 += t8;
				hap.add(hap8);
				sub.totalFld.setText("");
				String t8h = Integer.toString(hap8)+"원";
				money[8].setText(t8h);
				}
			}
		});
		table[9].addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				sub.setVisible(true);
				String mainTotalFld = sub.getInputTotalFld();
				String mainMenuTotal = sub.getInputMenuTotal();
				JTextArea table =(JTextArea)e.getSource();
				table.append(mainMenuTotal);
				sub.n="";
				if(mainTotalFld!= null) {
				int t9 = Integer.parseInt(mainTotalFld);
				hap9 += t9;
				hap.add(hap9);
				sub.totalFld.setText("");
				String t9h = Integer.toString(hap9)+"원";
				money[9].setText(t9h);
				}
			}
		});
		table[10].addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				sub.setVisible(true);
				String mainTotalFld = sub.getInputTotalFld();
				String mainMenuTotal = sub.getInputMenuTotal();
				JTextArea table =(JTextArea)e.getSource();
				table.append(mainMenuTotal);
				sub.n="";
				if(mainTotalFld!= null) {
				int t10 = Integer.parseInt(mainTotalFld);
				hap10 += t10;
				hap.add(hap10);
				sub.totalFld.setText("");
				String t10h = Integer.toString(hap10)+"원";
				money[10].setText(t10h);
				}
			}
		});
		table[11].addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				sub.setVisible(true);
				String mainTotalFld = sub.getInputTotalFld();
				String mainMenuTotal = sub.getInputMenuTotal();
				JTextArea table =(JTextArea)e.getSource();
				table.append(mainMenuTotal);
				sub.n="";
				if(mainTotalFld!= null) {
				int t11 = Integer.parseInt(mainTotalFld);
				hap11 += t11;
				hap.add(hap11);
				sub.totalFld.setText("");
				String t11h = Integer.toString(hap11)+"원";
				money[11].setText(t11h);
				}
			}
		});
		table[12].addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				sub.setVisible(true);
				String mainTotalFld = sub.getInputTotalFld();
				String mainMenuTotal = sub.getInputMenuTotal();
				JTextArea table =(JTextArea)e.getSource();
				table.append(mainMenuTotal);
				sub.n="";
				if(mainTotalFld!= null) {
				int t12 = Integer.parseInt(mainTotalFld);
				hap12 += t12;
				hap.add(hap12);
				sub.totalFld.setText("");
				String t12h = Integer.toString(hap12)+"원";
				money[12].setText(t12h);
				}
			}
		});
		table[13].addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				sub.setVisible(true);
				String mainTotalFld = sub.getInputTotalFld();
				String mainMenuTotal = sub.getInputMenuTotal();
				JTextArea table =(JTextArea)e.getSource();
				table.append(mainMenuTotal);
				sub.n="";
				if(mainTotalFld!= null) {
				int t13 = Integer.parseInt(mainTotalFld);
				hap13 += t13;
				hap.add(hap13);
				sub.totalFld.setText("");
				String t13h = Integer.toString(hap13)+"원";
				money[13].setText(t13h);
				}
			}
		});
		table[14].addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				sub.setVisible(true);
				String mainTotalFld = sub.getInputTotalFld();
				String mainMenuTotal = sub.getInputMenuTotal();
				JTextArea table =(JTextArea)e.getSource();
				table.append(mainMenuTotal);
				sub.n="";
				if(mainTotalFld!= null) {
				int t14 = Integer.parseInt(mainTotalFld);
				hap14 += t14;
				hap.add(hap14);
				sub.totalFld.setText("");
				String t14h = Integer.toString(hap14)+"원";
				money[14].setText(t14h);
				}
			}
		});

		//결제버튼 클릭시 벡터에 값 입력
		gulBtn[0].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int result = JOptionPane.showConfirmDialog(null, "현재 금액은 "+hap0+"원 입니다."+"결제 하시겠습니까?","결제 확인",JOptionPane.YES_NO_OPTION);
				if(result==JOptionPane.YES_OPTION) {
					sum+=hap0;
					hap.add(hap0);
					sub.totalFld.setText("");
					hap0=0;
					String t0h = Integer.toString(hap0)+"원";
					money[0].setText(t0h);
					table[0].setText("");	
				}
			}
		});
		gulBtn[1].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int result = JOptionPane.showConfirmDialog(null, "현재 금액은 "+hap1+"원 입니다."+"결제 하시겠습니까?","결제 확인",JOptionPane.YES_NO_OPTION);
				if(result==JOptionPane.YES_OPTION) {
					sum+=hap1;
					hap.add(hap1);
					sub.totalFld.setText("");
					hap1=0;
					String t1h = Integer.toString(hap1)+"원";
					money[1].setText(t1h);
					table[1].setText("");	
				}
			}
		});
		gulBtn[2].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int result = JOptionPane.showConfirmDialog(null, "현재 금액은 "+hap2+"원 입니다."+"결제 하시겠습니까?","결제 확인",JOptionPane.YES_NO_OPTION);
				if(result==JOptionPane.YES_OPTION) {
					sum+=hap2;
					hap.add(hap2);
					sub.totalFld.setText("");
					hap2=0;
					String t2h = Integer.toString(hap2)+"원";
					money[2].setText(t2h);
					table[2].setText("");	
				}
			}
		});
		gulBtn[3].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int result = JOptionPane.showConfirmDialog(null, "현재 금액은 "+hap3+"원 입니다."+"결제 하시겠습니까?","결제 확인",JOptionPane.YES_NO_OPTION);
				if(result==JOptionPane.YES_OPTION) {
					sum+=hap3;
					hap.add(hap3);
					sub.totalFld.setText("");
					hap3=0;
					String t3h = Integer.toString(hap3)+"원";
					money[3].setText(t3h);
					table[3].setText("");	
				}
			}
		});
		gulBtn[4].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int result = JOptionPane.showConfirmDialog(null, "현재 금액은 "+hap4+"원 입니다."+"결제 하시겠습니까?","결제 확인",JOptionPane.YES_NO_OPTION);
				if(result==JOptionPane.YES_OPTION) {
					sum+=hap4;
					hap.add(hap4);
					sub.totalFld.setText("");
					hap4=0;
					String t4h = Integer.toString(hap4)+"원";
					money[4].setText(t4h);
					table[4].setText("");	
				}
			}
		});
		gulBtn[5].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int result = JOptionPane.showConfirmDialog(null, "현재 금액은 "+hap5+"원 입니다."+"결제 하시겠습니까?","결제 확인",JOptionPane.YES_NO_OPTION);
				if(result==JOptionPane.YES_OPTION) {
					sum+=hap5;
					hap.add(hap5);
					sub.totalFld.setText("");
					hap5=0;
					String t5h = Integer.toString(hap5)+"원";
					money[5].setText(t5h);
					table[5].setText("");	
				}
			}
		});
		gulBtn[6].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int result = JOptionPane.showConfirmDialog(null, "현재 금액은 "+hap6+"원 입니다."+"결제 하시겠습니까?","결제 확인",JOptionPane.YES_NO_OPTION);
				if(result==JOptionPane.YES_OPTION) {
					sum+=hap6;
					hap.add(hap6);
					sub.totalFld.setText("");
					hap6=0;
					String t6h = Integer.toString(hap6)+"원";
					money[6].setText(t6h);
					table[6].setText("");	
				}
			}
		});
		gulBtn[7].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int result = JOptionPane.showConfirmDialog(null, "현재 금액은 "+hap7+"원 입니다."+"결제 하시겠습니까?","결제 확인",JOptionPane.YES_NO_OPTION);
				if(result==JOptionPane.YES_OPTION) {
					sum+=hap7;
					hap.add(hap7);
					sub.totalFld.setText("");
					hap7=0;
					String t7h = Integer.toString(hap7)+"원";
					money[7].setText(t7h);
					table[7].setText("");	
				}
			}
		});
		gulBtn[8].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int result = JOptionPane.showConfirmDialog(null, "현재 금액은 "+hap8+"원 입니다."+"결제 하시겠습니까?","결제 확인",JOptionPane.YES_NO_OPTION);
				if(result==JOptionPane.YES_OPTION) {
					sum+=hap8;
					hap.add(hap8);
					sub.totalFld.setText("");
					hap8=0;
					String t8h = Integer.toString(hap8)+"원";
					money[8].setText(t8h);
					table[8].setText("");	
				}
			}
		});
		gulBtn[9].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int result = JOptionPane.showConfirmDialog(null, "현재 금액은 "+hap9+"원 입니다."+"결제 하시겠습니까?","결제 확인",JOptionPane.YES_NO_OPTION);
				if(result==JOptionPane.YES_OPTION) {
					sum+=hap9;
					hap.add(hap9);
					sub.totalFld.setText("");
					hap9=0;
					String t9h = Integer.toString(hap9)+"원";
					money[9].setText(t9h);
					table[9].setText("");	
				}
			}
		});
		gulBtn[10].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int result = JOptionPane.showConfirmDialog(null, "현재 금액은 "+hap10+"원 입니다."+"결제 하시겠습니까?","결제 확인",JOptionPane.YES_NO_OPTION);
				if(result==JOptionPane.YES_OPTION) {
					sum+=hap10;
					hap.add(hap10);
					sub.totalFld.setText("");
					hap10=0;
					String t10h = Integer.toString(hap10)+"원";
					money[10].setText(t10h);
					table[10].setText("");	
				}
			}
		});
		gulBtn[11].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int result = JOptionPane.showConfirmDialog(null, "현재 금액은 "+hap11+"원 입니다."+"결제 하시겠습니까?","결제 확인",JOptionPane.YES_NO_OPTION);
				if(result==JOptionPane.YES_OPTION) {
					sum+=hap11;
					hap.add(hap11);
					sub.totalFld.setText("");
					hap11=0;
					String t11h = Integer.toString(hap11)+"원";
					money[11].setText(t11h);
					table[11].setText("");	
				}
			}
		});
		gulBtn[12].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int result = JOptionPane.showConfirmDialog(null, "현재 금액은 "+hap12+"원 입니다."+"결제 하시겠습니까?","결제 확인",JOptionPane.YES_NO_OPTION);
				if(result==JOptionPane.YES_OPTION) {
					sum+=hap12;
					hap.add(hap12);
					sub.totalFld.setText("");
					hap12=0;
					String t12h = Integer.toString(hap12)+"원";
					money[12].setText(t12h);
					table[12].setText("");	
				}
			}
		});
		gulBtn[13].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int result = JOptionPane.showConfirmDialog(null, "현재 금액은 "+hap13+"원 입니다."+"결제 하시겠습니까?","결제 확인",JOptionPane.YES_NO_OPTION);
				if(result==JOptionPane.YES_OPTION) {
					sum+=hap13;
					hap.add(hap13);
					sub.totalFld.setText("");
					hap13=0;
					String t13h = Integer.toString(hap13)+"원";
					money[13].setText(t13h);
					table[13].setText("");	
				}
			}
		});
		gulBtn[14].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int result = JOptionPane.showConfirmDialog(null, "현재 금액은 "+hap14+"원 입니다."+"결제 하시겠습니까?","결제 확인",JOptionPane.YES_NO_OPTION);
				if(result==JOptionPane.YES_OPTION) {
					sum+=hap14;
					hap.add(hap14);
					sub.totalFld.setText("");
					hap14=0;
					String t14h = Integer.toString(hap14)+"원";
					money[14].setText(t14h);
					table[14].setText("");	
				}
			}
		});
		
			for(int i=0; i<table.length; i++) { 
				JPanel tableAll = new JPanel();
				JPanel centerbottom = new JPanel();
				tableAll.setLayout(new BorderLayout());
				center.add(tableAll);
				gul[i].add(tableNumber[i]);
				gul[i].add(gulBtn[i]);
				gul[i].setFont(new Font("나눔스퀘어",Font.PLAIN,10));
				tableAll.add(centerbottom, BorderLayout.SOUTH);
				table[i].setCaretPosition(table[i].getDocument().getLength());
				tableAll.add(tableScroll[i]);
				tableAll.add(gul[i],BorderLayout.NORTH);
				centerbottom.add(new JLabel("합계:"));
				centerbottom.add(money[i]);
			}
			
			
			
			
			//rightall (오른쪽을 책임지는 전체 패널)
			rightAll.setLayout(new GridLayout(2,1,3,3));
			rightAll.add(rightTop);//right top
			rightAll.add(rightbottom);//
				//righttop
				rightTop.setLayout(new BorderLayout());
				rightTop.add(new JLabel("메모장"), BorderLayout.NORTH);
				rightTop.add(memoArea, BorderLayout.CENTER);
				//rightbottom
				rightbottom.setLayout(new GridLayout(6,1,2,2));
				rightbottom.setBackground(new Color(37,75,114));
				rightbottom.add(calculate);
					calculate.addActionListener(new ActionListener() { //당일정산 
						@Override
						public void actionPerformed(ActionEvent arg0) {
							if(sum!=0) {
								for(int i=0; i < hap.size(); i++) {
								gtn = hap.elementAt(i);
								jki += gtn;
								jk = jki/2;
								}
								System.out.println("n의값:"+jki);
								vetergap = Integer.toString(sum);
								System.out.println("벡터에 저장된 값은 :"+gtn);
								mesg = "현재 매출액은 " + vetergap + "원 입니다";
							JOptionPane.showMessageDialog(null, mesg);	
							graph.yearhap.add(jk);
							gt = graph.yearhap.size();
							System.out.println("연매출합사이즈 "+gt);
							}
							else {
								JOptionPane.showMessageDialog(null,"금일 매출이 아직 없습니다.");	
							}
						}
						
						});
					rightbottom.add(start);
					start.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							// TODO Auto-generated method stub
							//11월 30일 --------------------------------------------------------------------------
							JButton b = (JButton)e.getSource();
							if(b.getText().equals("영업시작")) {
								String tuo = clock.getText();
								String total = "오늘의 영업 시작 시간은 "+ tuo +" 입니다.";
								JOptionPane.showMessageDialog(null, total);
								b.setText("영업종료");
								
							}
							else {
								String tuo2 = clock.getText();
								String total2 = "오늘 영업 종료 시간은  "+ tuo2 + " 입니다." + "\n" + "수고하셨습니다!";
								JOptionPane.showMessageDialog(null, total2);
								b.setText("영업시작");
								sum=0;
						  //-------------------------------------------------------------------------------------      
							}
						}
					});
					
					rightbottom.add(annualSales);
						annualSales.addActionListener(new ActionListener() {
							
							@Override
							public void actionPerformed(ActionEvent e) {
								// TODO Auto-generated method stub
								graph.setVisible(true);
							}
						});
						rightbottom.add(btn1);
						rightbottom.add(btn2);
						btn1.setEnabled(false);
						btn2.setEnabled(false);
						rightbottom.add(exit);
						exit.addActionListener(new ActionListener() {
							
							@Override
							public void actionPerformed(ActionEvent e) {
								// TODO Auto-generated method stub
								System.exit(0);
							}
						});
					
			//bottom
			bottom.setLayout(new GridLayout(2,1,4,4));
			bottom.add(new JLabel("Ver 1"));
			bottom.add(new JLabel("제작자 : 김원규, 박세진"));
			
		setVisible(true);
		setSize(500,500);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
	}

	public static void main(String[] args) {
		new MainPage();
	}
}
