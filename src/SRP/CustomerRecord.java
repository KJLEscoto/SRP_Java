package SRP;

import java.io.*;
import java.util.ArrayList;

class Customer {
    private String customerNumber;
    private String customerName;
    private String customerAddress;
    private String customerContactNumber;

    public Customer(String customerNumber, String customerName, String customerAddress, String customerContactNumber) {
        this.customerNumber = customerNumber;
        this.customerName = customerName;
        this.customerAddress = customerAddress;
        this.customerContactNumber = customerContactNumber;
    }

    public void setCustomerNumber(String newCustomerNumber)
    {
        this.customerNumber = newCustomerNumber;
    }
    
    public String getCustomerNumber()
    {
        return customerNumber;
    }
    
    public void setCustomerName(String newCustomerName)
    {
        this.customerName = newCustomerName;
    }
    
    public String getCustomerName()
    {
        return customerName;
    }
    
    public void setCustomerAddress (String newCustomerAddress)
    {
        this.customerAddress = newCustomerAddress;
    }
    
    public String getCustomerAddress()
    {
        return customerAddress;
    }
    
    public void setCustomerContactNumber(String newCustomerContactNumber)
    {
        this.customerContactNumber = newCustomerContactNumber;
    }
    
    public String getCustomerContactNumber()
    {
        return customerContactNumber;
    }

    @Override
    public String toString() {
        return String.format("%-7s%-20s%-10s%-5s%n", customerNumber, customerName, customerAddress, customerContactNumber);
    }
}

class AddRecord extends Customer {
    public AddRecord(String customerNumber, String customerName, String customerAddress, String customerContactNumber) {
        super(customerNumber, customerName, customerAddress, customerContactNumber);
    }

    public void execute(ArrayList<Customer> customers, BufferedReader br) throws IOException {
        do {
            System.out.println("-----#ADD RECORD#-----");
            // Input fields...

            String response = br.readLine();
            if (response.equalsIgnoreCase("Y")) {
                customers.add(this);
                System.out.println("-----------------------------");
                System.out.println("Successfully Saved!");
                System.out.println("-----------------------------");
            } else {
                System.out.println("Saving Unsuccessful!");
            }

            System.out.print("Input Another Record? [Y/N]: ");
            response = br.readLine();
            if (!response.equalsIgnoreCase("Y")) {
                System.out.println("-----------------------------");
                System.out.println("Press Enter Key to go back to Menu....");
                System.out.println("-----------------------------");
                br.readLine();
                break;
            }
        } while (true);
    }
}

class DeleteRecord extends Customer {
    public DeleteRecord(String customerNumber, String customerName, String customerAddress, String customerContactNumber) {
        super(customerNumber, customerName, customerAddress, customerContactNumber);
    }

    public void execute(ArrayList<Customer> customers, BufferedReader br) throws IOException {
        System.out.println("--------#DELETE RECORD#--------");
        System.out.print("Enter customer number: ");
        String findRecord = br.readLine();

        boolean found = false;

        for (Customer customer : customers) {
            if (customer.getCustomerNumber().equals(findRecord)) {
                System.out.print(customer);
                System.out.println("-----------------------------");
                System.out.print("Do you want to delete? Y/N: ");
                String response = br.readLine();
                System.out.println("-----------------------------");

                if (response.equalsIgnoreCase("Y")) {
                    customers.remove(customer);
                    System.out.print("Successfully Deleted ");
                    System.out.println(customer);
                } else {
                    System.out.println("-----------------------------");
                    System.out.println("Unsuccessfully Deleted");
                    System.out.println("-----------------------------");
                }

                found = true;
                break;
            }
        }

        if (!found) {
            System.out.println("-----No records found-----");
        }
    }
}

class ViewRecordListings extends Customer {
    public ViewRecordListings(String customerNumber, String customerName, String customerAddress, String customerContactNumber) {
        super(customerNumber, customerName, customerAddress, customerContactNumber);
    }

    public void execute(ArrayList<Customer> customers) {
        System.out.println("--------#VIEW RECORD LISTINGS#--------");
        for (Customer customer : customers) {
            System.out.print(customer);
        }

        System.out.println("-----------------------------");
        System.out.println("Press Enter Key to go back to Menu....");
    }
}

class ViewRecord extends Customer {
    public ViewRecord(String customerNumber, String customerName, String customerAddress, String customerContactNumber) {
        super(customerNumber, customerName, customerAddress, customerContactNumber);
    }

    public void execute(ArrayList<Customer> customers, BufferedReader br) throws IOException {
        System.out.println("--------#VIEW RECORD#--------");
        System.out.print("Enter customer number: ");
        String findRecord = br.readLine();

        for (Customer customer : customers) {
            if (customer.getCustomerNumber().equals(findRecord)) {
                System.out.print(customer);
                System.out.println("-----------------------------");
                System.out.print("Do you want to modify? [Y/N]: ");
                System.out.println("-----------------------------");
                String response = br.readLine();

                if (response.equalsIgnoreCase("Y")) {
                    modifyRecord(br);
                }
                break;
            }
        }
    }

    private void modifyRecord(BufferedReader br) throws IOException {
        System.out.println("-----#MODIFY RECORD#-----");
        // Modify customer properties...

        System.out.print("Save? [Y/N]: ");
        String response = br.readLine();

        if (response.equalsIgnoreCase("Y")) {
            System.out.println("-----------------------------");
            System.out.println("Successfully Saved!");
            System.out.println("-----------------------------");
        } else {
            System.out.println("-----------------------------");
            System.out.println("Saving Unsuccessful!");
            System.out.println("-----------------------------");
        }
    }
}

public class CustomerRecord {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        ArrayList<Customer> customers = new ArrayList<>();

        boolean done = true;

        do {
            System.out.println("----------MENU ITEMS---------");
            System.out.println("1.\tADD NEW RECORD");
            System.out.println("2.\tDELETE RECORD");
            System.out.println("3.\tVIEW RECORD LISTINGS");
            System.out.println("4.\tVIEW A RECORD");
            System.out.println("5.\tEXIT");
            System.out.println("-----------------------------");

            System.out.print("Enter your Choice: ");
            String response = br.readLine();

            switch (response) {
                case "1":
                    new AddRecord("", "", "", "").execute(customers, br);
                    break;
                case "2":
                    new DeleteRecord("", "", "", "").execute(customers, br);
                    break;
                case "3":
                    new ViewRecordListings("", "", "", "").execute(customers);
                    break;
                case "4":
                    new ViewRecord("", "", "", "").execute(customers, br);
                    break;
                case "5":
                    done = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        } while (done);
    }
}
//
//
//import java.util.ArrayList;
//import java.util.Collections;
//import java.io.InputStreamReader;
//import java.io.BufferedReader;
//
//public class Main{
//
//    public static void main(String[]args) throws Exception{
//        
//        Students superClass = new Students();
//
//        ArrayList<Students> students = new ArrayList<>();
//        
//        students = superClass.giveRecords();
//
//        students = MainCall(students);
//
//    }
//
//    public static ArrayList<Students> MainCall(ArrayList<Students> students) throws Exception{
//
//        InputStreamReader reader = new InputStreamReader(System.in);
//        BufferedReader input = new BufferedReader(reader);
//
//        boolean flag = true;
//
//        do{
//            System.out.printf("\nMENU ITEMS:\n\t1.) ADD NEW RECORD\t2.) DELETE RECORD\t3.) VIEW RECORD LIST\t4.) VIEW A RECORD \t5.) EXIT\nResponse: ");
//            String resp = input.readLine();
//
//            switch(resp.toUpperCase()){
//                case "1":
//                    CreateClass create = new CreateClass(students);
//                    break;
//                case "2":
//                    DeleteRecords delete = new DeleteRecords(students);
//                    break;
//                case "3":
//                    int count = 0;
//                    if (students.size() <= 0){
//                        System.out.println("\n\tThere is no student records. Please add new record by press 1 in the menu. Press enter to return to the menu...");
//                        resp = input.readLine(); resp = null;
//                    }
//                    else{
//                        System.out.print("\n\nRecordList:");
//                    }
//                    for(Students p: students){
//                        System.out.printf("\n\nStudent [%d]:\n%-7S%-10S%-10S%-7S\n", (count+1), ("\tStudent ID: " + students.get(count).getid()), ("\tStudent Name: " + students.get(count).getName()), ("\tStudent Course: " + students.get(count).getCourse()), ("\tStudent Year: " + students.get(count).getYear())); count++; };
//                        
//                    break;
//                case "4":
//                    ViewRecord view = new ViewRecord(students);
//                    break;
//                case "5":
//                    System.out.println("Terminating Program..."); System.exit(0);
//                    break;
//                default:
//                    System.out.println("The value doesn't match to any options. Please try again...");
//            }
//        }while(flag);
//        
//        return students;
//    }
//}
//
//class ViewRecord extends Students{
//
//    InputStreamReader reader = new InputStreamReader(System.in);
//    BufferedReader input = new BufferedReader(reader); 
//
//    int ifound = 0; int count = 0; String resp = null;
//
//    ViewRecord(ArrayList<Students>students) throws Exception{
//
//        if(students.size() <= 0){
//            System.out.println("\n\tThere is no student records. Please add a record by pressing 1 in the menu.\n\t\tPress enter to return to the menu...");
//            resp = input.readLine(); resp = null;
//            Main runner = new Main(); Main.MainCall(students);
//        }
//
//        do{
//
//            System.out.printf("\nEnter Student ID to view:\nResponse: ");
//            String resp = input.readLine();
//
//            for(Students p: students){
//                if(p.getid().equals(resp)){
//                    System.out.printf("\nStudent Records:\nStudent ID: %S\nStudent Name: %S\nStudent Course: %S\nStudent Year: %S\n\nDo you want to modify the existing recod? [Yes|No]:\nResponse: ", p.getid(), p.getCourse(), p.getName(), p.getYear());
//                    ifound = 1; resp = input.readLine();
//                    
//                    if(ifound == 1 && resp.toUpperCase().equals("YES") || resp.toUpperCase().equals("Y")){
//                    System.out.printf("\nEnter new name:\nResponse: ");
//                    String newName = input.readLine();
//                    students.set(count, p).setName(newName);
//
//                    System.out.printf("\nEnter new course:\nResponse: ");
//                    String newCourse = input.readLine();
//                    students.set(count, p).setCourse(newCourse);
//
//                    System.out.printf("\nEnter new year:\nResponse: ");
//                    String newYear = input.readLine();
//                    students.set(count, p).setYear(newYear);;
//
//                    System.out.printf("\n\t\tModification Success! Press enter to return to menu or No to continue.\nResponse: ");
//                    resp = input.readLine();
//                    }
//                    
//                    super.addRecords(students);
//
//                    Main runner = new Main(); Main.MainCall(super.giveRecords());
//
//                    break;
//                }
//                count++;
//            }
//
//            if(!(ifound == 1)){
//                System.out.printf("\n\tStudent Records doesn't exist! Press enter to return to the menu or No to continue.\nResponse: ");
//                resp = input.readLine(); ifound = 0; continue;
//            }
//
//            
//        }while(resp.toUpperCase().equals("NO") || resp.toUpperCase().equals("N"));
//    }
//}
//class DeleteRecords extends Students{
//
//    ArrayList<Students> studentsClone = new ArrayList<>();
//    
//    public DeleteRecords(ArrayList<Students> students) throws Exception{
//
//        InputStreamReader reader = new InputStreamReader(System.in);
//        BufferedReader input = new BufferedReader(reader);  
//        
//        String resp = null;
//
//        do{
//            if (students.size() <= 0){
//                System.out.println("\n\tThere is no student records. Please add new record by press 1 in the menu. Press enter to return to the menu...");
//                resp = input.readLine(); resp = null;
//                break;
//            }
//            System.out.printf("\nEnter Student ID to delete:\nResponse: ");
//            resp = input.readLine();
//
//            int ifound = 0;
//
//            for(int i = 0; i < students.size(); i++){
//                if (students.get(i).getid().equals(resp)){
//                    ifound = 1;
//
//                    System.out.printf("\nStudent Records:\nStudent ID: %S\nStudent Name: %S\nStudent Course: %S\nStudent Year: %S", students.get(i).getid(), students.get(i).getCourse(), students.get(i).getName(), students.get(i).getYear());
//                    
//                    students.remove(i);
//                    System.out.printf("\n\n\tDeletion complete! Please press enter to return to the menu or No to continue.\nResponse:");
//                    resp = input.readLine();
//                    break;
//                }
//            }
//
//            if(!(ifound == 1)){
//                ifound = 0;
//                System.out.println("\tStudent Record doesn't exit! Please press enter to return to menu or No to continue.\nResponse:");
//                resp = input.readLine();
//            }
//        }while(resp.toUpperCase().equals("NO") || resp.toUpperCase().equals("N"));
//    }
//}
//
//
//class CreateClass extends Students{
//
//    InputStreamReader reader = new InputStreamReader(System.in);
//    BufferedReader input = new BufferedReader(reader);  
//
//    int ifound = 0;
//
//    public CreateClass(ArrayList<Students> students) throws Exception{
//    
//    String resp = null;
//
//    int count = 0;
//
//    do{
//        System.out.printf("\nEnter id : ");
//        String id = input.readLine();
//
//        
//        for(Students p: students){
//            if(p.getid().equals(id)){
//                ifound = 1;
//                break;
//            }
//        }
//
//        if (!(ifound == 0)){
//            ifound = 0;
//            System.out.println("\tThis Student ID already exist! Please try different Student ID");
//            CreateClass create = new CreateClass(students);
//        }
//
//        System.out.printf("\nEnter name : ");
//        String name = input.readLine();
//
//        System.out.printf("\nEnter course : ");
//        String course = input.readLine();
//
//        System.out.printf("\nEnter year : ");
//        String year = input.readLine();
//
//        System.out.printf("\n\nRecordList:\n\nStudent [%d]:\n%-7S%-10S%-10S%-7S\n\nDo you want to save? [Yes|No]\nResponse: ", (count+1), ("\tStudent ID: " + id), ("\tStudent Name: " + name), ("\tStudent Course: " + course), ("\tStudent ID: " + year));
//        resp = input.readLine(); count = 0;
//
//        if(resp.toUpperCase().equals("YES") || resp.toUpperCase().equals("Y")){
//            students.add(new Students(id, name, course, year));
//            super.addRecords(students);
//            System.out.printf("\n\tThe record has been saved!\n\t\tPlease press enter to return to menu or No to continue.\nResponse: ");
//            resp = input.readLine();
//        }else{
//            System.out.printf("\n\tThe record didn't saved!\n\t\tPress Enter to return to the menu or No to continue.\nResponse: ");
//            resp = input.readLine();
//        }
//    }while(resp.toUpperCase().equals("NO") || resp.toUpperCase().equals("N"));
//    Main runner = new Main(); Main.MainCall(students);
//    }
//}
//
//class Students{
//    
//    private String id;
//    private String name;
//    private String course;
//    private String year;
//
//    private ArrayList<Students> students = new ArrayList<>();
//
//    public Students(String id, String name, String course, String year){
//        super(); this.id = id; this.name = name; this.course = course; this.year = year;
//    }
//
//    public Students(){
//    }
//
//    public String toString(){
//        return "\nStudent id=" + id + ", name=" + name + ", course=" + course + ", year=" + year +"\n";
//    }
//
//    public void setId(String id){
//        this.id = id;
//    }
//    
//    public void setName(String name){
//        this.name = name;
//    }
//
//    public void setCourse(String course){
//        this.course = course;
//    }
//
//    public void setYear(String year){
//        this.year = year;
//    }
//
//    public String getid(){
//        return id;
//    }
//
//    public String getName(){
//        return name;
//    }
//
//    public String getCourse(){
//        return course;
//    }
//
//    public String getYear(){
//        return year;
//    }
//
//    public void addRecords(ArrayList<Students>students){
//        this.students = students;
//    }
//
//    public ArrayList<Students> giveRecords(){
//        return students;
//    }
//}