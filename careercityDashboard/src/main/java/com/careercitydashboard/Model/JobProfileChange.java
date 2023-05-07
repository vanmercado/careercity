package com.careercitydashboard.Model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "job_profile_change")
public class JobProfileChange {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String workdayId;
    private String firstName;
    private String lastName;
    private String prevJobProfile;
    private String newJobProfile;
    private String workType;
    private String method;
    private Date dateUsed;

    public JobProfileChange() {

    }

    public JobProfileChange(Integer id, String workdayId, String firstName, String lastName, String prevJobProfile,
            String newJobProfile, String workType, String method, Date dateUsed) {

        this.id = id;
        this.workdayId = workdayId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.prevJobProfile = prevJobProfile;
        this.newJobProfile = newJobProfile;
        this.workType = workType;
        this.method = method;
        this.dateUsed = dateUsed;

    }

    public Integer getId() {

        return id;

    }

    public void setId(Integer id) {

        this.id = id;

    }

    public String getWorkdayId() {

        return workdayId;

    }

    public void setWorkdayId(String workdayId) {

        this.workdayId = workdayId;

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

    public String getPrevJobProfile() {

        return prevJobProfile;

    }

    public void setPrevJobProfile(String prevJobProfile) {

        this.prevJobProfile = prevJobProfile;

    }

    public String getNewJobProfile() {

        return newJobProfile;

    }

    public void setNewJobProfile(String newJobProfile) {

        this.newJobProfile = newJobProfile;

    }

    public String getWorkType() {

        return workType;

    }

    public void setWorkType(String workType) {

        this.workType = workType;

    }

    public String getMethod() {

        return method;

    }

    public void setMethod(String method) {

        this.method = method;

    }

    public Date getDateUsed() {

        return dateUsed;

    }

    public void setDateUsed(Date dateUsed) {

        this.dateUsed = dateUsed;

    }

    @Override
    public String toString() {

        return "JobProfileChange [id=" + id + ", workdayId=" + workdayId + ", firstName=" + firstName + ", lastName="
                + lastName + ", prevJobProfile=" + prevJobProfile + ", newJobProfile=" + newJobProfile + ", workType="
                + workType + ", method=" + method + ", dateUsed=" + dateUsed + "]";

    }

}
