package name;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JDialog;

public class Name {

	public class MainName {
		
		private String name;
		private int[] num = new int[17];    //ÿ�³��ֵĴ���
		private double avgNum;              //����ƽ��������
		private int sumNum;                 //�ܹ����ִ���  
		
		public MainName(String name) {
			this.name = name;
		}
		
		public void setNum(int num, int flag) {
			this.num[flag] = num;
		}
		
		public int[] getNum() {
			return num;
		}
		
		public String getName() {
			return name;
		}
		
		public void setSumNum() {
			for(int flag : num) {
				sumNum += flag;
			}
			
		}
		
		public int getSumNum() {
			return sumNum;
		}
	
		public void setAvgNum() {	
			for (int i = 0; i < num.length; i++) {
				avgNum += (((double)num[i] / (double)sumNum) * (double)(i + 1));
			}
			avgNum /= 17;
			avgNum *= 10;
		}
		
		public double getAvgNum() {
			return this.avgNum;
		}
	}

	private String[] name = {
			"����",
			"�޶�",
			"����",
			"�˲�����",
			"����",
			"����",
			"�����",
			"˹����",
			"��˼��",
			"����"
	};
	
	private void updateName(Map<Integer, MainName> map) {
		for (int i = 0; i < map.size(); i++) {
			for (int j = 0; j < map.size() - 1 - i; j++) {
				if(map.get(j).getSumNum() > map.get(j + 1).getSumNum()) {
					MainName temp = map.get(j);
					map.put(j, map.get(j + 1));
					map.put((j + 1), temp);
				}
			}
		}
	}
	
	private Map<Integer, MainName> map = new HashMap<Integer,MainName>();
	
	private void setMap(String[] name) {
		
		int flag = 0;
		
		for(String mainName : name) {
			map.put(flag, (new MainName(mainName)));
			flag++;
		}
		
	}
	
	public void print(int flag) {
		System.out.println("���֣�" + map.get(flag).getName() + "\n");
		for (int j = 0; j < 17; j++) {
			System.out.println("��" + (j + 1) + "�����������" + map.get(flag).getNum()[j] + "��\n");
		}
		
		getPieChart(flag);
	}
	
	public void printAvgNum() {
		for (int i = 0; i < map.size(); i++) {
			map.get(i).setAvgNum();
			System.out.println(
					map.get(i).getName() + "���ֵ����λ����:" + (map.get(i).getAvgNum()));			
		}
	}
	
	public void printSum() {
		int sum =0;
		for (int i = 0; i < 10; i++) {
			sum = map.get(i).getSumNum();
			System.out.println(map.get(i).getName() + "һ��������" + sum + "��\n");
		}
	}
	
	public void getBarChart() {
		BarChart bc = new BarChart(map);
		
		JDialog jd=new JDialog();
		jd.setBounds(50, 50, 1400, 1200);
		jd.add(bc.getPanel());
		jd.setVisible(true);
	}
	
	public void getBarChart(int i) {
		BarChart bc = new BarChart(map, 0);
		
		JDialog jd=new JDialog();
		jd.setBounds(50, 50, 1400, 1200);
		jd.add(bc.getPanel());
		jd.setVisible(true);
	}
	
	public void getPieChart(int i) {
		PieChart pc = new PieChart(map.get(i));
		
		JDialog jd=new JDialog();
		jd.setBounds(50, 50, 1400, 1000);
		jd.add(pc.getPanel());
		jd.setVisible(true);
	}
	
	public Map<Integer, MainName> getMap() {
		return map;
	}
	
	public String[] getName() {
		return name;
	}
	
	public Name() throws IOException {		
		setMap(name);
		
		File file = new File("C:\\Users\\87776\\Desktop\\HarryPotter\\test");
		File[] fl = file.listFiles();
		
		for(int flag = 0; flag < 10; flag ++) {      //flagΪ�ڼ�����
			int flagNum = 0;        //flagNumΪ�ڼ��µ�һ����־
			for (File ft : fl) {					
				FileInputStream fis = new FileInputStream(ft);
				InputStreamReader isr = new InputStreamReader(fis, "utf-8");
				BufferedReader br = new BufferedReader(isr);
				StringBuffer c = new StringBuffer();
				String str = null;
				int i = 0;
				while ((str = br.readLine()) != null) {
					c.append(str);
				}
				Pattern pattern = Pattern.compile(map.get(flag).getName());
				Matcher mather = pattern.matcher(c);
				while(mather.find()) {
					i++;
				}
				map.get(flag).setNum(i, flagNum);
				fis.close();
				flagNum++;
			}
		}
		
		for (int i = 0; i < 10; i++) {
			map.get(i).setSumNum();
		}
		
		updateName(map);
	}

}