package com.careercitydashboard.Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "role")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ROLE_ID;

    private String USER_ROLE;

    public Integer getROLE_ID() {

        return ROLE_ID;

    }

    public void setROLE_ID(Integer rOLE_ID) {

        ROLE_ID = rOLE_ID;

    }

    public String getUSER_ROLE() {

        return USER_ROLE;

    }

    public void setUSER_ROLE(String uSER_ROLE) {

        USER_ROLE = uSER_ROLE;

    }

    @Override
    public String toString() {

        return "Role [ROLE_ID=" + ROLE_ID + ", USER_ROLE=" + USER_ROLE + "]";

    }

}
