package week7.lesson13.part3;

/**
 * 并查集代码
 */
public class UnionFind {
    int count;
    int[] parent;

    public UnionFind(int n) {
        this.count = n;
        parent = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = -1;
        }
    }

    public int find(int x) {
        int x_root = x ;
        while (parent[x_root] != -1) {
            x_root = parent[x_root];
        }
        //
        return x_root;
    }

    public int union(int p, int q) {
        int rootP = find(p);
        int rootQ = find(q);
        if (rootP == rootQ) return 0;
        parent[rootP] = rootQ;
        count--;
        return 1;
    }
}
