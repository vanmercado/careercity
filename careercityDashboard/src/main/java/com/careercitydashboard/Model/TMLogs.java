package com.careercitydashboard.Model;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import com.careercitydashboard.enums.UsedUsername;

/**
 * @author A.C.Estrada Jr. x209486
 * @date Apr 5, 2020
 */
@Entity
@Table(name = "tm_logs")
public class TMLogs {

    @Id
    @CreationTimestamp
    private Timestamp timestamp;

    @ManyToOne(targetEntity = Users.class, fetch = FetchType.LAZY)
    @JoinColumn(referencedColumnName = "user_id", name = "users_id")
    private Users users;

    @Enumerated(EnumType.STRING)
    private UsedUsername usedUsername;

    public TMLogs() {

        super();

    }

    /**
     * @return the timestamp
     */
    public Timestamp getTimestamp() {

        return timestamp;

    }

    /**
     * @param timestamp the timestamp to set
     */
    public void setTimestamp(Timestamp timestamp) {

        this.timestamp = timestamp;

    }

    /**
     * @return the users
     */
    public Users getUsers() {

        return users;

    }

    /**
     * @param users the users to set
     */
    public void setUsers(Users users) {

        this.users = users;

    }

    /**
     * @return the usedUsername
     */
    public UsedUsername getUsedUsername() {

        return usedUsername;

    }

    /**
     * @param usedUsername the usedUsername to set
     */
    public void setUsedUsername(UsedUsername usedUsername) {

        this.usedUsername = usedUsername;

    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {

        return "TMLogs [timestamp=" + timestamp + ", users=" + users + ", usedUsername=" + usedUsername + "]";

    }

}
