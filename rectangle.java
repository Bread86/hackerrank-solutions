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

class Result {

    /*
     * Complete the 'largestRectangle' function below.
     *
     * The function is expected to return a LONG_INTEGER.
     * The function accepts INTEGER_ARRAY h as parameter.
     */

    public static long largestRectangle(List<Integer> h) {
    // Write your code here
    int ps[]=new int[h.size()];
    Stack<Integer> s=new Stack<Integer>();
    for(int i=0;i<h.size();i++){
        while(!s.isEmpty()&&h.get(s.peek())>=h.get(i)){
            s.pop();
        }
        if(s.isEmpty()){
            ps[i]=-1;
        }
        else{
            ps[i]=s.peek();
        }
        s.push(i);
    }
    int ns[]=new int[h.size()];
    Stack<Integer> s2=new Stack<Integer>();
    for(int i=h.size()-1;i>=0;i--){
        while(!s2.isEmpty()&&h.get(s2.peek())>=h.get(i)){
            s2.pop();
        }
        if(s2.isEmpty()){
            ns[i]=h.size();
        }
        else{
            ns[i]=s2.peek();
        }
        s2.push(i);
    }
    int max=0;
    for(int i=0;i<h.size();i++){
        int current=(ns[i]-ps[i]-1)*h.get(i);
        max=Math.max(current, max);
    }
    return max;
    }

}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int n = Integer.parseInt(bufferedReader.readLine().trim());

        List<Integer> h = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
            .map(Integer::parseInt)
            .collect(toList());

        long result = Result.largestRectangle(h);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}
