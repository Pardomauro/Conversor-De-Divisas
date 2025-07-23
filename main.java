import logica.conversorDivisas;
import java.util.Scanner;

public class main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        conversorDivisas conversor = new conversorDivisas();
        
        System.out.println("=== CONVERSOR DE DIVISAS ===");
        System.out.println("Conectando con la API para obtener tipos de cambio actualizados...\n");
        
        // Mostrar los tipos de cambio actuales
        conversor.mostrarTiposCambio();
        
        while (true) {
            try {
                System.out.println("Seleccione la moneda a la que desea convertir desde pesos argentinos:");
                System.out.println("1 - Dólares (USD)");
                System.out.println("2 - Euros (EUR)");
                System.out.println("3 - Libra Esterlina (GBP)");
                System.out.println("4 - Pesos Chilenos (CLP)");
                System.out.println("5 - Reales (BRL)");
                System.out.println("0 - Salir");
                System.out.print("Opción: ");
                
                int opcion = scanner.nextInt();
                
                if (opcion == 0) {
                    System.out.println("¡Gracias por usar el conversor de divisas!");
                    break;
                }
                
                if (opcion < 1 || opcion > 5) {
                    System.out.println("❌ Opción no válida. Por favor, seleccione un número del 1 al 5.\n");
                    continue;
                }
                
                System.out.print("Ingrese la cantidad en pesos argentinos (ARS): $");
                double pesos = scanner.nextDouble();
                
                if (pesos < 0) {
                    System.out.println("❌ La cantidad no puede ser negativa.\n");
                    continue;
                }
                
                String monedaDestino = conversor.obtenerNombreMoneda(opcion);
                double resultado = conversor.convertir(pesos, monedaDestino);
                String nombreCompleto = conversor.obtenerNombreCompleto(monedaDestino);
                
                System.out.printf("\n✅ RESULTADO DE LA CONVERSIÓN:\n");
                System.out.printf("$%.2f ARS = %.4f %s (%s)\n", 
                    pesos, resultado, monedaDestino, nombreCompleto);
                
                // Mostrar el tipo de cambio utilizado
                double tipoCambio = resultado / pesos;
                System.out.printf("Tipo de cambio utilizado: 1 ARS = %.6f %s\n", 
                    tipoCambio, monedaDestino);
                
                System.out.println("\n" + "=".repeat(50) + "\n");
                
            } catch (Exception e) {
                System.out.println("❌ Error: " + e.getMessage());
                System.out.println("Por favor, intente nuevamente.\n");
                scanner.nextLine(); // Limpiar el buffer del scanner
            }
        }
        
        scanner.close();
    }
}

