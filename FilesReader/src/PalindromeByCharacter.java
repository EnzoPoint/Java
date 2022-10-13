import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.function.UnaryOperator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PalindromeByCharacter extends ReaderFile {

    /**
     * @param pathRelative
     */
    public PalindromeByCharacter(String pathRelative) {
        super(pathRelative);
    }


    /**
     * We edit function Display to display the file right side up
     *
     * @return
     */
    @Override
    public void Display() {
        int sizeList = contentFile.size();

        ArrayList<String> contentFilePalindrome = new ArrayList<>();

        for (int i = 0; i < sizeList; i++) {
            String line  = contentFile.get(i);
            String[] world = (line.split("/[\\s+]/g"));

            for (String palindrome : world) {
                StringBuilder worldInverse = new StringBuilder(palindrome);
                palindrome = worldInverse.reverse().toString();
                contentFilePalindrome.add(palindrome);
            }
        }
        Collections.reverse(contentFilePalindrome);
        contentFile.addAll(contentFilePalindrome);
        System.out.println("Content with after the reverse character : " + contentFile);
    }
}
