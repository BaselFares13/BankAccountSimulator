package User;

import java.util.Objects;

public class Person 
{
    private FullName fullName;
    private int age;
    private String city;

    public Person() 
    {
        fullName = null;
        age = 0;
        city = null;
    }

    public Person(FullName fullName, int age, String city) {
        this.fullName = fullName;
        this.age = age;
        this.city = city;
    }

    public void setFullName(FullName fullName) {
        this.fullName = fullName;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public FullName getFullName() {
        return fullName;
    }

    public int getAge() {
        return age;
    }

    public String getCity() {
        return city;
    }

    @Override
    public String toString() {
        return "Full Name : " + fullName + "\nAge : " + age + "\nCity : " + city;
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
        final Person other = (Person) obj;
        if (this.age != other.age) {
            return false;
        }
        if (!Objects.equals(this.city, other.city)) {
            return false;
        }
        return Objects.equals(this.fullName, other.fullName);
    }   
}
