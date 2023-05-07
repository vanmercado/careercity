package com.telusinternational.careercity.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name = "questionnaire_results")
public class QuestionnaireResults {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer QR_ID;

    private Integer WORKDAY_ID;

    private Integer ACCOUNT_ID;

    private String PILLAR;

    @CreationTimestamp
    private LocalDateTime DATE_CREATED;

    private String STATUS;

    @OneToOne(fetch = FetchType.LAZY, targetEntity = Users.class)
    @JoinColumn(name = "USER_ID", columnDefinition = "USER_ID")
    private Users user;

    public QuestionnaireResults(Integer workdayId, Integer accountId, String pillar, Users user) {

        this.WORKDAY_ID = workdayId;
        this.ACCOUNT_ID = accountId;
        this.PILLAR = pillar;
        this.user = user;

    }

    /**
     * @return the qR_ID
     */
    public Integer getQR_ID() {

        return QR_ID;

    }

    /**
     * @param qR_ID the qR_ID to set
     */
    public void setQR_ID(Integer qR_ID) {

        QR_ID = qR_ID;

    }

    /**
     * @return the wORKDAY_ID
     */
    public Integer getWORKDAY_ID() {

        return WORKDAY_ID;

    }

    /**
     * @param wORKDAY_ID the wORKDAY_ID to set
     */
    public void setWORKDAY_ID(Integer wORKDAY_ID) {

        WORKDAY_ID = wORKDAY_ID;

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
     * @return the pILLAR
     */
    public String getPILLAR() {

        return PILLAR;

    }

    /**
     * @param pILLAR the pILLAR to set
     */
    public void setPILLAR(String pILLAR) {

        PILLAR = pILLAR;

    }

    /**
     * @return the dATE_CREATED
     */
    public LocalDateTime getDATE_CREATED() {

        return DATE_CREATED;

    }

    /**
     * @param dATE_CREATED the dATE_CREATED to set
     */
    public void setDATE_CREATED(LocalDateTime dATE_CREATED) {

        DATE_CREATED = dATE_CREATED;

    }

    /**
     * @return the sTATUS
     */
    public String getSTATUS() {

        return STATUS;

    }

    /**
     * @param sTATUS the sTATUS to set
     */
    public void setSTATUS(String sTATUS) {

        STATUS = sTATUS;

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

        return "QuestionnaireResults [QR_ID=" + QR_ID + ", WORKDAY_ID=" + WORKDAY_ID + ", ACCOUNT_ID=" + ACCOUNT_ID
                + ", PILLAR=" + PILLAR + ", DATE_CREATED=" + DATE_CREATED + ", STATUS=" + STATUS + ", user=" + user
                + "]";

    }

}
