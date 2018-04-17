package models;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author kenny
 */
public class Clients {
    public int id;
    public int agentId;
    public String firstName;
    public String lastName;
    public int streetNumber;
    public String streetName;
    public String city;
    public String province;
    public String postalCode;
    public String telOffice;
    public String telCell;
    public String email;
    public String company;
    public String companyType;

    public Clients() {
    }
    
    
    public Clients(int id) {
        this.id = id;

    }

    public Clients(int agentId, String firstName, String lastName, int streetNumber ,String streetName, String city, String province, String postalCode, String telOffice, String telCell, String email, String company, String companyType) {
        this.agentId = agentId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.streetNumber = streetNumber;
        this.streetName = streetName;
        this.city = city;
        this.province = province;
        this.postalCode = postalCode;
        this.telOffice = telOffice;
        this.telCell = telCell;
        this.email = email;
        this.company = company;
        this.companyType = companyType;
    }

    public Clients(int id, int agentId, String firstName, String lastName, int streetNumber, String streetName, String city, String province, String postalCode, String telOffice, String telCell, String email, String company, String companyType) {
        this(agentId,firstName,lastName,streetNumber,streetName,city,province,postalCode,telOffice,telCell,email,company,companyType);
        this.id = id;
    }
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAgentId() {
        return agentId;
    }

    public void setAgentId(int agentId) {
        this.agentId = agentId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    
    public int getStreetNumber() {
        return streetNumber;
    }
    
    public void setStreetNumer (int streetNumber){
        this.streetNumber = streetNumber;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getTelOffice() {
        return telOffice;
    }

    public void setTelOffice(String telOffice) {
        this.telOffice = telOffice;
    }

    public String getTelCell() {
        return telCell;
    }

    public void setTelCell(String telCell) {
        this.telCell = telCell;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getCompanyType() {
        return companyType;
    }

    public void setCompanyType(String companyType) {
        this.companyType = companyType;
    }
    
}
