import java.io.IOException;
import java.util.Scanner;

public class MainReader {

    public static void main(String[] args) throws IOException {

        /// Ask user to input PATH for the 2 files
        Scanner reader = new Scanner(System.in); // Reading from System.in
        System.out.println("Enter the first path of the file : ");
        String path1String = reader.next();
        System.out.println("Enter the second path of the file : ");
        String path2String = reader.next();
        reader.close();

        System.out.println("");
        System.out.println("In first, we are going to display the method right side up");
        System.out.println("");
        ////////////  Method Right Side Up File \\\\\\\\\\\\\

        /// Start the process for file 1
        RightSideUpFile rightSideUpFile1 = new RightSideUpFile(path1String);
        /// Start the process for file 2
        RightSideUpFile rightSideUpFile2 = new RightSideUpFile(path2String);
        /// Read file 1
        rightSideUpFile1.read(path1String);
        /// Read file 2
        rightSideUpFile2.read(path2String);
        /// Display the 2 files in RightSideUp
        rightSideUpFile1.Display();
        rightSideUpFile2.Display();

        System.out.println("");
        System.out.println("Now, we are going to display the method left side up");
        System.out.println("");
        ////////////  Method Left Side Up File \\\\\\\\\\\\\

        /// Start the process for file 1
        LeftSideUpFile leftSideUpFile1 = new LeftSideUpFile(path1String);
        /// Start the process for file 2
        LeftSideUpFile leftSideUpFile2 = new LeftSideUpFile(path2String);
        /// Read file 1
        leftSideUpFile1.read(path1String);
        /// Read file 2
        leftSideUpFile2.read(path2String);
        /// Display the 2 files in LeftSideUp
        leftSideUpFile1.Display();
        leftSideUpFile2.Display();

        System.out.println("");
        System.out.println("Now, we are going to display the method palindrome (so we reverse all world)");
        System.out.println("");
        ////////////  Method Palindrome File \\\\\\\\\\\\\

        /// Start the process for file Exe1
        PalindromeByCharacter PalindromeByCharacterFile1 = new PalindromeByCharacter(path1String);
        /// Start the process for file Exe1
        PalindromeByCharacter PalindromeByCharacterFile2 = new PalindromeByCharacter(path2String);
        /// Read file 1
        PalindromeByCharacterFile1.read(path1String);
        /// Read file 2
        PalindromeByCharacterFile2.read(path2String);
        /// Display the 2 files in LeftSideUp
        PalindromeByCharacterFile1.Display();
        PalindromeByCharacterFile2.Display();
    }

}
