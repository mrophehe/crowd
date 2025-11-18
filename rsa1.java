import java.math.BigInteger;
import java.util.Random;
import java.util.Scanner;

public class SimpleRSA {
public static void main(String[] args) {
int bitlength = 48;
Random r = new Random();
BigInteger p = BigInteger.probablePrime(bitlength, r);
BigInteger q = BigInteger.probablePrime(bitlength, r);
System.out.println("Prime number p is" + p);
System.out.println("prime number q is" + q);
BigInteger N = p.multiply(q);
BigInteger phi = p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE));
BigInteger e = BigInteger.probablePrime(bitlength / 2, r);
while (!phi.gcd(e).equals(BigInteger.ONE)) {
e = e.add(BigInteger.ONE);
}
System.out.println("Public key is" + e);
BigInteger d = e.modInverse(phi);
System.out.println("Private key is" + d);
Scanner sc = new Scanner(System.in);
System.out.println("Enter the plain text:");
String msg = sc.nextLine();
System.out.println("Encrypting String: " + msg);
System.out.println("String in Bytes: " + bytesToString(msg.getBytes()));
byte[] encrypted = encrypt(msg.getBytes(), e, N);
byte[] decrypted = decrypt(encrypted, d, N);
System.out.println("Decrypting Bytes: " + bytesToString(decrypted));
System.out.println("Decrypted String: " + new String(decrypted));
}

private static String bytesToString(byte[] arr) {
StringBuilder sb = new StringBuilder();
for (byte b : arr) sb.append(b);
return sb.toString();
}

private static byte[] encrypt(byte[] msg, BigInteger e, BigInteger N) {
return new BigInteger(msg).modPow(e, N).toByteArray();
}

private static byte[] decrypt(byte[] msg, BigInteger d, BigInteger N) {
return new BigInteger(msg).modPow(d, N).toByteArray();
}
}
