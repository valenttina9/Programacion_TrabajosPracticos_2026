package ar.edu.unlar.programacion3.ui;

import java.util.Scanner; // capturamos entrada de usuario
import java.util.InputMismatchException; // capturamos excepcion de entrada

import ar.edu.unlar.programacion3.exception.*;
import ar.edu.unlar.programacion3.model.CuentaBancaria;
import ar.edu.unlar.programacion3.model.Transaccion;
import ar.edu.unlar.programacion3.service.CajeroService;

public class MenuCajero {
    private final CajeroService service = new CajeroService();
    private final Scanner sc = new Scanner(System.in); // capturamos entrada de usuario

    public void iniciar(CuentaBancaria cuenta) {
        boolean salir = false;
        System.out.println("Bienvenido al Cajero UNLAR");
        System.out.println("Titular "+ cuenta.getTitular());

        while (!salir) {
            try{
                System.out.println("1. Deposito");
                System.out.println("2. Extraccion");
                System.out.println("3. Mostrar historial");
                System.out.println("4. Consultar Saldo");
                System.out.println("5. Salir");
                System.out.println("Ingrese su opción: ");
                int opcion = sc.nextInt();
            
            switch (opcion) {
                case 1 -> realizarDeposito(cuenta);
                case 2 -> realizarExtraccion(cuenta);
                case 3 -> mostrarHistorial(cuenta);
                case 4 -> consultarSaldo(cuenta);
                case 5 -> {
                    System.out.println("Gracias por utilizar el Cajero UNLAR");
                    salir = true;
                }
                default -> System.out.println("Opción no valida.");
                }}
                catch (InputMismatchException e) {
                    System.out.println("Ingrese un número válido.");
                    sc.next(); // para limpiar el buffer 
                }
            }
        }

        // funciones 
        private void realizarDeposito(CuentaBancaria cuenta) {
            try{
                System.out.println("Ingrese el monto a depositar: ");
                double monto = sc.nextDouble();
                service.depositar(cuenta, monto);
                System.out.println("Depósito realizado con éxito. Nuevo saldo: "+ cuenta.getSaldo());
            }
            catch (CuentaInactivaException e) {
                System.err.println(e.getMessage());
            } catch (InputMismatchException e) {
                System.out.println("Ingrese un número válido.");
                sc.next(); // para limpiar el buffer 
            }
        }

        private void realizarExtraccion(CuentaBancaria cuenta) {
            try{
                System.out.println("Ingrese el monto a extraer: ");
                double monto = sc.nextDouble();
                service.extraer(cuenta, monto);
                System.out.println("Extracción realizada con éxito. Nuevo saldo: "+ cuenta.getSaldo());
            }
            catch (Exception e) { // capturamos todas las excepciones
                System.err.println(e.getMessage());
            }
        }
        private void consultarSaldo(CuentaBancaria cuenta) {
            System.out.println("Su saldo es: "+ cuenta.getSaldo());
        }
        private void mostrarHistorial(CuentaBancaria cuenta) {
            System.out.println("Historial de transacciones:");
            for (Transaccion transaccion : cuenta.getHistorialTransacciones()) {
                System.out.println(transaccion);
                System.out.println("-----------------");
            }
        }

    }  

