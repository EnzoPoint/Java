import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public abstract class ReaderFile implements Reader {

    private final String pathRelative;

    protected ArrayList<String> contentFile;

    public ReaderFile(String pathRelative) {
        this.pathRelative = pathRelative;
        this.contentFile = new ArrayList<String>();
    }

    /**
     * @param pathRelative
     * read file and add it to Arraylist contentFile
     * @throws IOException
     */
    public void read(String pathRelative) throws IOException {

        String pathAbsolute = System.getProperty("user.dir") + pathRelative;

        try (FileInputStream fis = new FileInputStream(pathAbsolute);
             InputStreamReader isr = new InputStreamReader(fis, StandardCharsets.UTF_8);
             BufferedReader reader = new BufferedReader(isr)
        ) {

            String str;
            while ((str = reader.readLine()) != null) {
                contentFile.add(str);
            }
        } catch (IOException e) {
            System.out.println("Error : " + e.getMessage());
            System.out.println("Ignore...");
        }
    }


    /**
     * Fais rien pour l'instant
     */
    public abstract void Display();
}

