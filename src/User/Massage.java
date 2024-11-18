package User;

import java.util.Scanner;

public class Massage 
{
    private String from;
    private String to;
    private String massageBody;

    public Massage() 
    {
        from = "";
        to = "";
        massageBody = "";
    }

    public Massage(String fromTo, String massage)
    {
        setFromTo(fromTo);
        setMassageBody(massage);
    }
    
    public Massage(String from, String to, String massage) {
        this.from = from;
        this.to = to;
        setMassageBody(massage);
    }
    
    public static String removeTheNewLinesFromTheMassage(String massage)
    {        
        StringBuilder newMassage = new StringBuilder();
        Scanner input;
        
        try 
        {
            input = new Scanner(massage);
            
            while(input.hasNext())
            {
                String Line = input.nextLine();
                newMassage.append(Line + "/");
                
            }
            input.close();
            
            newMassage.deleteCharAt(newMassage.length()-1);
        }
        catch(Exception ex) 
        {
            ex.printStackTrace();
        }
        
        return newMassage.toString();
    }
   
    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    public String getMassageWithoutNewLines()
    {
        return removeTheNewLinesFromTheMassage(massageBody);
    }
    
    public String getMassageBody() {
        return massageBody;
    }

    public void setFromTo(String fromTo)
    {
        String arr[] = fromTo.split(">");
        this.from = arr[0];
        this.to = arr[1];
    }
    
    public void setFrom(String from) {
        this.from = from;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public void setMassageBody(String massage) 
    {
        String arr[] = massage.split("/");
        
        for(int i = 0; i < arr.length; i++)
        {
            this.massageBody += arr[i] + "\n";
        }
    }

    @Override
    public String toString() {
        return massageBody;
    }  
}
