package br.ufrn.context.enviroment.gui;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

public class FireCellRenderer  implements TableCellRenderer {

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value,
			boolean arg2, boolean arg3, int row, int col) {
		
		JLabel lbl=new JLabel();
		lbl.setOpaque(false);
		if (value!=null){
			lbl.setText(value.toString());
			int valor=Integer.parseInt(value.toString());
			
			if (valor==1){
				lbl.setBackground(Color.RED);
				
			}
		}
		
		return lbl;
	}

}
