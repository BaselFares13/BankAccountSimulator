package User;

import java.util.ArrayList;
import java.util.Objects;

public class FullName 
{
    private String firstName;
    private String secondName;
    private String thirdName;
    private String lastName;

    public FullName() 
    {
        firstName = null;
        secondName = null;
        thirdName = null;
        lastName = null;
    }

    public FullName(String name) 
    {
        String []Name = name.split(" ");
        
        this.firstName = Name[0];
        this.secondName = Name[1];
        this.thirdName = Name[2];
        this.lastName = Name[3];
    }
    
    public FullName(String firstName, String secondName, String thirdName, String lastName) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.thirdName = thirdName;
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public String getThirdName() {
        return thirdName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public void setThirdName(String thirdName) {
        this.thirdName = thirdName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return firstName + " " + secondName + " " + thirdName + " " + lastName;
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
        final FullName other = (FullName) obj;
        if (!Objects.equals(this.firstName, other.firstName)) {
            return false;
        }
        if (!Objects.equals(this.secondName, other.secondName)) {
            return false;
        }
        if (!Objects.equals(this.thirdName, other.thirdName)) {
            return false;
        }
        return Objects.equals(this.lastName, other.lastName);
    } 
}
