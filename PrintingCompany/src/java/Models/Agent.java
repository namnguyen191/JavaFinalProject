/*
 * I'm an idiot 2.0
 */
package Models;

/**
 *
 * @author jpsza
 */
public class Agent {
    private int id;
    private String fName, lName, phone, email;
    
    public Agent(){
        
    }
    
    public Agent(String fName, String lName, String phone, String email){
        this.fName = fName;
        this.lName = lName;
        this.phone = phone;
        this.email = email;
    }
    
    public Agent(int id, String fName, String lName, String phone, String email){
        this.id = id;
        this.fName = fName;
        this.lName = lName;
        this.phone = phone;
        this.email = email;
    }

    public Agent(int id) {
        
    }

    public int getId() {
        return id;
    }

    public String getfName() {
        return fName;
    }

    public String getlName() {
        return lName;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
}
