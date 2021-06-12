package GUI.Tables;

import java.util.Vector;

import javax.swing.table.DefaultTableModel;

public class CollectionTableModel extends DefaultTableModel {
	public CollectionTableModel(Object[] columnNames, int rowCount) {
		super(columnNames, rowCount);
	}

	public boolean isCellEditable(int row, int col) {
		if(col == 0) {return true;}
		return false;
	}
	
	public Class<?> getColumnClass(int index){
		if(index == 0) return(Boolean.class);
		else if(index == 1 || index== 3) return (Integer.class);
		else return(String.class);
	}

}
