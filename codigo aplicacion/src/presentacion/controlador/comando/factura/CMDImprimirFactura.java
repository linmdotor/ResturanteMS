package presentacion.controlador.comando.factura;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import negocio.factoria.FactoriaNegocio;
import negocio.factura.SAFactura;
import negocio.factura.TFactura;
import negocio.factura.TFacturaPlato;
import negocio.plato.SAPlato;
import negocio.plato.TPlato;
import presentacion.controlador.CMD;
import presentacion.controlador.EnumComandos;
import presentacion.controlador.RespuestaCMD;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class CMDImprimirFactura implements CMD{

	public RespuestaCMD ejecuta(Object objeto)
	{
		
		ArrayList<TFacturaPlato> tfacturaplatos = (ArrayList<TFacturaPlato>) objeto; 
		
		//consigo el Transfer Factura y Platos
		SAFactura serviciosFactura = FactoriaNegocio.obtenerInstancia().generaSAFactura();		
		TFactura tfactura;
		RespuestaCMD respuesta = null;
		
		try {
			tfactura = serviciosFactura.obtenerFactura(tfacturaplatos.get(0).getID_Factura());
			SAPlato serviciosPlato = FactoriaNegocio.obtenerInstancia().generaSAPlato();
			ArrayList<TPlato> tplatos = new ArrayList<TPlato>();
			
			float total = 0;
			
			for(TFacturaPlato t : tfacturaplatos)
			{
				tplatos.add(serviciosPlato.obtenerPlato(t.getID_Plato()));
				total = total + (t.getPrecio()*t.getCantidad());
			}
			
			
			String ruta = "C:\\Users\\juancly\\Desktop\\factura_" + tfactura.getID_Factura() + ".pdf";
			
			//imprimo la factura
			
			String [][] str = guardarDatosTicket(tplatos, tfacturaplatos);
			try {
				
				generarPDF(str, tfactura, total, ruta);
				
			} catch (IOException | DocumentException e) {
				
				e.printStackTrace();
				respuesta= new RespuestaCMD(EnumComandos.ERROR, "Error al generar la factura.");
			}		
			
			respuesta= new RespuestaCMD(EnumComandos.CORRECTO, "Factura generada en: " + ruta);

		} catch (Exception e1) {
			respuesta= new RespuestaCMD(EnumComandos.ERROR, "Error inesperado al generar la factura.");
			e1.printStackTrace();
		}
		
		return respuesta;
	}
	
	
	
	
	
	public void generarPDF(String[][] ticket, TFactura tfactura, double total, String ruta) throws IOException, DocumentException
	{
		FileOutputStream archivo = new FileOutputStream(ruta);
		Document documento = new Document();
		PdfWriter.getInstance(documento, archivo);
		documento.open();
		
		
		documento.add(new Paragraph("TICKET RESTAURANTE " + "                                                                  " + "FECHA - " + tfactura.getFecha()));
		documento.add(new Paragraph("                                                                                                             " + "HORA - " + tfactura.getHora()));
		documento.add(new Paragraph("   "));
		documento.add(new Paragraph("DATOS EMPRESA                                                            DATOS CLIENTE"));
		documento.add(new Paragraph("NIF: " + tfactura.getNIF_Empresa() + "                                                                     NIF: " + tfactura.getNIF_Cliente() ));
		documento.add(new Paragraph("Nombre: " + tfactura.getNombre_Empresa() + "                                                 Nombre: " + tfactura.getNombre_Cliente()));
		documento.add(new Paragraph("Direcci�n: " + tfactura.getDir_Empresa() + "                                                 Direccion: " + tfactura.getDir_Cliente()));
		
		
		
		documento.add(new Paragraph(" "));
		documento.add(new Paragraph(" "));
		documento.add(new Paragraph(" "));
		
		PdfPTable tabla = new PdfPTable(7);
		
			tabla.addCell("Producto");
			tabla.addCell("Cant");
			tabla.addCell("IVA");
			tabla.addCell("Precio sin IVA");
			tabla.addCell("Precio con IVA");
			tabla.addCell("Total sin IVA");
			tabla.addCell("Total con IVA");
			
		
		float preciosinIVA = 0;
		float totalconIVA = 0;
		float totalsinIVA = 0;
		
		for (int f = 0; f < ticket.length; f++)
		{
			preciosinIVA = (Float.parseFloat(ticket[f][1]))-((Float.parseFloat(ticket[f][1])*(float)(tfactura.getIVA()))/100);
			totalconIVA = (Float.parseFloat(ticket[f][2])*(Float.parseFloat(ticket[f][1])));
			totalsinIVA = (Float.parseFloat(ticket[f][2]))*preciosinIVA;
				tabla.addCell(ticket[f][0]);//Producto
				tabla.addCell(ticket[f][2]);//Cantidad
				tabla.addCell(Integer.toString(tfactura.getIVA()));//IVA
				tabla.addCell(Float.toString(redondear(preciosinIVA)));//Precio sin IVA
				tabla.addCell(Float.toString(redondear(Float.valueOf(ticket[f][1]))));//Precio con IVA
				tabla.addCell(Float.toString(redondear(totalsinIVA)));
				tabla.addCell(Float.toString(redondear(totalconIVA)));

		}	
		
		documento.add(tabla);
		documento.add(new Paragraph(" "));
		documento.add(new Paragraph(" "));
		documento.add(new Paragraph(" "));
		documento.add(new Paragraph(" "));
		
		documento.add(new Paragraph("                                                               TOTAL: " + redondear((float)total)));
		documento.close();
	}


	public String[][] guardarDatosTicket(ArrayList<TPlato> tPlato, ArrayList<TFacturaPlato> tFacturaPlato)
	{
		String[][] ticket = new String[tPlato.size()][3];
		
		for (int f = 0; f < tPlato.size(); f++)
		{
			ticket[f][0] = tPlato.get(f).getNombre().toString();	
			ticket[f][1] = Float.toString(tFacturaPlato.get(f).getPrecio());	
			ticket[f][2] = Integer.toString(tFacturaPlato.get(f).getCantidad());	
		}
		
		return ticket;
	}
	
	
	public float redondear(float numero){		
		return (float)Math.rint(numero*100)/100;		
	}
}