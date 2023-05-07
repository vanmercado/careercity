package com.careercitydashboard.Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "site")
public class Site {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer SITE_ID;

    private String SITE_NAME;

    public Integer getSITE_ID() {

        return SITE_ID;

    }

    public void setSITE_ID(Integer sITE_ID) {

        SITE_ID = sITE_ID;

    }

    public String getSITE_NAME() {

        return SITE_NAME;

    }

    public void setSITE_NAME(String sITE_NAME) {

        SITE_NAME = sITE_NAME;

    }

    @Override
    public String toString() {

        return "Site [SITE_ID=" + SITE_ID + ", SITE_NAME=" + SITE_NAME + "]";

    }

}
