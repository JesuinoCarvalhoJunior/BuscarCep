package com.jcarvalhojr.buscarcep.Domain;


import com.jcarvalhojr.buscarcep.Helpers.Mask;

/**
 * Criado por JcarvalhoJr em 27/06/2018.
 */


public class Cep {

    private String cep;
    private String address_type;
    private String address_name;
    private String address;
    private String district;
    private String city;
    private String state;
    private String lat;
    private String lng;
    private String ddd;
    private String city_ibge;


    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getAddress_type() {
        return address_type;
    }

    public void setAddress_type(String address_type) {
        this.address_type = address_type;
    }

    public String getAddress_name() {
        return address_name;
    }

    public void setAddress_name(String address_name) {
        this.address_name = address_name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    public String getDdd() {
        return ddd;
    }

    public void setDdd(String ddd) {
        this.ddd = ddd;
    }

    public String getCity_ibge() {
        return city_ibge;
    }

    public void setCity_ibge(String city_ibge) {
        this.city_ibge = city_ibge;
    }

    @Override
    public String toString() {
        return
                "Cep:" + getCep()
                        + "\nTipo: " + getAddress_type()
                        + "\nEndereço:" + getAddress_name()
                        + "\nEnd. Completo: " + getAddress()
                        + "\nBairro: " + getDistrict()
                        + "\nCidade: " + getCity()
                        + "\nEstado: " + getState()
                        + "\nLat: " + getLat()
                        + "\nLng: " + getLng()
                        + "\nDDD: " + getDdd()
                        + "\nIBGE: " + getCity_ibge();
    }

}
