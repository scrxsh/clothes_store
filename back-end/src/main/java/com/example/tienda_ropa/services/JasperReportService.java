package com.example.tienda_ropa.services;

import com.example.tienda_ropa.model.VentasModel;
import com.example.tienda_ropa.repository.VentasRepository;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class JasperReportService {
    @Autowired
    private VentasRepository ventasRepository;

    public byte[] exportVentaPDFById(Long id) throws JRException {
        try {

            VentasModel venta = ventasRepository.findById(id).orElseThrow((null));
            List<VentasModel> ventas = new ArrayList<>();
            ventas.add(venta);
            InputStream reportStream = getClass().getResourceAsStream("/reports/ventas_report.jrxml");
            JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(ventas);
            JasperReport jasperReport = JasperCompileManager.compileReport(reportStream);

            Map<String, Object> parameters = new HashMap<>();
            parameters.put("ventaId", id);
            parameters.put("cliente", venta.getCliente().getNombre());
            parameters.put("empleado", venta.getEmpleado().getNombre());

            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);

            return JasperExportManager.exportReportToPdf(jasperPrint);

        } catch (Exception e) {
            e.printStackTrace();
            throw new JRException("Error al generar el reporte de la venta: " + e.getMessage());
        }
    }
}
