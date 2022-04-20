public class Bank {
    String bankName;
    double x;
    double y;
    KDtree branches = new KDtree();
    public Bank(String bankName, double x, double y) {
        this.bankName = bankName;
        this.x = x;
        this.y = y;
    }
}
