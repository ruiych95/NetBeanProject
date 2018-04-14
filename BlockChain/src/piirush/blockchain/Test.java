package piirush.blockchain;

import com.fasterxml.jackson.core.JsonProcessingException;
import java.util.ArrayList;
import java.util.Date;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Test 
{
    public static void main(String [] args)
    {
        BlockUtilities hashOperation = new BlockUtilities();
        ArrayList <Block> blockChain = new ArrayList<>();
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonString = "";
        
        Block block1 = new Block(0, new Date(), 
                "creditor", 
                "debtor", 
                "999999", 
                "IAMGENESIS");
        hashOperation.proofOfWork(block1, 3);
        
        Block block2 = new Block(1, new Date(), 
                "John Smith", 
                "Alex Gard", 
                "15000", 
                block1.getHash());
        hashOperation.proofOfWork(block2, 3);
        
        Block block3 = new Block(2, new Date(), 
                "Jenna Slone", 
                "Thomas Griggs", 
                "24000", 
                block2.getHash());
        hashOperation.proofOfWork(block3, 3);
        
        blockChain.add(block1);
        blockChain.add(block2);
        blockChain.add(block3);
        
        try 
        {
            jsonString = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(blockChain);
        } 
        catch (JsonProcessingException ex) 
        {
            ex.printStackTrace();
        }
        
        System.out.println("\n");
        System.out.println(jsonString);
    }
}
