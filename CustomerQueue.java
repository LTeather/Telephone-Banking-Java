package telephonebanking;

import java.text.DecimalFormat;

/**
 * This data structure represents a queue.
 * You should hold the underlying data using doubly linked list.
 * The data in the doubly linked list is "CustomerRequest" type
 */

public class CustomerQueue {
    
    //Define member variables of ADS2Queue class
    //You should define some trackers to locate the heads and tails of underlying doubly linked list.
    public DLLNode front = null;
    public DLLNode rear = null;
    DecimalFormat df = new DecimalFormat("###.##");
    
    
    // Default Constructor
    CustomerQueue(){}
    
    //Optional: Create some helper methods below for your implementation.

    
    /**
     * @return true if the queue is empty else false if there are items available
     */
    public boolean IsEmpty() {
        if (front == null && rear == null)
            return true;
        else
            return false;
    }

    /**
     * Print all the queueing item using format "No.X request:XXX amount_to_change:£XXX"
     */
    void ListAll() {
        if (IsEmpty()){
            System.out.println("There are no customers in the list!");
        }
        else {
            DLLNode placeHolder = front;
            int count = 1;
            
            while (placeHolder.next != null) {
                System.out.println("No." + count + " request:" + placeHolder.data.request + " amount_to_change: £" + df.format(placeHolder.data.amountToChange));
                count++;
                placeHolder=placeHolder.next;
            }
        }
    }
    
    /**
     * Adds value to the queue  (remember First-in, First-out).
     * You should renew your trackers inside this method
     * @param newReques the new customer join the queue
     */
    public void Push(CustomerRequest newReques) {
        if (IsEmpty()) {
            front = new DLLNode(newReques);
            rear = front;
        }
        else {
            DLLNode newNode = new DLLNode(newReques);
            rear.next = newNode;
            rear = newNode;
        }
    }

    /**
     * Gets the next item from the queue (remember First-in, First-out) 
     * or null if there are no more items.
     * You should renew your trackers inside this method 
     * @return the virtual customer 
     */
    public CustomerRequest Pop() {
        if (IsEmpty()){
            System.out.println("The List is empty!");
            return null;
        } 
        else if (front == rear) {
            DLLNode placeHolder = front;
            front = null;
            rear = null;
            return placeHolder.data;
        }
        else {
            DLLNode placeHolder = front;
            front = placeHolder.next;
            rear = placeHolder.previous;
            return placeHolder.data;
        }
    }
}
