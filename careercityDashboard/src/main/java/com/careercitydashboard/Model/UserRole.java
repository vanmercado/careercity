package com.careercitydashboard.Model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "users_role")
public class UserRole {

    @Id
    private Integer userRoleId;

    @OneToOne(fetch = FetchType.LAZY, targetEntity = Users.class)
    @JoinColumn(name = "USER_ID", columnDefinition = "USER_ID")
    private Users user;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ROLE_ID", columnDefinition = "ROLE_ID")
    private Role role;

    public UserRole() {

        super();

    }

    public Integer getUserRoleId() {

        return userRoleId;

    }

    public void setUserRoleId(Integer userRoleId) {

        this.userRoleId = userRoleId;

    }

    public Users getUser() {

        return user;

    }

    public void setUser(Users user) {

        this.user = user;

    }

    public Role getRole() {

        return role;

    }

    public void setRole(Role role) {

        this.role = role;

    }

    @Override
    public String toString() {

        return "UserRole [userRoleId=" + userRoleId + ", user=" + user + ", role=" + role + "]";

    }

}
