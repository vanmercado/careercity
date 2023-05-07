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
@Table(name = "position_history")
public class PositionHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer position_hist_id;

    private String position_hist_msg;

    private String position_hist_user;

    @CreationTimestamp
    private Date timestamp;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "POSITION_ID")
    private Position position;

    public Integer getPosition_hist_id() {

        return position_hist_id;

    }

    public void setPosition_hist_id(Integer position_hist_id) {

        this.position_hist_id = position_hist_id;

    }

    public String getPosition_hist_msg() {

        return position_hist_msg;

    }

    public void setPosition_hist_msg(String position_hist_msg) {

        this.position_hist_msg = position_hist_msg;

    }

    public String getPosition_hist_user() {

        return position_hist_user;

    }

    public void setPosition_hist_user(String position_hist_user) {

        this.position_hist_user = position_hist_user;

    }

    public Date getTimestamp() {

        return timestamp;

    }

    public void setTimestamp(Date timestamp) {

        this.timestamp = timestamp;

    }

    public Position getPosition() {

        return position;

    }

    public void setPosition(Position position) {

        this.position = position;

    }

    @Override
    public String toString() {

        return "PositionHistory [position_hist_id=" + position_hist_id + ", position_hist_msg=" + position_hist_msg
                + ", position_hist_user=" + position_hist_user + ", timestamp=" + timestamp + ", position=" + position
                + "]";

    }

}
