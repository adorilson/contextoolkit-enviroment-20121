package br.ufrn.context.enviroment.gui;

import java.util.Vector;

import javax.swing.table.AbstractTableModel;

import br.ufrn.context.enviroment.event.LocalModel;

public class ControlTableModel extends AbstractTableModel {

	private Vector<LocalModel> data;
	public static final int TEMPERATURE=0;
	public static final int POLLUTION=1;
	@Override
	public String getColumnName(int column) {
		switch(column){
			case 0:
				return "Local";
			case 1:
				return "Temperature";
			case 2:
				return "Pollution Level";
			case 3:
				return "On Fire";
		}
		return null;
	}
	
	private LocalModel find(String local){
		for(LocalModel elemento:data){
			if (elemento.getLocation().equalsIgnoreCase(local))
				return elemento;
		}
		return null;
	}
	
	public synchronized void addData(LocalModel local,int type){
		
		LocalModel temp=find(local.getLocation());
		
		if (temp==null)
			data.addElement(local);
		else{
			if(type==TEMPERATURE){
				temp.setFire(local.isFire());
				temp.setTemperature(local.getTemperature());
			}else{
				temp.setPollutionLevel(local.getPollutionLevel());
			}
		}
		fireTableDataChanged();
		
		
	}
	public ControlTableModel() {
		data=new Vector<LocalModel>();
	}

	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return 4;
	}

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return data.size();
	}

	@Override
	public Object getValueAt(int line, int col) {
		
		switch(col){
			case 0:
				return data.get(line).getLocation();
			case 1:
				return data.get(line).getTemperature();
			case 2:
				return data.get(line).getPollutionLevel();
			case 3:
				return data.get(line).isFire();
		}
		
		return null;
	}

}
