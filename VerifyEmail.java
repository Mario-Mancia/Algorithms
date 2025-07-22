//Code written by Mario-Mancia
//Java class to verify if a user email is valid.
import java.util.Objects;
import java.util.Scanner;

public class VerifyEmail {
    public static void main (String[] args) {
        Scanner sc = new Scanner(System.in);
        String email = "";
        do {
            System.out.println("Enter your email: ");
            email = sc.nextLine();
            if(Objects.equals(validateEmail(email), "Valid email")) {
                System.out.println("Your email is valid");

            } else {
                System.out.println("Error: " + validateEmail(email) + " Try again.");
            }
        } while(!Objects.equals(validateEmail(email), "Valid email"));
    };

    public static String validateEmail(String _email) {
        char[] emailChars = _email.toCharArray(); //This function convert String type to char.
        int arrobaCounter = 0;
        int arrobaIndex = 0;
        String emailDomain = "";
        String localAddress = "";

        for(int i = 0; i < emailChars.length; i++) {
            if(emailChars[0] == 64) {
                return "The @ character can not be in the first position.";
            }
            if(emailChars[i] == 64) {
                arrobaIndex = i;
                arrobaCounter++;
            }
            if(!isValidAsciiChar(emailChars[i])) {
                return "You can not use especial characters as %, /, #, ), etc.";
            }
        }
        if(arrobaCounter != 1) {
            return "You only can use @ character once time.";
        }
        //Rebuild the string after @ character and evaluate if is a valid domain
        for(int i = arrobaIndex + 1; i < emailChars.length; i++) {
            emailDomain += emailChars[i];
        }
        if(!isValidDomain(emailDomain)) {
            return "Your email domain provider do not exists.";
        }
        //Rebuild the string before @ and evaluate if its length is appropriate.
        for(int i = 0; i < arrobaIndex; i++) {
            localAddress += emailChars[i];
        }
        if(localAddress.length() < 5 && localAddress.length() > 50) {
            return "At least 5 normal characters required before @ character.";
        }
        return "Valid email";
    };

    /* The following function, evaluates all invalid ASCII char ranges, if character is equals
    to any invalid char, returns false immediately*/
    public static boolean isValidAsciiChar(char character) {
        for(int j = 32; j <= 44; j++) {
            if(character == j) {
                return false;
            }
        }
        if(character == 47) {
            return false;
        }
        //Values between these buckles are digits (48-57)
        for(int j = 58; j <= 63; j++) {
            if(character== j) {
                return false;
            }
        }
        //Values between these buckles are Uppercase characters and '@' (64-90)
        for(int j = 91; j <= 94; j++) {
            if(character == j) {
                return false;
            }
        }
        if(character == 96) {
            return false;
        }
        //Values between these buckles are Lowercase characters (97-122)
        for(int j = 123; j <= 163; j++) {
            if(character == j) {
                return false;
            }
        }
        //Valid characters: 164 = ñ; 165 = Ñ
        for(int j = 166; j <= 255; j++) {
            if(character == j) {
                return false;
            }
        }
        return true;
    };
    //The following function validate if email domain is in the string array.
    public static boolean isValidDomain(String domain) {
        String[] validDomains = {"gmail.com",
                "outlook.es",
                "uma.edu.sv",
                "yahoo.com",
                "gob.sv"
        };

        for(int i = 0; i < validDomains.length; i++) {
            if(Objects.equals(domain, validDomains[i])) {
                return true;
            }
        }
        return false;
    }
}
