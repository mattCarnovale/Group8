import java.util.Random;

/**
 * This class contains all the Manager method should contain
 */

public class Manager extends User {

    public Manager(int userID) {
        super(userID); 
    }

    public int randomDigitsID() {
        boolean isValidID = false;
        int newMemberID = 0;
        /*
        * Instead of having the Manager enter a 9 digit number when creating a Provider
        * this will generate a random 9 digit number for the Provider that is in the range
        * 100000000 - 999999999.
        */

        do {
            Random rand = new Random();
            newMemberID = 100000000 + rand.nextInt(899999999);
            isValidID = isIDTaken(newMemberID);
        } while(!isValidID);
        return newMemberID;
    }

    public boolean addMember() {
        int newID = randomDigitsID();
        return addOrganization("member", newID);
    }

    public boolean addProvider() {
        int newID = randomDigitsID();
        return addOrganization("provider", newID);
    }

    public boolean addOrganization(String status, int newID) {
        String tempName =  getStringAnswer(status + " name: ", 25);
        String tempAddress = getStringAnswer(status + " address: ", 25);
        String tempCity = getStringAnswer(status + " city: ", 14);
        String tempState = getStringAnswer(status + " state: ", 2);
        int tempZip = getIntAnswer(status + " zipCode: ", 5);

        boolean queryStatus = data.addOrganization(newID, tempName, tempAddress, tempCity, tempState, tempZip, status);
        return queryStatus? true:false;
    }

    public boolean isIDTaken(int id) {
        String verify = DataAccess.userVerification(id);
        return verify.equals("invalid")? true:false;
    } 


/*********  Driver  **********/
    // This function is used as the menu function when a manager is allocated
    public void run() {

        System.out.println("Manager driver");
    }
}
