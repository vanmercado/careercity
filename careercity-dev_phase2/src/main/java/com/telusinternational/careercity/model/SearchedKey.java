package com.telusinternational.careercity.model;

import java.time.LocalDateTime;

/*import javax.persistence.Column;*/
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
@Table(name = "searched_key")
public class SearchedKey {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer SK_ID;

    private String SEARCHED_TEXT;

    @CreationTimestamp
    private LocalDateTime DATE_CREATED;

    @OneToOne(fetch = FetchType.LAZY, targetEntity = Users.class)
    @JoinColumn(name = "USER_ID", columnDefinition = "USER_ID")
    private Users user;

    public SearchedKey() {

        super();

    }

    /**
     * @return the sK_ID
     */
    public Integer getSK_ID() {

        return SK_ID;

    }

    /**
     * @param sK_ID the sK_ID to set
     */
    public void setSK_ID(Integer sK_ID) {

        SK_ID = sK_ID;

    }

    /**
     * @return the sEARCHED_TEXT
     */
    public String getSEARCHED_TEXT() {

        return SEARCHED_TEXT;

    }

    /**
     * @param sEARCHED_TEXT the sEARCHED_TEXT to set
     */
    public void setSEARCHED_TEXT(String sEARCHED_TEXT) {

        SEARCHED_TEXT = sEARCHED_TEXT;

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

        return "SearchedKey [SK_ID=" + SK_ID + ", SEARCHED_TEXT=" + SEARCHED_TEXT + ", DATE_CREATED=" + DATE_CREATED
                + ", user=" + user + "]";

    }

}
