package client;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

import myframe.MainFrame;

public class Client extends JFrame implements ActionListener, FocusListener{
	private Socket s = null;
	private JButton btConnect = new JButton("����");
	private JTextField tfMsg  = new JTextField(10);
	public Client() {

		tfMsg.setText("����");
		tfMsg.setForeground(Color.GRAY);	
		
		this.setLayout(new FlowLayout());
		this.setTitle("�ͻ���");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.add(tfMsg);
		this.add(btConnect,BorderLayout.CENTER);
		this.setVisible(true);
		this.setSize(300, 100);
		this.setLocationRelativeTo(null);
		
		tfMsg.addFocusListener(this);
		btConnect.addActionListener(this);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {

		try {
//			s = new Socket("192.168.43.52", 9999);
			s = new Socket("192.168.1.136", 9999);
			this.setTitle("���ӳɹ�");	
			new MainFrame(s);
			this.dispose();
		} catch (Exception e1) {
			System.out.println(e1.getMessage());
		}
		
	}
	
	@Override
	public void focusGained(FocusEvent e) {
		//����ȡ����ʱ
		String temp = tfMsg.getText();
		if(temp.equals("����")) {
			tfMsg.setText("");
			tfMsg.setForeground(Color.black);
		}
	}

	@Override
	public void focusLost(FocusEvent e) {
		//��ʧȥ����ʱ
		String temp = tfMsg.getText();
		if(temp.equals("")) {
			tfMsg.setForeground(Color.GRAY);
			tfMsg.setText("����");
		}
	}
	
	public static void main(String[] args) {
		new Client();
	}

}
