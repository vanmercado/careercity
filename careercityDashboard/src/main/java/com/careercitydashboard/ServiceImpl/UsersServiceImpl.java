package com.careercitydashboard.ServiceImpl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.careercitydashboard.Dao.UsersRepo;
import com.careercitydashboard.Model.UserHistory;
import com.careercitydashboard.Model.Users;
import com.careercitydashboard.Service.UserHistoryService;
import com.careercitydashboard.Service.UsersService;

@Service
@Transactional
public class UsersServiceImpl implements UsersService {

    @Autowired
    private UserHistoryService userHistoryService;

    @Autowired
    private UsersRepo usersRepo;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public List<Users> getAllUsers() {

        return (List<Users>) this.usersRepo.findAll();

    }

    @Override
    public Users addUsers(Users users) {

        users.setPASSWORD(passwordEncoder.encode(users.getPASSWORD()));

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String auditor = auth.getName();
        Date date = new Date();
        Users createdUser = this.usersRepo.save(users);
        UserHistory userHistory = new UserHistory();
        userHistory.setUsers(createdUser);
        userHistory.setUser_hist_msg("Created by " + auditor);
        userHistoryService.saveUserSession(userHistory);

        return createdUser;

    }

    @Override
    public Users updateUsers(Users users) {

        users.setPASSWORD(passwordEncoder.encode(users.getPASSWORD()));

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String auditor = auth.getName();
        Date date = new Date();
        Users updatedUser = this.usersRepo.save(users);
        UserHistory userHistory = new UserHistory();
        userHistory.setUsers(updatedUser);
        userHistory.setUser_hist_msg("Password reset for " + users.getUsername());
        userHistory.setUser_hist_user(auditor);
        userHistoryService.saveUserSession(userHistory);

        return updatedUser;

    }

    @Override
    public Users updateUsersWithRawPass(Users users) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String auditor = auth.getName();
        Date date = new Date();
        Users updatedUser = this.usersRepo.save(users);
        UserHistory userHistory = new UserHistory();
        userHistory.setUsers(updatedUser);
        userHistory.setUser_hist_msg("Modified details of " + users.getUsername());
        userHistory.setUser_hist_user(auditor);
        userHistoryService.saveUserSession(userHistory);

        return updatedUser;

    }

    @Override
    public Users getUsersById(Integer USER_ID) {

        return this.usersRepo.findById(USER_ID).get();

    }

    @Override
    public Users saveUsers(Users users) {

        //users.setPASSWORD(passwordEncoder.encode(users.getPASSWORD()));
        return this.usersRepo.save(users);

    }

    @Override
    public List<Users> addUsersList(List<Users> usersList) {

        for (Users user : usersList) {

            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String auditor = auth.getName();
            Date date = new Date();
            Users createdUser = this.usersRepo.save(user);
            UserHistory userHistory = new UserHistory();
            userHistory.setUsers(createdUser);
            userHistory.setUser_hist_msg("Created by " + auditor);
            userHistoryService.saveUserSession(userHistory);

        }

        return usersList;

    }

    @Override
    public List<Users> findAllUsersWithAdminRole() {

        return usersRepo.findAllUsersWithAdminRole();

    }

    @Override
    public List<Users> findAllUsersWithUserRole() {

        return usersRepo.findAllUsersWithUserRole();

    }

    @Override
    public Users findUserByEmpId(String wdId) {

        return usersRepo.findUserByEmpId(wdId);

    }

    @Override
    public void updateUser(Users user) {

        usersRepo.save(user);

    }

    @Override
    public Users findByEmpId(String empId) {

        return this.usersRepo.findEmpId(empId);

    }

}
