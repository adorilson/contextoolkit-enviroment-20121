package br.ufrn.context.enviroment.gui;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;


public class PollutionCellRenderer implements TableCellRenderer {
	public static final String LOWER="Lower";
	public static final String MEDIUM="Medium";
	public static final String HIGHER="Higher";
	@Override
	public Component getTableCellRendererComponent(JTable table, Object value,
			boolean arg2, boolean arg3, int row, int col) {
		JLabel lbl=new JLabel();
		lbl.setOpaque(true);
		if (value!=null){
			lbl.setText(value.toString());
			
			if (value.toString().equalsIgnoreCase(LOWER))
				lbl.setBackground(Color.BLUE);
			else if(value.toString().equalsIgnoreCase(MEDIUM))
				lbl.setBackground(Color.ORANGE);
			else{
				lbl.setBackground(Color.BLACK);
				lbl.setForeground(Color.WHITE);
			}
				
		}
		
		return lbl;
	}

}
