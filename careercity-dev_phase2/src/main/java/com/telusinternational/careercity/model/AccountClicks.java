package com.telusinternational.careercity.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "account_clicks")
public class AccountClicks {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ID;

    private Integer ACCOUNT_ID;

    private String CATEGORY;

    @OneToOne(fetch = FetchType.LAZY, targetEntity = Users.class)
    @JoinColumn(name = "USER_ID", columnDefinition = "USER_ID")
    private Users user;

    /**
     * @param aCCOUNT_ID
     * @param cATEGORY
     * @param user
     */
    public AccountClicks(Integer aCCOUNT_ID, String cATEGORY, Users user) {

        super();
        ACCOUNT_ID = aCCOUNT_ID;
        CATEGORY = cATEGORY;
        this.user = user;

    }

    /**
     * 
     */
    public AccountClicks() {

        super();

    }

    /**
     * @return the iD
     */
    public Integer getID() {

        return ID;

    }

    /**
     * @param iD the iD to set
     */
    public void setID(Integer iD) {

        ID = iD;

    }

    /**
     * @return the aCCOUNT_ID
     */
    public Integer getACCOUNT_ID() {

        return ACCOUNT_ID;

    }

    /**
     * @param aCCOUNT_ID the aCCOUNT_ID to set
     */
    public void setACCOUNT_ID(Integer aCCOUNT_ID) {

        ACCOUNT_ID = aCCOUNT_ID;

    }

    /**
     * @return the cATEGORY
     */
    public String getCATEGORY() {

        return CATEGORY;

    }

    /**
     * @param cATEGORY the cATEGORY to set
     */
    public void setCATEGORY(String cATEGORY) {

        CATEGORY = cATEGORY;

    }

    /**
     * @return the user
     */
    public Users getUser() {

        return user;

    }

    /**
     * @param user the user to set
     */
    public void setUser(Users user) {

        this.user = user;

    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {

        return "AccountClicks [ID=" + ID + ", ACCOUNT_ID=" + ACCOUNT_ID + ", CATEGORY=" + CATEGORY + ", user=" + user
                + "]";

    }

}
