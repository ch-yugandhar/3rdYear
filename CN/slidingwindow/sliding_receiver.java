package slidingwindow;
import java.io.*;
import java.net.*;

class sliding_receiver {
    public static void main(String a[]) throws IOException {
        Socket s = new Socket(InetAddress.getLocalHost(), 10);

        BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
        PrintStream p = new PrintStream(s.getOutputStream());

        int i = 0, rptr = -1, nf, rws = 8;
        String rbuf[] = new String[8];
        String ch;
        
        System.out.println();

        do {
            nf = Integer.parseInt(in.readLine());

            if (nf <= rws - 1) {
                for (i = 1; i <= nf; i++) {
                    rptr = ++rptr % 8;
                    rbuf[rptr] = in.readLine(); // Use BufferedReader to read the frame
                    System.out.println("The received Frame " + rptr + " is: " + rbuf[rptr]);
                }
                rws -= nf;
                System.out.println("\nAcknowledgment sent\n");
                p.println(rptr + 1); // Send acknowledgment
                rws += nf;
            } else {
                break;
            }

            
            ch = in.readLine();
        }
        while (ch.equals("yes"));
    }
}
