package piirush.blockchain;

import java.security.Key;
import java.security.MessageDigest;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.util.ArrayList;
import java.util.Base64;

public class BlockUtilities 
{
    public static String hashBlock(String input)
    //public String hashBlock(Block block, int nonce)
    {
        try 
        {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            //String input = block.getAmount() + block.getTimeStamp() + block.getIndex() + block.getPrevHash() + nonce;

            byte[] hash = messageDigest.digest(input.getBytes("UTF-8"));	        
            StringBuffer hexString = new StringBuffer();
            for (int i = 0; i < hash.length; i++) 
            {
                String hex = Integer.toHexString(0xff & hash[i]);
                if(hex.length() == 1) hexString.append('0');
                    hexString.append(hex);
            }
            return hexString.toString();
        } 
        catch(Exception e) 
        {
            throw new RuntimeException(e);
        }
    }
    
    public boolean checkHash(ArrayList<Block> blockChain)
    {
        Block currentBlock; 
        Block previousBlock;
        
        for(int i=1; i < blockChain.size(); i++) 
        {
		currentBlock = blockChain.get(i);
		previousBlock = blockChain.get(i-1);
                /*if(!currentBlock.getHash().equals(hashBlock(currentBlock)) )
                {
                System.out.println("Current Hashes not equal");
                return false;
                }*/
                
		if(!previousBlock.getHash().equals(currentBlock.getPrevHash()) ) 
                {
                    System.out.println("Previous Hashes not equal");
                    return false;
		}
	}
        return true;
    }
    
    public void proofOfWork(Block block, int difficulty) 
    {
        int nonce = 1;
	String target = new String(new char[difficulty]).replace('\0', '0');
        String input = block.getAmount() + block.getTimeStamp() + block.getIndex() + block.getPrevHash();
        
        while(!hashBlock(input).substring( 0, difficulty).equals(target)) 
        {
            nonce++;
            input += nonce;
            block.setHash(hashBlock(input));
	}
	System.out.println("Block Valid : " + block.getHash());
    }
    
    public static byte[] applyECDSASig(PrivateKey privateKey, String input) 
    {
	Signature dsa;
	byte[] output = new byte[0];
	try 
        {
            dsa = Signature.getInstance("ECDSA", "BC");
            dsa.initSign(privateKey);
            byte[] strByte = input.getBytes();
            dsa.update(strByte);
            byte[] realSig = dsa.sign();
            output = realSig;
	} 
        catch (Exception e) 
        {
            throw new RuntimeException(e);
	}
	return output;
    }
	
    //Verifies a String signature 
    public static boolean verifyECDSASig(PublicKey publicKey, String data, byte[] signature) 
    {
        try 
        {
            Signature ecdsaVerify = Signature.getInstance("ECDSA", "BC");
            ecdsaVerify.initVerify(publicKey);
            ecdsaVerify.update(data.getBytes());
            return ecdsaVerify.verify(signature);
	}
        catch(Exception e) 
        {
            throw new RuntimeException(e);
        }
    }

    public static String getStringFromKey(Key key) 
    {
        return Base64.getEncoder().encodeToString(key.getEncoded());
    }
    
    /*public String calculateHash()
    {
    return hashBlock();
    }*/
}
