import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) throws IOException {
        final String HOST = "127.0.0.1";
        final int PORT = 4040;

        System.out.println("Client started.");

        try (
                Socket socket = new Socket(HOST, PORT);
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                Scanner in = new Scanner(socket.getInputStream());
                Scanner s = new Scanner(System.in);
        ) {
            System.out.println("Chose your username: ");
            out.println(s.nextLine());
            while (true) {
                System.out.print("Input: ");
                String input = s.nextLine();
                out.println(input);
                switch (input)
                {
                    case "Create news channel":
                        System.out.println(in.nextLine());
                        out.println(s.nextLine());
                        System.out.println(in.nextLine());
                        break;
                    case "Subscribe":
                        System.out.println(in.nextLine());
                        out.println(s.nextLine());
                        break;
                    case "Post news":
                        System.out.println(in.nextLine());
                        out.println(s.nextLine());
                        System.out.println(in.nextLine());
                        out.println(s.nextLine());
                        break;
                    case "Last news":
                        System.out.println(in.nextLine());
                        break;
                }
                if (input.equalsIgnoreCase("exit")) break;
            }
        }
    }

}