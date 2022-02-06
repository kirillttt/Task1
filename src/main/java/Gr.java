
import java.util.*;
class Gr {


    static class AdjListNode {
        int v;
        int size;
        AdjListNode(int _v, int _w) {
            v = _v;
            size = _w;
        }

        int getV() {
            return v;
        }

        int getSize() {
            return size;
        }
    }

    static class Graph {
        int V;

        ArrayList<ArrayList<AdjListNode>> adj;

        Graph(int V)
        {
            this.V = V;
            adj = new ArrayList<ArrayList<AdjListNode>>(V);

            for (int i = 0; i < V; i++) {
                adj.add(new ArrayList<AdjListNode>());
            }
        }

        void addEdge(int u, int v, int size) {
            AdjListNode node = new AdjListNode(v, size);
            adj.get(u).add(node);
        }


        void topologicalSortUtil(int v, boolean visited[],
                                 Stack<Integer> stack) {
            visited[v] = true;

            for (int i = 0; i < adj.get(v).size(); i++) {
                AdjListNode node = adj.get(v).get(i);
                if (!visited[node.getV()])
                    topologicalSortUtil(node.getV(), visited, stack);
            }
            stack.push(v);
        }

        int[] longestPath() {
            int s = 0;
            Stack<Integer> stack = new Stack<Integer>();
            int dist[] = new int[V];

            boolean visited[] = new boolean[V];
            for (int i = 0; i < V; i++)
                visited[i] = false;

            for (int i = 0; i < V; i++)
                if (visited[i] == false)
                    topologicalSortUtil(i, visited, stack);

            for (int i = 0; i < V; i++)
                dist[i] = Integer.MIN_VALUE;
            dist[s] = 0;

            while (stack.isEmpty() == false) {
                int u = stack.peek();
                stack.pop();
                if (dist[u] != Integer.MIN_VALUE) {
                    for (int i = 0; i < adj.get(u).size(); i++) {
                        AdjListNode node = adj.get(u).get(i);
                        if (dist[node.getV()] < dist[u] + node.getSize())
                            dist[node.getV()] = dist[u] + node.getSize();
                    }
                }
            }
            return dist;
        }
    }
}