package com.telusinternational.careercity.model;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.validator.constraints.Length;

import com.telusinternational.careercity.model.tm.TMJobDetails;

@Entity
@Table(name = "users")
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer USER_ID;

    @NotEmpty(message = "This field is required")
    @Length(max = 20, message = "Maximum of 20 characters is allowed")
    @Column(name = "USER_FNAME", nullable = false)
    private String firstname;

    @NotEmpty(message = "This field is required")
    @Length(max = 20, message = "Maximum of 20 characters is allowed")
    @Column(name = "USER_LNAME", nullable = false)
    private String lastname;

    @NotEmpty(message = "This field is required")
    @Email(message = "Please provide a valid email address format")
    @Column(name = "EMAIL", nullable = false, unique = true)
    private String email;

    @NotEmpty(message = "This field is required")
    @Length(min = 3, max = 8, message = "Must be between 6 to 8 characters")
    @Column(name = "USER_EMPID", nullable = false, unique = true)
    private String username;

    @NotEmpty(message = "This field is required")
    //@Length(min = 8, max = 16, message = "Password must be between 8 to 16 characters") //having conflict with pw enconder
    @Column(name = "PASSWORD", nullable = false)
    private String PASSWORD;

    @NotEmpty(message = "This field is required")
    @Column(name = "USER_GROUP", nullable = false)
    private String USER_GROUP;

    @Column(name = "created_by", nullable = false, updatable = false)
    private String created_by;

    @Column(name = "USER_STATUS", nullable = false)
    private String USER_STATUS;

    @CreationTimestamp
    @Column(name = "Date_Created", nullable = false, updatable = false)
    private Date dateCreated;

    @UpdateTimestamp
    @Column(name = "Date_Modified", nullable = false)
    private Date lastModifiedDate;

    @Column(name = "modified_by", nullable = false)
    private String lastModifiedBy;

    @NotEmpty(message = "This field is required")
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "users_role", joinColumns = @JoinColumn(name = "USER_ID"), inverseJoinColumns = @JoinColumn(name = "ROLE_ID"))
    private Set<Role> roles;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "tm_job_details")
    private TMJobDetails tmJobDetails;

    public Integer getUSER_ID() {

        return USER_ID;

    }

    public void setUSER_ID(Integer uSER_ID) {

        USER_ID = uSER_ID;

    }

    public String getFirstname() {

        return firstname;

    }

    public void setFirstname(String firstname) {

        this.firstname = firstname;

    }

    public String getLastname() {

        return lastname;

    }

    public void setLastname(String lastname) {

        this.lastname = lastname;

    }

    public String getEmail() {

        return email;

    }

    public void setEmail(String email) {

        this.email = email;

    }

    public String getUsername() {

        return username;

    }

    public void setUsername(String username) {

        this.username = username;

    }

    public String getPASSWORD() {

        return PASSWORD;

    }

    public void setPASSWORD(String pASSWORD) {

        PASSWORD = pASSWORD;

    }

    public String getUSER_GROUP() {

        return USER_GROUP;

    }

    public void setUSER_GROUP(String uSER_GROUP) {

        USER_GROUP = uSER_GROUP;

    }

    public String getCreated_by() {

        return created_by;

    }

    public void setCreated_by(String created_by) {

        this.created_by = created_by;

    }

    public String getUSER_STATUS() {

        return USER_STATUS;

    }

    public void setUSER_STATUS(String uSER_STATUS) {

        USER_STATUS = uSER_STATUS;

    }

    public Date getDateCreated() {

        return dateCreated;

    }

    public void setDateCreated(Date dateCreated) {

        this.dateCreated = dateCreated;

    }

    public Date getLastModifiedDate() {

        return lastModifiedDate;

    }

    public void setLastModifiedDate(Date lastModifiedDate) {

        this.lastModifiedDate = lastModifiedDate;

    }

    public String getLastModifiedBy() {

        return lastModifiedBy;

    }

    public void setLastModifiedBy(String lastModifiedBy) {

        this.lastModifiedBy = lastModifiedBy;

    }

    public Set<Role> getRoles() {

        return roles;

    }

    public void setRoles(Set<Role> roles) {

        this.roles = roles;

    }

    /**
     * @return the tmJobDetails
     */
    public TMJobDetails getTmJobDetails() {

        return tmJobDetails;

    }

    /**
     * @param tmJobDetails the tmJobDetails to set
     */
    public void setTmJobDetails(TMJobDetails tmJobDetails) {

        this.tmJobDetails = tmJobDetails;

    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {

        return "Users [USER_ID=" + USER_ID + ", firstname=" + firstname + ", lastname=" + lastname + ", email=" + email
                + ", username=" + username + ", PASSWORD=" + PASSWORD + ", USER_GROUP=" + USER_GROUP + ", created_by="
                + created_by + ", USER_STATUS=" + USER_STATUS + ", dateCreated=" + dateCreated + ", lastModifiedDate="
                + lastModifiedDate + ", lastModifiedBy=" + lastModifiedBy + ", roles=" + roles + ", tmJobDetails="
                + tmJobDetails + "]";

    }

}