package com.careercitydashboard.Service;

import java.util.List;

import com.careercitydashboard.Model.Users;

public interface UsersService {

    public List<Users> getAllUsers();

    public List<Users> addUsersList(List<Users> usersList);

    public Users addUsers(Users users);

    public Users updateUsers(Users users);

    public Users updateUsersWithRawPass(Users users);

    public Users getUsersById(Integer USER_ID);

    public Users saveUsers(Users users);

    public List<Users> findAllUsersWithAdminRole();

    public List<Users> findAllUsersWithUserRole();

    public Users findUserByEmpId(String wdId);

    public void updateUser(Users user);

    public Users findByEmpId(String empId);

}
