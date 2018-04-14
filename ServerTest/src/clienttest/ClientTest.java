/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clienttest;

import java.net.*;
import java.io.*;

public class ClientTest
{
    public static final int PORT = 1667;
    public static final String SERVER_IP = "192.168.43.148";

    public static void main(String [] args)
    {

        Socket clientSocket = null;

        try
        { // create socket
            //clientSocket = new Socket("localhost", PORT);
            clientSocket = new Socket(SERVER_IP, PORT);
        }
        catch (Exception e)
        {
            System.err.println("Error Creating Socket");
        }

        try
        {
            PrintWriter output = new PrintWriter(clientSocket.getOutputStream(), true);
            BufferedReader input = new BufferedReader(
                    new InputStreamReader(clientSocket.getInputStream()));
            BufferedReader inputData = new BufferedReader(new InputStreamReader(System.in));

            int token = 24;
            while(true)
            {
                String inputWord = String.valueOf(token);
                output.write(inputWord + "\r\n");
                output.flush();
                if (inputWord.equals(""))
                {
                    break;
                }
            }
            input.close();
            output.close();
            clientSocket.close();

        }
        catch (Exception e)
        {
            System.err.println("Closing Socket connection");
            if (clientSocket != null)
                try {clientSocket.close();}
                catch (IOException ex) {}
        }

    }
}

