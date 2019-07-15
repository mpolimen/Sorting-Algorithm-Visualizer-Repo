import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
public class SelectionSort extends JPanel implements Runnable
{
    private Thread main = new Thread(this);
    private ArrayList<Integer> list;
    private boolean wait = true;
    private int endNum;
    private int swaps=0; private int comps=-1;
    public SelectionSort()
    {
        super.setDoubleBuffered(true);
        main.start();

        // Declare and randomize an arraylist with elements 0-99
        list = new ArrayList<Integer>();
        for(int x=0; x<100; x++)
            list.add(x);
        shuffle(list);
        endNum = list.size() - 1;
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
        if(endNum == 0)
            g2.drawString("Sorted with "+swaps+" swaps and "+comps+" comparisons!", 25, 150);
        g2.setColor(Color.magenta);
        g2.drawString("SELECTION SORT", 150, 50);
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
            try{main.sleep(92);} catch(Exception e) {}
            // While every element hasn't been checked up to
            if(endNum > 0)
            {
                int index = 0;
                // Select the smallest element in the range
                for(int p=1; p<=endNum; p++)
                {
                    if(list.get(index) > list.get(p))
                        index = p;
                    comps++;
                }
                // Properly place the smallest element
                swaps++;
                list.set(endNum, list.set(index, list.get(endNum)));
                endNum--;
            }
        }
    }
}

