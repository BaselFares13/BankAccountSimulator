package User;

import java.util.ArrayList;
import java.util.Objects;

public class Account 
{
    private String username;
    private String password;
    private Date creationDate;

    public Account() 
    {
        username = null;
        password = null;
        creationDate = null;
    }

    public Account(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public Account(String username, String password, Date creationDate) {
        this.username = username;
        this.password = password;
        this.creationDate = creationDate;
    }
    
    public static boolean isTheUsernameUsedBefore(ArrayList<Account> accounts, Account account)
    {
        for(Account acc : accounts)
        {
            if(acc.getUsername().equals(account.getUsername()))
            {
                return true;
            }
        }
        
        return false;
    }
    
    public static Account getTheAccoutnFromTheUsername(ArrayList<Account> accounts, String Username)
    {
        for(Account acc : accounts)
        {
            if(acc.getUsername().equals(Username))
            {
                return acc;
            }
        }
        
        return null;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }
    
    @Override
    public String toString() {
        return "Username : " + username + "\nPassword : " + password + "\nCreation Date : " + creationDate;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Account other = (Account) obj;
        if (!Objects.equals(this.username, other.username)) {
            return false;
        }
        return Objects.equals(this.password, other.password);
    }      
}
