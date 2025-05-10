//816034459 Assignment 1
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Claim {
    private String claimNo;
    private static int claimCounter = 0;
    private LocalDate claimDate;
    private LocalDate processedDate;
    private double claimValue;
    private boolean disbursed;
    private String claimNote;

    public Claim(String claimDate, double claimValue, String claimOwnerInitials) {
        this.claimDate = parseDate(claimDate);
        this.claimValue = claimValue;
        this.claimNo = claimOwnerInitials.toUpperCase() + incrementClaimCounter();
        this.disbursed = false;
        this.claimNote = "";
    }
    
    private LocalDate parseDate(String claimDate) {
        //Website: https://www.geeksforgeeks.org/java-string-replaceall-method/
        //removing st, nd, rd, th
        String claimStringFormatted = claimDate.replaceAll("(\\d+)(st|nd|rd|th)", "$1");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMMM yyyy");
        return LocalDate.parse(claimStringFormatted, formatter);
    }
    
    private int incrementClaimCounter() {
        return ++claimCounter;
    }

    public String getClaimNo() {
        return claimNo;
    }

    public LocalDate getClaimDate() {
        return claimDate;
    }

    public double getClaimValue() {
        return claimValue;
    }

    public void disburse(boolean disbursed) {
        this.disbursed = disbursed;
        this.processedDate = LocalDate.now();
    }

    public void setClaimNote(String note) {
        if (!claimNote.isEmpty()) {
            claimNote += "\n ";
        }
        claimNote += note;
    }
    
    @Override
    public String toString() {
        String claimValueFormatted = "$" + String.format("%.2f", getClaimValue());
        String disbursedStatus = disbursed ? "Y" : "N";
        String processedDateStr = (processedDate != null) ? processedDate.toString() : "null";
        
        //Website for stringbuilder: https://www.geeksforgeeks.org/stringbuilder-class-in-java-with-examples/
        //Website for append: https://www.codecademy.com/resources/docs/java/stringbuilder/append
        
        StringBuilder sb = new StringBuilder();
        sb.append("CLAIM NO: ").append(getClaimNo()).append(" ")
                .append(claimDate.toString()).append(" ")
                .append(claimValueFormatted).append(" ")
                .append("PAID: ").append(disbursedStatus).append(" ")
                .append("PROCESSED ON: ").append(processedDateStr);

        if (!claimNote.isEmpty()) {
            sb.append("\n ").append("NOTE: ").append(claimNote);
        }

        return sb.toString();
    }
}
