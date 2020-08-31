package Pos;
import java.awt.*;
import java.awt.event.*;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Vector;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableStringConverter;

class SubPage extends JDialog{
	JPanel right = new JPanel(); // 오른쪽 메뉴,주문 버튼 있는 부분
	JPanel left = new JPanel(); // 계산기와 음식 메뉴 합계 나오는 부분
	JPanel leftTop = new JPanel(); // 음식 메뉴 합계 나오는 부분
	JPanel leftTopTop = new JPanel();
	JPanel leftTopBottom = new JPanel();
	JPanel leftBottom = new JPanel(); // 음식 총금액 계산기 나오는 부분
	JPanel leftBottomLeft = new JPanel(); // 음식 총금액 나오는 부분
	JPanel leftBottomRight = new JPanel(); // 계산기 나오는 부분
	JPanel rightTop = new JPanel(); // 메뉴 나오는 부분
	JPanel rightBottom = new JPanel();
	JPanel menu = new JPanel(); // 음식 메뉴 선택 부분
	JPanel calculator = new JPanel(); //계산기
	String[] menuName = {"고기","등심","안창살","부채살","채끝살","갈비살","제비추리","삼겹살","목살","모둠A",
						 "식사","갈비탕","냉면","육개장","김치찌개","된장찌개","미역국","콩나물국","계란찜","공깃밥",
						 "음료","참이슬","처음처럼","이슬톡톡","하이트","카스","막걸리","콜라","사이다","환타",
						 "미정","미정","미정","미정","미정"
						 };
	String[] option = {"주문","선택취소","전체취소","창닫기","영수증"};
	JButton[] optionButton = new JButton[5];
	String [] ColName = {"메뉴","가격"};
	String [][] Data ;
	DefaultTableModel model = new DefaultTableModel(Data,ColName);
	JTable jTable = new JTable(model);
	int[] price = {0,27000,27000,27000,30000,35000,36000,10000,10000,160000,
				   0,10000,7000,10000,7000,7000,20000,7000,7000,7000,
				   0,4000,4000,4000,4000,4000,4000,1500,1500,1500,0,0,0,0,0
				   };
	JButton[] menuButton = new JButton[35];
	JLabel totalLbl = new JLabel("합계 : ");
	JTextField totalFld = new JTextField(20);
	int sum;
	String menuSum;
	Vector<Integer> v = new Vector<Integer>();//합을 위한 벡터?
	Vector<String> s = new Vector<String>();
	String a;
	String n = "";
	int minus;
	int hap;
	public SubPage(JFrame frame, String title) {
		super(frame,title,true);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		setLayout(new BorderLayout());
		setBackground(new Color(37,75,114));
		left.setBackground(new Color(37,75,114));
		leftTop.setBackground(new Color(37,75,114));
		leftTopTop.setBackground(new Color(37,75,114));
		leftTopBottom.setBackground(new Color(37,75,114));
		right.setBackground(new Color(37,75,114));
		left.setLayout(new BorderLayout()); // 계산기와 음식 메뉴 합계 나오는 부분 레이아웃
		rightTop.setLayout(new GridLayout(7,5,5,5)); // 오른쪽 메뉴,주문 버튼 있는 부분 레이아웃
		rightBottom.setLayout(new GridLayout(1,5,5,5));
		leftBottom.setLayout(new BorderLayout()); // 음식 총금액 계산기 나오는 부분 레이아웃
		leftTop.setLayout(new BorderLayout());
		right.setLayout(new BorderLayout());
		totalFld.setSize(350, 70);
		jTable.getTableHeader().setFont(new Font("맑은고딕", Font.BOLD, 15));
		jTable.setRowHeight(35);
		JScrollPane tableScroll = new JScrollPane(jTable);
		tableScroll.setPreferredSize(new Dimension(600,700));
		for(int i=0;i<menuButton.length;i++) {
			rightTop.add(menuButton[i]=new JButton(menuName[i]));
			menuButton[i].setBackground(Color.WHITE);
			menuButton[i].setFont(new Font("나눔스퀘어",Font.BOLD,20));
		}
		for(int i=0;i<optionButton.length;i++) {
			rightBottom.add(optionButton[i]=new JButton(option[i]));
			optionButton[i].setBackground(Color.BLACK);
			optionButton[i].setForeground(Color.WHITE);
			optionButton[i].setFont(new Font("나눔스퀘어",Font.BOLD,20));
		}
		
		menuButton[0].setEnabled(false); // 고기버튼 비활성화
		menuButton[10].setEnabled(false);  // 식사버튼 비활성화
		menuButton[20].setEnabled(false);  // 음료버튼 비활성화
		menuButton[30].setEnabled(false);	//미정버튼 비활성화
		menuButton[31].setEnabled(false);	//미정버튼 비활성화
		menuButton[32].setEnabled(false);	//미정버튼 비활성화
		menuButton[33].setEnabled(false);	//미정버튼 비활성화
		menuButton[34].setEnabled(false);	//미정버튼 비활성화
		
		leftTopTop.add(tableScroll); 	//텍스트에리어 추가
		totalLbl.setFont(new Font("나눔스퀘어",Font.BOLD,30));
		totalFld.setFont(new Font("나눔스퀘어",Font.BOLD,30));
		leftTopBottom.add(totalLbl);	
		leftTopBottom.add(totalFld);		
		leftBottom.add(leftBottomLeft,BorderLayout.WEST); 
		leftBottom.add(leftBottomRight,BorderLayout.EAST);
		leftTop.add(leftTopTop,BorderLayout.CENTER);
		leftTop.add(leftTopBottom,BorderLayout.SOUTH);
		left.add(leftTop,BorderLayout.NORTH);  
		left.add(leftBottom,BorderLayout.SOUTH);
		right.add(rightTop,BorderLayout.CENTER);
		right.add(rightBottom,BorderLayout.SOUTH);
		for(int i=0;i<menuButton.length;i++) {
			int index = i;
			menuButton[i].addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					JButton menuButton = (JButton)e.getSource();
					DefaultTableModel m = (DefaultTableModel)jTable.getModel();
					m.addRow(new Object[]{menuName[index],price[index]});
					sum = sum+price[index];
					totalFld.setText(Integer.toString(sum));
					
				}
			});
		}
		add(left,BorderLayout.WEST);
		add(right,BorderLayout.CENTER);
		setSize(1255,800);
		
		optionButton[0].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				DefaultTableModel m = (DefaultTableModel)jTable.getModel();
				v.add(sum);
				sum=0;				
				for(int i = 0;i<jTable.getRowCount();i++) {
					s.add((m.getValueAt(i , 0).toString())+"\t"+(m.getValueAt(i , 1).toString())+"원"+"\n");
				}
				for(int i=0;i<s.size();i++) {
					n += s.get(i);
					System.out.println(n);
				}
				
				setVisible(false);
			}});
		optionButton[1].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel m = (DefaultTableModel)jTable.getModel();
				int row = jTable.getSelectedRow();
				int col = jTable.getSelectedColumn();
				minus = (int) m.getValueAt(row , col);
				System.out.println(minus);
				sum = sum - minus;
				totalFld.setText(Integer.toString(sum)+"원");
				m.removeRow(jTable.getSelectedRow());
			}
		});
		optionButton[2].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel m = (DefaultTableModel)jTable.getModel();				
				m.setRowCount(0);
				totalFld.setText(String.valueOf(""));
				sum=0;
			}
		});
		optionButton[3].addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub\
				DefaultTableModel m = (DefaultTableModel)jTable.getModel();				
				m.setRowCount(0);
				totalFld.setText(String.valueOf(""));
				sum=0;
				setVisible(false);
			}
		});
		optionButton[4].setEnabled(false);
	}
			public String getInputMenuTotal() {
					DefaultTableModel m = (DefaultTableModel)jTable.getModel();					
					m.setRowCount(0);	
					s.clear();
					
					return n;
					
				}
			public String getInputTotalFld() {
				if(totalFld.getText().length()==0)return null;
				else {
					String b = totalFld.getText();
//					hap += Integer.parseInt(b); 
//					b = Integer.toString(hap);
//					totalFld.setText("");
					
					return b;
				}
			}
}
