package Pos;
import java.awt.*;
import java.awt.event.*;
import java.util.Calendar;
import javax.swing.*;
import javax.swing.event.*;

class TimerThread extends Thread {//시간 클래스
	JLabel timerLabel;
	int year,month,day,hour,minute,second;
	String mon;
	public TimerThread(JLabel timerLabel) {
		this.timerLabel = timerLabel;
	}

	public TimerThread() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void run() {
		
		while(true) {
			
			Calendar c = Calendar.getInstance();
			
			year = c.get(Calendar.YEAR);
			month = c.get(Calendar.MONTH)+1;
			day = c.get(Calendar.DATE);
			hour = c.get(Calendar.HOUR);
			minute = c.get(Calendar.MINUTE);
			second = c.get(Calendar.SECOND);
			String clockText = 
			Integer.toString(year)+"-"+Integer.toString(month)+"-"+Integer.toString(day)
			+"["+Integer.toString(hour)+":"+Integer.toString(minute)+":"+Integer.toString(second)+"]";
			timerLabel.setText(clockText);
			mon = Integer.toString(month);
			
			try {
				Thread.sleep(1000);
			}
			catch (InterruptedException e) {
				return;
			}
		}
	}
	public String mo() {
		return mon;
	}
}
//시간 클래스 종료
