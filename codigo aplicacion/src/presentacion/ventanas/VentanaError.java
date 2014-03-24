package presentacion.ventanas;

import javax.swing.JFrame;

public class VentanaError extends JFrame {

	public VentanaError(String mensajeError) {

		javax.swing.JOptionPane.showMessageDialog(this, mensajeError, "Error",
				javax.swing.JOptionPane.ERROR_MESSAGE);

	}

}
