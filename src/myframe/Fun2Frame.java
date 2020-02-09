package myframe;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Fun2Frame extends JFrame implements ActionListener{
	private Socket s;
	
	private JButton fun2Button = new JButton("������״ͼ");
	private JButton mainButton = new JButton("����");
	private JPanel  fun2Panel  = new JPanel();
	
	public Fun2Frame(Socket s) {
		this.s = s;
		
		//��
		fun2Button.addActionListener(this);  
		mainButton.addActionListener(this);
		fun2Panel.add(fun2Button);
		fun2Panel.add(mainButton);
		
		this.setTitle("���ܶ�");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.add(fun2Panel,BorderLayout.NORTH);
		this.add(new JLabel(new ImageIcon("C:\\Users\\87776\\Desktop\\images.jpg")));
		this.setSize(450,300);
		this.setVisible(true);
		this.setLocationRelativeTo(null);
	}

	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == fun2Button) { 
			try {
				OutputStream out = s.getOutputStream();           //��ȡ����˵��������Ϊ���������������
				PrintWriter bufw = new PrintWriter(out,true);
				
				bufw.println(22);                                 //�������ݸ������
				this.setTitle("����������״ͼ...");
				this.setTitle("��״ͼ�������");
				bufw.println(0);
			} catch (Exception e2) {
				System.out.println(e2.getMessage());
			}
		}else if (e.getSource() == mainButton) {
			//������ҳ��
			try {
				OutputStream out = s.getOutputStream();          //��ȡ����˵��������Ϊ���������������
				PrintWriter bufw = new PrintWriter(out,true);
				bufw.println(0);                                 //�������ݸ������
				System.out.println("���ܶ��ѷ���\n");
				new MainFrame(s);
				this.dispose();
			} catch (Exception e2) {
				System.out.println(e2.getMessage());
			}
		}
	}
}