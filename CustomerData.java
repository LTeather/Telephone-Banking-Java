package telephonebanking;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.DecimalFormat;
import java.util.Scanner;

/**
 * The underlying data structure of CustomerData is HashMap 
 * You must implement this HashMap using open addressing and full resize rehashing
 * with linear probing.
 * 
 * You may need to include some sub-classes and helper functions in this class.
 */

class CustomerData {   
    //Define some useful members here
    private int noofitems;
    private HashPair[] data;
    DecimalFormat df = new DecimalFormat("###.##");
    
    //You should have a Hash Function here
    public void InitHashMap(int initlen) {
      noofitems=0;
      
      data = new HashPair[initlen];
    } 
    
    
    private int HashFunction(String key) {
        int retVal = 0;

        if(key != null) {
            for(int i = 0; i < key.length(); i++) {
                retVal += ((int)key.charAt(i)) * 911.69;
            }   
            
            retVal = Math.abs(retVal);
            return retVal;            
        }
        else {
            return 0;
        }
    }    
    
    // Creates a hashmap with a specified inital capacity
    CustomerData() throws FileNotFoundException{       
        try (Scanner scanner = new Scanner(new File("BankUserDataset.csv"))) {
            
            //Initialise your members here. After this point, you should already 
            //have a ready-to-use HashMap  
            InitHashMap(1000);
            
            scanner.useDelimiter("\r\n");            
            while(scanner.hasNext()){
                String currentLine[] = scanner.next().split(",");
                String currentID = currentLine[0];
                float currentBalance = Float.parseFloat(currentLine[1]);
                System.out.println(currentID+"\t£"+currentBalance);

                //Load currentID and currentBalance into your initialised HashMap 
                //one by one inside the loop. The loop will stop after reading the 
                //last data point in the file. 
                AddData(currentID, currentBalance);
            }
        }        
    }
    
    //Optional: Create some helper methods below for your implementation.
    public int GetNoOfItems() {
        return noofitems;
    }

    /**
     * Adds the <id> and <amount> to the hash map. 
     * @param id is case sensitive
     * You must use the hash function method you have defined. 
     * You must check load factor here and resize if over 0.7.  
     * Use full rehashing.
     * Place item into data, but check and resolve collisions first using linear probing.
     */
    public void AddData(String id, double amount) {
        int hash = HashFunction(id);
        hash = hash % data.length;

        //Create the new HashPair item ready to put in out data structure
         HashPair newPair = new HashPair(id, amount);

        //Check load factor here and resize if over 70% full
        int loadFactor = GetNoOfItems() / data.length;
        if(loadFactor >= 0.7) {
            //resize the array
        }

        //Insert item into the data, but check and resolve collisions first
        if(data[hash] == null) {
            data[hash] = newPair;
        }
        else {
            while(data[hash] != null) {
                hash++;
                hash = hash % data.length;
            }

            data[hash] = newPair;
            noofitems++;
        }        
    }
    
    /**
     * Deletes the account record (the <id> and <amount>) from the hash map if it exists.
     * @param id is case sensitive.
     * You must use the hash function method you have defined.
     * You will need to implement the same linear probing to resolve collisions. 
     * If the key is not in the hash map then the hash map remains unchanged
     */
    public void DeleteData(String id) {
        int hash = HashFunction(id);
        hash = hash % data.length;
        
        while(!data[hash].key.equals(id)) {
            hash++;
            hash = hash % data.length;
        }            

        if(data[hash].key.equals(id)) {
            data[hash].key   = "0";
            data[hash].value = 0D;
        }        
    }
    
    /**
     * To check if the account record exists.
     * @param id is case sensitive.
     * You must use the hash function method you have defined.
     * You will need to implement the same linear probing to resolve collisions. 
     * return FALSE if the account does not exists
     * return TRUE if the account exists
     */
    public boolean Exist(String id) {
        int hash  = HashFunction(id);
        hash = hash % data.length;

        if(data[hash] == null) {
            return false;
        }
        else {
            while(data[hash] != null && !data[hash].key.equals(id)) {
                if(data[hash].key.equals(id)) {
                    return true;
                }
                else {
                    hash++;
                    hash = hash % data.length;
                }
            }
            
            return false;
        }        
    }
    
    /**
     * Update the balance information associated with the <id>
     * @param id is case sensitive
     * @param amount    Positive values means increase the balance (save money)
     *                  Negative value means decrease the balance (withdraw money)
     * You must use the hash function method you have defined.
     * You will need to implement the same linear probing to resolve collisions. 
     */
    public void Update(String id, double amount) {
        int hash = HashFunction(id);
        hash = hash % data.length;
        
        if(data[hash] != null) {
            if(Exist(id)) {
                while(!data[hash].key.equals(id)) {
                    hash++;
                    hash = hash % data.length;
                }            
                double newbalance = data[hash].value + amount;
                data[hash].value = newbalance;                
            }
        }         
    }
    
    /**
     * Print the balance information associated with the <id>
     * @param id is case sensitive
     * You must use the hash function method you have defined.
     * You will need to implement the same linear probing to resolve collisions. 
     */
    public void Display(String id) {
        int hash  = HashFunction(id);
        hash = hash % data.length;
        
        if(data[hash] != null) {
            System.out.println("The latested balance is: £" + df.format(data[hash].value));    
        }
        else {
            System.out.println("The hashmap is empty!");
        }
        
    }
 }
