package my.collegecanteen.app;

public class Customer {

    private String ConfirmPassword,Department,EmailId,EnrollmentNo,Fname,Lname,Mobile,Password,Semester;

    public Customer(String confirmPassword, String department, String emailId, String enrollmentNo, String fname, String lname, String mobile, String password, String semester) {
        ConfirmPassword = confirmPassword;
        this.Department = department;
        EmailId = emailId;
        EnrollmentNo = enrollmentNo;
        Fname = fname;
        Lname = lname;
        Mobile = mobile;
        Password = password;
        Semester = semester;
    }

    public Customer(){

        ConfirmPassword ="";
        Department = "";
        EmailId ="";
        EnrollmentNo ="";
        Fname ="";
        Lname ="";
        Mobile ="";
        Password ="";
        Semester ="";
    }


    public String getConfirmPassword() {
        return ConfirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        ConfirmPassword = confirmPassword;
    }

    public String getDepartment() {
        return Department;
    }

    public void setDepartment(String department) {
        Department = department;
    }

    public String getEmailId() {
        return EmailId;
    }

    public void setEmailId(String emailId) {
        EmailId = emailId;
    }

    public String getEnrollmentNo() {
        return EnrollmentNo;
    }

    public void setEnrollmentNo(String enrollmentNo) {
        EnrollmentNo = enrollmentNo;
    }

    public String getFname() {
        return Fname;
    }

    public void setFname(String fname) {
        Fname = fname;
    }

    public String getLname() {
        return Lname;
    }

    public void setLname(String lname) {
        Lname = lname;
    }

    public String getMobile() {
        return Mobile;
    }

    public void setMobile(String mobile) {
        Mobile = mobile;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getSemester() {
        return Semester;
    }

    public void setSemester(String semester) {
        Semester = semester;
    }
}

