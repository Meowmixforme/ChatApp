/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

// V8255920 James Fothergill

package Client;

import javax.swing.*;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author v8255
 */
        
public class Client {
    
    static JFrame chatWindow = new JFrame("PocketBeasts Chat Application");
    static JTextArea chatArea = new JTextArea(22, 40);
    static JTextField textField = new JTextField(40);
    static JLabel blankLabel = new JLabel("             ");
    static JButton sendButton = new JButton("Send");
    
    static BufferedReader in;
    static PrintWriter out;
    
    static JLabel nameLabel = new JLabel("      ");
    
    //Constructor
    Client()
    {
        chatWindow.setLayout(new FlowLayout());
        
        chatWindow.add(nameLabel);
        chatWindow.add(new JScrollPane(chatArea));
        chatWindow.add(blankLabel);
        chatWindow.add(textField);
        chatWindow.add(sendButton);
        
        chatWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        chatWindow.setSize(475, 500);
        chatWindow.setVisible(true);
        
        textField.setEditable(false);
        chatArea.setEditable(false);
        
        sendButton.addActionListener(new Listener());
        textField.addActionListener(new Listener());
        
    }
    
    //Main logic for chat client
    void startChat() throws Exception
    {
        //localhost for demo
        String ipAddress = JOptionPane.showInputDialog(
                    chatWindow,
                    "Enter IP Address:",
                    "IP Address Required!",
                    JOptionPane.PLAIN_MESSAGE);
        
        Socket soc = new Socket (ipAddress, 9800);
        in = new BufferedReader(new InputStreamReader(soc.getInputStream()));
        out = new PrintWriter(soc.getOutputStream(), true);
        
        while (true)
        {
            String str = in.readLine();
            if (str.equals("NAMEREQUIRED"))
            {
                String name = JOptionPane.showInputDialog(
                chatWindow,
                "Enter a unique name:",
                "Name Required!!",
                JOptionPane.PLAIN_MESSAGE);
                
             out.println(name);
            }
            else if(str.equals("NAMEALREADYEXISTS"))
            {
                String name = JOptionPane.showInputDialog(
                chatWindow,
                "Enter another name:",
                "Name Already Exists!!",
                JOptionPane.WARNING_MESSAGE);
                
               out.println(name);
                
            }
            else if (str.startsWith("NAMEACCEPTED"))
            {
                textField.setEditable(true);
                nameLabel.setText("You are logged in as: "+str.substring(12));
            }
            
            else
            {
                chatArea.append(str + "\n");
            }
        }
        
    }
            
            
            
     public static void main(String[] args) throws Exception {
         
         // A user can launch infinate clients by running the client after running the
         // server and choosing localhost as IP
         Client pbclient1 = new Client(); 
         pbclient1.startChat();
         
         
         
         

}
}

class Listener implements ActionListener
{

    @Override
    public void actionPerformed(ActionEvent e) {
       Client.out.println(Client.textField.getText());
       Client.textField.setText("");
    }
}