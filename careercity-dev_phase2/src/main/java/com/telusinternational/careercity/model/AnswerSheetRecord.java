/**
 * 
 */
package com.telusinternational.careercity.model;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
/*import javax.persistence.OneToMany;*/
import javax.persistence.OneToOne;
import javax.persistence.Table;

/*import org.apache.catalina.User;*/
import org.hibernate.annotations.CreationTimestamp;

/**
 * @author A.C.Estrada Jr. x209486
 * @date Oct 25, 2018
 */
@Entity
@Table(name = "answer_sheet_record")
public class AnswerSheetRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ANSWER_SHEET_RECORD_ID;

    private Integer WORKDAY_ID;

    //private String ANSWER_SHEET_RECORD;

    private String A1;

    private String A2;

    private String A3;

    private String A4;

    private String A5;

    private String A6;

    private String A7;

    private String A8;

    private String A9;

    private String A10;

    private String A11;

    private String A12;

    @OneToOne(fetch = FetchType.LAZY, targetEntity = Users.class)
    @JoinColumn(name = "USER_ID", columnDefinition = "USER_ID")
    private Users user;

    @CreationTimestamp
    private Timestamp DATE_CREATED;

    /**
     * 
     */
    public AnswerSheetRecord() {

        super();

    }

    /**
     * @return the aNSWER_SHEET_RECORD_ID
     */
    public Integer getANSWER_SHEET_RECORD_ID() {

        return ANSWER_SHEET_RECORD_ID;

    }

    /**
     * @param aNSWER_SHEET_RECORD_ID the aNSWER_SHEET_RECORD_ID to set
     */
    public void setANSWER_SHEET_RECORD_ID(Integer aNSWER_SHEET_RECORD_ID) {

        ANSWER_SHEET_RECORD_ID = aNSWER_SHEET_RECORD_ID;

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
     * @return the a1
     */
    public String getA1() {

        return A1;

    }

    /**
     * @param a1 the a1 to set
     */
    public void setA1(String a1) {

        A1 = a1;

    }

    /**
     * @return the a2
     */
    public String getA2() {

        return A2;

    }

    /**
     * @param a2 the a2 to set
     */
    public void setA2(String a2) {

        A2 = a2;

    }

    /**
     * @return the a3
     */
    public String getA3() {

        return A3;

    }

    /**
     * @param a3 the a3 to set
     */
    public void setA3(String a3) {

        A3 = a3;

    }

    /**
     * @return the a4
     */
    public String getA4() {

        return A4;

    }

    /**
     * @param a4 the a4 to set
     */
    public void setA4(String a4) {

        A4 = a4;

    }

    /**
     * @return the a5
     */
    public String getA5() {

        return A5;

    }

    /**
     * @param a5 the a5 to set
     */
    public void setA5(String a5) {

        A5 = a5;

    }

    /**
     * @return the a6
     */
    public String getA6() {

        return A6;

    }

    /**
     * @param a6 the a6 to set
     */
    public void setA6(String a6) {

        A6 = a6;

    }

    /**
     * @return the a7
     */
    public String getA7() {

        return A7;

    }

    /**
     * @param a7 the a7 to set
     */
    public void setA7(String a7) {

        A7 = a7;

    }

    /**
     * @return the a8
     */
    public String getA8() {

        return A8;

    }

    /**
     * @param a8 the a8 to set
     */
    public void setA8(String a8) {

        A8 = a8;

    }

    /**
     * @return the a9
     */
    public String getA9() {

        return A9;

    }

    /**
     * @param a9 the a9 to set
     */
    public void setA9(String a9) {

        A9 = a9;

    }

    /**
     * @return the a10
     */
    public String getA10() {

        return A10;

    }

    /**
     * @param a10 the a10 to set
     */
    public void setA10(String a10) {

        A10 = a10;

    }

    /**
     * @return the a11
     */
    public String getA11() {

        return A11;

    }

    /**
     * @param a11 the a11 to set
     */
    public void setA11(String a11) {

        A11 = a11;

    }

    /**
     * @return the a12
     */
    public String getA12() {

        return A12;

    }

    /**
     * @param a12 the a12 to set
     */
    public void setA12(String a12) {

        A12 = a12;

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

    /**
     * @return the dATE_CREATED
     */
    public Timestamp getDATE_CREATED() {

        return DATE_CREATED;

    }

    /**
     * @param dATE_CREATED the dATE_CREATED to set
     */
    public void setDATE_CREATED(Timestamp dATE_CREATED) {

        DATE_CREATED = dATE_CREATED;

    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {

        return "AnswerSheetRecord [ANSWER_SHEET_RECORD_ID=" + ANSWER_SHEET_RECORD_ID + ", WORKDAY_ID=" + WORKDAY_ID
                + ", A1=" + A1 + ", A2=" + A2 + ", A3=" + A3 + ", A4=" + A4 + ", A5=" + A5 + ", A6=" + A6 + ", A7=" + A7
                + ", A8=" + A8 + ", A9=" + A9 + ", A10=" + A10 + ", A11=" + A11 + ", A12=" + A12 + ", user=" + user
                + ", DATE_CREATED=" + DATE_CREATED + "]";

    }

}
