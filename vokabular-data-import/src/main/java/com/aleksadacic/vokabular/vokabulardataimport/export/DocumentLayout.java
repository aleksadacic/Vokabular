package com.aleksadacic.vokabular.vokabulardataimport.export;

import java.util.List;
import java.util.stream.Collectors;

public class DocumentLayout {

    //TODO da se ucita iz fajla pa da mozemo da kontrolisemo preko fajla
    private String htmlReport = """
            <html lang="en">
            <head>
                <meta charset="UTF-8"/>
                <meta name="viewport"
                      content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0"/>
                <meta http-equiv="X-UA-Compatible" content="ie=edge"/>
                <title><$title$></title>
            </head>
            <body>
            <h2><$title$></h2>
            <table>
                <thead style="font-weight: bold">
                <$columns$>
                </thead>
                <tbody>
                <$rows$>
                </tbody>
            </table>
                        
            </body>
            </html>                      
            """;

    public void replaceTitle(String value) {
        htmlReport = htmlReport.replace("<$title$>", value);
    }

    public void setColumns(List<String> values) {
        String thead = values.stream().map(e -> "<td>" + e + "</td>").collect(Collectors.joining("\n", "<tr>", "</tr>"));
        htmlReport = htmlReport.replace("<$columns$>", thead);
    }

    public void setRows(List<List<String>> values) {
        StringBuilder tr = new StringBuilder();
        for (List<String> row : values) {
            String td = row.stream().map(e -> "<td>" + e + "</td>").collect(Collectors.joining("\n", "<tr>", "</tr>"));
            tr.append(td);
        }
        htmlReport = htmlReport.replace("<$rows$>", tr.toString());
    }

    public String getHtmlReport() {
        return htmlReport;
    }
}
