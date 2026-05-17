package ar.edu.unlar.programacion3;

import ar.edu.unlar.programacion3.ui.MenuCajero;
import ar.edu.unlar.programacion3.model.CuentaBancaria;
import ar.edu.unlar.programacion3.service.CajeroService;

public class Main {
    public static void main(String[] args) {
        // Crear una instancia de CajeroService para ejecutar sus métodos
        CajeroService service = new CajeroService();

        //! SIMULACION
        // Creamos 3 cuentas bancarias
        CuentaBancaria cuenta1 = new CuentaBancaria("123456", 1000, "Valentina", true);
        CuentaBancaria cuenta2 = new CuentaBancaria("123456", 2000, "Pedro", true);
        CuentaBancaria cuenta3 = new CuentaBancaria("3456789", 3000, "Ana", false);

        //! Simulamos operaciones y excepciones a proposito

        try {
            service.depositar(cuenta1, 2000);   // 1
            service.extraer(cuenta1, 500);      // 2
            service.transferir(cuenta1, cuenta2, 1000); // 3 (Origen 1, Destino 2)
            service.depositar(cuenta2, 3000);   // 4
            service.extraer(cuenta2, 200);      // 5
            service.extraer(cuenta2, 100);      // 6
            service.depositar(cuenta1, 50);     // 7
            service.extraer(cuenta1, 10);       // 8
            service.transferir(cuenta2, cuenta1, 500); // 9
            service.depositar(cuenta2, 1000);   // 10
            service.extraer(cuenta3, 1000);     // 11: Cuenta inactiva


            System.out.println("\n--- Probando Excepciones ---");
            try { service.extraer(cuenta1, 20000); } // 11: Límite excedido
            catch (Exception e) { System.out.println("Capturada: " + e.getMessage()); }

            try { service.extraer(cuenta1, 99999); } // 12: Saldo insuficiente
            catch (Exception e) { System.out.println("Capturada: " + e.getMessage()); }

            try { service.depositar(cuenta3, 500); } // 13: Cuenta inactiva
            catch (Exception e) { System.out.println("Capturada: " + e.getMessage()); }
            
            service.extraer(cuenta2, 500);      // 14
            service.depositar(cuenta2, 100);    // 15

            System.out.println("\n=== SIMULACION FINALIZADA CON EXITO ===\n");

        } catch (Exception e) {
            System.err.println("Error inesperado en simulacion: " + e.getMessage());
        }

        // Iniciar el Cajero con la cuenta 1, 2 o 3 (que está desactivada)
        MenuCajero menu = new MenuCajero();
        menu.iniciar(cuenta1);
    }
}