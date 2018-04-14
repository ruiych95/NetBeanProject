package socketport;

import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class SocketPort 
{
    
    private static final int TIME_OUT = 200;
    
    public static void main(String[] args) throws UnknownHostException, SocketException, InterruptedException, ExecutionException 
    {
        final ExecutorService executorService = Executors.newFixedThreadPool(20);
        final List<Future<ScanResult>> futures = new ArrayList<>();
        
        int hostAmount = 0;
        int ports [] = {22, 23};
        String hostIP;
        String subnetMask = "";
        BruteForce bruteForce = new BruteForce();
        
        try(final DatagramSocket socket = new DatagramSocket())
        {
            socket.connect(InetAddress.getByName("8.8.8.8"), 10002);
            String ip = socket.getLocalAddress().getHostAddress();
            System.out.println("ip : " + ip);
            subnetMask = ip.substring(0, ip.lastIndexOf(".")) + ".";
            System.out.println("subnet : " + subnetMask);
        }
        for(int i = 0; i <= 255; i++)
        {
            String ip = subnetMask+i;
            futures.add(hostDiscovered(executorService, ip));
        }
        executorService.shutdown();
        for (final Future<ScanResult> f : futures) 
        {
            if (f.get().getDiscovered()) 
            {
                hostIP = f.get().getIP();
                System.out.println("Host : " + hostIP +"\t"+ " discovered.");
                    for(Integer port: ports)
                    {
                        String portName = "";
                        if(portIsOpen(hostIP, port))
                        {
                            switch(port)
                            {
                                case 22:
                                  portName = "SSH";
                                  System.out.println("\t"+"port : "+ port +"["+portName+"] "+"is open");
                                  bruteForce.ssh(hostIP);
                                  break;
                                case 23:
                                  portName = "Telnet";
                                  System.out.println("\t"+"port : "+ port +"["+portName+"] "+"is open");
                                  break;
                            }
                            
                        }
                    }
                hostAmount++;
            }
        }
        
        /*for(int i = 0; i <= 255; i++)
        {
        String ip = subnetMask+i;
        if(SocketPort.hostDiscovered(ip))
        {
        System.out.println("Host : " + ip + " discovered.");
        for(Integer port: ports)
        {
        String portName = "";
        if(portIsOpen(ip, port))
        {
        switch(port)
        {
        case 22:
        portName = "SSH";
        break;
        case 23:
        portName = "Telnet";
        break;
        }
        System.out.println("port : "+ port +"["+portName+"] "+"is open");
        }
        }
        hostAmount++;
        }
        }*/
        System.out.println(hostAmount + " Hosts discovered.");
        /*Enumeration e = NetworkInterface.getNetworkInterfaces();
        while(e.hasMoreElements())
        {
        NetworkInterface n = (NetworkInterface) e.nextElement();
        Enumeration ee = n.getInetAddresses();
        while (ee.hasMoreElements())
        {
        InetAddress i = (InetAddress) ee.nextElement();
        System.out.println(i.getHostAddress());
        }
        }*/
    }
    
    public static boolean portIsOpen(String ip, int port) 
    {
        try 
        {
            Socket socket = new Socket();
            socket.connect(new InetSocketAddress(ip, port), TIME_OUT);
            socket.close();
            return true;
        } 
        catch (Exception ex)
        {
            return false;
        }
    }
    
    public static Future<ScanResult> hostDiscovered(final ExecutorService es, final String ip) 
    {
        return es.submit(new Callable<ScanResult>() 
        {
            @Override 
            public ScanResult call() 
            {
                try 
                {
                    InetAddress inet = InetAddress.getByName(ip);
                    if (inet.isReachable(5000))
                    {
                      return new ScanResult(ip, true);
                    } 
                    else 
                    {
                        return new ScanResult(ip, false);
                    }
                } 
                catch ( Exception e ) 
                {
                    System.out.println("Exception:" + e.getMessage());
                    return new ScanResult(ip, false);
                }
            }
        });
    }
}


