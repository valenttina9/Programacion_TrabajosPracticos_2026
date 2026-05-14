package unlar.programacion3.PracticaParcial.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import unlar.programacion3.PracticaParcial.model.Cliente;
import unlar.programacion3.PracticaParcial.model.Cuenta;
import unlar.programacion3.PracticaParcial.model.HistorialMovimientos;
import unlar.programacion3.PracticaParcial.service.ServiceBanco;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/banco")
public class BancoController {
    
    @Autowired // Inyectar el servicio ServiceBanco
    private ServiceBanco serviceBanco;

    @GetMapping("/sucursales/{idSucursal}/clientes")
    public List<Cliente> getClientes (@PathVariable String idSucursal) {
        return serviceBanco.ClientesPorSucursal(idSucursal);
    }

    @GetMapping("/clientes/{dni}/cuentas")
        public List<Cuenta> getCuentas(@PathVariable String dni){
            return serviceBanco.CuentaPorCliente(dni);
        }
    
    @GetMapping("/cuentas/{cbu}/movimientos")
        public List<HistorialMovimientos> getMovimientos(@PathVariable String cbu){
            return serviceBanco.HistorialMovimientosPorCuenta(cbu);
        }
    
    @GetMapping("/todas-las-cuentas")
    public List<Cuenta> getTodas() {
        return serviceBanco.getListaCuentas();
    }
}

    
