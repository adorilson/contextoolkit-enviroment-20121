package br.ufrn.context.enviroment.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import net.miginfocom.swing.MigLayout;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import br.ufrn.context.enviroment.event.EventHandler;
import br.ufrn.context.enviroment.services.PollutionService;
import br.ufrn.context.enviroment.services.TemperatureService;

import context.arch.enactor.Enactor;
import context.arch.enactor.EnactorXmlParser;
import context.arch.widget.Widget;
import context.arch.widget.WidgetXmlParser;

public class FormControlCenter extends JFrame {

	private JPanel contentPane;
	private JTable tblControlPanel;
	private Widget fireAlarmWidget;
	private TemperatureService service;
	private Enactor enactor;
	private EventHandler handler;
	
	private Enactor pollutionEnactor;
	private Widget pollutionWidget;
	private PollutionService pollutionService;
	
	private void initWidgetsEnactor(){
		
		fireAlarmWidget=WidgetXmlParser.createWidget("widgets/alarmfire-widget.xml");
		
		
		handler=new EventHandler(new ControlTableModel());
		tblControlPanel.setModel(handler.getTableModel());
		tblControlPanel.getColumnModel().getColumn(3).setCellRenderer(new FireCellRenderer());
		
		service=new TemperatureService(fireAlarmWidget, handler);
		fireAlarmWidget.addService(service);
		
		enactor=EnactorXmlParser.createEnactor("widgets/temperature-enactor.xml");
		
		pollutionWidget=WidgetXmlParser.createWidget("widgets/pollution-widget.xml");
		pollutionService=new PollutionService(pollutionWidget, handler);
		pollutionWidget.addService(pollutionService);
		pollutionEnactor=EnactorXmlParser.createEnactor("widgets/pollution-enactor.xml");
		
		
	}
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FormControlCenter frame = new FormControlCenter();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public JTable getTblControlPanel() {
		return tblControlPanel;
	}

	/**
	 * Create the frame.
	 */
	public FormControlCenter() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[grow]", "[grow]"));
		
		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane, "cell 0 0,grow");
		
		tblControlPanel = new JTable();
		scrollPane.setViewportView(tblControlPanel);
		
		initWidgetsEnactor();
	}

}
