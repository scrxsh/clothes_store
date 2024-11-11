package com.example.tienda_ropa.services;

import com.example.tienda_ropa.models.VentaModel;
import com.example.tienda_ropa.repositories.VentaRepository;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanArrayDataSource;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class FacturacionService {
    @Autowired
    VentaRepository ventaRepository;

    public byte[] exportarFacturaVenta(Long id) throws JRException {
        try {

            VentaModel venta = ventaRepository.findById(id).orElseThrow((null));
            List<VentaModel> ventas = new ArrayList<>();
            ventas.add(venta);
            InputStream reportStream = getClass().getResourceAsStream("/reports/factura_venta.jasper");

            JasperReport jasperReport = (JasperReport) JRLoader.loadObject(reportStream);
            JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(ventas);
            JRBeanArrayDataSource ds = new JRBeanArrayDataSource(ventas.toArray());

            Map<String, Object> parameters = new HashMap<>();
            parameters.put("ds", ds);

            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);

            return JasperExportManager.exportReportToPdf(jasperPrint);

        } catch (Exception e) {
            throw new JRException("Error al generar el reporte de la venta: " + e.getMessage());
        }
    }
}
