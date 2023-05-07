package com.telusinternational.careercity.service;

import java.util.Date;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
/*import org.springframework.security.config.authentication.UserServiceBeanDefinitionParser;*/
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
/*import org.springframework.security.core.userdetails.UserDetails;*/
/*import org.springframework.security.core.userdetails.UsernameNotFoundException;*/
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.telusinternational.careercity.dao.UsersRepository;
import com.telusinternational.careercity.model.UserHistory;
import com.telusinternational.careercity.model.Users;

@Service
@Transactional
public class UsersServiceImpl {

    @Autowired
    private UserHistoryServiceImpl userHistoryService;

    @Autowired
    private UsersRepository usersRepo;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public List<Users> getAllUsers() {

        // TODO Auto-generated method stub
        return (List<Users>) this.usersRepo.findAll();

    }

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

    public Users getUsersById(Integer USER_ID) {

        // TODO Auto-generated method stub
        return this.usersRepo.findById(USER_ID).get();

    }

    public Users saveUsers(Users users) {

        // TODO Auto-generated method stub
        users.setPASSWORD(passwordEncoder.encode(users.getPASSWORD()));
        return this.usersRepo.save(users);

    }

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

    public Users getAuthenticatedUserByEmail(String email) {

        return usersRepo.findByEmail(email);

    }

}
