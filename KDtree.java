public class KDtree {

    public static int K = 2;
    public Node root = null;
    boolean error = false;
    public Node insert(Node root, Node node, int d) {

        if (root == null) {
            return node;
        }
        int ll = d % K;
        if (node.point[0] == root.point[0] && node.point[1] == root.point[1]) {
            error = true;
            return node;
        }
        if (node.point[ll] >= root.point[ll]) {
            root.right = insert(root.right, node, d + 1);
        } else {

            root.left = insert(root.left, node, d + 1);
        }

        return root;
    }
    public boolean insert(Branch b){
        error = false;
        Node n = new Node();
        n.branch = b;
        n.point[0] = b.x;
        n.point[1] = b.y;
        if (root == null){
            root = n;
            return false;
        }else {
            insert(root,n,0);
        }
        return error;
    }

    public boolean search(Node root, int[] point, int d) {

        if (root == null) {
            return false;
        }
        if (root.point[0] == point[0] && root.point[1] == point[1]) {
            return true;
        }
        int depth = d % K;
        if (point[depth] >= root.point[depth]) {
            return search(root.right, point, d + 1);
        }
        return search(root.left, point, d + 1);
    }
    public Node findMin(Node root, int d, int depth) {

        if (root == null) {
            return null;
        }
        int ll = depth % K;

        if (ll == d) {
            if (root.left == null) {
                return root;
            }
            return findMin(root.left, d, depth + 1);
        }
        return Min(root, findMin(root.left, d, depth + 1), findMin(root.right, d, depth + 1), depth);
    }

    public Node Min(Node a, Node b, Node c, int depth) {

        Node temp = a;
        if (b != null && b.point[depth] < temp.point[depth]) {
            temp = b;
        }
        if (c != null && c.point[depth] < temp.point[depth]) {
            temp = c;
        }
        return temp;

    }
    boolean seenToDeleteNode = false;
    boolean notExist = false;
    String deletedBankName= null;
    public Node deleteNode(Node root , double [] point , int d){
        if (root == null) {
            if (!seenToDeleteNode){
                notExist = true;
            }
            return null;
        }
        int ll = d % K ;
        if(root.point[0] == point[0] && root.point[1] == point[1]){
            if (root.branch.branchName.equals("master")){
                if (!seenToDeleteNode){
                    return null;
                }
            }
            if (!seenToDeleteNode){
                deletedBankName = root.branch.bankName;
            }
            seenToDeleteNode = true;
            if(root.right != null) {
                Node min = findMin(root.left, ll, 0);
                root.point[0] = min.point[0];
                root.point[1] = min.point[1];
                root.branch = min.branch;
                root.right = deleteNode(root.right, min.point, d + 1);
            }
            else if (root.left != null) {
                Node min = findMin(root.left, ll, 0);
                root.point[0] = min.point[0];
                root.point[1] = min.point[1];
                root.branch = min.branch;
                root.right = deleteNode(root.left, min.point, d + 1);
                root.left = null;
            }else {
                return null;
            }
            return root;
        }
        if (point[ll] >= root.point[ll]) {
            root.right = deleteNode(root.right, point, d + 1);
        } else {
            root.left = deleteNode(root.left, point, d + 1);
        }
        return root;
    }

    void preorder(Node root){
        if (root == null)
            return;
        System.out.println(String.format("%s %f %f", root.branch.branchName, root.branch.x, root.branch.y));
        preorder(root.left);
        preorder(root.right);
    }
    void listB(Node node,State state,int depth){
        if (node == null)
            return;
        int isX = depth%2;
        if (isX == 0 && state.xCondition(node.point[0]) == +1){
            listB(node.left,state,depth+1);
        }
        else if (isX == 0 && state.xCondition(node.point[0]) == -1){
            listB(node.right,state,depth+1);
        }
        else if (isX == 0 && state.xCondition(node.point[0]) == 0){
            listB(node.left,state,depth+1);
            listB(node.right,state,depth+1);

        }
        else if(isX == 1 && state.yCondition(node.point[1])==+1){
            listB(node.left,state,depth+1);
        }
        else if(isX == 1 && state.yCondition(node.point[1])==-1){
            listB(node.right,state,depth+1);
        }
        else if(isX == 1 && state.yCondition(node.point[1])==0){
            listB(node.left,state,depth+1);
            listB(node.right,state,depth+1);
        }
        if (state.isInState(node.point[0],node.point[1])){
            System.out.printf("%s -> %s, X: %f, Y: %f%n",
                    node.branch.bankName,
                    node.branch.branchName,
                    node.point[0],
                    node.point[1]);
        }
    }
    //near
public void findNearBank(int x , int y){
    int point [] = new int[2];
    point[0] = x;
    point[1] = y;
    Object[] temp = new Object[4];
    temp[0] = Double.POSITIVE_INFINITY;
    temp[1] = "";
    Object[] o;
}

}
