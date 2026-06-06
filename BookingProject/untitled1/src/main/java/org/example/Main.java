package org.example;
import java.time.chrono.ThaiBuddhistChronology;
import java.util.*;

public class Main{
    public static void main(String args[]){

        Scanner in = new Scanner(System.in);
        Theatre t[] = new Theatre[4];

        for(int i=0; i<4; i++)
        {
            String name = in.nextLine();
            int capacity = in.nextInt();
            float rating = in.nextFloat();
            in.nextLine();
            String location = in.nextLine();

            t[i] = new Theatre(name, capacity, rating, location);
        }
        int checkcap = in.nextInt();
        in.nextLine();
        String checkloc = in.nextLine();
        System.out.println("Checkcap: "+checkcap);
        System.out.println("checkloc: "+checkloc);

        String checkingcap = capacityvalidation(t, checkcap);

        if(checkingcap != null)
        {
            System.out.println("Theatre Name: "+checkingcap);
        }
        else {
            System.out.println("Searched Theatre capacity is not available");
        }

        Theatre Checklist = locvalidation(t, checkloc);

        if(Checklist != null)
        {
            System.out.println(Checklist.getName());
            System.out.println(Checklist.getRating());
            System.out.println(Checklist.getCapacity());
        }

        else {
            System.out.println("No Theatre found in searched location");
        }
    }

    public static String capacityvalidation(Theatre t[], int capa)
    {
        for(int i=0; i<4; i++)
        {
            if(capa == t[i].getCapacity())
            {
                return t[i].getName();
            }
        }
        return null;
    }

    public static Theatre locvalidation(Theatre t[], String loc)
    {
        ArrayList<Theatre> out = new ArrayList<>();

        for(int i=0; i<t.length; i++)
        {
            if(loc.equalsIgnoreCase(t[i].getLocation())) {
                out.add(t[i]);
            }
        }
        if(out.size() == 0)
        {
            return null;
        }
        return out.get(0);
    }
}

class Theatre{
    private String name;
    private int capacity;
    private float rating;
    private String location;

    public Theatre()
    {}

    public Theatre(String name, int capacity, float rating, String location)
    {
        this.name= name;
        this.capacity=capacity;
        this.rating = rating;
        this.location=location;
    }

    public String getName(){
        return name;
    }
    public int getCapacity(){
        return capacity;
    }
    public float getRating(){
        return rating;
    }
    public String getLocation(){
        return location;
    }

    public void setName(String name)
    {
        this.name=name;
    }
    public void setCapacity(int capacity){
        this.capacity=capacity;
    }
    public void setRating(float rating){
        this.rating=rating;
    }
    public void setType(String location){
        this.location = location;
    }

}