/**
 * 
 */
package com.telusinternational.careercity.util;

/*import org.springframework.security.core.Authentication;*/
import com.telusinternational.careercity.enums.UsedUsername;
/*import com.telusinternational.careercity.model.Users;*/

public class UserUtilities {

    public UsedUsername identifyUsedUsername(String username) {

        if (username.contains("@")) {
            return UsedUsername.EMAIL;
        }
        else {
            return UsedUsername.WORKDAY;
        }

    }

    //public static Users getAuthenticatedUser() {
    //    UserDetailsImpl authenticatPrincipal = (UserDetailsImpl) authentication.getPrincipal();
    //    Users user = authenticatPrincipal.getUsers();
    //    return user;
    //}

}
