package mx.edu.uacm.is.slt.as.intermediario_polizas.adaptadores;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Adaptador de salida que encapsula la comunicación con el Sistema Dueño.
 * Las capas internas no conocen detalles HTTP; reciben y entregan valores simples.
 * Las rutas siguen el listado de servicios REST de la documentación del Sistema Dueño.
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

    // ---------- LECTURA ----------

    public String obtenerCliente(String curp) {
        return restClient.get()
                .uri("/cliente/{curp}", curp)
                .retrieve()
                .body(String.class);
    }

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

    public String obtenerBeneficiario(String fechaNacimiento, String clavePoliza,
                                      String nombres, String primerApellido) {
        return restClient.get()
                .uri("/beneficiario/{fecha}/{clave}/{nombres}/{apellido}",
                     fechaNacimiento, clavePoliza, nombres, primerApellido)
                .retrieve()
                .body(String.class);
    }

    // ---------- ESCRITURA: CLIENTES ----------

    public String altaCliente(String curp, String direccion, LocalDate fechaNacimiento,
                              String nombres, String primerApellido, String segundoApellido) {
        String[] v = valores(curp, direccion, fechaNacimiento, nombres, primerApellido, segundoApellido);
        return restClient.post()
                .uri(plantilla("/cliente", v.length), (Object[]) v)
                .retrieve()
                .body(String.class);
    }

    public String modificarCliente(String curp, String direccion, LocalDate fechaNacimiento,
                                   String nombres, String primerApellido, String segundoApellido) {
        String[] v = valores(curp, direccion, fechaNacimiento, nombres, primerApellido, segundoApellido);
        return restClient.put()
                .uri(plantilla("/cliente", v.length), (Object[]) v)
                .retrieve()
                .body(String.class);
    }

    // ---------- ESCRITURA: PÓLIZAS ----------

    public String altaPoliza(String clave, int tipo, String monto, String descripcion, String curpCliente) {
        String[] v = valores(clave, tipo, monto, descripcion, curpCliente);
        return restClient.post()
                .uri(plantilla("/poliza", v.length), (Object[]) v)
                .retrieve()
                .body(String.class);
    }

    public String modificarPoliza(String clave, int tipo, String monto, String descripcion, String curpCliente) {
        String[] v = valores(clave, tipo, monto, descripcion, curpCliente);
        return restClient.put()
                .uri(plantilla("/poliza", v.length), (Object[]) v)
                .retrieve()
                .body(String.class);
    }

    public String bajaPoliza(String clave) {
        return restClient.delete()
                .uri("/poliza/{clave}", clave)
                .retrieve()
                .body(String.class);
    }

    // ---------- ESCRITURA: BENEFICIARIOS ----------

    public String altaBeneficiario(LocalDate fechaNacimiento, String clavePoliza, int porcentaje,
                                   String nombres, String primerApellido, String segundoApellido) {
        String[] v = valores(fechaNacimiento, clavePoliza, porcentaje, nombres, primerApellido, segundoApellido);
        return restClient.post()
                .uri(plantilla("/beneficiario", v.length), (Object[]) v)
                .retrieve()
                .body(String.class);
    }

    // ---------- AUXILIARES ----------

    /** Convierte los valores a texto, omitiendo los nulos (p. ej. segundo apellido ausente). */
    private static String[] valores(Object... partes) {
        List<String> salida = new ArrayList<>();
        for (Object p : partes) {
            if (p != null) {
                salida.add(p.toString());
            }
        }
        return salida.toArray(new String[0]);
    }

    /** Construye la plantilla de ruta con una variable por cada valor: /prefijo/{v0}/{v1}... */
    private static String plantilla(String prefijo, int cantidad) {
        StringBuilder sb = new StringBuilder(prefijo);
        for (int i = 0; i < cantidad; i++) {
            sb.append("/{v").append(i).append('}');
        }
        return sb.toString();
    }
}
