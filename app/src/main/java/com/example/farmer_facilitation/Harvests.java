package com.example.farmer_facilitation;

public class Harvests {
    String date,harvest,quantity,Uprice;
    public Harvests(String date, String harvest, String quantity, String Uprice)
    {
        this.date=date;
        this.harvest=harvest;
        this.quantity=quantity;
        this.Uprice=Uprice;
    }

    public String getDate() {
        return date;
    }

    public String getHarvest() {
        return harvest;
    }

    public String getQuantity() {
        return quantity;
    }

    public String getUprice() {
        return Uprice;
    }
}
