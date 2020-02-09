package myframe;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import name.Name;

public class MainFrame extends JFrame implements ActionListener{
	private Socket s;
	
	private JButton fun1Button = new JButton("����1");
	private JButton fun2Button = new JButton("����2");
	private JButton fun3Button = new JButton("����3");
	private JButton fun4Button = new JButton("����4");
	
	public MainFrame(Socket s) {
		this.s = s;
		
		//�ĸ�button���а�
		fun1Button.addActionListener(this);
		fun2Button.addActionListener(this);
		fun3Button.addActionListener(this);
		fun4Button.addActionListener(this);
		
		this.setLayout(new FlowLayout());
		
		this.add(fun1Button);
		this.add(fun2Button);
		this.add(fun3Button);
		this.add(fun4Button);
		this.add(new JLabel(new ImageIcon("C:\\Users\\87776\\Desktop\\images (1).jpg")));
		
		this.setTitle("����ѡ�����");
		this.setSize(450,250);
		this.setVisible(true);
		this.setLocationRelativeTo(null);
	}
	
	public void actionPerformed(ActionEvent e) {
		OutputStream out = null;
		PrintWriter bufw = null;
		try {
			out = s.getOutputStream();          //��ȡ����˵��������Ϊ���������������
			bufw = new PrintWriter(out,true);
		} catch (Exception e1) {
			 System.out.println(e1.getMessage());
		}
		
		if(e.getSource() == fun1Button) {
			//��ʾ����һ
			bufw.println(1);                                 //�������ݸ������
			this.dispose();  
			new Fun1Frame(s);
		}
		else if (e.getSource() == fun2Button) {
			//��ʾ���ܶ�
			bufw.println(2);                                 //�������ݸ������
			this.dispose();  
			new Fun2Frame(s);
		}
		else if (e.getSource() == fun3Button) {
			//��ʾ������
			bufw.println(3);                                 //�������ݸ������
			this.dispose();
			try {
				new Fun3Frame(s, new Name().getMap());
			} catch (IOException e1) {
				System.out.println(e1.getMessage());
			}
		}
		else {
			//��ʾ������
			bufw.println(4);                                 //�������ݸ������
			this.dispose();  
			new Fun4Frame(s);
		}
	}
}
