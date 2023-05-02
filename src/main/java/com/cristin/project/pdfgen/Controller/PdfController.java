package com.cristin.project.pdfgen.Controller;


import com.cristin.project.pdfgen.Model.DocumentDto;
import com.cristin.project.pdfgen.Service.PdfService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/document")
public class PdfController {

    @Autowired
    PdfService pdfService;

    @GetMapping("/generate")
    public ResponseEntity<String> generatePdf(@RequestBody DocumentDto documentDto) {
        log.info("PdfController - generatePdf() - Generating Pdf from data received");
        return new ResponseEntity<>(pdfService.generatePdf(documentDto), HttpStatus.OK);
    }
}
