package name;

import java.awt.Font;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.swing.JTextArea;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.tabbedui.VerticalLayout;

import name.Name.MainName;

public class BarChart {
	ChartPanel jframe;
	
	private List<MainName> list1 = new ArrayList<MainName>();    //��һ��
	private List<MainName> list2 = new ArrayList<MainName>();    //�ڶ���
	private List<MainName> list3 = new ArrayList<MainName>();    //������
	
	public BarChart(Map<Integer, MainName> map, int x) {
		// TODO �Զ����ɵĹ��캯�����
		JTextArea taMsg = new JTextArea("������:\n");
		setList(map);
		
		DefaultCategoryDataset data = (DefaultCategoryDataset) getDataSet2(map);
		JFreeChart chart = ChartFactory.createBarChart3D(
				"���ִ���", 
				"�½�", 
				"����", 
				data,
				PlotOrientation.VERTICAL,
				true, false, false);
		
        CategoryPlot plot = chart.getCategoryPlot();                    		//���ͼ���������
        CategoryAxis domain = plot.getDomainAxis();                             //ˮƽ�ײ��б� 
        domain.setTickLabelFont(new Font("����", Font.BOLD, 10));               //��ֱ������������
        domain.setLabelFont(new Font("����", Font.BOLD, 10));                   //ˮƽ�ײ���������
        ValueAxis rangeAxis = plot.getRangeAxis();                             //��ȡ��״��
        rangeAxis.setLabelFont(new Font("����", Font.BOLD, 10));
        chart.getLegend().setItemFont(new Font("����", Font.BOLD, 16));         //����legend����
        chart.getTitle().setFont(new Font("����", Font.BOLD, 16));
        
        jframe = new ChartPanel(chart);
        jframe.add(taMsg);
        jframe.setLayout(new VerticalLayout());
		Font font = new Font("����", 0, 20);     //���������ʽ
        taMsg.setFont(font);
        
        taMsg.append("��һ�ࣺ");
        for (int i = 0; i < list1.size(); i++) {
			taMsg.append(list1.get(i).getName() + " ");
		}
        taMsg.append("\n�ڶ��ࣺ");
        for (int i = 0; i < list2.size(); i++) {
			taMsg.append(list2.get(i).getName() + " ");
		}
        taMsg.append("\n�����ࣺ");
        for (int i = 0; i < list3.size(); i++) {
			taMsg.append(list3.get(i).getName() + " ");
		}
        
        try {
			FileOutputStream fos = new FileOutputStream(
					"C:\\Users\\87776\\Desktop\\HarryPotter\\Fun4BarChart.jpg");
	        ChartUtilities.writeChartAsJPEG(fos, chart, 1000, 700);
	        fos.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public BarChart(Map<Integer, MainName> map) {
		JTextArea taMsg = new JTextArea("���ڸ�����Ϊ��\n");
		
		DefaultCategoryDataset data = (DefaultCategoryDataset) getDataSet1(map);
		JFreeChart chart = ChartFactory.createBarChart3D(
				"�������ִ���", 
				"����", 
				"����", 
				data,
				PlotOrientation.VERTICAL,
				true, false, false);
		
        CategoryPlot plot = chart.getCategoryPlot();                    		//���ͼ���������
        CategoryAxis domain = plot.getDomainAxis();                             //ˮƽ�ײ��б� 
        domain.setTickLabelFont(new Font("����", Font.BOLD, 10));               //��ֱ������������
        domain.setLabelFont(new Font("����", Font.BOLD, 10));                   //ˮƽ�ײ���������
        ValueAxis rangeAxis = plot.getRangeAxis();                             //��ȡ��״��
        rangeAxis.setLabelFont(new Font("����", Font.BOLD, 10));
        chart.getLegend().setItemFont(new Font("����", Font.BOLD, 16));         //����legend����
        chart.getTitle().setFont(new Font("����", Font.BOLD, 16));
        
        jframe = new ChartPanel(chart);
        jframe.add(taMsg);
        jframe.setLayout(new VerticalLayout());
		Font font = new Font("����", 0, 20);     //���������ʽ
		taMsg.setFont(font);                     //����taMsg�����ʽ
        
        for (int i = 0; i < map.size(); i++) {
			taMsg.append(map.get(i).getName() + " ");
			
		}
        
        try {
			FileOutputStream fos = new FileOutputStream(
					"C:\\Users\\87776\\Desktop\\HarryPotter\\BarChart.jpg");
	        ChartUtilities.writeChartAsJPEG(fos, chart, 1000, 700);
	        fos.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public ChartPanel getPanel() {
		return jframe;
	}
	
	private void setList(Map<Integer, MainName> map) {
		for (int i = 0; i < map.size(); i++) {
			double avgNum = map.get(i).getAvgNum();
			if(avgNum <= 3) {
				list1.add(map.get(i));
			}else if (avgNum >= 7.5) {
				list3.add(map.get(i));
			}else {
				list2.add(map.get(i));
			}
		}
	}
	
	private CategoryDataset getDataSet1(Map<Integer, MainName> map) {
		 DefaultCategoryDataset data=new DefaultCategoryDataset();
		 //��������
		 for (int i = 0; i < 10; i++) {
			data.setValue(map.get(i).getSumNum(), "", map.get(i).getName());
		}
		 return data;
	}
	
	private CategoryDataset getDataSet2(Map<Integer, MainName> map) {
		 DefaultCategoryDataset data=new DefaultCategoryDataset();
		 //��������
		 for (int i = 0; i < 17; i++) {
			for (int j = 0; j < map.size(); j++) {
//				data.setValue((map.get(j).getNum()[i] * 100 / map.get(j).getSumNum()), "��" + (i + 1) + "��", map.get(j).getName());
			    data.setValue(map.get(j).getAvgNum(), "", map.get(j).getName());
			}
		}
		 return data;
	}
}