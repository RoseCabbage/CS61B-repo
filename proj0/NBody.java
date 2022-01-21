public class NBody {
    public static double readRadius(String filePath) {
        In in = new In(filePath);
        in.readInt();
        return in.readDouble();
    }

    public static Planet[] readPlanets(String filePath) {
        In in = new In(filePath);
        int numOfPlanets = in.readInt();
        Planet[] planets = new Planet[numOfPlanets];
        in.readDouble();
        for (int i = 0; i < numOfPlanets; i++) {
            planets[i] = new Planet(in.readDouble(), in.readDouble(), in.readDouble(),
                    in.readDouble(), in.readDouble(), in.readString());
        }
        return planets;
    }

    public static void main(String[] args) {
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String filename = args[2];
        double radius = readRadius(filename);
        Planet[] planets = readPlanets(filename);
        int num = planets.length;

        StdDraw.clear();
        StdDraw.setScale(-radius, radius);
        StdDraw.picture(0, 0, "images/starfield.jpg");

        for (Planet p : planets) {
            p.draw();
        }
        StdDraw.show();

        StdDraw.enableDoubleBuffering();
        for (double t = 0; t < T; t += dt) {
            double[] xForces = new double[num];
            double[] yForces = new double[num];
            for (int j = 0; j < num; j++) {
                xForces[j] = planets[j].calcNetForceExertedByX(planets);
                yForces[j] = planets[j].calcNetForceExertedByY(planets);
            }
            for (int j = 0; j < num; j++) {
                planets[j].update(dt, xForces[j], yForces[j]);
            }
            StdDraw.clear();
            StdDraw.setScale(-radius, radius);
            StdDraw.picture(0, 0, "images/starfield.jpg");
            for (Planet p : planets){
                p.draw();
            }
            StdDraw.show();
            StdDraw.pause(20);
        }
        StdOut.printf("%d\n", planets.length);
        StdOut.printf("%.2e\n", radius);
        for (int i = 0; i < planets.length; i++) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                    planets[i].xxPos, planets[i].yyPos, planets[i].xxVel,
                    planets[i].yyVel, planets[i].mass, planets[i].imgFileName);
        }
    }
}
