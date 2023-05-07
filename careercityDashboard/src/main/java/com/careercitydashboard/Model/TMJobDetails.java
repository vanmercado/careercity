/**
 * 
 */
package com.careercitydashboard.Model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

/**
 * @author A.C.Estrada Jr. x209486
 * @date Apr 5, 2020
 */
@Entity
@Table(name = "tm_job_details")
public class TMJobDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer tmJobDetailsId;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = TMPersonalDetails.class)
    @JoinColumn(name = "tm_personal_details_id")
    private TMPersonalDetails tmPersonalDetails;

    private String employeeId;

    private String primaryWorkEmail;

    private String networkLogin;

    private String location;

    private String jobTitle;

    private String jobProfileId;

    private String jobLevel;

    private Date hireDate;

    private String immediateSupervisorId;

    private String immediateSupervisorName;

    private String managerImmediateSupervisorId;

    private String managerImmediateSupervisorName;

    private String site;

    private String functionalAreaId;

    private String functionalAreaName;

    private String functionalAreaHierarchyId;

    private String functionalAreaHierarchyName;

    private String remarks;

    @CreationTimestamp
    private Date dateCreated;

    public TMJobDetails() {

        super();

    }

    public Integer getTmJobDetailsId() {

        return tmJobDetailsId;

    }

    public void setTmJobDetailsId(Integer tmJobDetailsId) {

        this.tmJobDetailsId = tmJobDetailsId;

    }

    public TMPersonalDetails getTmPersonalDetails() {

        return tmPersonalDetails;

    }

    public void setTmPersonalDetails(TMPersonalDetails tmPersonalDetails) {

        this.tmPersonalDetails = tmPersonalDetails;

    }

    public String getEmployeeId() {

        return employeeId;

    }

    public void setEmployeeId(String employeeId) {

        this.employeeId = employeeId;

    }

    public String getPrimaryWorkEmail() {

        return primaryWorkEmail;

    }

    public void setPrimaryWorkEmail(String primaryWorkEmail) {

        this.primaryWorkEmail = primaryWorkEmail;

    }

    public String getNetworkLogin() {

        return networkLogin;

    }

    public void setNetworkLogin(String networkLogin) {

        this.networkLogin = networkLogin;

    }

    public String getLocation() {

        return location;

    }

    public void setLocation(String location) {

        this.location = location;

    }

    public String getJobTitle() {

        return jobTitle;

    }

    public void setJobTitle(String jobTitle) {

        this.jobTitle = jobTitle;

    }

    public String getJobProfileId() {

        return jobProfileId;

    }

    public void setJobProfileId(String jobProfileId) {

        this.jobProfileId = jobProfileId;

    }

    public String getJobLevel() {

        return jobLevel;

    }

    public void setJobLevel(String jobLevel) {

        this.jobLevel = jobLevel;

    }

    public Date getHireDate() {

        return hireDate;

    }

    public void setHireDate(Date hireDate) {

        this.hireDate = hireDate;

    }

    public String getImmediateSupervisorId() {

        return immediateSupervisorId;

    }

    public void setImmediateSupervisorId(String immediateSupervisorId) {

        this.immediateSupervisorId = immediateSupervisorId;

    }

    public String getImmediateSupervisorName() {

        return immediateSupervisorName;

    }

    public void setImmediateSupervisorName(String immediateSupervisorName) {

        this.immediateSupervisorName = immediateSupervisorName;

    }

    public String getManagerImmediateSupervisorId() {

        return managerImmediateSupervisorId;

    }

    public void setManagerImmediateSupervisorId(String managerImmediateSupervisorId) {

        this.managerImmediateSupervisorId = managerImmediateSupervisorId;

    }

    public String getManagerImmediateSupervisorName() {

        return managerImmediateSupervisorName;

    }

    public void setManagerImmediateSupervisorName(String managerImmediateSupervisorName) {

        this.managerImmediateSupervisorName = managerImmediateSupervisorName;

    }

    public String getSite() {

        return site;

    }

    public void setSite(String site) {

        this.site = site;

    }

    public String getFunctionalAreaId() {

        return functionalAreaId;

    }

    public void setFunctionalAreaId(String functionalAreaId) {

        this.functionalAreaId = functionalAreaId;

    }

    public String getFunctionalAreaName() {

        return functionalAreaName;

    }

    public void setFunctionalAreaName(String functionalAreaName) {

        this.functionalAreaName = functionalAreaName;

    }

    public String getFunctionalAreaHierarchyId() {

        return functionalAreaHierarchyId;

    }

    public void setFunctionalAreaHierarchyId(String functionalAreaHierarchyId) {

        this.functionalAreaHierarchyId = functionalAreaHierarchyId;

    }

    public String getFunctionalAreaHierarchyName() {

        return functionalAreaHierarchyName;

    }

    public void setFunctionalAreaHierarchyName(String functionalAreaHierarchyName) {

        this.functionalAreaHierarchyName = functionalAreaHierarchyName;

    }

    public String getRemarks() {

        return remarks;

    }

    public void setRemarks(String remarks) {

        this.remarks = remarks;

    }

    public Date getDateCreated() {

        return dateCreated;

    }

    public void setDateCreated(Date dateCreated) {

        this.dateCreated = dateCreated;

    }

    @Override
    public String toString() {

        return "TMJobDetails [tmJobDetailsId=" + tmJobDetailsId + ", tmPersonalDetails=" + tmPersonalDetails
                + ", employeeId=" + employeeId + ", primaryWorkEmail=" + primaryWorkEmail + ", networkLogin="
                + networkLogin + ", location=" + location + ", jobTitle=" + jobTitle + ", jobProfileId=" + jobProfileId
                + ", jobLevel=" + jobLevel + ", hireDate=" + hireDate + ", immediateSupervisorId="
                + immediateSupervisorId + ", immediateSupervisorName=" + immediateSupervisorName
                + ", managerImmediateSupervisorId=" + managerImmediateSupervisorId + ", managerImmediateSupervisorName="
                + managerImmediateSupervisorName + ", site=" + site + ", functionalAreaId=" + functionalAreaId
                + ", functionalAreaName=" + functionalAreaName + ", functionalAreaHierarchyId="
                + functionalAreaHierarchyId + ", functionalAreaHierarchyName=" + functionalAreaHierarchyName
                + ", remarks=" + remarks + ", dateCreated=" + dateCreated + "]";

    }

}
