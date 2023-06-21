/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */

// V8255920 James Fothergill

package Server;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

/**
 *
 * @author v8255
 */
public class Server {
    
    static ArrayList<String> userNames = new ArrayList<String>();
    static ArrayList<PrintWriter> printWriters = new ArrayList<PrintWriter>();

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        

            System.out.println("Waiting for clients ..."); 
            ServerSocket ss = new ServerSocket(9800);
            while (true)
            {
                Socket soc = ss.accept();
                System.out.println("Connection established"); 
                ConversationHandler handler = new ConversationHandler(soc);
                handler.start();
            }
    }
}

class ConversationHandler extends Thread
{
    Socket socket;
    BufferedReader in;
    PrintWriter out;
    String name;
    PrintWriter pw;
    static FileWriter fw;
    static BufferedWriter bw;
    
    public ConversationHandler (Socket socket) throws IOException{
        this.socket = socket;
        
//       fw = new FileWriter("c:\\Server-Logs.txt", true);
//        bw = new BufferedWriter(fw);
//        pw = new PrintWriter(bw, true);
    }
    public void run()  
    {
       try
       {
           in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
           out = new PrintWriter(socket.getOutputStream(), true);
           
           int count = 0;
           while (true)
           {
            if(count > 0)
            {
                out.println("NAMEALREADYEXISTS");
            }
            else
            {
                out.println("NAMEREQUIRED");
            }
            
            name = in.readLine();
            
            if (name == null)
            {
                return;
            }
            
            if (!Server.userNames.contains(name))
            {
                Server.userNames.add(name);
                break;
            }
            count++;
           }
           
           out.println("NAMEACCEPTED"+name);
           Server.printWriters.add(out);
           
           while (true)
           {
               String message = in.readLine();
               
               if (message == null)
               {
                   return;
               }
            //   pw.println(name + ": " + message);
               
               for(PrintWriter writer :Server.printWriters)
               {
                   writer.println(name + ": " + message);
               }
           }
           
       }
       catch (Exception e)
       {
           System.out.println(e);
       }
    }
}