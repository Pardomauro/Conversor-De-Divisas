package logica;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class conversorDivisas {
    
    private static final String API_URL = "https://api.exchangerate-api.com/v4/latest/ARS";
    private Map<String, Double> tiposCambio;
    
    public conversorDivisas() {
        this.tiposCambio = new HashMap<>();
        cargarTiposCambio();
    }
    
    private void cargarTiposCambio() {
        try {
            String jsonResponse = obtenerDatosAPI();
            parsearTiposCambio(jsonResponse);
        } catch (Exception e) {
            System.err.println("Error al cargar tipos de cambio desde la API: " + e.getMessage());
            cargarTiposCambioManual(); // Fallback con valores predeterminados
        }
    }
    
    private String obtenerDatosAPI() throws Exception {
        URI uri = new URI(API_URL);
        HttpURLConnection connection = (HttpURLConnection) uri.toURL().openConnection();
        connection.setRequestMethod("GET");
        connection.setConnectTimeout(5000);
        connection.setReadTimeout(5000);
        
        int responseCode = connection.getResponseCode();
        if (responseCode != 200) {
            throw new Exception("Error en la respuesta de la API: " + responseCode);
        }
        
        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        StringBuilder response = new StringBuilder();
        String line;
        
        while ((line = reader.readLine()) != null) {
            response.append(line);
        }
        reader.close();
        connection.disconnect();
        
        return response.toString();
    }
    
    private void parsearTiposCambio(String jsonResponse) {
        // Parser JSON simple usando expresiones regulares
        tiposCambio.put("USD", extraerValor(jsonResponse, "USD"));
        tiposCambio.put("EUR", extraerValor(jsonResponse, "EUR"));
        tiposCambio.put("GBP", extraerValor(jsonResponse, "GBP"));
        tiposCambio.put("CLP", extraerValor(jsonResponse, "CLP"));
        tiposCambio.put("BRL", extraerValor(jsonResponse, "BRL"));
        
        System.out.println("✓ Tipos de cambio actualizados desde la API");
    }
    
    private double extraerValor(String json, String moneda) {
        // Buscar el patrón "USD":0.001234 en el JSON
        Pattern pattern = Pattern.compile("\"" + moneda + "\"\\s*:\\s*([0-9]*\\.?[0-9]+)");
        Matcher matcher = pattern.matcher(json);
        
        if (matcher.find()) {
            return Double.parseDouble(matcher.group(1));
        } else {
            throw new RuntimeException("No se pudo encontrar el tipo de cambio para " + moneda);
        }
    }
    
    private void cargarTiposCambioManual() {
        // Valores de respaldo en caso de que la API falle
        tiposCambio.put("USD", 0.001);
        tiposCambio.put("EUR", 0.00095);
        tiposCambio.put("GBP", 0.00082);
        tiposCambio.put("CLP", 0.97);
        tiposCambio.put("BRL", 0.0058);
        
        System.out.println("⚠ Usando tipos de cambio predeterminados (sin conexión a API)");
    }
    
    public double convertir(double cantidadPesos, String monedaDestino) {
        if (!tiposCambio.containsKey(monedaDestino)) {
            throw new IllegalArgumentException("Moneda no soportada: " + monedaDestino);
        }
        
        return cantidadPesos * tiposCambio.get(monedaDestino);
    }
    
    public void mostrarTiposCambio() {
        System.out.println("\n=== TIPOS DE CAMBIO ACTUALES ===");
        System.out.printf("1 ARS = %.4f USD\n", tiposCambio.get("USD"));
        System.out.printf("1 ARS = %.4f EUR\n", tiposCambio.get("EUR"));
        System.out.printf("1 ARS = %.4f GBP\n", tiposCambio.get("GBP"));
        System.out.printf("1 ARS = %.4f CLP\n", tiposCambio.get("CLP"));
        System.out.printf("1 ARS = %.4f BRL\n", tiposCambio.get("BRL"));
        System.out.println("================================\n");
    }
    
    public String obtenerNombreMoneda(int opcion) {
        switch (opcion) {
            case 1: return "USD";
            case 2: return "EUR";
            case 3: return "GBP";
            case 4: return "CLP";
            case 5: return "BRL";
            default: throw new IllegalArgumentException("Opción no válida");
        }
    }
    
    public String obtenerNombreCompleto(String codigo) {
        switch (codigo) {
            case "USD": return "Dólares Estadounidenses";
            case "EUR": return "Euros";
            case "GBP": return "Libras Esterlinas";
            case "CLP": return "Pesos Chilenos";
            case "BRL": return "Reales Brasileños";
            default: return codigo;
        }
    }
}
