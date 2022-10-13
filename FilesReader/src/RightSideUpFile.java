public class RightSideUpFile extends ReaderFile {

    /**
     * @param pathRelative
     */
    public RightSideUpFile(String pathRelative) {
        super(pathRelative);
    }

    /**
     * We edit function Display to display the file right side up
     *
     * @return
     */
    @Override
    public void Display() {
        System.out.println("Content of the file : " + contentFile);
    }
}
