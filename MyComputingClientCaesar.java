package client;
//Doğa Kayra Yılmazarslan
/* CLIENT */
import java.io.*;
import java.net.*;
import java.util.Scanner;
public class MyComputingClientCaesar {

 public static void main(String args[]) {

 Socket client = null;

 // Default port number we are going to use
 int portnumber = 1234;
 if (args.length >= 1){
 portnumber = Integer.parseInt(args[0]);
 }

 for (int i=0; i <10; i++) {
 try {
 String msg = "";

 // Create a client socket
 client = new Socket(InetAddress.getLocalHost(), portnumber);
 System.out.println("Client socket is created " + client);

 // Create an output stream of the client socket
 OutputStream clientOut = client.getOutputStream();
 PrintWriter pw = new PrintWriter(clientOut, true);

 // Create an input stream of the client socket
 InputStream clientIn = client.getInputStream();
 BufferedReader br = new BufferedReader(new
 InputStreamReader(clientIn));

 // Create BufferedReader for a standard input
 BufferedReader stdIn = new BufferedReader(new
 InputStreamReader(System.in));

 System.out.println("Enter your name. Type Bye to exit. ");

 // Read data from standard input device and write it
 // to the output stream of the client socket.
 
  Scanner myObj = new Scanner(System.in);  // Create a Scanner object
  System.out.println("Enter encrypted text :");
  String text = myObj.nextLine();

   int num_of_space = text.split(" ").length-1;
 int key = num_of_space * 3;

  String encrypted = "";
 for (int j= 0; j < text.length(); j++) {
 int c = text.charAt(j);
 if (Character.isUpperCase(c)) {
 c = c + (key % 26);
 if (c > 'Z') {
 c = c - 26;
 }
 } else if (Character.isLowerCase(c)) {
 c = c + (key % 26);
 if (c > 'z') {
 c = c - 26;
 }
 }
 encrypted += (char) c;
 }
 System.out.println("Encryped: " + encrypted);
  pw.println(encrypted);

 // Read data from the input stream of the client socket.
 System.out.println("Message returned from the server = " + br.readLine());

 pw.close();
 br.close();
 client.close();

 // Stop the operation
 if (msg.equalsIgnoreCase("Bye")) {
 break;
 }

 } catch (IOException ie) {
 System.out.println("I/O error " + ie);
 }
 }
 }
}
