 import java.util.ArrayList;
 import java.util.LinkedList;
 import java.util.Stack;
 import java.util.Queue; 
 
//n-арное дерево

public class MyTree {
    public static void main(String[] args) {
        MyTree root = new MyTree(1, 3);

        root.addChild(2);
        root.getChild(0).addChild(4);
        root.getChild(0).getChild(0).addChild(7);
        root.getChild(0).getChild(0).addChild(8);
        root.getChild(0).getChild(0).addChild(9);

        root.addChild(3);
        root.getChild(1).addChild(5);
        root.getChild(1).getChild(0).addChild(11);        
        root.getChild(1).addChild(6);
        root.getChild(1).getChild(1).addChild(20);       

        System.out.println("My tree using depth-first search:\n");
        MyTree.printTreeDFS(root);
        System.out.println();
        System.out.println("My tree using breadth-first search:\n");
        MyTree.printTreeBFS(root);
        System.out.println();
    }

    private final int value;
    private final int amount; // количество потомков
    private final ArrayList<MyTree> children;

    
    public MyTree(int value, int amount) {
        this.value = value;
        this.amount = amount;
        children = new ArrayList<>(amount);
    }
    
    private static class Pair {
        MyTree node;
        int depth; // глубина узла 
        
        Pair (MyTree node, int depth) {
            this.node = node;
            this.depth = depth;
        }
    }
   
    public void addChild(int value) {
        if (children.size() < amount) {
            children.add(new MyTree(value, amount));
        }
    }
    
    public MyTree getChild(int index) {
        if (index < children.size()) {
            return children.get(index);
        } else {
        return null;
        }
    }

    private static void getSpaces(int depth, int value) {
        for (int i = 0; i < depth; ++i) {
            System.out.print("   ");
        }
        System.out.println(value);
    }

    // Depth-first search

    public static void printTreeDFS(MyTree root) {
        Stack<Pair> stack = new Stack<>();
        stack.push(new Pair(root, 0));

        while (!stack.empty()) {
            Pair current = stack.pop();
            MyTree node = current.node;
            int depth = current.depth;
            getSpaces(depth, node.value);
            ArrayList<MyTree> children = node.children;

            for (int i = children.size() - 1; i >= 0; i--) {
                stack.push(new Pair(children.get(i), depth + 1));
            }
        }
    }

    // Breadth-first search

    public static void printTreeBFS(MyTree root) {
        Queue<Pair> queue = new LinkedList<>();
        queue.add(new Pair(root, 0));
        while (!queue.isEmpty()) {
            Pair current = queue.remove();
            MyTree node = current.node;
            int depth = current.depth;
            getSpaces(depth, node.value);
            ArrayList<MyTree> children = node.children;

            for (MyTree child : children) {
                queue.add(new Pair(child, depth + 1));
            }
        }
    }
}