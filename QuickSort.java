import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
public class QuickSort extends JPanel implements Runnable
{
    private Thread main = new Thread(this);
    private ArrayList<Integer> list;
    private boolean wait = true;
    private boolean sorted = false;
    private int position = 0;
    private int swaps=0; private int comps=0;
    /////////////////////////////////////
    private ArrayList<Integer> sortedIndexes = new ArrayList<Integer>();
    private int left=0, right=99;
    public QuickSort()
    {
        super.setDoubleBuffered(true);
        main.start();

        // Declare and randomize an arraylist with elements 0-99
        list = new ArrayList<Integer>();
        for(int x=0; x<100; x++)
            list.add(x);
        shuffle(list);
    }

    public void shuffle(ArrayList<Integer> list)
    {
        for(int p=0; p<list.size(); p++)
        {
            int randomN = (int)(Math.random() * list.size());
            list.set(p, list.set(randomN, list.get(p)));
        }
    }

    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.black);
        g2.fillRect(0, 0, 1400, 750);
        g2.setColor(Color.white);
        for(int p=0; p<list.size(); p++)
        {
        	if(list.get(p) >= 0 && list.get(p) < 33)
            {
                // Set the color to a gradient variation of blue
                g2.setColor(new Color(3*(33-list.get(p)), 3*(33-list.get(p)), 255));
            }
            else if(list.get(p) >= 33 && list.get(p) < 66)
            {
                // Set the color to a gradient variation of green
                g2.setColor(new Color(3*(66-list.get(p)), 255, 3*(66-list.get(p))));
            }
            else
            {
                // Set the color to a gradient variation of red
                g2.setColor(new Color(255, 3*(100-list.get(p)), 3*(100-list.get(p))));
            }
            g2.fillRect(p*4, list.get(p)*7, 2, 1000);
        }
        g2.setColor(Color.green);
        Font font = new Font("Arial", 1, 18);
        g2.setFont(font);
        if(sorted)
            g2.drawString("Sorted with "+swaps+" swaps and "+comps+" comparisons!", 5, 150);
        g2.setColor(Color.magenta);
        g2.drawString("QUICK SORT", 150, 50);
    }

    public void run()
    {
        while(true)
        {
            repaint();
            if(wait)
            {
                try{main.sleep(2000);} catch(Exception e) {}
                wait = false;
            }
            try{main.sleep(2);} catch(Exception e) {}
            if(!sorted)
            {
                quickRecursion(list, 0, list.size()-1);
            }
            sorted = true;
        }
    }
    
    // The recursive sorting algorithm
    public void quickRecursion(ArrayList<Integer> list, int left, int right)
    {
    	// Checks base case, but the base case simply ends the recursion
        if(right - left >= 1)
        {
        	// Pivot index starts at the leftmost element
            int pivot = list.get(left);
            // Used for global list editing
            int origRight = right, origLeft = left;
            while(left<=right)
            {
                repaint();
                // Sleep is to make the visualization more pronounced
                try{main.sleep(4);} catch(Exception e) {}
                
                // Make the correct comparisons and shifts
                if(list.get(left) >= pivot)
                {
                    left++;
                    comps++;
                }
                else if(list.get(right) <= pivot)
                {
                    right--;
                    comps += 2;
                }
                else
                {
                    list.set(left, list.set(right, list.get(left)));
                    comps += 2;
                }
            }
            // Edit the global list in accordance to the swap
            list.set(origLeft, list.set(right, list.get(origLeft)));
            swaps++;
            repaint();
            // Smaller caller
            quickRecursion(list, origLeft, right - 1);
            quickRecursion(list, right + 1, origRight);
        }
    }
}