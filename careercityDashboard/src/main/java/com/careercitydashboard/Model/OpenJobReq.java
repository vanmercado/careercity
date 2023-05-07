package com.careercitydashboard.Model;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "open_job_req")
public class OpenJobReq {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String jobProfile;
    private String jobPostingTitle;
    private String jobReqId;
    private String functionalArea;
    private String location;
    private Date dateRequestEntered;

    public Integer getId() {

        return id;

    }

    public void setId(Integer id) {

        this.id = id;

    }

    public String getJobProfile() {

        return jobProfile;

    }

    public void setJobProfile(String jobProfile) {

        this.jobProfile = jobProfile;

    }

    public String getJobPostingTitle() {

        return jobPostingTitle;

    }

    public void setJobPostingTitle(String jobPostingTitle) {

        this.jobPostingTitle = jobPostingTitle;

    }

    public String getJobReqId() {

        return jobReqId;

    }

    public void setJobReqId(String jobReqId) {

        this.jobReqId = jobReqId;

    }

    public String getFunctionalArea() {

        return functionalArea;

    }

    public void setFunctionalArea(String functionalArea) {

        this.functionalArea = functionalArea;

    }

    public String getLocation() {

        return location;

    }

    public void setLocation(String location) {

        this.location = location;

    }

    public Date getDateRequestEntered() {

        return dateRequestEntered;

    }

    public void setDateRequestEntered(Date dateRequestEntered) {

        this.dateRequestEntered = dateRequestEntered;

    }

    @Override
    public String toString() {

        return "OpenJobReq [id=" + id + ", jobProfile=" + jobProfile + ", jobPostingTitle=" + jobPostingTitle
                + ", jobReqId=" + jobReqId + ", functionalArea=" + functionalArea + ", location=" + location
                + ", dateRequestEntered=" + dateRequestEntered + "]";

    }

}
