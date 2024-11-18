package User;

public class Date 
{
    private int day;
    private int month;
    private int year;

    public Date() 
    {
        day = 0;
        month = 0;
        year = 0;
    }
    
    public Date(String date)
    {
        String d[] = date.split("/");
        
        day = Integer.parseInt(d[0]);
        month = Integer.parseInt(d[1]);
        year = Integer.parseInt(d[2]);
    }

    public Date(int day, int month, int year) {
        this.day = day;
        this.month = month;
        this.year = year;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getDay() {
        return day;
    }

    public int getMonth() {
        return month;
    }

    public int getYear() {
        return year;
    }

    @Override
    public String toString() {
        return day + "/" + month + "/" + year;
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
        final Date other = (Date) obj;
        if (this.day != other.day) {
            return false;
        }
        if (this.month != other.month) {
            return false;
        }
        return this.year == other.year;
    }
      
}
