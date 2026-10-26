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
    	try {
        return restClient.get()
                .uri("/poliza/{clave}", clave)
                .retrieve()
                .body(String.class);
    	}catch(Exception e) {
    		throw new SistemaDuenoNoDisponible("El Sistema Dueño no está disponible: " + clave, e);
    	}
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
	
 // ---------- MÉTODOS PARA ALTAS (POST) ----------

    public String registrarCliente(Object cliente) {
        return restClient.post()
                .uri("/cliente")
                .body(cliente)
                .retrieve()
                .body(String.class);
    }

    public String registrarPoliza(Object poliza) {
        return restClient.post()
                .uri("/poliza")
                .body(poliza)
                .retrieve()
                .body(String.class);
    }

    public String registrarBeneficiario(Object beneficiario) {
        return restClient.post()
                .uri("/beneficiario")
                .body(beneficiario)
                .retrieve()
                .body(String.class);
    }	
 // ---------- MÉTODOS PARA BAJAS (DELETE) ----------
    public String eliminarPoliza(String clave) {
        return restClient.delete()
                .uri("/poliza/{clave}", clave)
                .retrieve()
                .body(String.class);
    }
}//fin de la clase
