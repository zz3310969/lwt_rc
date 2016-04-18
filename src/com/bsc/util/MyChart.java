package com.bsc.util;

import java.awt.Color;
import java.awt.Font;
import java.awt.RenderingHints;
import java.io.File;
import java.io.FileOutputStream;
import java.text.DecimalFormat;
import java.text.NumberFormat;

import org.apache.log4j.Logger;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.NumberTickUnit;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.ValueMarker;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.data.xy.XYDataset;
import org.jfree.ui.RectangleAnchor;
import org.jfree.ui.TextAnchor;


/**
 * @version 1.0
 */
public class MyChart {
	private String CHART_PATH =File.separator+"weightimages"+File.separator;
	private static Logger log = Logger.getLogger(MyChart.class);
	/**
	 * 
	 */
	public MyChart(String basePath) {
		if(basePath == null){
			return;
		}
		File file = new File(basePath);
		CHART_PATH = file.getParent() + CHART_PATH;
		file = new File(CHART_PATH);
		if(!file.exists()){
			new File(file.getParent()).mkdirs();
		}
	}
	
	/**
	 * 分布点状图
	 * 
	 * @param title
	 * @param xAxisLabel
	 * @param yAxisLabel
	 * @param dataset
	 * @param charName
	 * @return
	 */
	public String createPointXYChart(String title, String xAxisLabel, String yAxisLabel, 
			XYDataset dataset, String charName, int upperBound, int lowerBound, double[] lineMark){
		
//		分布点状图
		JFreeChart chart = ChartFactory.createScatterPlot(title, xAxisLabel, yAxisLabel,
				dataset, PlotOrientation.VERTICAL, true, false, false);
//		设置参数
		XYPlot plot = (XYPlot) chart.getPlot();
		// 设置无数据时的信息 
		plot.setNoDataMessage("数据不完整，无法生成正确图片！请检查"); 		
		// 设置无数据时的信息显示颜色 
		plot.setNoDataMessagePaint(Color.red);
		
		TextTitle t = chart.getTitle();
		t.setFont(new Font("宋体",Font.BOLD,14));//标题文字

		
		plot.setRangeGridlinesVisible(true); 
//		plot.setDomainGridlinesVisible(true);
		plot.setRangeGridlinePaint(Color.WHITE);// 虚线色彩 		
//		plot.setDomainGridlinePaint(Color.WHITE);
		plot.setBackgroundPaint(Color.lightGray); 

		chart.getLegend().setItemFont(new Font("黑体",Font.BOLD,12));//图例文字

//		ValueMarker//设置分割线
		double d;
		for(int i = 0; i < lineMark.length; i++){
			d = lineMark[i];
			ValueMarker vmarker = new ValueMarker(d);//设置分割线
//			vmarker.setLabel(String.valueOf(Math.round(d)));
	        vmarker.setLabel(String.valueOf(d));
	        vmarker.setLabelPaint(Color.black);
	        vmarker.setPaint(Color.yellow);
	        vmarker.setLabelFont(new Font("宋体",Font.BOLD,10));
	        vmarker.setLabelAnchor(RectangleAnchor.BOTTOM_RIGHT);
	        vmarker.setLabelTextAnchor(TextAnchor.TOP_RIGHT);
	        plot.addRangeMarker(vmarker);
		}
		

//		Y
		ValueAxis yAxis = plot.getRangeAxis();
//		yAxis.setAutoRange(true);//default
		NumberAxis yNumber = (NumberAxis) plot.getRangeAxis();
		yNumber.setTickUnit(new NumberTickUnit(10));		
		yAxis.setUpperBound(upperBound);
		yAxis.setLowerBound(lowerBound);
		plot.setRangeAxis(yAxis);

//		X
//		ValueAxis xAxis = plot.getDomainAxis();
//		xAxis.setAutoRange(false);
		NumberAxis xNumber = (NumberAxis) plot.getDomainAxis();
//		xNumber.setVisible(false);
		xNumber.setTickUnit(new NumberTickUnit(1));// 设置X轴跨度每次增加1		
//		xAxis.setUpperBound(2);
//		xAxis.setLowerBound(0);
				
//		输出
		FileOutputStream fos_jpg = null; 
		try { 
			// 文件夹不存在则创建 
			isChartPathExist(CHART_PATH); 
			String chartName = CHART_PATH + charName; 
			fos_jpg = new FileOutputStream(chartName); 
			// 高宽的设置 
			ChartUtilities.writeChartAsPNG(fos_jpg, chart, 560, 240); 
		
//			System.ee.println("chartPathName: " + chartName);
			return chartName; 
		}catch (Exception e){ 
			log.error(e.getMessage());
			return null; 
		}finally{ 
			try { 
				fos_jpg.close(); 
//				System.out.println("create error-chart. Name: " + charName); 
			}catch (Exception e){ 
				log.error(e.getMessage());
			} 
		}
	}
	
	/** 
	* 折线图 
	* 
	* @param chartTitle 
	* @param x 
	* @param y 
	* @param xyDataset 
	* @param charName 
	* @param yLowerBound 
	* @return 
	*/ 
	public String createXYChart (String chartTitle, String x, String y, 
						CategoryDataset xyDataset, String charName, double yUpperBound, double yLowerBound, double[] lineMark){
		
		JFreeChart chart = ChartFactory.createLineChart(chartTitle, x, y, 
				xyDataset, PlotOrientation.VERTICAL, true, true, false); 
		
		chart.setTextAntiAlias(false); 
		chart.setBackgroundPaint(Color.WHITE); 
		// 设置图标题的字体重新设置title 
		Font font = new Font("宋体", Font.BOLD, 15); 
		TextTitle title = new TextTitle(chartTitle); 
		title.setFont(font); 
		chart.setTitle(title); 
		// 设置面板字体 
		Font labelFont = new Font("宋体", Font.BOLD, 12); 
		
		chart.setBackgroundPaint(Color.WHITE); 
		
		chart.getLegend().setItemFont(new Font("黑体",Font.BOLD,12));//图例文字
		
		CategoryPlot categoryplot = (CategoryPlot) chart.getPlot(); 
		
		// 设置无数据时的信息 
		categoryplot.setNoDataMessage("数据不完整，无法生成正确图片！请检查"); 
		
		// 设置无数据时的信息显示颜色 
		categoryplot.setNoDataMessagePaint(Color.red);
		
		// x轴 // 分类轴网格是否可见 
		categoryplot.setDomainGridlinesVisible(true); 
		// y轴 //数据轴网格是否可见 
		categoryplot.setRangeGridlinesVisible(true); 
		
		categoryplot.setRangeGridlinePaint(Color.WHITE);// 虚线色彩 
		
		categoryplot.setDomainGridlinePaint(Color.WHITE);// 虚线色彩 
		
		categoryplot.setBackgroundPaint(Color.lightGray); 
		
		// 设置轴和面板之间的距离 
		// categoryplot.setAxisOffset(new RectangleInsets(5D, 5D, 5D, 5D)); 

//		ValueMarker//设置分割线
		double d;
		for(int i = 0; i < lineMark.length; i++){
			d = lineMark[i];
			ValueMarker vmarker = new ValueMarker(d);//设置分割线
//			vmarker.setLabel(String.valueOf(Math.round(d)));
	        vmarker.setLabel(String.valueOf(d));
	        vmarker.setLabelPaint(Color.black);
	        vmarker.setPaint(Color.yellow);
	        vmarker.setLabelFont(new Font("宋体",Font.PLAIN,10));
	        vmarker.setLabelAnchor(RectangleAnchor.BOTTOM_RIGHT);
	        vmarker.setLabelTextAnchor(TextAnchor.TOP_RIGHT);
	        categoryplot.addRangeMarker(vmarker);
		}
		
		ValueAxis rangeAxis = categoryplot.getRangeAxis();	

//		rangeAxis.setFixedAutoRange(80);
//		rangeAxis.setLowerBound(60);
		rangeAxis.setUpperBound(yUpperBound);
		rangeAxis.setLowerBound(yLowerBound);
		
		CategoryAxis domainAxis = categoryplot.getDomainAxis(); 
		
		domainAxis.setLabelFont(labelFont);// 轴标题 
		domainAxis.setTickLabelFont(labelFont);// 轴数值 	
		
//		domainAxis.setCategoryLabelPositions(CategoryLabelPositions.UP_45); // 横轴上的
		domainAxis.setCategoryLabelPositions(CategoryLabelPositions.STANDARD); // 横轴上的 
		// Lable 
		// 45度倾斜 
		// 设置距离图片左端距离 
		domainAxis.setLowerMargin(0.0); 
		// 设置距离图片右端距离 
		domainAxis.setUpperMargin(0.0); 
		
		NumberAxis numberaxis = (NumberAxis) categoryplot.getRangeAxis(); 
		numberaxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits()); 
		numberaxis.setAutoRangeIncludesZero(true); 
		
		// 获得renderer 注意这里是下嗍造型到lineandshaperenderer！！ 
		LineAndShapeRenderer lineandshaperenderer = (LineAndShapeRenderer) categoryplot 
		.getRenderer(); 
		
		lineandshaperenderer.setBaseShapesVisible(true); // series 点（即数据点）可见 
		lineandshaperenderer.setBaseLinesVisible(true); // series 点（即数据点）间有连线可见 
		
		// 显示折点数据 
		// lineandshaperenderer.setBaseItemLabelGenerator(new 
		// StandardCategoryItemLabelGenerator()); 
		// lineandshaperenderer.setBaseItemLabelsVisible(true); 
		
		FileOutputStream fos_jpg = null; 
		
		try {
			isChartPathExist(CHART_PATH); 
			String chartName = CHART_PATH + charName; 
			fos_jpg = new FileOutputStream(chartName); 
			
			// 将报表保存为png文件 
			ChartUtilities.writeChartAsPNG(fos_jpg, chart, 560, 200); 
			
//			System.ee.println("chartPathName: " + chartName);
			return chartName;			 
		}catch (Exception e){ 
			log.error(e.getMessage());
			return null; 
		}finally{ 
			try{ 
				fos_jpg.close();
//				System.out.println("Create XYChar Finished. Name: " + charName); 
			}catch (Exception e){ 
				log.error(e.getMessage());
			}
		} 		
	}
	
	/** 
	* 饼状图 
	* @param dataset 数据集 
	* @param chartTitle 图标题 
	* @param charName 生成图的名字 
	* @param pieKeys 分饼的名字集 
	* @return 
	*/ 
	public String createPieChart(PieDataset dataset, String chartTitle, String charName, String[] pieKeys){ 
		JFreeChart chart = ChartFactory.createPieChart3D(chartTitle, // chart 
		// title 
		dataset,// data 
		true,// include legend 
		true, false); 
		
		// 使下说明标签字体清晰,去锯齿类似于 
		chart.getRenderingHints().put(RenderingHints.KEY_TEXT_ANTIALIASING,RenderingHints.VALUE_TEXT_ANTIALIAS_OFF); // 的效果 
		chart.setTextAntiAlias(false); 
		// 图片背景色 
		chart.setBackgroundPaint(Color.white); 
		// 设置图标题的字体重新设置title 
		Font font = new Font("宋体", Font.BOLD, 15); 
		TextTitle title = new TextTitle(chartTitle); 
		title.setFont(font); 
		chart.setTitle(title); 
		
		
		chart.getLegend().setItemFont(new Font("宋体", Font.PLAIN, 12)); //图例文字乱码

		
		PiePlot3D plot = (PiePlot3D) chart.getPlot(); 
		// 图片中显示百分比:默认方式 
		
		// 指定饼图轮廓线的颜色 
		// plot.setBaseSectionOutlinePaint(Color.BLACK); 
		// plot.setBaseSectionPaint(Color.BLACK); 
		
		// 设置无数据时的信息 
		plot.setNoDataMessage("数据不完整，无法生成正确图片！请检查"); 
		
		// 设置无数据时的信息显示颜色 
		plot.setNoDataMessagePaint(Color.red); 
		
		// 图片中显示百分比:自定义方式，{0} 表示选项， {1} 表示数值， {2} 表示所占比例 ,小数点后两位 
		plot.setLabelGenerator(new StandardPieSectionLabelGenerator("{2}", NumberFormat.getNumberInstance(),new DecimalFormat("0.0%"))); 
		// 图例显示百分比:自定义方式， {0} 表示选项， {1} 表示数值， {2} 表示所占比例 
		plot.setLegendLabelGenerator(new StandardPieSectionLabelGenerator("{0}")); 
		
		plot.setLabelFont(new Font("宋体", Font.TRUETYPE_FONT, 9)); 
		
		// 指定图片的透明度(0.0-1.0) 
		plot.setForegroundAlpha(0.85f); 
		// 指定显示的饼图上圆形(false)还椭圆形(true) 
		plot.setCircular(false, true); 
		
		// 设置第一个 饼块section 的开始位置，默认是12点钟方向 
		plot.setStartAngle(90); 
		
		// // 设置分饼颜色 
		plot.setSectionPaint(pieKeys[0], new Color(244, 194, 144)); 
		plot.setSectionPaint(pieKeys[1], new Color(144, 233, 144)); 
		
		FileOutputStream fos_jpg = null; 
		try { 
			// 文件夹不存在则创建 
			isChartPathExist(CHART_PATH); 
			String chartName = CHART_PATH + charName; 
			fos_jpg = new FileOutputStream(chartName); 
			// 高宽的设置影响椭圆饼图的形状 
			ChartUtilities.writeChartAsPNG(fos_jpg, chart, 266, 202); 
		
//			System.ee.println("chartPathName: " + chartName);
			return chartName; 
		}catch (Exception e){ 
			log.error(e.getMessage());
			return null; 
		}finally{ 
			try { 
				fos_jpg.close(); 
//				System.out.println("create pie-chart."); 
			}catch (Exception e){ 
				log.error(e.getMessage());
			} 
		} 
	
	} 
	
public String createErrorChart(String charName){
		
		String chartTitle = "数据不完整，无法生成正确图片！请检查";
		String[] pieKeys = { "错误", "Error" };
		double[] data = { 1, 1 }; 
		
		PieDataset dataset = getDataPieSetByUtil(data, pieKeys);		
		
		JFreeChart chart = ChartFactory.createPieChart3D(chartTitle, // chart 
				// title 
				dataset,// data 
				true,// include legend 
				true, false); 
		// 使下说明标签字体清晰,去锯齿类似于 
		// chart.getRenderingHints().put(RenderingHints.KEY_TEXT_ANTIALIASING,RenderingHints.VALUE_TEXT_ANTIALIAS_OFF);的效果 
		chart.setTextAntiAlias(false); 
		// 图片背景色 
		chart.setBackgroundPaint(Color.white); 
		// 设置图标题的字体重新设置title 
		Font font = new Font("宋体", Font.BOLD, 15); 
		TextTitle title = new TextTitle(chartTitle); 
		title.setFont(font); 
		chart.setTitle(title); 
		chart.getLegend().setItemFont(new Font("黑体",Font.BOLD,12));//图例文字
		PiePlot3D plot = (PiePlot3D) chart.getPlot(); 
		// 图片中显示百分比:默认方式 
		
		// 指定饼图轮廓线的颜色 
		 plot.setBaseSectionOutlinePaint(Color.RED); 
		 plot.setBaseSectionPaint(Color.RED); 
		
		// 设置无数据时的信息 
		plot.setNoDataMessage("数据不完整，无法生成正确图片！请检查"); 
		
		// 设置无数据时的信息显示颜色 
		plot.setNoDataMessagePaint(Color.red); 
		
		// 图片中显示百分比:自定义方式，{0} 表示选项， {1} 表示数值， {2} 表示所占比例 ,小数点后两位 
		plot.setLabelGenerator(new StandardPieSectionLabelGenerator("", NumberFormat.getNumberInstance(),new DecimalFormat("0.0%"))); 
		// 图例显示百分比:自定义方式， {0} 表示选项， {1} 表示数值， {2} 表示所占比例 
		plot.setLegendLabelGenerator(new StandardPieSectionLabelGenerator("")); 
		
		plot.setLabelFont(new Font("宋体", Font.TRUETYPE_FONT, 12)); 
		
		// 指定图片的透明度(0.0-1.0) 
		plot.setForegroundAlpha(0.85f); 
		// 指定显示的饼图上圆形(false)还椭圆形(true) 
		plot.setCircular(true, false); 
		
		// 设置第一个 饼块section 的开始位置，默认是12点钟方向 
		plot.setStartAngle(90); 
		
		// // 设置分饼颜色 
		plot.setSectionPaint(pieKeys[0], new Color(200, 0, 0)); 
		plot.setSectionPaint(pieKeys[1], new Color(199, 0, 0)); 
		
		FileOutputStream fos_jpg = null; 
		try { 
			// 文件夹不存在则创建 
			isChartPathExist(CHART_PATH); 
			String chartName = CHART_PATH + charName; 
			fos_jpg = new FileOutputStream(chartName); 
			// 高宽的设置影响椭圆饼图的形状 
			ChartUtilities.writeChartAsPNG(fos_jpg, chart, 350, 250); 
		
//			System.ee.println("chartPathName: " + chartName);
			return chartName; 
		}catch (Exception e){ 
			e.printStackTrace(); 
			return null; 
		}finally{ 
			try { 
				fos_jpg.close(); 
//				System.out.println("create error-chart. Name: " + charName); 
			}catch (Exception e){ 
				log.error(e.getMessage());
			} 
		}								
	}
	
	/** 
	* 判断文件夹是否存在，如果不存在则新建 
	* @param chartPath 
	*/ 
	private void isChartPathExist(String chartPath) { 
		File file = new File(chartPath); 
		if (!file.exists()) { 
			file.mkdirs(); 
			// log.info("CHART_PATH="+CHART_PATH+"create."); 
		} 	
	} 	
	
	public PieDataset getDataPieSetByUtil(double[] data, String[] datadescription){
		if (data != null && datadescription != null){ 
			if (data.length == datadescription.length){ 
				DefaultPieDataset dataset = new DefaultPieDataset(); 
				for (int i = 0; i < data.length; i++){ 
					dataset.setValue(datadescription[i], data[i]); 
				} 
				return dataset; 
			}
		}
		return null; 
	} 	

//	/**
//	 * @param args
//	 */
//	public static void main(String[] args) {
//		MyChart mc = new MyChart();
//		
//		/*
//		XYSeriesCollection xyCollection = new XYSeriesCollection();
//		XYSeries xyseries1 = new XYSeries("收缩压");
//		xyseries1.add(1, 125);
//
//		XYSeries xyseries2 = new XYSeries("舒张压");
//		xyseries2.add(1, 90);
//
//		xyCollection.addSeries(xyseries1);
//		xyCollection.addSeries(xyseries2);
//		
//		double[] lineMark = new double[] {105, 115};
//		mc.createPointXYChart("本次血压情况", "", "血压值", xyCollection, "test.png", 150, 70, lineMark);
//		*/
//		
//		String[] rowKeys; 
//		String[] columnKeys;
//		double[][] data = new double[2][2];
//		CategoryDataset dataset;
//		
//		data[0][0] = Double.parseDouble("130");
//		data[1][0] = Double.parseDouble("85");
//		data[0][1] = Double.parseDouble("120");
//		data[1][1] = Double.parseDouble("90");						
//							
//		double min = data[0][0];
//		for(int i = 0; i < 2; i++){
//			for(int j = 0; j < 2; j++){
//				if(min > data[i][j]){
//					min = data[i][j];
//				}
//			}
//		}				
//		min = min - 20;
//		
//		rowKeys = new String[]{ "SBP：收缩压", "DBP：舒张压"}; 
//		columnKeys = new String[]{ "上次", "本次" }; 
//		dataset = DatasetUtilities.createCategoryDataset(rowKeys, columnKeys, data); 
//		double[] lineMark = new double[] {88, 90};
//		mc.createXYChart("血压动态变化趋势图", "", "", dataset, "x_tu.png", 150, min, lineMark);
//
//	}

	public String getCHART_PATH() {
		return CHART_PATH;
	}

	public void setCHART_PATH(String chart_path) {
		CHART_PATH = chart_path;
	}

}