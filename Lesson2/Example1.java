import java.io.File;

public class Example1 {
    public static void main(String[] args) {
        String pathProject = "Lesson2";
        String pathFile = pathProject.concat("/Example.java");
        File f3 = new File(pathFile);
        System.out.println(f3.getAbsolutePath());
    }
}