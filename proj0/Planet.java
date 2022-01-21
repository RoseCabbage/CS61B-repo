public class Planet {
    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;
    public static final double G = 6.67e-11;

    public Planet(double xP, double yP, double xV,
                  double yV, double m, String img) {
        xxPos = xP;
        yyPos = yP;
        xxVel = xV;
        yyVel = yV;
        mass = m;
        imgFileName = img;
    }

    public Planet(Planet p) {
        xxPos = p.xxPos;
        yyPos = p.yyPos;
        xxVel = p.xxVel;
        yyVel = p.yyVel;
        mass = p.mass;
        imgFileName = p.imgFileName;
    }

    private boolean equals(Planet other) {
        return ((this.xxPos == other.xxPos) && (this.yyPos == other.yyPos));
    }

    public double calcDistance(Planet other) {
        return Math.sqrt((other.xxPos - this.xxPos) * (other.xxPos - this.xxPos) +
                (other.yyPos - this.yyPos) * (other.yyPos - this.yyPos));
    }

    public double calcForceExertedBy(Planet other) {
        double dist = this.calcDistance(other);
        return G * this.mass * other.mass / (dist * dist);
    }

    public double calcForceExertedByX(Planet other) {
        double force = this.calcForceExertedBy(other);
        double dist = this.calcDistance(other);
        return force * (other.xxPos - this.xxPos) / dist;
    }

    public double calcForceExertedByY(Planet other) {
        double force = this.calcForceExertedBy(other);
        double dist = this.calcDistance(other);
        return force * (other.yyPos - this.yyPos) / dist;
    }

    public double calcNetForceExertedByX(Planet[] planets) {
        double force = 0.0;
        for (Planet p : planets) {
            if (!this.equals(p)) {
                force += this.calcForceExertedByX(p);
            }
        }
        return force;
    }

    public double calcNetForceExertedByY(Planet[] planets) {
        double force = 0.0;
        for (Planet p : planets) {
            if (!this.equals(p)) {
                force += this.calcForceExertedByY(p);
            }
        }
        return force;
    }

    public void update(double dt, double xforce, double yforce) {
        double ax = xforce / this.mass;
        double ay = yforce / this.mass;
        xxVel += dt * ax;
        yyVel += dt * ay;
        xxPos += xxVel * dt;
        yyPos += yyVel * dt;
    }

    public void draw() {
        StdDraw.picture(xxPos, yyPos, "/images/" + imgFileName);
    }
}
