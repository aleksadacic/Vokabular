package com.aleksadacic.vokabular.vokabulardataimport.export;

import com.aleksadacic.engine.dataimport.report.ImportReport;
import org.xhtmlrenderer.pdf.ITextRenderer;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ReportDocument {
    private ReportDocument() {
    }

    public static byte[] of(ImportReport report) {
        try {
            List<Map<String, Object>> data = report.toStructuredData();
            DocumentLayout layout = new DocumentLayout();
            layout.replaceTitle(report.getTitle());
            layout.setColumns(data.get(0).keySet().stream().toList());
            List<List<String>> rows = new ArrayList<>();
            for (Map<String, Object> row : data) {
                rows.add(row.values().stream().map(Object::toString).toList());
            }
            layout.setRows(rows);
            return toBytes(layout.getHtmlReport());
        } catch (IOException e) {
            return new byte[0];
        }
    }

    private static byte[] toBytes(String htmlReport) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try (baos) {
            ITextRenderer renderer = new ITextRenderer();
            renderer.setDocumentFromString(htmlReport);
            renderer.layout();
            renderer.createPDF(baos);
        }
        return baos.toByteArray();
    }
}
