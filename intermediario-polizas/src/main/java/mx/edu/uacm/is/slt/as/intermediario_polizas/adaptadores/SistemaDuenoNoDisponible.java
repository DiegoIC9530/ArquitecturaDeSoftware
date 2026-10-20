package mx.edu.uacm.is.slt.as.intermediario_polizas.adaptadores;

public class SistemaDuenoNoDisponible extends RuntimeException{
	public SistemaDuenoNoDisponible(String mensaje, Throwable causa) {
		super(mensaje, causa);
		
	}
}
