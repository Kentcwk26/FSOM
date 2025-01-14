import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class ManageProfile {
    // Declare protected variables to be inherited by child classes
    protected String username, password, name, ic, gender, contact, email, userType;
    // Declare private variable to count number of duplicate admin IDs
    private int numRecord = 0;

    // A constructor for the Customer class, which initializes a new instance of the class with the given parameters
    public ManageProfile(String username, String password, String name, String ic, String gender, String contact, String email, String userType) {
        this.username = username;                           // Assign the parameter 'username' to the class variable 'username'
        this.password = password;                           // Assign the parameter 'password' to the class variable 'password'
        this.name = name;                                   // Assign the parameter 'name' to the class variable 'name'
        this.ic = ic;                                       // Assign the parameter 'ic' to the class variable 'ic'
        this.gender = gender;                               // Assign the parameter 'gender' to the class variable 'gender'
        this.contact = contact;                             // Assign the parameter 'contact' to the class variable 'contact'
        this.email = email;                                 // Assign the parameter 'email' to the class variable 'email'
        this.userType = userType;                           // Assign the parameter 'userType' to the class variable 'userType'
    }

    // Define a method named "checkDuplicateRecord" that takes a string parameter named "record"
    public int checkDuplicateRecord(String record) {
        // Create a new BufferedReader object to read from a file named "user.txt"
        try (BufferedReader br = new BufferedReader(new FileReader("user.txt"))) { 
            // Declare a string variable named "line"
            String line;
            // Read each line from the file until the end of the file is reached
            while ((line = br.readLine()) != null) {
                // Split the line into an array of strings using a comma and a space as the delimiter
                String[] tokens = line.split(",");
                // Extract the first element of the array and assigns it to a new string variable named "adminId"
                String Id = tokens[0]; 
                // Check if the admin ID passed as a parameter contains the extracted admin ID
                if (record.contains(Id)) {
                    // increments the value of a variable named "adminID" if the admin ID is found
                    numRecord++;                         
                }
            }
        // Catch an IOException that may occur during file input/output operations and print the stack trace of the caught exception
        } catch (IOException ex) {
            ex.printStackTrace();                          
        }
        // Return the number of duplicate admin IDs found in the file
        return numRecord;
    }
    
    // Define a method named "add" that takes a string parameter named "id"
    public String add(String id) {
        try {
            // Open a FileWriter to append to the end of the "admin.txt" file
            FileWriter admin = new FileWriter("user.txt", true);

            // Create a PrintWriter to write to the FileWriter
            try (PrintWriter system = new PrintWriter(admin);) {
                // Write a comma-separated string of admin information to the file
                // The admin ID is passed in as a parameter, while the other information is obtained from the instance variables of the current object
                system.write(id+","+name + "," + ic + "," + gender + "," + contact + "," + email +"\n");
            }
        // If there is an error, return the exception message as a string
        } catch (IOException ex) {    
            return ex.toString();
        }
        // If there is no error, return a string indicating that the write was successful
        return "Complete";
    }

    // Define a method named "edit" that takes a string parameter named "search"
    public String edit(String id) { 
        // Begin a try block
        try { 
            // Create a new file object named "tempFile" that represents a file named "user.temp"
            File tempFile = new File("user.temp"); 
            // Create a new file object named "adminFile" that represents a file named "user.txt"
            File userFile = new File("user.txt"); 
            // Create a boolean variable named "found" and initializes it to false
            boolean found = false; 
            // Create a new buffered reader object named "reader" that reads from the "userFile" file
            try (BufferedReader reader = new BufferedReader(new FileReader(userFile)); 
                // Create a new print writer object named "writer" that writes to the "tempFile" file
                PrintWriter writer = new PrintWriter(new FileWriter(tempFile))) { 
                // Declare a string variable named "currentLine"
                String currentLine;
                // Begin a while loop that reads each line of the "adminFile" file until there are no more lines
                while ((currentLine = reader.readLine()) != null) { 
                    // Split the current line into an array of strings using the delimiter ", "
                    String[] fields = currentLine.split(","); 
                    // Check if the first field of the current line matches the search parameter
                    if (fields[0].equals(id)) { 
                        // Replace the second field with the instance variable "name"
                        fields[1] = name;
                        // Replace the third field with the instance variable "ic"
                        fields[2] = ic; 
                        // Replace the fourth field with the instance variable "gender"
                        fields[3] = gender; 
                        // Replace the fifth field with the instance variable "contact"
                        fields[4] = contact; 
                        // Replace the sixth field with the instance variable "email"
                        fields[5] = email; 
                        // Replace the seventh field with the instance variable "address"
                        writer.println(String.join(",", fields)); 
                        // Sets the "found" variable to true
                        found = true; 
                    // If the first field of the current line does not match the search parameter
                    } else { 
                        // Writes the current line to the "tempFile" file
                        writer.println(currentLine); 
                    }
                }
            } 
            // If the search parameter was not found in the file
            if (!found) { 
                // Delete the "tempFile" file
                tempFile.delete(); 
                // Return a message indicating that the record was not found
                return "Record not found"; 
            }
            // Delete the "adminFile" file
            userFile.delete(); 
            // Rename the "tempFile" file to "adminFile"
            tempFile.renameTo(userFile); 
            // Return a message indicating that the record was successfully updated
            return "Success"; 
        // Catch any IOException that may occur and return a string representation of the exception
        } catch (IOException ex) {
            return ex.toString(); 
        }
    }
    
}