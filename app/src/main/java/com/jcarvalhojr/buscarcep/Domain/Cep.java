package com.jcarvalhojr.buscarcep.Domain;

/**
 * Criado por JcarvalhoJr em 27/06/2018.
 */


public class Cep {


    private String Cep;
    private String Address_Type;
    private String Address_Name;
    private String Address;
    private String District;
    private String City;
    private String State;
    private String Lat;
    private String Lng;
    private String Ddd;
    private String City_ibge;


    public String getCep() {
        return Cep;
    }

    public void setCep(String cep) {
        Cep = cep;
    }

    public String getAddress_Type() {
        return Address_Type;
    }

    public void setAddress_Type(String address_Type) {
        Address_Type = address_Type;
    }

    public String getAddress_Name() {
        return Address_Name;
    }

    public void setAddress_Name(String address_Name) {
        Address_Name = address_Name;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getDistrict() {
        return District;
    }

    public void setDistrict(String district) {
        District = district;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String city) {
        City = city;
    }

    public String getState() {
        return State;
    }

    public void setState(String state) {
        State = state;
    }

    public String getLat() {
        return Lat;
    }

    public void setLat(String lat) {
        Lat = lat;
    }

    public String getLng() {
        return Lng;
    }

    public void setLng(String lng) {
        Lng = lng;
    }

    public String getDdd() {
        return Ddd;
    }

    public void setDdd(String ddd) {
        Ddd = ddd;
    }

    public String getCity_ibge() {
        return City_ibge;
    }

    public void setCity_ibge(String city_ibge) {
        City_ibge = city_ibge;
    }

    @Override
    public String toString() {
        return
                "CEP:" + getCep()
                        + "\nAddress_Type:" + getAddress_Type()
                        + "\nAddress_Name:" + getAddress_Name()
                        + "\nAddress:" + getAddress()
                        + "\nDistrict:" + getDistrict()
                        + "\nCity:" + getCity()
                        + "\nState:" + getState()
                        + "\n Lat:" + getLat()
                        + "\n Lng:" + getLng()
                        + "\nDdd:" + getDdd()
                        + "\nCity_ibge:" + getCity_ibge();

    }
}
