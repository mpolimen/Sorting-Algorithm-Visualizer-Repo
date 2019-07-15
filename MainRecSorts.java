/*
 * Marco Polimeni
 * February 7, 2019
 * Visualization of recursive sorts!
 */
import javax.swing.*;
import java.awt.*;
public class MainRecSorts
{
    public MainRecSorts()
    {
    	// Create a window for merge sort
        JFrame frame = new JFrame();
        frame.setTitle("Merge Sort");
        frame.setSize(450,700);
        frame.setResizable(true);
        frame.setLocation(15,15);
        frame.add(new MergeSort());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        // Create a window for merge sort
        JFrame frame2 = new JFrame();
        frame2.setTitle("Quick Sort");
        frame2.setSize(450,700);
        frame2.setResizable(true);
        frame2.setLocation(465,15);
        frame2.add(new QuickSort());
        frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame2.setVisible(true);
    }

    /*
     * Press OK To Start
     */
    public static void main(String args [])
    {
        new MainRecSorts();
    }
}

