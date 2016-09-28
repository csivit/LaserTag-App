package csivit.rakshith.lasertag;

/**
 *
 * @author rakshith
 */
public class Player {
    private String name;
    private String registrationNumber;
    private String email;
    private String phoneNumber;
    private char gender;

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    
    public String getRegistrationNumber() {
        return registrationNumber;
    }
    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public char getGender() {
        return gender;
    }
    public void setGender(char gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return registrationNumber + " - " + name;
    }
}
