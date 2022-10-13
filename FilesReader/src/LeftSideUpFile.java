import java.util.Arrays;
import java.util.Collections;

public class LeftSideUpFile extends ReaderFile {

    /**
     * @param pathRelative
     */
    public LeftSideUpFile(String pathRelative) {
        super(pathRelative);
    }

    /**
     * We edit function Display to display the file right side up
     *
     * @return just return content with the reverse
     */
    @Override
    public void Display() {
        Collections.reverse(contentFile);
        System.out.println("Content after the reverse : " + contentFile);
    }
}
