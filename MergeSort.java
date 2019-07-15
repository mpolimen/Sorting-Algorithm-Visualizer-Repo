import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
public class MergeSort extends JPanel implements Runnable
{
    private Thread main = new Thread(this);
    private ArrayList<Integer> list;
    private boolean wait = true;
    private boolean sorted = false;
    private int position = 0;
    private int swaps=0; private int comps=0;
    ////////////////////
    private int left=0, right;
    private ArrayList<Integer>leftStack = new ArrayList<Integer>();
    private ArrayList<Integer>rightStack = new ArrayList<Integer>();
    public MergeSort()
    {
        super.setDoubleBuffered(true);
        main.start();

        // Declare and randomize an arraylist with elements 0-99
        list = new ArrayList<Integer>();
        for(int x=0; x<100; x++)
            list.add(x);
        shuffle(list);
        leftStack.add(0); rightStack.add(list.size());
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
        g2.drawString("MERGE SORT", 150, 50);
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
                mergeRecursion(list);
            }
            sorted = true;
        }
    }

    // The recursive sorting algorithm
    private ArrayList<Integer> mergeRecursion(ArrayList<Integer> list)
    {
    	// Base case
        if(list.size() < 2)
            return list;
        
        // Split the lists
        int mid = list.size()/2;
        ArrayList<Integer> left = new ArrayList<Integer>();
        for(int l=0; l<mid; l++)
            left.add(list.get(l));
        ArrayList<Integer> right = new ArrayList<Integer>();
        for(int r=mid; r<list.size(); r++)
            right.add(list.get(r));
        
        // Smaller caller
        return merge(mergeRecursion(left), mergeRecursion(right));
    }

    // Interweave the lists in a sorted fashion
    private ArrayList<Integer> merge(ArrayList<Integer> left, ArrayList<Integer> right)
    {
    	// Find the segment of the global list to edit
        int startLeft = list.indexOf(left.get(0));
        // New list to be returned
        ArrayList<Integer> out = new ArrayList<Integer>();
        int i=0, j=0;
        // Standard merge algorithm
        while(i < left.size() || j < right.size())
        {
            comps++;
            if((j == right.size()) || (i < left.size() && left.get(i).compareTo(right.get(j)) >= 0))
            {
                out.add(left.get(i));
                i++;
            }
            else
            {
                out.add(right.get(j));
                j++;
            }
            // Sleep is to make the visualization more pronounced
            try{main.sleep(5);} catch(Exception e) {}
            // Correctly edits the according segment of the global list
            if(list.get(out.size() + startLeft - 1) != out.get(out.size() - 1))
                swaps++;
            list.set(out.size()+startLeft - 1, out.get(out.size() - 1));
            repaint();
        }
        return out;
    }
}