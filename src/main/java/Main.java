import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String args[]) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the dimension of the side of the matrix");
        int n = scanner.nextInt();
        Gr.Graph g = new Gr.Graph(n*n+1);
        int[][] Vertexes = new int[n][n];
        System.out.println("Enter the matrix "+ n+"x"+n);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print("Enter element [" + i + "][" + j + "]:");
                Vertexes[i][j] = scanner.nextInt();
            }
        }
        scanner.close();
        int[] VertexArr = new int[n*n];
        int c = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                VertexArr[c++] = Vertexes[i][j];
            }
        }
        g.addEdge(0,1,Vertexes[0][0]);

        for (int i = n+1; i < n*n; i = i+n) {
            g.addEdge(0, i,VertexArr[i-1]);
        }
            for (int j =1; j < n*n; j++) {
                if (j%n!=0){
                g.addEdge( j,  1 + j, VertexArr[ j]);
                }}
        for (int i = 1; i < n*(n-1); i += n) {
            for (int j = i; j < n*(n-1); j+=n+1) {
                g.addEdge(j,j+n+1,VertexArr[j+n]);
            }
        }
        for (int i = n-1; i > 1 ; i--) {
            for (int j = i; j <= n*(n-1) ; j+=n) {
                g.addEdge(j,j+n+1,VertexArr[j+n]);
            }
        }
        for (int i = n*(n-1)+1; i > 1; i -= n) {
            for (int j = i; j > n; j=j-n+1) {
                g.addEdge(j,j-n+1,VertexArr[j-n]);
                int a = j-n+1;
            }
        }
        for (int i = n*n-1; i > n*(n-1); i--) {
            for (int j = i; j>n*(n-1); j = j-n+1) {
                g.addEdge(j,j-n+1, VertexArr[j-n]);
            }
        }
        int[] a;
        int s = 0;
        a = g.longestPath();
        for (int i = 1; i <= n*n; i++) {
            System.out.println(" initialVertex -> " + "["+(i-1)/n+"]["+(i-1)%n+"] = "+a[i]);
        }
        int max = Arrays.stream(a).max().getAsInt();
        System.out.println( "longest path tree from vertex 'initialVertex' = " + max);
    }
}
