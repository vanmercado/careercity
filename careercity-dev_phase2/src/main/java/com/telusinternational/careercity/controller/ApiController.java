package com.telusinternational.careercity.controller;

import java.security.Principal;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.telusinternational.careercity.model.Account;
import com.telusinternational.careercity.model.Users;
import com.telusinternational.careercity.service.AccountService;
import com.telusinternational.careercity.service.QuestionnaireResultsService;
/*import com.telusinternational.careercity.service.TMLogsService;*/
import com.telusinternational.careercity.service.UsersServiceImpl;

@CrossOrigin
@RestController
@RequestMapping("/careercity-api")
public class ApiController {

    @Autowired
    public QuestionnaireResultsService questionnairesultsService;

    @Autowired
    private AccountService accountService;

    @Autowired
    private UsersServiceImpl userService;

    /*@Autowired
    private TMLogsService tmLogsService;*/

    @GetMapping("/accounts-api")
    public List<Account> showAllCCOAccounts() {

        return this.accountService.getAllAccounts();

    }

    @GetMapping("/user")
    public Principal user(Principal principal) {

        return principal;

    }

    @PostMapping("/authenticated-user")
    public Users authenticatedUser(@RequestBody Users user) {

        String email = user.getEmail();
        return userService.getAuthenticatedUserByEmail(email);

    }

}
