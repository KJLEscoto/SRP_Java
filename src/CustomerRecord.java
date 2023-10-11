import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

// change CustomerRecord == Main
public class CustomerRecord {
	public static void main(String[] args) throws Exception {
		Customers superClass = new Customers();

		ArrayList<Customers> customer = new ArrayList<>();

		customer = superClass.giveRecords();

		customer = MainCall(customer);
	}

	public static ArrayList<Customers> MainCall(ArrayList<Customers> customer) throws Exception {
		InputStreamReader reader = new InputStreamReader(System.in);
		BufferedReader input = new BufferedReader(reader);

		boolean flag = true;

		do {
			System.out.println("\n\n----------MENU ITEMS---------");
			System.out.println("1  --  ADD NEW RECORD");
			System.out.println("2  --  DELETE RECORD");
			System.out.println("3  --  VIEW RECORD LISTINGS");
			System.out.println("4  --  VIEW A RECORD");
			System.out.println("5  --  EXIT");
			System.out.println("-----------------------------");

			System.out.print("Enter your Choice: ");
			String response = input.readLine();

			switch (response.toUpperCase()) {
			case "1":
				// add
				new AddRecord(customer);
				break;
			case "2":
				// delete
				new DeleteRecord(customer);
				break;
			case "3":
				// view list
				break;
			case "4":
				// view record
				new ViewRecord(customer);
				break;
			case "5":
				// end of the program
				System.out.println("\nProgram Terminated...");
				System.exit(0);
				break;
			default:
				// catch invalid input
				System.out.println("\nThe value doesn't match to any options. Please try again...");
			}

		} while (flag);
		return customer;
	}
}


// to add a record
class AddRecord extends Customers {

	// initialize variable
	int found = 0;
	int count = 0;

	// to execute
	public AddRecord(ArrayList<Customers> customer) throws Exception {
		String response = null;

		do {
			System.out.println("\n\n--------ADD NEW RECORD-------");
			System.out.print("Enter Customer Number : ");
			String cNumber = input.readLine();

			// detect existing customer number
			for (Customers c : customer) {
				if (c.getCustomerNumber().equalsIgnoreCase(cNumber)) {
					found = 1;
					break;
				}
			}
			
			// detected
			if (!(found == 0)) {
				found = 0;
				System.out.println("\nLooks like this Customer Number already exist! Try different Customer Number.");
				new AddRecord(customer);
			}

			// inputs
			System.out.print("Enter Name : ");
			String cName = input.readLine();

			System.out.print("Enter Address : ");
			String cAddress = input.readLine();

			System.out.print("Enter Contact Number : ");
			String cContactNumber = input.readLine();

			// print record list
			System.out.printf(
					"\n\n---------RECORD LIST---------\nCustomer [%d]:\n%S\n%S\n%S\n%S\n\nDo you want to save? [Y|N]\nResponse: ",
					(count + 1), ("  Customer Number: " + cNumber), ("  Customer Name: " + cName),
					("  Customer Address: " + cAddress), ("  Customer Contact Number: " + cContactNumber));
			response = input.readLine();

			// to save or not
			if (response.equalsIgnoreCase("y")) {
				customer.add(new Customers(cNumber, cName, cAddress, cContactNumber));
				super.addRecords(customer);
				count++;

				System.out.printf(
						"\n  The record has been saved!\n  Please press [Enter] to return to menu or [A] to Add again.\n\nResponse: ");
				response = input.readLine();
			} else {
				System.out.printf(
						"\n  The record didn't saved!\n  Press [Enter] to return to the menu or [A] to Add again.\n\nResponse: ");
				response = input.readLine();
			}
		} while (response.equalsIgnoreCase("A"));
		CustomerRecord.MainCall(customer);
	}

}


// to delete a record
class DeleteRecord extends Customers {
	//ArrayList<Customer> customerClone = new ArrayList<>();

	// to execute
	public DeleteRecord(ArrayList<Customers> customer) throws Exception {
		String response = null;

		do {
			System.out.println("\n\n--------DELETE RECORD--------");
			if (customer.size() <= 0) {
				System.out.print(
						"\n  There is no customer records.\n  Press [1] to add new record or Press [Enter] to return to the menu...\n\nResponse: ");
				response = input.readLine();
				
				// add new record
				if(response.equalsIgnoreCase("1")) {
					new AddRecord(customer);
				}
				response = null;
				break;
			}
			System.out.print("Enter Customer Number to delete : ");
			response = input.readLine();

			int found = 0;

			// loop for checking array
			for (int i = 0; i < customer.size(); i++) {
				
				// checking for customer number
				if (customer.get(i).getCustomerNumber().equals(response)) {
					found = 1;
					System.out.printf("\n\n-------CUSTOMER DELETED------");
					System.out.printf("\n  Customer Number: %S\n  Customer Name: %S\n  Customer Address: %S\n  Customer Contact Number: %S",
							customer.get(i).getCustomerNumber(),customer.get(i).getCustomerName(),customer.get(i).getCustomerAddress(),customer.get(i).getCustomerContactNumber());
					customer.remove(i);
					System.out.print("\n\n  Deletion Complete! Press [Enter] to return to the menu or [D] to Delete again.\n\nResponse: ");
					response = input.readLine();					
				}
			}
			
			// detect unknown customer record
			if(!(found == 1)){
                found = 0;
                System.out.println("\n  Customer Record doesn't exist! Press [Enter] to return to menu or [D] to Delete again.\n\nResponse: ");
                response = input.readLine();
            }
		} while (response.equalsIgnoreCase("D"));
		CustomerRecord.MainCall(customer);
	}
}

// to view record
class ViewRecord extends Customers {
	int found = 0; 
	int count = 0; 
	String response = null;
	
	ViewRecord(ArrayList<Customers> customer) throws Exception{
		if(customer.size() <= 0){
            System.out.print("\n  There is no customer records.\n  Press [1] to add new record or Press [Enter] to return to the menu...\n\nResponse: ");
            response = input.readLine(); 
            
            if(response.equalsIgnoreCase("1")) {
				new AddRecord(customer);
			}
            response = null;
            CustomerRecord.MainCall(customer);
        }
	}
}


// customer info
class Customers {

	// it will accept input
	public InputStreamReader reader = new InputStreamReader(System.in);
	public BufferedReader input = new BufferedReader(reader);

	// declaring variables
	private String customerNumber;
	private String customerName;
	private String customerAddress;
	private String customerContactNumber;

	// arrays
	private ArrayList<Customers> customer = new ArrayList<>();

	// constructor
	public Customers(String customerNumber, String customerName, String customerAddress, String customerContactNumber) {
		super();
		this.customerNumber = customerNumber;
		this.customerName = customerName;
		this.customerAddress = customerAddress;
		this.customerContactNumber = customerContactNumber;
	}

	public Customers() {

	}

	public String toString() {
		return "\nCustomer Number=" + customerNumber + ", Customer Name=" + customerName + ", Customer Address="
				+ customerAddress + ", Customer Contact Number=" + customerContactNumber + "\n";
	}

	// setters and getters
	public void setCustomerNumber(String customerNumber) {
		this.customerNumber = customerNumber;
	}

	public String getCustomerNumber() {
		return customerNumber;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerAddress(String customerAddress) {
		this.customerAddress = customerAddress;
	}

	public String getCustomerAddress() {
		return customerAddress;
	}

	public void setCustomerContactNumber(String customerContactNumber) {
		this.customerContactNumber = customerContactNumber;
	}

	public String getCustomerContactNumber() {
		return customerContactNumber;
	}

	public void addRecords(ArrayList<Customers> customer) {
		this.customer = customer;
	}

	public ArrayList<Customers> giveRecords() {
		return customer;
	}
}
