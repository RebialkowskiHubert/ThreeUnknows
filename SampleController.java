package application;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class SampleController
{
	@FXML
	TextField xod;
	@FXML
	TextField xdo;
	@FXML
	TextField twielkpop;
	@FXML
	TextField tmut;
	@FXML
	TextField tkrzyz;
	@FXML
	TextField tpok;
	
	@FXML
	TextArea ta;
	
	ObservableList<String> ol=FXCollections.observableArrayList
			(Funkcja.funkcje[0], Funkcja.funkcje[1],Funkcja.funkcje[2]);
	
	@SuppressWarnings("rawtypes")
	@FXML
	ChoiceBox cb;
	
	@FXML
	TextField w1;
	@FXML
	TextField w2;
	@FXML
	TextField wmin;
	
	@FXML
	NumberAxis xAxis=new NumberAxis();
	@FXML
	NumberAxis minAxis=new NumberAxis();
	@FXML
	LineChart<Number, Number> lc=new LineChart<Number, Number>(xAxis, minAxis);
	
	@FXML
	NumberAxis yAxis=new NumberAxis();
	@FXML
	NumberAxis min2Axis=new NumberAxis();
	@FXML
	LineChart<Number, Number> lc2=new LineChart<Number, Number>(yAxis, min2Axis);
	
	int n=0;
	
	@FXML
	void btnOblicz()
	{
		String opc=(String) cb.getValue();
		Funkcja.opc=opc;
		
		String sxod=xod.getText();
		String sxdo=xdo.getText();
		String spopsize=twielkpop.getText();
		int popsize=Integer.parseInt(spopsize);
		String smut=tmut.getText();
		double mut=Double.parseDouble(smut);
		String scross=tkrzyz.getText();
		double cross=Double.parseDouble(scross);
		String slimit=tpok.getText();
		int limit=Integer.parseInt(slimit);
		Funkcja f=new Funkcja();
		f.x1=Double.parseDouble(sxod);
		f.x2=Double.parseDouble(sxdo);
		f.oblicz(popsize, mut, cross, limit);
		
		ta.setText(f.stat);
		w1.setText(f.wx);
		w2.setText(f.wy);
		wmin.setText(f.wmin);
		
		double x=Double.parseDouble(f.wx);
		double y=Double.parseDouble(f.wy);
		double min=Double.parseDouble(f.wmin);		
		n++;
		
		XYChart.Series<Number, Number> seriesXmin=new XYChart.Series<Number, Number>();
		seriesXmin.setName("Minimum funkcji, wynik "+n);
		seriesXmin.getData().add(new XYChart.Data<Number, Number>(x, min));
		
		XYChart.Series<Number, Number> seriesX=new XYChart.Series<Number, Number>();
		seriesX.setName("Funkcja w zależności od x");
		
		XYChart.Series<Number, Number> seriesY=new XYChart.Series<Number, Number>();
		seriesY.setName("Funkcja w zależności od y");
		
		
		for(double i=-6.0; i<=6.0; i+=0.05)
		{
			if(Funkcja.flag==1)
			{
				double wminX=Math.sin(i)+Math.sin(i-y)-Math.cos(i)*Math.tan(y)/Math.sqrt(Math.pow((i+y),2)+Math.pow((i-y),2));
				seriesX.getData().add(new XYChart.Data<Number, Number>(i, wminX));
			
				double wminY=Math.sin(x)+Math.sin(x-i)-Math.cos(x)*Math.tan(i)/Math.sqrt(Math.pow((x+i),2)+Math.pow((x-i),2));
				seriesY.getData().add(new XYChart.Data<Number, Number>(i, wminY));
			}
			
			if(Funkcja.flag==2)
			{
				double wminX=Math.tan(i)+Math.cos(i-y)-Math.sin(i)*Math.cos(y)/Math.sqrt(Math.pow((i+y),2)+Math.pow((i-y),2));
				seriesX.getData().add(new XYChart.Data<Number, Number>(i, wminX));
			
				double wminY=Math.tan(x)+Math.cos(x-i)-Math.sin(x)*Math.cos(i)/Math.sqrt(Math.pow((x+i),2)+Math.pow((x-i),2));
				seriesY.getData().add(new XYChart.Data<Number, Number>(i, wminY));
			}
			
			if(Funkcja.flag==3)
			{
				double wminX=Math.cos(i)+Math.tan(i+y)-Math.tan(i)*Math.sin(y)/Math.sqrt(Math.pow((i-y),2)+Math.pow((i+y),2));
				seriesX.getData().add(new XYChart.Data<Number, Number>(i, wminX));
			
				double wminY=Math.cos(x)+Math.tan(x+i)-Math.tan(x)*Math.sin(i)/Math.sqrt(Math.pow((x-i),2)+Math.pow((x+i),2));
				seriesY.getData().add(new XYChart.Data<Number, Number>(i, wminY));
			}
		}
		
		XYChart.Series<Number, Number> seriesYmin=new XYChart.Series<Number, Number>();
		seriesYmin.setName("Minimum funkcji, wynik "+n);
		seriesYmin.getData().add(new XYChart.Data<Number, Number>(y, min));
		
		lc.getData().add(seriesX);
		lc.getData().add(seriesXmin);
		lc2.getData().add(seriesY);
		lc2.getData().add(seriesYmin);
	}
	
	@SuppressWarnings("unchecked")
	@FXML
	private void initialize()
	{
		cb.setValue(ol.get(0));
		cb.setItems(ol);
	}
}
