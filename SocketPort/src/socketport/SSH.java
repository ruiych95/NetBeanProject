package socketport;

import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;

public class SSH 
{
    private final int PORT = 22;
    
    public boolean connect(String ip, String user, String password)
    {
        //ip = "192.168.43.13";
        try
        {
            //System.out.println("user : " + user + " password : " + password);
            JSch jsch = new JSch();
            Session session = jsch.getSession(user, ip, PORT);
            session.setPassword(password);
            session.setConfig("StrictHostKeyChecking", "no");
            //System.out.println("Establishing Connection...");
            session.setTimeout(50000);
            session.connect();
            //System.out.println("Connection established.");
            session.disconnect();
            return true;
        }
        catch(Exception e)
        {
            //System.err.println(e);
            //System.out.println("Wrong credentials.");
            //System.out.println();
            return false;
        }
        

        //String remoteFile="/home/john/test.txt";

        /*try
        {
        JSch jsch = new JSch();
        Session session = jsch.getSession(user, host, port);
        session.setPassword(password);
        session.setConfig("StrictHostKeyChecking", "no");
        System.out.println("Establishing Connection...");
        session.connect();
        System.out.println("Connection established.");
        return true;
        
        System.out.println("Crating SFTP Channel.");
        ChannelSftp sftpChannel = (ChannelSftp) session.openChannel("sftp");
        sftpChannel.connect();
        System.out.println("SFTP Channel created.");
        InputStream out= null;
        out= sftpChannel.get(remoteFile);
        BufferedReader br = new BufferedReader(new InputStreamReader(out));
        String line;
        while ((line = br.readLine()) != null)
        System.out.println(line);
        br.close();
        }
        catch(Exception e)
        {
        System.err.print(e);
        return false;
        }*/
    }
}
