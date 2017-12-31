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
		
		JFrame demo = new JFrame();		//宣告視窗
		demo.setTitle("作業七_產品和性別圓餅圖");
        demo.setSize(1400, 600);			//設定視窗長寬
        demo.setLocationRelativeTo(null);	//視窗起始畫面在螢幕中央
        demo.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	//設定把視窗關掉 program就結束
        CSVReader reader = new CSVReader(new FileReader("bin\\myjava\\homework\\query_result.csv"));
        //FileReader預設路徑為專案的根路徑   注意:路徑要打兩條反斜線
       
        
        while((nextline = reader.readNext())!=null)	//一直讀檔案  一行一行讀  直到讀到null
        {
	        product.put(nextline[0]+" "+nextline[1],nextline[2]);	//把id跟product和在一起當key
	        gender.put(nextline[0],nextline[2]);
        }
        reader.close();
       
        
        //計算product數量
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

        //把最後的HashMap重頭跑一次 計算gender次數
        for (String key : gender.keySet())
        {
        	if(gender.get(key).equals("M"))
        		male++;
        	else if(gender.get(key).equals("F"))
        		female++;
        }

        
        
///////////////////////////以上用來讀檔和計算數量////////////////////////////////
///////////////////////////以下用來畫圓餅圖////////////////////////////////////
        
        
        //設定產品圓餅圖資料
        DefaultPieDataset dataset = new DefaultPieDataset();
        dataset.setValue("ProductA", productA);
        dataset.setValue("ProductB", productB);
        dataset.setValue("ProductC", productC);
        JFreeChart chart = ChartFactory.createPieChart3D("Product Percentage",dataset,true,true,false);
        
        //設定性別圓餅圖資料
        DefaultPieDataset dataset2 = new DefaultPieDataset();
        dataset2.setValue("Male", male);
        dataset2.setValue("Female",female);
        JFreeChart chart2 = ChartFactory.createPieChart3D("Gender Percentage",dataset2,true,true,false);
        
        //宣告圓餅圖的樣式
        PiePlot pieplot = (PiePlot) chart.getPlot();
        PiePlot pieplot2 = (PiePlot) chart2.getPlot();
        //圓餅圖標簽
        StandardPieSectionLabelGenerator standarPieIG = new StandardPieSectionLabelGenerator("{0}\n {1} \n({2})", NumberFormat.getNumberInstance(),new DecimalFormat("0.0%"));  
        pieplot.setLabelGenerator(standarPieIG);
        pieplot2.setLabelGenerator(standarPieIG);
        
        //3D樣式
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
        
        //宣告畫布  把圓餅圖放入畫布
        ChartPanel panel = new ChartPanel(chart);
        ChartPanel panel2 = new ChartPanel(chart2);
        
        //把panel放到視窗西側   panel2放到視窗東側
        demo.add(panel,BorderLayout.WEST);
        demo.add(panel2,BorderLayout.EAST);
        demo.setVisible(true);
        
	}
}
