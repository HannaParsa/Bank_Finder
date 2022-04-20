import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Map<String, Bank> banks = new HashMap<>();
        Map<String, State> states = new HashMap<>();
        Scanner scanner = new Scanner(System.in);
        KDtree all = new KDtree();
        while (true) {
            String command = scanner.next();
            switch (command) {
                case "addB":
                    String bankName = scanner.next();
                    double x = scanner.nextDouble(), y = scanner.nextDouble();
                    Bank bank = new Bank(bankName, x, y);
                    banks.put(bankName, bank);
                    Branch main = new Branch(bankName, "master", x, y);
                    boolean hasError = all.insert(main);
                    if (hasError) {
                        System.out.println("Duplicate x and y");
                    } else {
                        System.out.println("Bank added.");
                        bank.branches.insert(main);
                    }
                    break;
                case "addBr":
                    String bankN = scanner.next();
                    String branchN = scanner.next();
                    double xb = scanner.nextDouble(), yb = scanner.nextDouble();
                    Bank b = banks.get(bankN);
                    Branch bb = new Branch(bankN, branchN, xb, yb);
                    boolean hasErr = all.insert(bb);
                    if (hasErr) {
                        System.out.println("Duplicate x and y");
                    } else {
                        b.branches.insert(bb);
                        System.out.println("Branch added");
                    }
                    break;
                case "listBrs":
                    Bank bnk = banks.get(scanner.next());
                    bnk.branches.preorder(bnk.branches.root);
                    break;
                case "delBr":
                    all.seenToDeleteNode = false;
                    all.notExist = false;
                    all.deletedBankName = null;
                    double[] points = new double[]{scanner.nextDouble(),scanner.nextDouble()};
                    Node deleted = all.deleteNode(all.root,points,0);
                    if (deleted == null){
                        System.out.println("try to delete master branch is forbidden.");
                    }else if (all.notExist){
                        System.out.println("no branch exists in this point.");
                    }else {
                        System.out.println("branch deleted.");
                        Bank b1 = banks.get(all.deletedBankName);
                        b1.branches.deleteNode(b1.branches.root,points,0 );
                    }
                    break;
                case "addN":
                    String nameN = scanner.next();
                    State state = new State(nameN, scanner.nextDouble(), scanner.nextDouble(),
                            scanner.nextDouble(), scanner.nextDouble());
                    states.put(nameN,state);
                    System.out.println("Area added.");
                    break;
                case "listB":
                    all.listB(all.root,states.get(scanner.next()),0);
                    break;
                case "nearB":
                    int X = scanner.nextInt();
                    int Y= scanner.nextInt();
                    //ToDo
                    break;
                case "exit":
                    return;
            }
        }
    }
}
