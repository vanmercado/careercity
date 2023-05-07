/**
 * 
 */
package com.telusinternational.careercity.model.tm;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import com.telusinternational.careercity.enums.Gender;

/**
 * @author A.C.Estrada Jr. x209486
 * @date Apr 5, 2020
 */
@Entity
@Table(name = "tm_personal_details")
public class TMPersonalDetails {

    @Id
    @Column(name = "tm_personal_details_id")
    private Integer tmPersonalDetailsId;

    private String firstName;

    private String lastName;

    private String suffix;

    private String secondName;

    private String address;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    private String cellphoneNumber;

    private String remarks;

    @CreationTimestamp
    private Timestamp dateCreated;

    public TMPersonalDetails() {

        super();

    }

    public TMPersonalDetails(Integer tmPersonalDetailsId, String firstName, String lastName, String suffix,
            String secondName, String address, Gender gender, String cellphoneNumber, String remarks,
            Timestamp dateCreated) {

        super();
        this.tmPersonalDetailsId = tmPersonalDetailsId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.suffix = suffix;
        this.secondName = secondName;
        this.address = address;
        this.gender = gender;
        this.cellphoneNumber = cellphoneNumber;
        this.remarks = remarks;
        this.dateCreated = dateCreated;

    }

    public Integer getTmPersonalDetailsId() {

        return tmPersonalDetailsId;

    }

    public void setTmPersonalDetailsId(Integer tmPersonalDetailsId) {

        this.tmPersonalDetailsId = tmPersonalDetailsId;

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

    public String getSuffix() {

        return suffix;

    }

    public void setSuffix(String suffix) {

        this.suffix = suffix;

    }

    public String getSecondName() {

        return secondName;

    }

    public void setSecondName(String secondName) {

        this.secondName = secondName;

    }

    public String getAddress() {

        return address;

    }

    public void setAddress(String address) {

        this.address = address;

    }

    public Gender getGender() {

        return gender;

    }

    public void setGender(Gender gender) {

        this.gender = gender;

    }

    public String getCellphoneNumber() {

        return cellphoneNumber;

    }

    public void setCellphoneNumber(String cellphoneNumber) {

        this.cellphoneNumber = cellphoneNumber;

    }

    public String getRemarks() {

        return remarks;

    }

    public void setRemarks(String remarks) {

        this.remarks = remarks;

    }

    public Timestamp getDateCreated() {

        return dateCreated;

    }

    public void setDateCreated(Timestamp dateCreated) {

        this.dateCreated = dateCreated;

    }

    @Override
    public String toString() {

        return "TMPersonalDetails [tmPersonalDetailsId=" + tmPersonalDetailsId + ", firstName=" + firstName
                + ", lastName=" + lastName + ", suffix=" + suffix + ", secondName=" + secondName + ", address="
                + address + ", gender=" + gender + ", cellphoneNumber=" + cellphoneNumber + ", remarks=" + remarks
                + ", dateCreated=" + dateCreated + "]";

    }

}
