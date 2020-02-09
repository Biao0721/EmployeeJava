package name;

import java.awt.Color;
import java.awt.Font;
import java.io.FileOutputStream;
import java.util.List;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.title.LegendTitle;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;

import name.Name.MainName;

public class PieChart {
	ChartPanel jframe;
	
	public PieChart(MainName mn) {
		DefaultPieDataset data = (DefaultPieDataset) getPieDataset(mn);
		JFreeChart chart = ChartFactory.createPieChart3D(
				mn.getName() + "���½ڳ����ܶ�", data,
				true, false, false);
		chart.setAntiAlias(false);   //�رվ����
			
		Font font = new Font("����", Font.PLAIN, 14);
		
		PiePlot plot = (PiePlot)chart.getPlot();
		//���ͼ������ʾ����
		plot.setLabelFont(font);
		LegendTitle lt = chart.getLegend();
		lt.setItemFont(font);
		
		//���ճ���Ƶ�ʴ�С��������ɫ��ǳ
		setColor(plot, data, mn);
		
		//��������
		TextTitle chartTitle = chart.getTitle();
		chartTitle.setFont(new Font(
				"����", Font.BOLD, 20));
		
		//���ðٷֱ�
		plot.setLabelGenerator(new StandardPieSectionLabelGenerator("{1}��"));
		
		jframe = new ChartPanel(chart);
		
		//���ر�״ͼ
		try {
			FileOutputStream fos = new FileOutputStream(
					"C:\\Users\\87776\\Desktop\\HarryPotter\\PieChart.jpg");
	        ChartUtilities.writeChartAsJPEG(fos, chart, 1000, 700);
	        fos.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public ChartPanel getPanel() {
		return jframe;
	}
	
	private void setColor(PiePlot plot, PieDataset data, MainName mn) {
		List keys = data.getKeys();
		for (int i = 0; i < keys.size(); i++) {
			plot.setSectionPaint(keys.get(i).toString(),new Color(
					0,  mn.getNum()[i] * 1000 / mn.getSumNum() / 4, mn.getNum()[i] * 1000 / mn.getSumNum() / 4));
		}
	}

	private PieDataset getPieDataset(MainName mn){
        DefaultPieDataset dataset = new DefaultPieDataset();
        int[] num = mn.getNum();
        
        for (int i = 0; i < num.length; i++) {
			dataset.setValue("��" + (i + 1) + "��", num[i]);
		}
        return dataset;     
    }
}
	