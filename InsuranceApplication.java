//816034459 Assignment 1
import java.util.Scanner;
import java.io.File;

public class InsuranceApplication {
    public static void main(String[] args) {
        Mainframe mainframe = new Mainframe();
        String filePath = "data.txt"; 
        
        try {
            File file = new File(filePath);
            Scanner sc = new Scanner(file);
            String line;
            boolean isInsuredPersonSection = true;

            while (sc.hasNextLine()) {
                line = sc.nextLine();
                String[] parts = line.split(", ");
                if (isInsuredPersonSection) {
                    try {
                        Integer.parseInt(parts[0]);
                        isInsuredPersonSection = false;
                    } catch (NumberFormatException e) {
                        String name = parts[0];
                        String birthDate = parts[1];
                        mainframe.addClient(name, birthDate);
                    }
                }

                if (!isInsuredPersonSection) {
                    int insuredPersonId = Integer.parseInt(parts[0]);
                    String claimDate = parts[1];
                    double amount = Double.parseDouble(parts[2]);
                    mainframe.addClaim(insuredPersonId, claimDate, amount);
                }
            }

            mainframe.processClaims();
            System.out.println(mainframe.getReports());

        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }
}
