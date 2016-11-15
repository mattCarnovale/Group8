import java.sql.Date;
/**
 * This class contains all the provider methods should contain.
 */

public class Provider extends User {
    private int memberID;

    public Provider(int userID) {
        super(userID);
    }

    //This method displays the Provider Directory.
    ////The directory stores service names, service codes, and service fees.		
    public void providerDirectoryDisplay() {		
        String providerDirectory = data.directoryLookUp();		
		
        if(providerDirectory != null) {		
            System.out.println(providerDirectory);		
        }		
        else {		
            System.out.println("Provider Directory Is Empty");		
        }		
		
    }
    
    
/**
 *Input : memberName to verify status
 *Output: True for member and false for suspended
 *This function calls propptID method and prints the status if the member
*/
    public boolean memberVerification(int verifyMember) {
	String memberType;
	boolean status = false;
	try{	
	      memberType = data.userVerification(verifyMember);//returns member, provider, manager or suspended 
	      if(memberType.equals("member")){//member is suspended
	         status = true;
	         memberID = verifyMember;
	      }
	      else if(memberType.equals("suspended")){
		 status = false;
	      }
	      else
	  	 throw new NumberFormatException();
	   }
	catch (NumberFormatException ex){
	   System.out.println("Invalid member ID. Unable to verify member. Try Again.");
	}
	return status;	
    }



    /*Name: promptID, Input: String, int Output: int
    Description: Prompts for an ID and returns a valid reponse.
    */
    protected int promptID(String name, int length) {
        System.out.print("Enter the " + name + " ID: ");
        //Read input as String and convert into int
        int idNumber = Integer.parseInt(System.console().readLine());
        if(idNumber == 0 || String.valueOf(idNumber).length() != length) {
            System.out.print("Invalid ID format. Please enter a " + length + "-digit ID number: ");
            idNumber = Integer.parseInt(System.console().readLine());
        }
        return idNumber;
    }

    /* Name: promptString
     * Input: String prompt, int maxLength
     * Output: String
     * Description: Prompts for a message and truncates input by length passed as argument.*/

    private String promptString(String prompt, int maxLength) {
        System.out.print(prompt);
        String buffer = System.console().readLine();
        return buffer.substring(0, Math.min(maxLength, buffer.length()));
    }

    /* Name: createBill
     * Input: None
     * Output: boolean
     * Description: Prompts for information required in bill and passes data as args for data access.*/
    
    public boolean createBill() {
        Date currentDate = new Date(System.currentTimeMillis());
        int serviceID = promptID("service", 6);
        boolean success = false;
        while(data.serviceVerification(serviceID) == false) {
            System.out.print("Invalid service ID!");
            serviceID = promptID("service", 6);
        }
        String comment = promptString("Enter a comment (100 max characters): ", 100);
        data.createBill(memberID, userID, serviceID, 1, currentDate, comment); 
        return true;
    }

/*********  Driver  **********/
    public void run() {
        //memberVerification()
        System.out.println("Provider driver");

    }
}
