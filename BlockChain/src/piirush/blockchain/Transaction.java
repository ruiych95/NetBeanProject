package piirush.blockchain;

import java.security.*;
import java.util.ArrayList;

public class Transaction 
{
	
    public String transactionId; // this is also the hash of the transaction.
    public PublicKey sender; // senders address/public key.
    public PublicKey reciepient; // Recipients address/public key.
    public float value;
    public byte[] signature; // this is to prevent anybody else from spending funds in our wallet.
	
    public ArrayList<TransactionInput> inputs = new ArrayList<TransactionInput>();
    public ArrayList<TransactionOutput> outputs = new ArrayList<TransactionOutput>();
	
    private static int sequence = 0; // a rough count of how many transactions have been generated. 
	
	// Constructor: 
    public Transaction(PublicKey from, PublicKey to, float value,  ArrayList<TransactionInput> inputs) 
    {
	this.sender = from;
	this.reciepient = to;
	this.value = value;
	this.inputs = inputs;
    }
	
	// This Calculates the transaction hash (which will be used as its Id)
    private String calulateHash() 
    {
	sequence++; //increase the sequence to avoid 2 identical transactions having the same hash
	return  BlockUtilities.hashBlock(
                BlockUtilities.getStringFromKey(sender) +
                BlockUtilities.getStringFromKey(reciepient) +
                Float.toString(value) + sequence);
    }
    
    //Signs all the data we dont wish to be tampered with.
    public void generateSignature(PrivateKey privateKey) 
    {
        String data = BlockUtilities.getStringFromKey(sender) + BlockUtilities.getStringFromKey(reciepient) + Float.toString(value)	;
        signature = BlockUtilities.applyECDSASig(privateKey,data);		
    }
//Verifies the data we signed hasnt been tampered with
    public boolean verifiySignature() 
    {
        String data = BlockUtilities.getStringFromKey(sender) + BlockUtilities.getStringFromKey(reciepient) + Float.toString(value)	;
        return BlockUtilities.verifyECDSASig(sender, data, signature);
    }
}