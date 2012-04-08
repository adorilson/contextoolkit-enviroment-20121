package br.ufrn.context.enviroment.event;

import java.util.StringTokenizer;

import br.ufrn.context.enviroment.gui.ControlTableModel;

public class EventHandler {
	
	public static final String ONFIRE="On Fire";
	public static final String OFFFIRE="Off Fire";
	
	
	
	private ControlTableModel tableModel;
	
	
	public EventHandler(ControlTableModel _tableModel){
		tableModel=_tableModel;
	}
	
	
	
	public ControlTableModel getTableModel() {
		return tableModel;
	}



	public void changeTemperature(String status){
		System.out.println("EventHandler.changeTemperature");
		System.out.println(status);
		
		StringTokenizer st=new StringTokenizer(status,"-");
		
		String fire=st.nextToken();
		double temperature=Double.parseDouble(st.nextToken());
		String place=st.nextToken();
		
		LocalModel modelo=new LocalModel();
		
		modelo.setTemperature(temperature);
		modelo.setLocation(place);
		
		if (fire.equalsIgnoreCase(ONFIRE)){
			modelo.setFire(1);
		}else{
			modelo.setFire(0);
		}
		
		tableModel.addData(modelo, ControlTableModel.TEMPERATURE);
		
	}
	
	public void changePollutionLevel(String status){
		
		System.out.println("EventHandler.changePollutionLevel");
		System.out.println(status);
		
		StringTokenizer st=new StringTokenizer(status,"-");
		
		//String fire=st.nextToken();
		String pollution=st.nextToken();
		String place=st.nextToken();
		
		LocalModel modelo=new LocalModel();
		
		modelo.setPollutionLevel(pollution);
		modelo.setLocation(place);
		
		tableModel.addData(modelo, ControlTableModel.POLLUTION);
		
	}
	
}
