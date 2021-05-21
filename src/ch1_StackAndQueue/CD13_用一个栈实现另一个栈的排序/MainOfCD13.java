import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.Stack;
import java.util.StringJoiner;

public class MainOfCD13 {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Stack<Integer> stack = new Stack<>();
        int N  = Integer.parseInt(reader.readLine());
        String[] arr = reader.readLine().split(" ");
        for (int i = N - 1; i >= 0; i--) {
            stack.push(Integer.parseInt(arr[i]));
        }

        // core function
        sortStackByStack(stack);

        StringJoiner res = new StringJoiner(" ");
        while (!stack.isEmpty() ) {
            res.add(String.valueOf(stack.pop()));
        }
        System.out.println(res.toString());
    }

    public static void sortStackByStack(Stack<Integer> stack) {
        Stack<Integer> help = new Stack<>();
        while (!stack.isEmpty()) {
            int cur = stack.pop();
            while (!help.isEmpty() && help.peek() < cur) {
                stack.push(help.pop());
            }
            help.push(cur);
        }
        while (!help.isEmpty()) {
            stack.push(help.pop());
        }
    }
}
