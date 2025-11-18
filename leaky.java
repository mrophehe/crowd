package cnlab;
import java.util.Scanner;

public class Leakybucket {
    public static void main(String[] args) {

        int bucketCap = 4, rate = 3, bucket = 0;
        int[] arr = new int[50];

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of packets: ");
        int n = sc.nextInt();

        System.out.println("Enter packet sizes:");
        for (int i = 1; i <= n; i++)
            arr[i] = sc.nextInt();

        System.out.println("Clock\tPkt\tAccept\tSent\tRemain");

        for (int i = 1; i <= n; i++) {

            int recv, sent;

            // Check if packet fits
            if (arr[i] != 0 && bucket + arr[i] > bucketCap)
                recv = -1;              // dropped
            else {
                recv = arr[i];
                if (recv != 0) bucket += recv;
            }

            // Leakage step
            if (bucket == 0)
                sent = 0;
            else if (bucket <= rate) {
                sent = bucket;
                bucket = 0;
            } else {
                sent = rate;
                bucket -= rate;
            }

            // Print
            if (recv == -1)
                System.out.println(i + "\t" + arr[i] + "\tdropped\t" + sent + "\t" + bucket);
            else
                System.out.println(i + "\t" + arr[i] + "\t" + recv + "\t" + sent + "\t" + bucket);
        }
    }
}
