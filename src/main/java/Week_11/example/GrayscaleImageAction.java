package Week_11.example; // tajuk harini recursive nnti parallel

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;
import javax.imageio.ImageIO;

public class GrayscaleImageAction extends RecursiveAction {

    private static final long serialVersionUID = 1L;
    private final int row; // base on the row of the image
    private final BufferedImage image; // call for the full image to work on

    // constructor initiates the row and image
    public GrayscaleImageAction(int row, BufferedImage bufferedImage) {
        this.row = row;
        this.image = bufferedImage;
    }

    @Override
    protected void compute() { // to compute based on recursive action
        for (int column = 0; column < image.getWidth(); column++) {
            int rgb = image.getRGB(column, row);
            int r = (rgb >> 16) & 0xFF;
            int g = (rgb >> 8) & 0xFF;
            int b = rgb & 0xFF;  // foundation of the colour of human eyes sensitivity

            int gray = (int) (0.2126 * r + 0.7152 * g + 0.0722 * b);
            int grayRGB = (0xFF << 24) | (gray << 16) | (gray << 8) | gray; // FF = 8bits
            // int grayRGB = (0xFF * 16777216) put any number to try - nak sharp tinggi kan gray
            image.setRGB(column, row, grayRGB);
        }
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        if (args.length == 0) { // the entry of prg, can throw exception for file reading and relate to the thread interruption
            System.err.println("Usage: java GrayscaleImageAction <image-file>");
            return;
        //    System.out.println("Usage: java GrayscaleImageAction <C:\\Users\\HP\\Documents\\SEM 4\\REAL-TIME P\\Tutorial\\src\\main\\java\\Week_11\\example\\photo_2025-02-05_01-48-23.jpg>"); // add pic url
        }

        BufferedImage bufferedImage = ImageIO.read(new File(args[0])); //reading the input image file
        ForkJoinPool pool = new ForkJoinPool(); //create thread pool to run the parallel

        for (int row = 0; row < bufferedImage.getHeight(); row++) {
            pool.execute(new GrayscaleImageAction(row, bufferedImage));
        }

        pool.shutdown(); // tell the pool to stop and accept the new task - sbb kita takda task yg lain so to start new task
        while (!pool.isTerminated()) {
            Thread.sleep(10); // wait for all task to finish
        }

        ImageIO.write(bufferedImage, "jpg", new File("grayscale_output.jpg")); //write the modified image to new image ".jpg/png"
        System.out.println("Grayscale image written to grayscale_output.jpg");
    }
}
