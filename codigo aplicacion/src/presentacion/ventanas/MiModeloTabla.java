package presentacion.ventanas;

import javax.swing.table.DefaultTableModel;

@SuppressWarnings("serial")
public class MiModeloTabla extends DefaultTableModel {

	public boolean isCellEditable(int row, int column) {
		return false;

	}

}