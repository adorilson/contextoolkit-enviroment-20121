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

import context.arch.discoverer.Discoverer;
import context.arch.enactor.Enactor;
import context.arch.enactor.EnactorXmlParser;
import context.arch.widget.Widget;
import context.arch.widget.WidgetXmlParser;
import javax.swing.JLabel;
import java.awt.Color;

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
	private JPanel panel;
	private JLabel lblNewLabel;
	private JLabel lblIncndio;
	private JLabel lblLowe;
	private JLabel lblLowePollutionLevel;
	private JLabel lblNewLabel_1;
	private JLabel lblMediumPollutionLevel;
	private JLabel lblHigh;
	private JLabel lblHighPollutionLevel;
	
	private void initWidgetsEnactor(){
		
		handler=new EventHandler(new ControlTableModel());
		
		fireAlarmWidget=WidgetXmlParser.createWidget("widgets/alarmfire-widget.xml");
		service=new TemperatureService(fireAlarmWidget, handler);
		fireAlarmWidget.addService(service);
		
		enactor=EnactorXmlParser.createEnactor("widgets/temperature-enactor.xml");
		
		pollutionWidget=WidgetXmlParser.createWidget("widgets/alarmpollution-widget.xml");
		pollutionService=new PollutionService(pollutionWidget, handler);
		pollutionWidget.addService(pollutionService);
		
		pollutionEnactor=EnactorXmlParser.createEnactor("widgets/pollution-enactor.xml");
		
		tblControlPanel.setModel(handler.getTableModel());
		tblControlPanel.getColumnModel().getColumn(3).setCellRenderer(new FireCellRenderer());
		tblControlPanel.getColumnModel().getColumn(2).setCellRenderer(new PollutionCellRenderer());
	}
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		Discoverer.start();
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
		setBounds(100, 100, 458, 422);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[grow]", "[grow][grow]"));
		
		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane, "cell 0 0,grow");
		
		tblControlPanel = new JTable();
		scrollPane.setViewportView(tblControlPanel);
		
		panel = new JPanel();
		contentPane.add(panel, "cell 0 1,grow");
		panel.setLayout(new MigLayout("", "[61px][]", "[16px][][][]"));
		
		lblNewLabel = new JLabel("Inc\u00EAndio");
		lblNewLabel.setOpaque(true);
		lblNewLabel.setForeground(Color.RED);
		lblNewLabel.setBackground(Color.RED);
		panel.add(lblNewLabel, "cell 0 0,grow");
		
		lblIncndio = new JLabel("On Fire");
		panel.add(lblIncndio, "cell 1 0");
		
		lblLowe = new JLabel("Lower");
		lblLowe.setForeground(Color.BLUE);
		lblLowe.setBackground(Color.BLUE);
		lblLowe.setOpaque(true);
		panel.add(lblLowe, "cell 0 1,growx");
		
		lblLowePollutionLevel = new JLabel("Lower Pollution Level");
		panel.add(lblLowePollutionLevel, "cell 1 1");
		
		lblNewLabel_1 = new JLabel("Medium");
		lblNewLabel_1.setOpaque(true);
		lblNewLabel_1.setBackground(Color.ORANGE);
		lblNewLabel_1.setForeground(Color.ORANGE);
		panel.add(lblNewLabel_1, "cell 0 2,grow");
		
		lblMediumPollutionLevel = new JLabel("Medium Pollution Level");
		panel.add(lblMediumPollutionLevel, "cell 1 2");
		
		lblHigh = new JLabel("High");
		lblHigh.setBackground(Color.BLACK);
		lblHigh.setOpaque(true);
		panel.add(lblHigh, "cell 0 3,growx");
		
		lblHighPollutionLevel = new JLabel("Higher Pollution Level");
		panel.add(lblHighPollutionLevel, "cell 1 3");
		
		initWidgetsEnactor();
	}

}
