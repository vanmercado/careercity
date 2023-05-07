/**
 * 
 */
package com.careercitydashboard.Model;

import java.util.Date;
import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import com.careercitydashboard.enums.Gender;

/**
 * @author A.C.Estrada Jr. x209486
 * @date Apr 5, 2020
 */
@Entity
@Table(name = "tm_personal_details")
public class TMPersonalDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer tmPersonalDetailsId;

    private String firstName;

    private String lastName;

    private String suffix;

    private String secondName;

    private String address;

    private Integer age;

    private String ageGroup;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    private String cellphoneNumber;

    private String remarks;

    private Date birthdate;
    @CreationTimestamp
    private Timestamp dateCreated;

    public TMPersonalDetails() {

    }

    /**
     * @return the tmPersonalDetailsId
     */
    public Integer getTmPersonalDetailsId() {

        return tmPersonalDetailsId;

    }

    /**
     * @param tmPersonalDetailsId the tmPersonalDetailsId to set
     */
    public void setTmPersonalDetailsId(Integer tmPersonalDetailsId) {

        this.tmPersonalDetailsId = tmPersonalDetailsId;

    }

    /**
     * @return the firstName
     */
    public String getFirstName() {

        return firstName;

    }

    /**
     * @param firstName the firstName to set
     */
    public void setFirstName(String firstName) {

        this.firstName = firstName;

    }

    /**
     * @return the lastName
     */
    public String getLastName() {

        return lastName;

    }

    /**
     * @param lastName the lastName to set
     */
    public void setLastName(String lastName) {

        this.lastName = lastName;

    }

    /**
     * @return the suffix
     */
    public String getSuffix() {

        return suffix;

    }

    /**
     * @param suffix the suffix to set
     */
    public void setSuffix(String suffix) {

        this.suffix = suffix;

    }

    /**
     * @return the secondName
     */
    public String getSecondName() {

        return secondName;

    }

    /**
     * @param secondName the secondName to set
     */
    public void setSecondName(String secondName) {

        this.secondName = secondName;

    }

    /**
     * @return the address
     */
    public String getAddress() {

        return address;

    }

    /**
     * @param address the address to set
     */
    public void setAddress(String address) {

        this.address = address;

    }

    /**
     * @return the age
     */
    public Integer getAge() {

        return age;

    }

    /**
     * @param age the age to set
     */
    public void setAge(Integer age) {

        this.age = age;

    }

    /**
     * @return the gender
     */
    public Gender getGender() {

        return gender;

    }

    /**
     * @param gender the gender to set
     */
    public void setGender(Gender gender) {

        this.gender = gender;

    }

    /**
     * @return the cellphoneNumber
     */
    public String getCellphoneNumber() {

        return cellphoneNumber;

    }

    /**
     * @param cellphoneNumber the cellphoneNumber to set
     */
    public void setCellphoneNumber(String cellphoneNumber) {

        this.cellphoneNumber = cellphoneNumber;

    }

    /**
     * @return the remarks
     */
    public String getRemarks() {

        return remarks;

    }

    /**
     * @param remarks the remarks to set
     */
    public void setRemarks(String remarks) {

        this.remarks = remarks;

    }

    /**
     * @return the birthdate
     */
    public Date getBirthdate() {

        return birthdate;

    }

    /**
     * @param birthdate the birthdate to set
     */
    public void setBirthdate(Date birthdate) {

        this.birthdate = birthdate;

    }

    /**
     * @return the dateCreated
     */
    public Timestamp getDateCreated() {

        return dateCreated;

    }

    /**
     * @param dateCreated the dateCreated to set
     */
    public void setDateCreated(Timestamp dateCreated) {

        this.dateCreated = dateCreated;

    }

    public String getAgeGroup() {

        return ageGroup;

    }

    public void setAgeGroup(String ageGroup) {

        this.ageGroup = ageGroup;

    }

    @Override
    public String toString() {

        return "TMPersonalDetails [tmPersonalDetailsId=" + tmPersonalDetailsId + ", firstName=" + firstName
                + ", lastName=" + lastName + ", suffix=" + suffix + ", secondName=" + secondName + ", address="
                + address + ", age=" + age + ", gender=" + gender + ", ageGroup=" + ageGroup + ", cellphoneNumber="
                + cellphoneNumber + ", remarks=" + remarks + ", birthdate=" + birthdate + ", dateCreated=" + dateCreated
                + "]";

    }

}
