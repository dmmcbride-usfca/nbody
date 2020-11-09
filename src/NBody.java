import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import java.awt.Graphics;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class NBody extends JPanel implements ActionListener {
    public static List<Planet> uni;
    private static String data_struct;
    private int maxX = 768;
    private int maxY = 768;

    Timer tm = new Timer(1, this);

    public void paintComponent(Graphics g){
        super.paintComponent(g);

        g.setColor(Color.BLACK);

        // gets each planet in the universe and paints it as an oval
        for (int i = 0; i < uni.size(); i++) {
            g.fillOval((int) uni.get(i).xcor, (int) uni.get(i).ycor, uni.get(i).size, uni.get(i).size);
        }

        tm.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        // Calculates the force that each planet applies the all the others O(n^2) time complexity
        for (int i=0; i<uni.size();i++){
            for (int j=0; j<uni.size();j++){
                if (j!=i){
                    uni.get(i).force(uni.get(j));
                }
            }
        }

        // Once all forces are calculated, the planets can be moved O(n) time complexity
        for (int i=0; i<uni.size();i++){
            uni.get(i).move(1);;
        }

        repaint();
    }

    public static void main(String [] args){

        BufferedReader reader;
        Planet p;
        try {
            reader = new BufferedReader(new FileReader(args[0]));
            String line = reader.readLine();
            data_struct = line;

            if (data_struct.equals("ArrayList")){
                uni = new ArrayList<Planet>();
            } else if (data_struct.equals("LinkedList")){
                uni = new LinkedList<Planet>();
            }

            line = reader.readLine();
            double scale = Double.parseDouble(line);
            line = reader.readLine();
            while (line != null) {
                String [] att = line.split(",");
                p = new Planet(att[0],
                        scale,
                        Double.parseDouble(att[2]),
                        Double.parseDouble(att[3]),
                        Double.parseDouble(att[4]),
                        Double.parseDouble(att[5]),
                        Double.parseDouble(att[1]),
                        Integer.parseInt(att[6]));
                uni.add(p);
                line = reader.readLine();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        NBody t = new NBody();
        JFrame jf = new JFrame();
        jf.setTitle("NBody");
        jf.setSize(t.maxX, t.maxY);

        jf.add(t);

        jf.setVisible(true);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }


}
