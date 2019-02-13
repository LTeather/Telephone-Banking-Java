package telephonebanking;

public class DLLNode {
    public CustomerRequest data;
    public DLLNode previous;
    public DLLNode next;
    
    //Constructor
    public DLLNode(CustomerRequest value) {
        data     = value; 
        previous = null;
        next     = null;
    }
}
