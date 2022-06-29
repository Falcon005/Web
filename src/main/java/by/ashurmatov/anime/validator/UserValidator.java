package by.ashurmatov.anime.validator;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserValidator {
    private static final Logger logger = LogManager.getLogger(UserValidator.class);
    /**
     * For Password
     * ^                                   # start of line
     *   (?=.*[0-9])                       # positive lookahead, digit [0-9]
     *   (?=.*[a-z])                       # positive lookahead, one lowercase character [a-z]
     *   (?=.*[A-Z])                       # positive lookahead, one uppercase character [A-Z]
     *   (?=.*[!@#&()–[{}]:;',?/*~$^+=<>]) # positive lookahead, one of the special character in this [..]
     *   .                                 # matches anything
     *   {8,20}                            # length at least 8 characters and maximum of 20 characters
     * $                                   # end of line
     */
    private static final String PASSWORD_REGEX = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{4,20}$";
    /**
     * For Username
     * Username consists of alphanumeric characters (a-zA-Z0-9), lowercase, or uppercase.
     * Username allowed of the dot (.), underscore (_), and hyphen (-).
     * The dot (.), underscore (_), or hyphen (-) must not be the first or last character.
     * The dot (.), underscore (_), or hyphen (-) does not appear consecutively, e.g., java..regex
     * The number of characters must be between 5 to 20.
     *
     *
     *   ^[a-zA-Z0-9]      # start with an alphanumeric character
     *   (                 # start of (group 1)
     *     [._-](?![._-])  # follow by a dot, hyphen, or underscore, negative lookahead to
     *                     # ensures dot, hyphen, and underscore does not appear consecutively
     *     |               # or
     *     [a-zA-Z0-9]     # an alphanumeric character
     *   )                 # end of (group 1)
     *   {3,18}            # ensures the length of (group 1) between 3 and 18
     *   [a-zA-Z0-9]$      # end with an alphanumeric character
     *
     *                     # {3,18} plus the first and last alphanumeric characters,
     *                     # total length became {5,20}
     */
    private static final String LOGIN_REGEX = "^[a-zA-Z0-9]([._-](?![._-])|[a-zA-Z0-9]){3,18}[a-zA-Z0-9]$";
    /**
     * For Email
     * ^                       #start of the line
     *   (                     #   start of group #1
     *     .+                  #     any characters (matches Unicode), must contains one or more (+)
     *   )                     #   end of group   #1
     *     @                   #     must contains a "@" symbol
     *       (                 #         start of group #2
     *         \S+             #           non white space characters, must contains one or more (+)
     *       )                 #         end of group #2
     * $
     */
    private static final String EMAIL_REGEX = "^(.+)@(\\S+)$";
    private static final String FIRSTNAME_AND_LASTNAME_REGEX = "^[A-Za-z]{0,25}$";

    private static final String VALID_LOGIN_MESSAGE = "Login is valid";
    private static final String VALID_PASSWORD_MESSAGE = "Password is valid";
    private static final String INVALID_LOGIN_MESSAGE = "Login is not valid";
    private static final String INVALID_PASSWORD_MESSAGE = "Password is not valid";
    private static final String PASSWORD_DO_NOT_MATCH_MESSAGE = "Password do not match";
    private static final String VALID_EMAIL_MESSAGE = "Email is valid";
    private static final String INVALID_EMAIL_MESSAGE = "Email is not valid";
    private static final String VALID_FIRSTNAME = "Firstname is valid";
    private static final String INVALID_FIRSTNAME = "Firstname is not valid";
    private static final String VALID_LASTNAME = "Lastname is valid";
    private static final String INVALID_LASTNAME = "Lastname is not valid";

    public static boolean isPasswordValid(String password){
        Pattern pattern = Pattern.compile(PASSWORD_REGEX);
        Matcher matcher = pattern.matcher(password);
        boolean result = matcher.matches();
        String log = result? VALID_PASSWORD_MESSAGE:INVALID_PASSWORD_MESSAGE;
        logger.log(Level.DEBUG,log);
        return result;
    }
    public static boolean isLoginValid(String username){
        Pattern pattern = Pattern.compile(LOGIN_REGEX);
        Matcher matcher = pattern.matcher(username);
        boolean result = matcher.matches();
        String log = result? VALID_LOGIN_MESSAGE : INVALID_LOGIN_MESSAGE;
        logger.info(log);
        return result;
    }

    public static boolean isPasswordMatches(String password1, String password2) {
        return password1.equals(password2);
    }

    public static boolean isEmailValid(String email) {
        Pattern pattern = Pattern.compile(EMAIL_REGEX);
        Matcher matcher = pattern.matcher(email);
        boolean result = matcher.matches();
        String log = result? VALID_EMAIL_MESSAGE : INVALID_EMAIL_MESSAGE;
        logger.log(Level.DEBUG,log);
        return result;
    }

    public static boolean isFirstnameValid(String firstname) {
        Pattern pattern = Pattern.compile(FIRSTNAME_AND_LASTNAME_REGEX);
        Matcher matcher = pattern.matcher(firstname);
        boolean result = matcher.matches();
        String log = result? VALID_FIRSTNAME : INVALID_FIRSTNAME;
        logger.log(Level.DEBUG,log);
        return result;
    }

    public static boolean isLastnameValid(String lastname) {
        Pattern pattern = Pattern.compile(FIRSTNAME_AND_LASTNAME_REGEX);
        Matcher matcher = pattern.matcher(lastname);
        boolean result = matcher.matches();
        String log = result? VALID_LASTNAME : INVALID_LASTNAME;
        logger.log(Level.DEBUG,log);
        return result;
    }

    public static String validateUserForRegister(String email,String firstname,String lastname,String username,String password) {
        String errorMessage="";
        if(!UserValidator.isEmailValid(email)) {
            errorMessage = INVALID_EMAIL_MESSAGE;
        }
        else if(!UserValidator.isFirstnameValid(firstname)) {
            errorMessage = INVALID_FIRSTNAME;
        }
        else if(!UserValidator.isLastnameValid(lastname)) {
            errorMessage = INVALID_LASTNAME;
        }
        else if(!UserValidator.isLoginValid(username)) {
            errorMessage = INVALID_LOGIN_MESSAGE;
        }
        else if(!UserValidator.isPasswordValid(password)) {
            errorMessage = INVALID_PASSWORD_MESSAGE;
        }
        return errorMessage;
    }

    public static String validateUserForLogin(String username, String password) {
        String errorMessage = "";
        if(!UserValidator.isLoginValid(username)) {
            errorMessage = INVALID_LOGIN_MESSAGE;
        }
        else if(!UserValidator.isPasswordValid(password)) {
            errorMessage = INVALID_PASSWORD_MESSAGE;
        }
        return errorMessage;
    }

}
