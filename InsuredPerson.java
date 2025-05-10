//816034459 Assignment 1
import java.time.LocalDate;
import java.util.ArrayList;
import java.time.format.DateTimeFormatter;

public class InsuredPerson {
    private String fullName;
    private LocalDate DOB;
    private ArrayList<Claim> claims;
    private double disbursements;

    public InsuredPerson(String fullName, String DOB) {
        this.fullName = fullName;
        this.DOB = parseDate(DOB);
        this.claims = new ArrayList<>();
        this.disbursements=0.00;
    }
    
    private LocalDate parseDate(String DOB) {
        // removing st, nd, rd, th from the date
        //website: https://www.geeksforgeeks.org/java-string-replaceall-method/
        String birthDateString = DOB.replaceAll("(\\d+)(st|nd|rd|th)", "$1");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMMM yyyy");
        return LocalDate.parse(birthDateString, formatter);
    }
    
    public String getInitials() {
        String[] nameParts = getFullName().split(" ");
        StringBuilder initials = new StringBuilder();
        for (String part : nameParts) {
            initials.append(part.charAt(0));
        }
        return initials.toString().toUpperCase();
    }

    public String getFullName() {
        return fullName;
    }

    public LocalDate getDOB() {
        return DOB;
    }

    public ArrayList<Claim> getClaims() {
        return this.claims;
    }

    public void addClaim(String claimDate, double claimValue) {
        claims.add(new Claim(claimDate, claimValue, getInitials()));
    }
    
    //Website for getYear and getMonth: https://www.baeldung.com/java-year-month-day
    public int getAge() {
        LocalDate now = LocalDate.now();
        int age = now.getYear() - getDOB().getYear();
        
        //if the month has not passed yet, then the age would be one year less
        if (now.getMonthValue() < getDOB().getMonthValue() ||
            (now.getMonthValue() == getDOB().getMonthValue() && now.getDayOfMonth() < getDOB().getDayOfMonth())) {
            age--;
        }
        return age;
    }

    public double getDisbursements() {
        return disbursements;
    }

    public void recordDisbursements(double value) {
        this.disbursements = value; 
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getFullName()).append(" ").append(DOB).append(" ").append(getAge()).append(" YRS\n");

        for (Claim claim : claims) {
            sb.append(claim.toString()).append("\n");
        }

        sb.append("DISBURSEMENTS: $").append(String.format("%.2f", getDisbursements())).append("\n");

        return sb.toString();
    }
}
