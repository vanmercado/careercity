package com.careercitydashboard.Model;

import java.util.Date;

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
@Table(name = "user_history")
public class UserHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer user_hist_id;

    private String user_hist_msg;

    private String user_hist_user;

    @CreationTimestamp
    private Date timestamp;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID")
    private Users users;

    public Integer getUser_hist_id() {

        return user_hist_id;

    }

    public void setUser_hist_id(Integer user_hist_id) {

        this.user_hist_id = user_hist_id;

    }

    public String getUser_hist_msg() {

        return user_hist_msg;

    }

    public void setUser_hist_msg(String user_hist_msg) {

        this.user_hist_msg = user_hist_msg;

    }

    public String getUser_hist_user() {

        return user_hist_user;

    }

    public void setUser_hist_user(String user_hist_user) {

        this.user_hist_user = user_hist_user;

    }

    public Date getTimestamp() {

        return timestamp;

    }

    public void setTimestamp(Date timestamp) {

        this.timestamp = timestamp;

    }

    public Users getUsers() {

        return users;

    }

    public void setUsers(Users users) {

        this.users = users;

    }

    @Override
    public String toString() {

        return "UserHistory [user_hist_id=" + user_hist_id + ", user_hist_msg=" + user_hist_msg + ", user_hist_user="
                + user_hist_user + ", timestamp=" + timestamp + ", users=" + users + "]";

    }

}
