package piirush.blockchain;

import java.util.Date;

public class Block 
{
    private long index;
    private Date timeStamp;
    private String creditor;
    private String debtor;
    private String amount;
    private String hash;
    private String prevHash;
    
    public Block(long index, Date timeStamp, String creditor, String debtor, String amount,  String prevHash)
    {
        this.index = index;
        this.timeStamp = timeStamp;
        this.creditor = creditor;
        this.debtor = debtor;
        this.amount = amount;
        this.prevHash = prevHash;
    }
    
    public long getIndex() 
    {
        return index;
    }

    public void setIndex(long index) 
    {
        this.index = index;
    }
    
    public Date getTimeStamp() 
    {
        return timeStamp;
    }

    public void setTimeStamp(Date timeStamp) 
    {
        this.timeStamp = timeStamp;
    }

    public String getCreditor() 
    {
        return creditor;
    }

    public void setCreditor(String creditor) 
    {
        this.creditor = creditor;
    }

    public String getDebtor() 
    {
        return debtor;
    }

    public void setDebtor(String debtor) 
    {
        this.debtor = debtor;
    }
    
    public String getAmount() 
    {
        return amount;
    }

    public void setAmount(String amount) 
    {
        this.amount = amount;
    }
    
    public String getHash() 
    {
        return hash;
    }

    public void setHash(String hash) 
    {
        this.hash = hash;
    }

    public String getPrevHash() 
    {
        return prevHash;
    }

    public void setPrevHash(String prevHash) 
    {
        this.prevHash = prevHash;
    }
    
}
