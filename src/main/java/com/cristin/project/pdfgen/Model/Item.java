package com.cristin.project.pdfgen.Model;

import lombok.Data;

@Data
public class Item {
    public String name;
    public String quantity;
    public double rate;
    public double amount;
}