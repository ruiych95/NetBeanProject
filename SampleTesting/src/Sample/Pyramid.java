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
public class Pyramid 
{
    public static void main(String [] args)
    {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please insert line : ");
        int lineNum = scanner.nextInt();
        int spaceNum = lineNum - 1;
        String asterisk = "";
        String space = "";
        for(int i = 0; i < (lineNum*2)-1; i++)
        {
            asterisk += "*";
        }
        for(int i = 0; i < spaceNum; i++)
        {
            space += " ";
        }
        String line = space + asterisk;
        System.out.println(line);
        for (int i = 0; i < lineNum; i++) 
            System.out.println(line.substring(i, 4 + 2*i));
    }
}
