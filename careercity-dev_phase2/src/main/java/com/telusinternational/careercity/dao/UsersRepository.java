/**
 * 
 */
package com.telusinternational.careercity.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.telusinternational.careercity.model.Users;

/**
 * @author A.C.Estrada Jr. x209486
 * @date May 5, 2020
 */
@Repository
public interface UsersRepository extends JpaRepository<Users, Integer> {

    public Users findByUsername(String username);

    public Users findByEmail(String email);

}
