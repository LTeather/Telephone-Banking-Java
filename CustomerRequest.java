package telephonebanking;
import java.util.Random;

/**
 * Generate a virtual customer
 * 
 */
public class CustomerRequest {
    public int request; //The indication of which request the customer have 
    public double amountToChange;//For open account, save and withdraw money task
    public String id;//Input manually in the main class for bank user database.   
    
    private Random rand;
    
    /**
     * The request index numbers are:
     * 0: open a new account
     * 1: close the account
     * 2: check balance
     * 3: save money
     * 4: withdraw money
     */
    CustomerRequest(){
        request = 0;
        amountToChange = 0.0;
        rand=new Random();
    }
    
    public void newRequest() {
        //Generate a random amount of money (0.1~10.1) to change (only for open/save/withdraw)
        double random = rand.nextDouble()*10+0.1; 

        //Generate a number for random tasks between 0-99
        int rand_int = rand.nextInt(100);
        
        //10% of the tasks are "open accounts"
        if (rand_int>=0 && rand_int<10)      {request = 0; amountToChange=random*1000;}
        
        //5% of the tasks are "close accounts"
        if (rand_int>=10 && rand_int<15)    {request = 1; amountToChange=0.0;}
        
        //35% of the tasks are "check balance"
        if (rand_int>=15 && rand_int<50)    {request = 2; amountToChange=0.0;}
        
        //25% of the tasks are "save money"
        if (rand_int>=50 && rand_int<75)    {request = 3; amountToChange=random;}
        
        //25% of the tasks are "withdraw money"
        if (rand_int>=75 && rand_int<=100)   {request = 4; amountToChange=random;}          
    }
    
}
