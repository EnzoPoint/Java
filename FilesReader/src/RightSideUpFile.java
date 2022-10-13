public class RightSideUpFile extends ReaderFile {

    /**
     * @param pathRelative
     * the path we have enter in ReaederFile.java
     */
    public RightSideUpFile(String pathRelative) {
        super(pathRelative);
    }

    /**
     * We edit function Display to display the file right side up*
     * we just return the content file in the right side up
     */
    @Override
    public void Display() {
        System.out.println("Content of the file : " + contentFile);
    }
}
