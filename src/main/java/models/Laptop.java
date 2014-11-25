package models;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by Мадина on 22.11.2014.
 */
@XmlRootElement
public class Laptop {
    private int id;
    private String brandName;
    private String processor;
    private double diagonal;
    private String ram;
    private String HDD;
    private String os;
    private String picture;

    public Laptop() {}

    public Laptop (int id,String brandName,String processor, double diagonal,
                   String ram, String HDD, String os, String picture) {
        this.id = id;
        this.brandName = brandName;
        this.processor = processor;
        this.diagonal = diagonal;
        this.ram = ram;
        this.HDD = HDD;
        this.os = os;
        this.picture = picture;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getProcessor() {
        return processor;
    }

    public void setProcessor(String processor) {
        this.processor = processor;
    }

    public double getDiagonal() {
        return diagonal;
    }

    public void setDiagonal(double diagonal) {
        this.diagonal = diagonal;
    }

    public String getHDD() {
        return HDD;
    }

    public void setHDD(String HDD) {
        this.HDD = HDD;
    }

    public String getOs() {
        return os;
    }

    public void setOs(String os) {
        this.os = os;
    }

    public String getRam() {
        return ram;
    }

    public void setRam(String ram) {
        this.ram = ram;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }
}
