package presentacion.ventanas;

import javax.swing.JFrame;

public class VentanaCorrecto extends JFrame {

	public VentanaCorrecto(String mensaje) {

		javax.swing.JOptionPane.showMessageDialog(this, mensaje, "Correcto",
				javax.swing.JOptionPane.INFORMATION_MESSAGE);

	}

}
