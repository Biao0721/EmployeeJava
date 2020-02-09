package myframe;

import java.awt.BorderLayout;
import java.awt.event.*;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Fun4Frame extends JFrame implements ActionListener{
	private Socket s = null;
	
	private JButton fun1Button = new JButton("�����������");
	private JButton mainButton = new JButton("����");
	private JPanel  fun4Panel  = new JPanel();
	
	public Fun4Frame(Socket s) {
		this.s = s;
		
		//��
		fun1Button.addActionListener(this); 
		mainButton.addActionListener(this);
		fun4Panel.add(fun1Button);
		fun4Panel.add(mainButton);
		
		this.setTitle("������");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.add(fun4Panel,BorderLayout.NORTH);
		this.setSize(800,500);
		this.setVisible(true);
		this.setLocationRelativeTo(null);

	}
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == fun1Button) {
			try {
				OutputStream out = s.getOutputStream();           //��ȡ����˵��������Ϊ���������������
				PrintWriter bufw = new PrintWriter(out,true);
				bufw.println(44);
			} catch (Exception e2) {
				System.out.println(e2.getMessage());
			}
		}else {
			//������ҳ��
			try {
				OutputStream out = s.getOutputStream();          //��ȡ����˵��������Ϊ���������������
				PrintWriter bufw = new PrintWriter(out,true);
				bufw.println(0);                                 //�������ݸ������
				this.dispose();
				new MainFrame(s);
			} catch (Exception e2) {
				System.out.println(e2.getMessage());
			}
		}
	}
}
