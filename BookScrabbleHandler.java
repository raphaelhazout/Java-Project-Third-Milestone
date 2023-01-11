package test;


import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class BookScrabbleHandler implements ClientHandler{
    Scanner in;
    PrintWriter out;
    @Override
    public void handleClient(InputStream inFromclient, OutputStream outToClient) {
        in = new Scanner(inFromclient);
        out = new PrintWriter(outToClient);
            String input = in.next();
            DictionaryManager dm = DictionaryManager.get();
            if (input.startsWith("Q,")) {
                input = input.replaceFirst("Q,", "");
                boolean t = dm.query(input.split(","));
                out.println(t+"\n");
                out.flush();
            } else {
                input = input.replaceFirst("C,", "");
                boolean t = dm.challenge(input.split(","));
                out.println(t+"\n");
                out.flush();
            }
        close();
    }
    // runClient(port, "Q,s1.txt,s2.txt,"+s1[1], true);
    @Override
    public void close() {
        in.close();
        out.close();
    }
}
