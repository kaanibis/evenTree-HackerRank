package eventree;
import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;
import java.util.regex.*;
import java.util.stream.*;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;


public class EvenTree {

    // Complete the evenForest function below.
static ArrayList<Integer> tree[]; 
    static boolean isChecked[];
    static int result;
    
    static int dfs(int start)
    {
        int cnt = 1;
        isChecked[start] = true;
        System.out.print(start + "->");
        
        for(int i = 0 ; i < tree[start].size() ; i++)
        {
            int next = tree[start].get(i);
            if(isChecked[next] == false)
            {
                cnt += dfs(next);
            }
        }
       
        System.out.print("("+cnt + ")");
        if(cnt % 2 == 0 )    // check even
        {
            System.out.println();            
            result++;
        }
        
        return cnt;
    }
    static int evenForest(int t_nodes, int t_edges, List<Integer> t_from, List<Integer> t_to) {
        tree = new ArrayList[t_nodes + 1];
        for(int i = 0 ; i < tree.length; i++)
        {
            tree[i] = new ArrayList<Integer>();
        }
        
        
        for(int i = 0 ; i < t_edges; i++)
        {
            int x = t_from.get(i);
            int y = t_to.get(i);
            
            tree[x].add(y);
            tree[y].add(x);
        }
        
        isChecked = new boolean[t_nodes + 1];
        result = 0;
        
        
        dfs(1);
        result--;
        return result;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String[] tNodesEdges = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

        int tNodes = Integer.parseInt(tNodesEdges[0]);
        int tEdges = Integer.parseInt(tNodesEdges[1]);

        List<Integer> tFrom = new ArrayList<>();
        List<Integer> tTo = new ArrayList<>();

        for (int i = 0; i < tEdges; i++) {
            String[] tFromTo = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

            tFrom.add(Integer.parseInt(tFromTo[0]));
            tTo.add(Integer.parseInt(tFromTo[1]));
        }

        int res = evenForest(tNodes, tEdges, tFrom, tTo);

        bufferedWriter.write(String.valueOf(res));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}
