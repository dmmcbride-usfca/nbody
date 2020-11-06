public class Planet {
    private static final double G = 6.67408e-11;
    public double scale;

    public String name;
    public double xcor;
    public double ycor;
    public double xdirv;
    public double ydirv;
    public double m;
    public int size;

    public double fx;
    public double fy;

    public Planet(String name, double scale, double xcor, double ycor, double xdirv, double ydirv, double m, int size) {
        this.name = name;
        this.scale = scale;
        this.xcor = xcor;
        this.ycor = ycor;
        this.xdirv = xdirv;
        this.ydirv = ydirv;
        this.m = m;
        this.size = size;
    }
    public double getx(){
        return xcor;
    }
    public double gety(){
        return ycor;
    }

    public double distFrom(Planet other){
        double distx = xcor*scale - other.xcor*scale;
        double disty = ycor*scale - other.ycor*scale;
        return Math.sqrt(Math.pow(distx,2)+Math.pow(disty,2));
    }

    public void force(Planet other) {
        double dist = distFrom(other);
        double F = (G * m * other.m) / (Math.pow(dist, 2));
        this.fx += F * other.xcor*scale / dist;
        this.fy += F * other.ycor*scale / dist;
    }

    public void move(double t) {
        xdirv += t * fx / m;
        ydirv += t * fy / m;
        xcor += (t * xdirv)/scale;
        ycor += (t * ydirv)/scale;
        fx = 0.0;
        fy = 0.0;
    }

    @Override
    public String toString() {
        return "Planet{" +
                "name='" + name + '\'' +
                ", xcor=" + xcor +
                ", ycor=" + ycor +
                ", xdirv=" + xdirv +
                ", ydirv=" + ydirv +
                ", m=" + m +
                ", size=" + size +
                ", fx=" + fx +
                ", fy=" + fy +
                '}';
    }

    public static void main(String [] args){
        ArrayList<Planet> sim = new ArrayList<Planet>();
        Planet a = new Planet("P0",200, 100, 100, -250, -250.0, 150.0, 10);
        Planet b = new Planet("P1",200, 150, 150, 100, 100.0, 100.0, 10);
        sim.add(a);
        System.out.println(a);
        sim.add(b);
        a.force(b);
        b.force(a);
        System.out.println(a);
//        System.out.println(b);
        a.move(10);
        b.move(10);
        System.out.println(a);
//        System.out.println(b);
        a.force(b);
        b.force(a);
        System.out.println(a);
//        System.out.println(b);
        a.move(10);
        b.move(10);
        System.out.println(a);
//        System.out.println(b);
    }
}

