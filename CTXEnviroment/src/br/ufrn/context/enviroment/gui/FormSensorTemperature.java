package br.ufrn.context.enviroment.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.factories.FormFactory;

import context.arch.discoverer.Discoverer;
import context.arch.storage.Attributes;
import context.arch.widget.Widget;
import context.arch.widget.WidgetXmlParser;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JSpinner;
import javax.swing.JCheckBox;
import javax.swing.JButton;
import net.miginfocom.swing.MigLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FormSensorTemperature extends JFrame {

	private JPanel contentPane;
	private JTextField txtLocal;
	private JTextField txtTemperature;
	private JCheckBox chkIsFire ;

	/**
	 * Launch the application.
	 */
	private Widget temperatureWidget;
	private JButton btnUpdateWidget;
	public void initWidgets(){
		
		temperatureWidget=WidgetXmlParser.createWidget("widgets/temperature-widget.xml");
		
	}
	
	public static void main(String[] args) {
		Discoverer.start();
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FormSensorTemperature frame = new FormSensorTemperature();
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
	public FormSensorTemperature() {
		setTitle("Temperature and Fire");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 138);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[83px][70px][][263px,grow]", "[28px][28px][]"));
		
		JLabel lblLocal = new JLabel("Location:");
		contentPane.add(lblLocal, "cell 0 0,alignx right,aligny center");
		
		txtLocal = new JTextField();
		contentPane.add(txtLocal, "cell 1 0 3 1,growx,aligny top");
		txtLocal.setColumns(10);
		
		chkIsFire = new JCheckBox("Is Fire");
		contentPane.add(chkIsFire, "cell 0 1,alignx left,aligny center");
		
		JLabel lblTemperature = new JLabel("Temperature:");
		contentPane.add(lblTemperature, "cell 1 1 2 1,alignx left,aligny center");
		
		txtTemperature = new JTextField();
		contentPane.add(txtTemperature, "cell 3 1,growx");
		txtTemperature.setColumns(10);
		
		btnUpdateWidget = new JButton("Update Widget");
		btnUpdateWidget.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				/*temperatureWidget.updateData("location",txtLocal.getText());
				temperatureWidget.updateData("temperature", Double.parseDouble(txtTemperature.getText()));
				if (chkIsFire.isSelected())
					temperatureWidget.updateData("fire", 1);
				else
					temperatureWidget.updateData("fire", 0);*/
				
				Attributes att=new Attributes();
				
				att.addAttribute("location", txtLocal.getText());
				att.addAttribute("temperature", Double.parseDouble(txtTemperature.getText()));
				if (chkIsFire.isSelected())
					att.addAttribute("fire", 1);
				else
					att.addAttribute("fire", 0);
				temperatureWidget.updateData(att);
				
			}
		});
		contentPane.add(btnUpdateWidget, "cell 3 2");
		
		initWidgets();
	}

}
