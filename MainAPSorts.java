/*
 * Marco Polimeni
 * September 25, 2018
 * Visualization of AP sorts!
 */

import javax.swing.*;
import java.awt.*;
public class MainAPSorts
{
    public MainAPSorts()
    {
        // Create the window for bubble sort
        JFrame frame = new JFrame();
        frame.setTitle("Bubble Sort");
        frame.setSize(450,700);
        frame.setResizable(true);
        frame.setLocation(15,15);
        frame.add(new BubbleSort());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        // Create the window for selection sort
        JFrame frame2 = new JFrame();
        frame2.setTitle("Selection Sort");
        frame2.setSize(450,700);
        frame2.setResizable(true);
        frame2.setLocation(465,15);
        frame2.add(new SelectionSort());
        frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame2.setVisible(true);

        // Create the window for insertion sort
        JFrame frame3 = new JFrame();
        frame3.setTitle("Insertion Sort");
        frame3.setSize(450,700);
        frame3.setResizable(true);
        frame3.setLocation(915,15);
        frame3.add(new InsertionSort());
        frame3.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame3.setVisible(true);
    }

    /*
     * Press OK To Start
     */
    public static void main(String args [])
    {
        new MainAPSorts();
    }
}

