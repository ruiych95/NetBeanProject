package socketport;

public class ScanResult 
{
    private final String ip;
    private final boolean isDiscovered;
    
    public ScanResult(String ip, boolean isDiscovered)
    {
        this.ip = ip;
        this.isDiscovered = isDiscovered;
    }
    
    public String getIP()
    {
        return ip;
    }
    
    public boolean getDiscovered()
    {
        return isDiscovered;
    }
}
