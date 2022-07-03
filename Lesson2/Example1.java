import java.io.File;
import java.util.logging.*;

public class Example1 {
    public static void main(String[] args) {
        String pathProject = "Lesson2";
        String pathDir = pathProject.concat("/Example.java");
        File dir = new File(pathDir);
        System.out.println(dir.getAbsolutePath());
        if (dir.mkdir()) {
            System.out.println("+");
        } else {
            System.out.println("-");
        }
        for (String fname : dir.list()) {
            System.out.println(fname);
        }

        Logger logger = Logger.getLogger(Example1.class.getName());
        logger.setLevel(Level.INFO);
        ConsoleHandler ch = new ConsoleHandler();
        logger.addHandler(ch);
        // SimpleFormatter sFormat = new SimpleFormatter();
        // ch.setFormatter(sFormat);
        XMLFormatter xml = new XMLFormatter();
        ch.setFormatter(xml);
        logger.log(Level.WARNING, "Testing test 1");
        logger.info("Testing test 2");
    }
}