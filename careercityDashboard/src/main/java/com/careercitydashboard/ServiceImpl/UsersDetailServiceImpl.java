package com.careercitydashboard.ServiceImpl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import org.springframework.stereotype.Service;

import com.careercitydashboard.Dao.UsersDetailsRepo;
import com.careercitydashboard.Model.Users;

@Service
@Transactional
public class UsersDetailServiceImpl implements UserDetailsService {

    @Autowired
    private UsersDetailsRepo usersDetailsRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Users users = usersDetailsRepo.findByUsername(username);
        UsersDetailsImpl userDetailsImpl = null;

        if (users != null) {

            userDetailsImpl = new UsersDetailsImpl(users);
            userDetailsImpl.getUsername();

        }
        else {

            System.out.println("User not exist with name : " + username);

        }

        return userDetailsImpl;

    }

}
