/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import ADO.Leituras;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.sql.SQLException;
import java.util.List;
import javax.swing.JOptionPane;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.block.BlockBorder;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;



/**
 *
 * @author Aluno
 */
public class Create_Grafico{
    
    public XYDataset createDataset(List<Leituras> leituras,Integer fkMaqComp) throws SQLException {
        
        XYSeries dados = new XYSeries("2000");
       
        leituras.forEach(lendo ->{
           
            
            dados.add(lendo.getIdLeitura(), lendo.getLeitura());
           
        });
        

       
        XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(dados);

        return dataset;
    }

    public JFreeChart createChart( XYDataset dataset,String tipoLeitura) {
         
        JFreeChart chart = ChartFactory.createXYLineChart(
                tipoLeitura + "por Horário",
                "ID",
                tipoLeitura,
                dataset,
                PlotOrientation.VERTICAL,
                true,
                true,
                false
        );

        XYPlot plot = chart.getXYPlot();

        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();

        renderer.setSeriesPaint(0, Color.RED);
        renderer.setSeriesStroke(0, new BasicStroke(2.0f));
        

        plot.setRenderer(renderer);
        plot.setBackgroundPaint(Color.white);
        plot.setRangeGridlinesVisible(false);
        plot.setDomainGridlinesVisible(false);

        chart.getLegend().setFrame(BlockBorder.NONE);

        chart.setTitle(new TextTitle(tipoLeitura +" por ID",
                        new Font("Serif", Font.BOLD, 18)
                )
        );

        return chart;
    }

    
}
