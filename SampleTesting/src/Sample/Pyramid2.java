/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Sample;

import java.util.Scanner;

/**
 *
 * @author ubuntu
 */
public class Pyramid2 
{
    public static void main(String[] args) 
    {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Please input line : ");
        int lineNum = scanner.nextInt();
        for(int i=0;i<lineNum;i++) 
        {
            for(int j=0;j<lineNum-i;j++) 
            {
                System.out.print(" ");
            }
            for(int k=0;k<=i;k++) 
            {
                System.out.print("$ ");
            }
            System.out.println();  
        }
    }
}
