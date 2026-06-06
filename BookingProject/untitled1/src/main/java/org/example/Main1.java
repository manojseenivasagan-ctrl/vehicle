package org.example;

public class Main1{
    public static void main(String args[])
    {
        int originalnum = 12321;
        int num = originalnum;
        int rev = 0;

        while(num!=0)
        {
            int lastnum = num %10;
            rev = rev * 10 + lastnum;
            num = num/10;
        }

        if(originalnum==rev)
        {
            System.out.println("Palindrome");
        }
        else
        {
            System.out.println("Not Palindrome");
        }

    }
}