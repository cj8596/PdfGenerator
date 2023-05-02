package com.cristin.project.pdfgen.Service;

import com.cristin.project.pdfgen.Model.DocumentDto;
import com.cristin.project.pdfgen.Model.Item;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.layout.property.VerticalAlignment;

import java.io.File;

@Slf4j
@Service
public class PdfService {

    @Value("${document.path}")
    private String docPath;

    public String generatePdf(DocumentDto documentDto) {
        log.info("PdfService - generatePdf() - Request Received : {}", documentDto);
        String path = null;
        try {
            path = docPath +"Invoice.pdf";
            setaccessToFile(path);
            PdfWriter writer = new PdfWriter(path);
            PdfDocument pdfDoc = new PdfDocument(writer);
            pdfDoc.addNewPage();
            Document document = new Document(pdfDoc);
            float[] pointColumnWidths = {100F, 100F, 100F, 100F, 100F, 100F, 100F, 100F, 100F, 100F};
            Table table = new Table(pointColumnWidths);
            Cell address = new Cell(3, 5);
            address.add("Seller: ").add(documentDto.getSeller()).add(documentDto.getSellerAddress()).add("GSTIN: ").add(documentDto.getSellerGstin());
            Cell address2 = new Cell(3, 5);
            address2.add("Buyer: ").add(documentDto.getBuyer()).add(documentDto.getSellerAddress()).add("GSTIN: ").add(documentDto.getSellerGstin());
            table.addCell(address).setFontSize(10F).setBold();
            table.addCell(address2).setFontSize(10F).setBold();
            table.addCell(new Cell(1, 4).add("Item").setHeight(15F).setTextAlignment(TextAlignment.CENTER).setVerticalAlignment(VerticalAlignment.MIDDLE).setFontSize(10F).setBold());
            table.addCell(new Cell(1, 2).add("Quantity").setHeight(15F).setTextAlignment(TextAlignment.CENTER).setVerticalAlignment(VerticalAlignment.MIDDLE).setBold().setFontSize(10F));
            table.addCell(new Cell(1, 2).add("Rate").setHeight(15F).setTextAlignment(TextAlignment.CENTER).setVerticalAlignment(VerticalAlignment.MIDDLE).setFontSize(10F));
            table.addCell(new Cell(1, 2).add("Amount").setHeight(15F).setTextAlignment(TextAlignment.CENTER).setVerticalAlignment(VerticalAlignment.MIDDLE).setFontSize(10F));
            for (Item item : documentDto.getItems()) {
                table.addCell(new Cell(1, 4).add(item.getName()).setHeight(15F).setTextAlignment(TextAlignment.CENTER).setVerticalAlignment(VerticalAlignment.MIDDLE).setFontSize(10F).setBold());
                table.addCell(new Cell(1, 2).add(item.getQuantity()).setHeight(15F).setTextAlignment(TextAlignment.CENTER).setVerticalAlignment(VerticalAlignment.MIDDLE).setBold().setFontSize(10F));
                table.addCell(new Cell(1, 2).add(String.valueOf(item.getRate())).setHeight(15F).setTextAlignment(TextAlignment.CENTER).setVerticalAlignment(VerticalAlignment.MIDDLE).setFontSize(10F));
                table.addCell(new Cell(1, 2).add(String.valueOf(item.getAmount())).setHeight(15F).setTextAlignment(TextAlignment.CENTER).setVerticalAlignment(VerticalAlignment.MIDDLE).setFontSize(10F));
            }
            table.addCell(new Cell(1, 10).add(" ").setHeight(75F).setTextAlignment(TextAlignment.CENTER).setVerticalAlignment(VerticalAlignment.MIDDLE).setFontSize(10F));
            document.add(table);
            document.close();
            log.info("PdfService - generatePdf() - PDF has been created");
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return "Please Check " + path + " for your pdf";
    }


    public void setaccessToFile(String path) {
        File file = new File(path);
        file.setReadable(true, false);
        file.setExecutable(true, false);
        file.setWritable(true, false);
    }
}
