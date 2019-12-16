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
public class RealEstateErrorObject implements Serializable {
    private String titleError, descriptionError, priceError, areaError, addressError, categoryError, cityError, stateError;

    public RealEstateErrorObject() {
    }

    public String getTitleError() {
        return titleError;
    }

    public void setTitleError(String titleError) {
        this.titleError = titleError;
    }

    public String getDescriptionError() {
        return descriptionError;
    }

    public void setDescriptionError(String descriptionError) {
        this.descriptionError = descriptionError;
    }

    public String getPriceError() {
        return priceError;
    }

    public void setPriceError(String priceError) {
        this.priceError = priceError;
    }

    public String getAreaError() {
        return areaError;
    }

    public void setAreaError(String areaError) {
        this.areaError = areaError;
    }

    public String getAddressError() {
        return addressError;
    }

    public void setAddressError(String addressError) {
        this.addressError = addressError;
    }

    public String getCategoryError() {
        return categoryError;
    }

    public void setCategoryError(String categoryError) {
        this.categoryError = categoryError;
    }

    public String getCityError() {
        return cityError;
    }

    public void setCityError(String cityError) {
        this.cityError = cityError;
    }

    public String getStateError() {
        return stateError;
    }

    public void setStateError(String stateError) {
        this.stateError = stateError;
    }
}
