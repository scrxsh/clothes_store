package com.example.tienda_ropa.controlador;

import com.example.tienda_ropa.model.VentasModel;
import com.example.tienda_ropa.services.JasperReportService;
import com.example.tienda_ropa.services.VentasServices;
import jakarta.annotation.Resource;
import net.sf.jasperreports.engine.JRException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/ventas")
@CrossOrigin("*")
public class VentasControlador {

    @Autowired
    VentasServices ventasServices;
    @Autowired
    JasperReportService jasperReportService;

    @GetMapping("/todos")
    public List<VentasModel> obtenerTodo(){
        return ventasServices.obtenerTodosVentas();
    }

    @PostMapping("/crear")
    public VentasModel crear(@RequestBody VentasModel ventas, @RequestParam Long id_producto, @RequestParam Long id_cliente, @RequestParam Long id_empleado){
        return ventasServices.crearVentas(ventas,id_producto,id_cliente,id_empleado);
    }

    @PutMapping("/actualizar")
    public VentasModel actualizar(@RequestBody VentasModel ventas){
        return ventasServices.actualizarVentas(ventas);
    }

    @DeleteMapping("/borrar/{id}")
    public void borrar(@PathVariable Long id){
        ventasServices.borrarVentas(id);
    }


    @GetMapping("/export/pdf/{id}")
    public ResponseEntity<byte[]> exportPDFById(@PathVariable Long id) {
        try {
            byte[] reportContent = jasperReportService.exportVentaPDFById(id);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);
            headers.setContentDispositionFormData("filename", "venta_" + id + ".pdf");

            return ResponseEntity.ok()
                    .headers(headers)
                    .body(reportContent);

        } catch (JRException e) {
            return ResponseEntity.internalServerError().build();
        }
    }
}



