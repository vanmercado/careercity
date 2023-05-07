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
@Table(name = "account_history")
public class AccountHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer account_hist_id;

    private String account_hist_msg;

    private String account_hist_user;

    @CreationTimestamp
    private Date timestamp;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ACCOUNT_ID")
    private Account account;

    public Integer getAccount_hist_id() {

        return account_hist_id;

    }

    public void setAccount_hist_id(Integer account_hist_id) {

        this.account_hist_id = account_hist_id;

    }

    public Account getAccount() {

        return account;

    }

    public void setAccount(Account account) {

        this.account = account;

    }

    public String getAccount_hist_msg() {

        return account_hist_msg;

    }

    public void setAccount_hist_msg(String account_hist_msg) {

        this.account_hist_msg = account_hist_msg;

    }

    public String getAccount_hist_user() {

        return account_hist_user;

    }

    public void setAccount_hist_user(String account_hist_user) {

        this.account_hist_user = account_hist_user;

    }

    public Date getTimestamp() {

        return timestamp;

    }

    public void setTimestamp(Date timestamp) {

        this.timestamp = timestamp;

    }

    @Override
    public String toString() {

        return "AccountHistory [account_hist_id=" + account_hist_id + ", account=" + account + ", account_hist_msg="
                + account_hist_msg + ", account_hist_user=" + account_hist_user + ", timestamp=" + timestamp + "]";

    }

}
