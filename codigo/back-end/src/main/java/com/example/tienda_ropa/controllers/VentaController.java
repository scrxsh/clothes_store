package com.example.tienda_ropa.controllers;

import com.example.tienda_ropa.models.VentaModel;
import com.example.tienda_ropa.services.FacturacionService;
import com.example.tienda_ropa.services.VentaService;
import net.sf.jasperreports.engine.JRException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("api/ventas")
@CrossOrigin("*")
public class VentaController {

    @Autowired
    VentaService ventaService;
    @Autowired
    FacturacionService facturacionService;

    @GetMapping("/todos")
    public List<VentaModel> obtenerTodo(){
        return ventaService.obtenerTodosVentas();
    }

    @GetMapping("/info/{id}")
    public VentaModel obtenerPorId(@PathVariable Long id){
      return ventaService.obtenerVentaPorId(id);
    };
    @PostMapping("/crear")
    public VentaModel crear(@RequestBody VentaModel ventas, @RequestParam Long id_producto, @RequestParam Long id_cliente, @RequestParam Long id_empleado){
        return ventaService.crearVentas(ventas,id_producto,id_cliente,id_empleado);
    }

    @PutMapping("/actualizar")
    public VentaModel actualizar(@RequestBody VentaModel ventas){
        return ventaService.actualizarVentas(ventas);
    }

    @DeleteMapping("/borrar/{id}")
    public void borrar(@PathVariable Long id){
        ventaService.borrarVentas(id);
    }


    @GetMapping("/factura/pdf/{id}")
    public ResponseEntity<byte[]> exportPDF(@PathVariable Long id) {
        try {
            byte[] reportContent = facturacionService.exportarFacturaVenta(id);

            LocalDateTime fechaActual = LocalDateTime.now();
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);
            headers.setContentDispositionFormData("filename", "F0" + id + "-" + fechaActual + ".pdf");

            return ResponseEntity.ok().headers(headers).body(reportContent);

        } catch (JRException e) {
            return ResponseEntity.internalServerError().build();
        }
    }
}



