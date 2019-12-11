/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huudn.dtos;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author ngochuu
 */
public class RealEstateDTO implements Serializable {
    private int realEstateID, price;
    private String title, description, address;
    private float area;
    private boolean active;
    private int stateID;
    private Date postTime;
    private int categoryID;

    public RealEstateDTO() {
    }

    public RealEstateDTO(int realEstateID, int price, String title, String description, String address, float area, boolean active, int stateID, Date postTime, int categoryID) {
        this.realEstateID = realEstateID;
        this.price = price;
        this.title = title;
        this.description = description;
        this.address = address;
        this.area = area;
        this.active = active;
        this.stateID = stateID;
        this.postTime = postTime;
        this.categoryID = categoryID;
    }

    public int getRealEstateID() {
        return realEstateID;
    }

    public void setRealEstateID(int realEstateID) {
        this.realEstateID = realEstateID;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public float getArea() {
        return area;
    }

    public void setArea(float area) {
        this.area = area;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public int getStateID() {
        return stateID;
    }

    public void setStateID(int stateID) {
        this.stateID = stateID;
    }

    public Date getPostTime() {
        return postTime;
    }

    public void setPostTime(Date postTime) {
        this.postTime = postTime;
    }

    public int getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(int categoryID) {
        this.categoryID = categoryID;
    }

}
