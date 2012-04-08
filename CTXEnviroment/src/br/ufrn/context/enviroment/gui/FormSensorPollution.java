package br.ufrn.context.enviroment.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;

import context.arch.discoverer.Discoverer;
import context.arch.storage.Attributes;
import context.arch.widget.Widget;
import context.arch.widget.WidgetXmlParser;

public class FormSensorPollution extends JFrame {

	private JPanel contentPane;
	private JTextField txtLocal;
	private JTextField txtPollutionLevel;
	private JButton btnUpdateWidget;

	private Widget pollutionWidget;
	
	/**
	 * Launch the application.
	 */
	
	public void initWidgets(){
		
		pollutionWidget=WidgetXmlParser.createWidget("widgets/pollution-widget.xml");
		
	}
	
	public static void main(String[] args) {
		Discoverer.start();
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FormSensorPollution frame = new FormSensorPollution();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public FormSensorPollution() {
		setTitle("Pollution Measure");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 116);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[][grow]", "[][]"));
		
		JLabel lblLocal = new JLabel("Local:");
		contentPane.add(lblLocal, "cell 0 0,alignx trailing");
		
		txtLocal = new JTextField();
		contentPane.add(txtLocal, "cell 1 0,growx");
		txtLocal.setColumns(10);
		
		JLabel lblPollutionMeasure = new JLabel("Pollution Measure:");
		contentPane.add(lblPollutionMeasure, "cell 0 1,alignx trailing");
		
		txtPollutionLevel = new JTextField();
		contentPane.add(txtPollutionLevel, "cell 1 1,growx");
		txtPollutionLevel.setColumns(10);
		
		btnUpdateWidget = new JButton("Update Widget");
		btnUpdateWidget.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("actionPerformed");
				Attributes att=new Attributes();
				
				att.addAttribute("local", txtLocal.getText());
				att.addAttribute("level", Integer.parseInt(txtPollutionLevel.getText()));
				pollutionWidget.updateData(att);
				System.out.println("chamou pollutionWidget.updateData(att);");
				
			}
		});
		contentPane.add(btnUpdateWidget, "cell 3 3");
		
		initWidgets();
		
		
	}

}
