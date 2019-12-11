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
public class RealEstateImageDTO implements Serializable {
    private int realEstateImagesID;
    private String ImageURL;
    private int realEstateID;

    public RealEstateImageDTO() {
    }

    public RealEstateImageDTO(int realEstateImagesID, String ImageURL, int realEstateID) {
        this.realEstateImagesID = realEstateImagesID;
        this.ImageURL = ImageURL;
        this.realEstateID = realEstateID;
    }

    public int getRealEstateImagesID() {
        return realEstateImagesID;
    }

    public void setRealEstateImagesID(int realEstateImagesID) {
        this.realEstateImagesID = realEstateImagesID;
    }

    public String getImageURL() {
        return ImageURL;
    }

    public void setImageURL(String ImageURL) {
        this.ImageURL = ImageURL;
    }

    public int getRealEstateID() {
        return realEstateID;
    }

    public void setRealEstateID(int realEstateID) {
        this.realEstateID = realEstateID;
    }

}
