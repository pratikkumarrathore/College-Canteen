package my.collegecanteen.app.OwnerFoodPanel;

public class Owner {

    private String Area;
    private String City;
    private String ConfirmPassword;
    private String EmailID;
    private static String Fname;
    private String House;
    private static String Lname;
    private String Mobile;
    private String Password;
    private String Pincode;
    private String State;

    public Owner(String area, String city, String confirmPassword, String emailID, String fname, String house, String lname, String mobile, String password, String postcode, String state) {
        Area = area;
        City = city;
        ConfirmPassword = confirmPassword;
        EmailID = emailID;
        Fname = fname;
        House = house;
        Lname = lname;
        Mobile = mobile;
        Password = password;
        Pincode = postcode;
        State = state;
    }

    public Owner() {
        Area = "";
        City = "";
        ConfirmPassword = "";
        EmailID = "";
        Fname = "";
        House = "";
        Lname = "";
        Mobile = "";
        Password = "";
        Pincode = "";
        State = "";
    }


    public String getArea() {
        return Area;
    }

    public String getCity() {
        return City;
    }

    public String getConfirmPassword() {
        return ConfirmPassword;
    }

    public String getEmailID() {
        return EmailID;
    }

    public static String getFname() {
        return Fname;
    }

    public String getHouse() {
        return House;
    }

    public static String getLname() {
        return Lname;
    }

    public String getMobile() {
        return Mobile;
    }

    public String getPassword() {
        return Password;
    }

    public String getPostcode() {
        return Pincode;
    }

    public String getState() {
        return State;
    }
}
