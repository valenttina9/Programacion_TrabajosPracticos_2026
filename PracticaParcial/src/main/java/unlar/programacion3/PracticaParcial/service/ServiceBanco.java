package unlar.programacion3.PracticaParcial.service;

import java.util.stream.Collectors;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;
import unlar.programacion3.PracticaParcial.model.Cuenta;
import unlar.programacion3.PracticaParcial.model.CuentaCorriente;
import unlar.programacion3.PracticaParcial.model.Sucursales;
import unlar.programacion3.PracticaParcial.model.CajadeAhorro;
import unlar.programacion3.PracticaParcial.model.HistorialMovimientos;
import unlar.programacion3.PracticaParcial.model.Cliente;

import org.springframework.stereotype.Service;

import jakarta.annotation.PostConstruct;

@Service
public class ServiceBanco {
    private List<Cuenta> cuentas = new ArrayList<>();
    private List<Cliente> clientes = new ArrayList<>();
    private List<Sucursales> sucursales = new ArrayList<>();

    @PostConstruct
    public void cargarDatosIniciales(){

        // 1. crear datos del banco
        Sucursales sucursal1=new Sucursales();
        sucursal1.setDireccion("La Rioja");
        sucursal1.setNombre("Banco Rioja");
        sucursal1.setId("sucursal1");

        Sucursales sucursal2=new Sucursales();
        sucursal2.setDireccion("La Rioja");
        sucursal2.setNombre("Banco Nacion");
        sucursal2.setId("sucursal2");

        sucursales.add(sucursal1);
        sucursales.add(sucursal2);

        // 2. Crear clientes

        Cliente cliente1=new Cliente();
        cliente1.setNombre("Juan");
        cliente1.setEmail("juan@example.com");
        cliente1.setDni("12345678");
        cliente1.setSucursal(sucursal1);
        clientes.add(cliente1);

        Cliente cliente2=new Cliente();
        cliente2.setNombre("María");
        cliente2.setEmail("maria@example.com");
        cliente2.setDni("98765432");
        cliente2.setSucursal(sucursal2);
        clientes.add(cliente2);

        Cliente cliente3=new Cliente();
        cliente3.setNombre("Pedro");
        cliente3.setEmail("pedro@example.com");
        cliente3.setDni("87654321");
        cliente3.setSucursal(sucursal1);
        clientes.add(cliente3);

        // 3. Crear cuentas

        CuentaCorriente cuenta1=new CuentaCorriente();
        cuenta1.setCbu("1234567890");
        cuenta1.setSaldo(1000);
        cuenta1.setTasaInteres(0.05);
        cuenta1.setCliente(cliente1);
        cuentas.add(cuenta1);

        CajadeAhorro cuenta2=new CajadeAhorro();
        cuenta2.setCbu("2345678901");
        cuenta2.setSaldo(2000);
        cuenta2.setLimiteDescubierto(1000);
        cuenta2.setCliente(cliente2);   
        cuentas.add(cuenta2);

        CuentaCorriente cuenta3=new CuentaCorriente();
        cuenta3.setCbu("3456789012");
        cuenta3.setSaldo(3000);
        cuenta3.setTasaInteres(0.05);
        cuenta3.setCliente(cliente3);
        cuentas.add(cuenta3);

        // 4. Simular movimientos en dos cuentas
        cuenta1.getHistorialMovimientos().add(new HistorialMovimientos(LocalDate.now(), 1000, "Depósito"));
        cuenta2.getHistorialMovimientos().add(new HistorialMovimientos(LocalDate.now(), 500, "Retiro"));
        cuenta2.getHistorialMovimientos().add(new HistorialMovimientos(LocalDate.now(), 2000, "Depósito"));
        cuenta2.getHistorialMovimientos().add(new HistorialMovimientos(LocalDate.now(), 1000, "Depósito"));

    }
     public List<Cliente> ClientesPorSucursal(String idSucursal){
        return clientes.stream()
                .filter(cliente -> cliente.getSucursal().getId().equals(idSucursal))
                .collect(Collectors.toList()); // convertir a lista
     }

     public List<Cuenta> CuentaPorCliente(String dni){
        return cuentas.stream()
                .filter(cuenta -> cuenta.getCliente().getDni().equals(dni))
                .collect(Collectors.toList());
     }

     public List<HistorialMovimientos> HistorialMovimientosPorCuenta(String cbu){
        return cuentas.stream()
                .filter(cuenta -> cuenta.getCbu().equals(cbu))
                .findFirst()
                .map(Cuenta::getHistorialMovimientos)
                .orElse(null);
     }
     public List<Cuenta> getListaCuentas(){
        return cuentas;
     }

}
