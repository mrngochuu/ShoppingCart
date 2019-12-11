/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huudn.dtos;

import java.io.Serializable;

/**
 *
 * @author ngochuu
 */
public class Order_RealEstateDTO implements Serializable {
    private int oderID;
    private int realEstateID;

    public Order_RealEstateDTO() {
    }

    public Order_RealEstateDTO(int oderID, int realEstateID) {
        this.oderID = oderID;
        this.realEstateID = realEstateID;
    }

    public int getOderID() {
        return oderID;
    }

    public void setOderID(int oderID) {
        this.oderID = oderID;
    }

    public int getRealEstateID() {
        return realEstateID;
    }

    public void setRealEstateID(int realEstateID) {
        this.realEstateID = realEstateID;
    }

}
