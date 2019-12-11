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
public class CityDTO implements Serializable {
    private int cityID;
    private String cityName;

    public CityDTO() {
    }

    public CityDTO(int cityID, String cityName) {
        this.cityID = cityID;
        this.cityName = cityName;
    }
    
    public int getCityID() {
        return cityID;
    }

    public void setCityID(int cityID) {
        this.cityID = cityID;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }
    
}
