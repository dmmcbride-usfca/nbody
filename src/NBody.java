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
//    public static List<Planet> uni = new ArrayList<Planet>();
//    public static LinkedList<Planet> uni = new LinkedList<Planet>();
    private int maxX = 768;
    private int maxY = 768;


    Timer tm = new Timer(1, this);
    int x, y, velX = 1, velY = 1;


    public void paintComponent(Graphics g){
        super.paintComponent(g);

        g.setColor(Color.BLACK);
        for (int i = 1; i <= uni.size(); i++) {
            g.fillOval((int) uni.get(i).xcor, (int) uni.get(i).ycor, uni.get(i).size, uni.get(i).size);
        }

        tm.start();
    }
    @Override
    public void actionPerformed(ActionEvent e) {

        for (int i=1; i<=uni.size();i++){
            for (int j=1; j<=uni.size();j++){
                if (j!=i){
                    uni.get(i).force(uni.get(j));
                }
            }
            uni.get(i).move(1);
        }

        repaint();
    }

    public static void main(String [] args){

        BufferedReader reader = null;
        Planet p;
        String [] attributes;
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


//        Planet a = new Planet("P0",200, 300, 300, 0, 0, 99999.0, 10);
//        Planet b = new Planet("P1",200, 315, 315, 0, 0, 99999.0, 10);
//        uni.add(a);
//        uni.add(b);
//
//
//        NBody t = new NBody();
//        JFrame jf = new JFrame();
//        jf.setTitle("NBody");
//        jf.setSize(t.maxX, t.maxY);
//
//        jf.add(t);
//
//        jf.setVisible(true);
//        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);



//        ArrayList<Planet> sim = new ArrayList<Planet>(2);
//        Planet a = new Planet("P0", 1000000000000000000.0, 384, 384, 0.0, 0.0, 20);
//        Planet b = new Planet("P1", 150000.0, 350, 350, 0.0, 0.0, 12);
//        sim.add(a);
//        sim.add(b);
    }


}
