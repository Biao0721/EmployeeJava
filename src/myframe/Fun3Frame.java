package myframe;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import name.Name.MainName;

public class Fun3Frame extends JFrame implements ActionListener, FocusListener{	
	private Socket s; 
	private Map<Integer, MainName> map;
	
	private JButton fun3Button = new JButton("�����ܶ�ͼ");	
	private JButton mainButton = new JButton("����");
	private JPanel  fun3Panel  = new JPanel();
	private JTextField tfMsg   = new JTextField();
	private JTextArea  taMsg   = new JTextArea("������ʮ��������Ӧ�����\n");
	
	public Fun3Frame(Socket s, Map<Integer, MainName> map) {
		this.s = s;
		this.map = map;
		
		Font font = new Font("����", 0, 20);
		taMsg.setFont(font);
		
		this.setLayout(new FlowLayout());
		
		tfMsg.setText("�����������ѯ�˵����");
		tfMsg.setForeground(Color.GRAY);
		
		//��
		fun3Button.addActionListener(this);
		mainButton.addActionListener(this);
		tfMsg.addFocusListener(this);
		fun3Panel.add(fun3Button);
		fun3Panel.add(mainButton);
		
		this.setTitle("������");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.add(fun3Panel,BorderLayout.NORTH);
		this.add(tfMsg);
		this.add(taMsg);
		this.setSize(800,325);
		this.setVisible(true);
		this.setLocationRelativeTo(null);
		
		for (int i = 0; i < 10; i++) {
			taMsg.append((i + 1) + ":" + map.get(i).getName() + "\n");
		}
		
	}

	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == fun3Button) {
			try {
				OutputStream out = s.getOutputStream();                               //��ȡ����˵��������Ϊ���������������
				PrintWriter bufw = new PrintWriter(out,true);
				if(tfMsg.getText() == null) {
					taMsg.append("�����������ѯ�˵����");
				}else if (tfMsg.getText().length() > 1) {
					bufw.println(40);
					System.out.println();
				} else {
					bufw.println(3 + tfMsg.getText());                                 //�������ݸ������
				}
			} catch (Exception e2) {
				System.out.println(e2.getMessage());
			}
		}else {
			//������ҳ��
			try {
				OutputStream out = s.getOutputStream();                                //��ȡ����˵��������Ϊ���������������
				PrintWriter bufw = new PrintWriter(out,true);
				bufw.println(0);                                                       //�������ݸ������
				new MainFrame(s);
				this.dispose();
			} catch (Exception e2) {
				System.out.println(e2.getMessage());
			}
		}
	}
	
	public void focusGained(FocusEvent e) {
		//����ý���ʱ
		String temp = tfMsg.getText();
		if(temp.equals("�����������ѯ�˵����")) {
			tfMsg.setText("");
			tfMsg.setForeground(Color.black);
		}
	}

	public void focusLost(FocusEvent e) {
		//��ʧȥ����ʱ
		String temp = tfMsg.getText();
		if(temp.equals("")) {
			tfMsg.setForeground(Color.GRAY);
			tfMsg.setText("�����������ѯ�˵����");
		}
	}
}