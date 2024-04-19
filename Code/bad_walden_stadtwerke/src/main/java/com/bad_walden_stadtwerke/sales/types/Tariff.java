package com.bad_walden_stadtwerke.sales.types;

public class Tariff {
    private final int id;
    private final String name;
    private final String description;
    private final int price;
    private final String unit;
    private final String category;

    public Tariff(int id, String name, String description, int price, String unit, String category) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.unit = unit;
        this.category = category;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getPrice() {
        return price;
    }

    public String getUnit() {
        return unit;
    }

    public String getCategory() {
        return category;
    }
}
