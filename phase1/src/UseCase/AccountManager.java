package UseCase;

import Entity.*;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Manages Accounts.
 */
public class AccountManager implements Serializable{
    private HashMap<String, Account> idToAccount;
    private HashMap<String, Account> emailToAccount;

    /**
     * Create an AccountManager Object.
     */
    public AccountManager(){
        this.emailToAccount = new HashMap<>();
        this.idToAccount = new HashMap<>();
        }

    /**
     * change the userName of an account
     * @param account the target account to change userName
     * @param userName the userName user enters that they want to change to
     */
    public void setUserName(Account account, String userName){
        account.setUserName(userName);
    }

    /**
     * set the password. User can change their password to something different from the previous one.
     * @param account the account that the password is changed
     * @param newPassword the new password that user want to change to.
     * @return true if successfully changed password, false otherwise.
     */
    public boolean setPassword(Account account, String newPassword){
        // users need to enter the correct password to set a new password (just like iPhone)
        if ((!account.getPassword().equals(newPassword))){
            account.setPassword(newPassword);
            return true;
        } else {
            return false;
        }
    }

    /**
     * totally reset the HashMap of ID to Account.
     * @param hm A HashMap object we want to assign.
     */
    public void setIdToAccount(HashMap<String, Account> hm) {
        this.idToAccount = hm;
    }

    /**
     * totally reset the HashMap of Email to Account.
     * @param hm A HashMap we want to assign.
     */
    public void setEmailToAccount(HashMap<String, Account> hm) {
        this.emailToAccount = hm;
    }

    /**
     * return whether user's entered password is same as the password of the account
     * @param account the account that user wants to login to
     * @param enteredPassword the password that user enters
     * @return true if user's entered password is correct, false otherwise
     */
    public boolean checkPassword(Account account, String enteredPassword){
        return account.getPassword().equals(enteredPassword);
    }

    /**
     * find an account by email or userId. Since both email and id are String, we differentiate
     * them by the keyword "@". If the input contains @, then it is email. else, it is userId.
     * @param userInput the input from user. May be an email or an userId.
     * @return the account if the account is found, null if otherwise.
     */
    public Account findAccount(String userInput){
        if (userInput.contains("@")){
            return emailToAccount.getOrDefault(userInput, null);
        } else {
            return idToAccount.getOrDefault(userInput, null);
        }
    }

    /**
     * create a regular account, add it to all accounts and the hashmaps.
     * @return the userId of the new account.
     */
    private String createRegAcc(String email){
        UserAccount newAccount = new UserAccount(email);
        emailToAccount.put(email, newAccount);
        idToAccount.put(newAccount.getUserId(), newAccount);
        return newAccount.getUserId();
    }

    /**
     * create a admin account, add it to all accounts and the hashmaps.
     * @return the userId of the new account.
     */
    private String createAdminAcc(String email){
        AdminAccount newAccount = new AdminAccount(email);
        emailToAccount.put(email, newAccount);
        idToAccount.put(newAccount.getUserId(), newAccount);
        return newAccount.getUserId();
    }

    /**
     * create a trial account. The trial account is not added to emailToAccount.
     * @return the userId of the new account.
     */
    private String createTrialAcc(){
        TrialAccount newAccount = new TrialAccount();
        idToAccount.put(newAccount.getUserId(), newAccount);
        return newAccount.getUserId();
    }

    /**
     * create an account which type is determined by user's email. if user's email contains "@admin.com",
     * we think that it would be an admin. If the user has no email (email is empty), then we create
     * trial account. Else, it would be a regular account.
     * @param email: the email that user enters
     * @return userId: the userId of the user so they can login in the future.
     */
    public String createAccount(String email){
        String userId;
        if (email.contains("@admin.com")){
            userId = createAdminAcc(email);
        } else if (email.equals("")){
            userId = createTrialAcc();
        } else {
            userId = createRegAcc(email);
        }
        return userId;
    }

    /**
     * remove an account from allAccount, emailToAccount, and idToAccount. If successfully
     * removed, return true, else return false.
     * @param account: the account want to be removed
     * @return true if successfully removed account, false otherwise.
     */
    public boolean removeAccount(Account account){
        if (this.getAllAccount().contains(account)) {
            idToAccount.remove(account.getUserId());
            emailToAccount.remove(account.getEmail());
            return true; //Return true if the account object is in the collection.
        } else {
            return false; //Return false if the account object is not in the collection.
        }
    }

    /**
     * return the list of all accounts
     * @return allAccount: the list that contains all accounts.
     */
    public ArrayList<Account> getAllAccount() {
        return new ArrayList<>(this.idToAccount.values());
    }

    /**
     * find the role of the account
     * @param account the account that is to be checked
     * @return the String that represents the role of the account ("regular", "admin", or "trial").
     */
    public String checkAccountRole(Account account){
        return account.getAccountType();
    }

    /**
     * Add new planner to a given account. return true if any one of the planners is added.
     * @param account An Account object that need to be read.
     * @param planner An array list of Planners that need to be added.
     * @return A boolean value representing whether the adding is successful or not.
     */
    public boolean setPlanners(Account account, ArrayList<Planner> planner){
        if (account.getAccountType().equals("regular")){
            return ((UserAccount) account).setPlanners(planner);
        } else {
            return false;
        }
    }

    /**
     * Add new planner to a given account. return true if the planner is added.
     * @param account An Account object that need to be read.
     * @param planner An array list of Planners that need to be added.
     * @return A boolean value representing whether the adding is successful or not.
     */
    public boolean setPlanners(Account account, Planner planner){
        if (account.getAccountType().equals("regular")){
            return ((UserAccount) account).setPlanners(planner);
        } else {
            return false;
        }
    }

    /**
     *
     * @param retriever A String representing the User ID or Email.
     * @return An ArrayList of Planner that owned by this account, if the account is regular. Else, return
     * null.
     */
    public ArrayList<Planner> getPlanners(String retriever) {
        if (findAccount(retriever).getAccountType().equals("regular")){
            return ((UserAccount) findAccount(retriever)).getPlanner();
        } else {
            return null;
        }
    }

}
