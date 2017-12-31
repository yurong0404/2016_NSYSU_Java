package myjava.homework;

import java.awt.BorderLayout;
import java.io.*;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import javax.swing.*;
import com.opencsv.*;
import java.util.*;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.util.Rotation;

public class Main {

	public static void main(String[] args)throws Exception {
		HashMap<String,String> product = new HashMap<String,String>();
		HashMap<String,String> gender = new HashMap<String,String>();
		String[] nextline;
		double productA=0,productB=0,productC=0,male=0,female=0;
		
		JFrame demo = new JFrame();		//�ŧi����
		demo.setTitle("�@�~�C_���~�M�ʧO����");
        demo.setSize(1400, 600);			//�]�w�������e
        demo.setLocationRelativeTo(null);	//�����_�l�e���b�ù�����
        demo.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	//�]�w��������� program�N����
        CSVReader reader = new CSVReader(new FileReader("bin\\myjava\\homework\\query_result.csv"));
        //FileReader�w�]���|���M�ת��ڸ��|   �`�N:���|�n������ϱ׽u
       
        
        while((nextline = reader.readNext())!=null)	//�@��Ū�ɮ�  �@��@��Ū  ����Ū��null
        {
	        product.put(nextline[0]+" "+nextline[1],nextline[2]);	//��id��product�M�b�@�_��key
	        gender.put(nextline[0],nextline[2]);
        }
        reader.close();
       
        
        //�p��product�ƶq
        for (String key : product.keySet())
        {
        	String s[];
        	s = key.split(" ");
        	if(s[1].equals("Product_A"))
        		productA++;
        	else if(s[1].equals("Product_B"))
        		productB++;
        	else if(s[1].equals("Product_C"))
        		productC++;
        }

        //��̫᪺HashMap���Y�]�@�� �p��gender����
        for (String key : gender.keySet())
        {
        	if(gender.get(key).equals("M"))
        		male++;
        	else if(gender.get(key).equals("F"))
        		female++;
        }

        
        
///////////////////////////�H�W�Ψ�Ū�ɩM�p��ƶq////////////////////////////////
///////////////////////////�H�U�Ψӵe����////////////////////////////////////
        
        
        //�]�w���~���ϸ��
        DefaultPieDataset dataset = new DefaultPieDataset();
        dataset.setValue("ProductA", productA);
        dataset.setValue("ProductB", productB);
        dataset.setValue("ProductC", productC);
        JFreeChart chart = ChartFactory.createPieChart3D("Product Percentage",dataset,true,true,false);
        
        //�]�w�ʧO���ϸ��
        DefaultPieDataset dataset2 = new DefaultPieDataset();
        dataset2.setValue("Male", male);
        dataset2.setValue("Female",female);
        JFreeChart chart2 = ChartFactory.createPieChart3D("Gender Percentage",dataset2,true,true,false);
        
        //�ŧi���Ϫ��˦�
        PiePlot pieplot = (PiePlot) chart.getPlot();
        PiePlot pieplot2 = (PiePlot) chart2.getPlot();
        //���ϼ�ñ
        StandardPieSectionLabelGenerator standarPieIG = new StandardPieSectionLabelGenerator("{0}\n {1} \n({2})", NumberFormat.getNumberInstance(),new DecimalFormat("0.0%"));  
        pieplot.setLabelGenerator(standarPieIG);
        pieplot2.setLabelGenerator(standarPieIG);
        
        //3D�˦�
        PiePlot3D pieplot3d = (PiePlot3D)chart.getPlot();
        PiePlot3D pieplot3d2 = (PiePlot3D)chart2.getPlot();
        pieplot3d.setStartAngle(90D);   
        pieplot3d.setDirection(Rotation.CLOCKWISE);  
        pieplot3d.setForegroundAlpha(0.5F);
        pieplot3d.setCircular(true);
        pieplot3d2.setStartAngle(90D);   
        pieplot3d2.setDirection(Rotation.CLOCKWISE);  
        pieplot3d2.setForegroundAlpha(0.5F);
        pieplot3d2.setCircular(true);
        
        //�ŧi�e��  ����ϩ�J�e��
        ChartPanel panel = new ChartPanel(chart);
        ChartPanel panel2 = new ChartPanel(chart2);
        
        //��panel�������谼   panel2�������F��
        demo.add(panel,BorderLayout.WEST);
        demo.add(panel2,BorderLayout.EAST);
        demo.setVisible(true);
        
	}
}
