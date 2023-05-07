package com.telusinternational.careercity.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "account")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ACCOUNT_ID;

    private String ACCOUNT_NAME;

    private String LOCATION;

    private String ACCOUNT_IMAGE_PATH;

    private String LOB;

    private String DESCRIPTION;

    private String TM_TASK;

    private String EDUCATION;

    private String BPO_EXP;

    private String SKILLS;

    private String BUSINESS_VERTICAL;

    private String OPERATING_HOURS;

    private String SKILLS_PREMIUM;

    private String CATEGORY;

    private String TRAININGS;

    private String WORKDAY_JOB_PROFILE;

    private String JOB_REQ;

    private String INTERNAL_POSTING_URL;
    
    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "ACCOUNT_ID")
    private List<FTAccountAnswers> questionAnswers;

    public Integer getACCOUNT_ID() {

        return ACCOUNT_ID;

    }

    public void setACCOUNT_ID(Integer aCCOUNT_ID) {

        ACCOUNT_ID = aCCOUNT_ID;

    }

    public String getACCOUNT_NAME() {

        return ACCOUNT_NAME;

    }

    public void setACCOUNT_NAME(String aCCOUNT_NAME) {

        ACCOUNT_NAME = aCCOUNT_NAME;

    }

    public String getLOCATION() {

        return LOCATION;

    }

    public void setLOCATION(String lOCATION) {

        LOCATION = lOCATION;

    }

    public String getACCOUNT_IMAGE_PATH() {

        return ACCOUNT_IMAGE_PATH;

    }

    public void setACCOUNT_IMAGE_PATH(String aCCOUNT_IMAGE_PATH) {

        ACCOUNT_IMAGE_PATH = aCCOUNT_IMAGE_PATH;

    }

    public String getLOB() {

        return LOB;

    }

    public void setLOB(String lOB) {

        LOB = lOB;

    }

    public String getDESCRIPTION() {

        return DESCRIPTION;

    }

    public void setDESCRIPTION(String dESCRIPTION) {

        DESCRIPTION = dESCRIPTION;

    }

    public String getTM_TASK() {

        return TM_TASK;

    }

    public void setTM_TASK(String tM_TASK) {

        TM_TASK = tM_TASK;

    }

    public String getEDUCATION() {

        return EDUCATION;

    }

    public void setEDUCATION(String eDUCATION) {

        EDUCATION = eDUCATION;

    }

    public String getBPO_EXP() {

        return BPO_EXP;

    }

    public void setBPO_EXP(String bPO_EXP) {

        BPO_EXP = bPO_EXP;

    }

    public String getSKILLS() {

        return SKILLS;

    }

    public void setSKILLS(String sKILLS) {

        SKILLS = sKILLS;

    }

    public String getBUSINESS_VERTICAL() {

        return BUSINESS_VERTICAL;

    }

    public void setBUSINESS_VERTICAL(String bUSINESS_VERTICAL) {

        BUSINESS_VERTICAL = bUSINESS_VERTICAL;

    }

    public String getOPERATING_HOURS() {

        return OPERATING_HOURS;

    }

    public void setOPERATING_HOURS(String oPERATING_HOURS) {

        OPERATING_HOURS = oPERATING_HOURS;

    }

    public String getSKILLS_PREMIUM() {

        return SKILLS_PREMIUM;

    }

    public void setSKILLS_PREMIUM(String sKILLS_PREMIUM) {

        SKILLS_PREMIUM = sKILLS_PREMIUM;

    }

    public String getCATEGORY() {

        return CATEGORY;

    }

    public void setCATEGORY(String cATEGORY) {

        CATEGORY = cATEGORY;

    }

    public String getTRAININGS() {

        return TRAININGS;

    }

    public void setTRAININGS(String tRAININGS) {

        TRAININGS = tRAININGS;

    }

    public String getWORKDAY_JOB_PROFILE() {

        return WORKDAY_JOB_PROFILE;

    }

    public void setWORKDAY_JOB_PROFILE(String wORKDAY_JOB_PROFILE) {

        WORKDAY_JOB_PROFILE = wORKDAY_JOB_PROFILE;

    }

    public String getJOB_REQ() {

        return JOB_REQ;

    }

    public void setJOB_REQ(String jOB_REQ) {

        JOB_REQ = jOB_REQ;

    }
    
    public String getINTERNAL_POSTING_URL() {
    	
    	return INTERNAL_POSTING_URL;
    	
    }
    
    public void setiNTERNAL_POSTING_URL(String iNTERNAL_POSTING_URL) {
    	
    	INTERNAL_POSTING_URL = iNTERNAL_POSTING_URL;
    	
    }
    
    
    public List<FTAccountAnswers> getQuestionAnswers() {

        return questionAnswers;

    }

    public void setQuestionAnswers(List<FTAccountAnswers> questionAnswers) {

        this.questionAnswers = questionAnswers;

    }

    @Override
    public String toString() {

        return "Account [ACCOUNT_ID=" + ACCOUNT_ID + ", ACCOUNT_NAME=" + ACCOUNT_NAME + ", LOCATION=" + LOCATION
                + ", ACCOUNT_IMAGE_PATH=" + ACCOUNT_IMAGE_PATH + ", LOB=" + LOB + ", DESCRIPTION=" + DESCRIPTION
                + ", TM_TASK=" + TM_TASK + ", EDUCATION=" + EDUCATION + ", BPO_EXP=" + BPO_EXP + ", SKILLS=" + SKILLS
                + ", BUSINESS_VERTICAL=" + BUSINESS_VERTICAL + ", OPERATING_HOURS=" + OPERATING_HOURS
                + ", SKILLS_PREMIUM=" + SKILLS_PREMIUM + ", CATEGORY=" + CATEGORY + ", TRAININGS=" + TRAININGS
                + ", WORKDAY_JOB_PROFILE=" + WORKDAY_JOB_PROFILE + ", JOB_REQ=" + JOB_REQ
                + ", INTERNAL_POSTING_URL=" + INTERNAL_POSTING_URL + ",  questionAnswers=" + questionAnswers + "]";

    }

}
