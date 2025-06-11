import java.io.File;
import java.util.concurrent.atomic.AtomicInteger;

public class Assignment1Solved {
    public static void main(String[] args) {

        File folder = new File("C:\\Users\\HP\\Documents\\SEM 4\\REAL-TIME P\\Tutorial\\src");

        if (!folder.exists() || !folder.isDirectory()) {
            System.out.println("The specified path is not a valid directory.");
            return;
        }

        AtomicInteger javaFileCount = new AtomicInteger(0);
        AtomicInteger solvedProblemCount = new AtomicInteger(0);

        countJavaFiles(folder, javaFileCount, solvedProblemCount);

        System.out.println("Number of Java Files = " + javaFileCount.get());
        System.out.println("Number of Issues = " + solvedProblemCount.get());
    }

    public static void countJavaFiles(File folder, AtomicInteger javaFileCount, AtomicInteger solvedProblemCount) {
        File[] files = folder.listFiles();
        if (files == null) return;

        for (File file : files) {
            if (file.isDirectory()) {
                countJavaFiles(file, javaFileCount, solvedProblemCount);
            } else if (file.getName().endsWith(".java")) {
                javaFileCount.incrementAndGet();

                if (file.getName().toLowerCase().contains("solved")) {
                    solvedProblemCount.incrementAndGet();
                }
            }
        }
    }
}


