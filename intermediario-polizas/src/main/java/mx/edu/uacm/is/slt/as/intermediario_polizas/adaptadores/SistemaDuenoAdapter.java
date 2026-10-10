package mx.edu.uacm.is.slt.as.intermediario_polizas.adaptadores;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

/**
 * Adaptador de salida que encapsula la comunicación con el Sistema Dueño.
 * Sigue la regla de la dependencia de Clean Architecture:
 * las capas internas no conocen detalles HTTP.
 */
@Component



public class SistemaDuenoAdapter {

	private static final String BASE_URL = "http://nachintoch.mx:8080";

    private final RestClient restClient;

    public SistemaDuenoAdapter() {
        this.restClient = RestClient.builder()
                .baseUrl(BASE_URL)
                .build();
    }

    // ---------- CLIENTES ----------
    public String obtenerCliente(String curp) {
        return restClient.get()
                .uri("/cliente/{curp}", curp)
                .retrieve()
                .body(String.class);
    }

    // ---------- PÓLIZAS ----------
    public String obtenerPoliza(String clave) {
        return restClient.get()
                .uri("/poliza/{clave}", clave)
                .retrieve()
                .body(String.class);
    }

    public String obtenerTodasLasPolizas() {
        return restClient.get()
                .uri("/polizas")
                .retrieve()
                .body(String.class);
    }

    public String obtenerPolizasPorCliente(String curp) {
        return restClient.get()
                .uri("/polizas/{curp}", curp)
                .retrieve()
                .body(String.class);
    }

    public String obtenerPolizasPorTipo(int tipo) {
        return restClient.get()
                .uri("/polizas/{tipo}", tipo)
                .retrieve()
                .body(String.class);
    }

    // ---------- BENEFICIARIOS ----------
    public String obtenerBeneficiario(String fechaNacimiento, String clavePoliza,
                                       String nombres, String primerApellido) {
        return restClient.get()
                .uri("/beneficiario/{fecha}/{clave}/{nombres}/{apellido}",
                     fechaNacimiento, clavePoliza, nombres, primerApellido)
                .retrieve()
                .body(String.class);
    }
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}//fin de la clase
