public class State {
    public String name;
    public double x1, x2, y1, y2;

    public State(String name, double x1, double x2, double y1, double y2) {
        this.name = name;
        this.x1 = x1;
        this.x2 = x2;
        this.y1 = y1;
        this.y2 = y2;
    }

    public int xCondition(double x) {
        if (x <= x2 && x >= x1) {
            return 0;
        }
        if (x > x2) {
            return +1;
        }
        return -1;
    }
    public int yCondition(double y) {
        if (y <= y2 && y >= y1) {
            return 0;
        }
        if (y > y2) {
            return +1;
        }
        return -1;
    }
    boolean isInState(double x,double y){
        return xCondition(x) == 0 && yCondition(y) == 0;
    }
}
