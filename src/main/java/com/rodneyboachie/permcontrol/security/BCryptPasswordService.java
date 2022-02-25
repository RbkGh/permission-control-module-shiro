package com.rodneyboachie.permcontrol.security;

import org.apache.shiro.authc.credential.PasswordService;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;

/**
 * author: acerbk
 * Date: 23/02/2022
 * Time: 10:05 pm
 */
@Service
public class BCryptPasswordService implements PasswordService {
    @Override
    public String encryptPassword(Object plaintextPassword) throws IllegalArgumentException {
        String str;
        if (plaintextPassword instanceof String) {
            str = (String) plaintextPassword;
        } else if (plaintextPassword instanceof char[]) {
            str = new String((char[]) plaintextPassword);
        } else if (plaintextPassword instanceof byte[]) {
            str = new String((byte[]) plaintextPassword);
        } else {
            throw new IllegalArgumentException("Unsupported password type: " + plaintextPassword.getClass().getName());
        }
        return BCrypt.hashpw(str, BCrypt.gensalt());
    }

    @Override
    public boolean passwordsMatch(Object submittedPlaintext, String encrypted) {
        return BCrypt.checkpw(String.valueOf(submittedPlaintext), encrypted);
    }
}
