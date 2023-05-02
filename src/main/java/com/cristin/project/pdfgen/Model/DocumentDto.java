package com.cristin.project.pdfgen.Model;

import lombok.Data;

import java.util.List;

@Data
public class DocumentDto {
    public String seller;
    public String sellerGstin;
    public String sellerAddress;
    public String buyer;
    public String buyerGstin;
    public String buyerAddress;
    public List<Item> items;

}
