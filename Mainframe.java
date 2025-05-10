import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

public class Mainframe {
    private static int MAX_DATE = 60;
    private ArrayList<InsuredPerson> clients;
    
    public Mainframe() {
        this.clients = new ArrayList<>();
    }

    public void addClient(String fullName, String DOB) {
        clients.add(new InsuredPerson(fullName, DOB));
    }

    public void addClaim(int clientIndex, String claimDate, double claimValue) {
        if (clientIndex >= 0 && clientIndex < clients.size()) {
            InsuredPerson client = clients.get(clientIndex);
            client.addClaim(claimDate, claimValue);
        }
    }

    public boolean validateDayRange(LocalDate claimDate) {
        LocalDate today = LocalDate.now();
        return !claimDate.isAfter(today) && !claimDate.isBefore(today.minusDays(MAX_DATE));
    }

    public void processClaims() {
        for (InsuredPerson client : clients) {
            double totalDisbursements = 0.0; 
    
            for (Claim claim : client.getClaims()) {
                boolean valid = validateDayRange(claim.getClaimDate());
                claim.disburse(valid); 
    
                if (valid) {
                    totalDisbursements += claim.getClaimValue(); 
                } else {
                    claim.setClaimNote("CLAIM OLDER THAN 60 DAYS / INVALID DATE");
                }
            }
            client.recordDisbursements(totalDisbursements); 
        }
    }


    public String getReports() {
        StringBuilder report = new StringBuilder();
        for (InsuredPerson client : clients) {
            report.append(client.toString()).append("\n");
        }
        return report.toString();
    }
    
    //Website for DateTimeFormatter and DateTimeParseException
    //https://labex.io/tutorials/java-how-to-handle-java-time-format-datetimeparseexception-417320
    public static LocalDate convertDate(String dateString) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            return LocalDate.parse(dateString, formatter);
        } catch (DateTimeParseException e) {
            return null;
        }
    }
}
