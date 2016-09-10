package com.spacegame.network;

import netgame.common.Client;
import netgame.common.DisconnectMessage;

import javax.swing.*;
import javax.swing.text.AttributeSet;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

/**
 * Created by jparrott on 9/3/2016.
 */
public class PlayerTerminal extends JFrame {

    private final static int PORT = 37829;
    private final Color DEFAULT_TEXT_COLOR = Color.GREEN;
    private final static Logger log = LogManager.getLogger(PlayerTerminal.class);

    private JTextField messageInput;   // For entering commands sent to the server
    private TradeWarsClient connection;      // Represents the connection to the Hub; used to send and process messages;
    private volatile boolean connected; // This is true while the client is connected to the hub.
    private String playerName;
    private JTextPane transcript;

    /**
     * get the server of the server host and then launch a new player terminal
     * @param args
     */
    public static void main(String[] args) {
        try {
            /*
            UIManager.setLookAndFeel("com.jtattoo.plaf.aluminium.AluminiumLookAndFeel");
            UIManager.setLookAndFeel("com.jtattoo.plaf.mint.MintLookAndFeel");
            UIManager.setLookAndFeel("com.jtattoo.plaf.luna.LunaLookAndFeel");
            UIManager.setLookAndFeel("com.jtattoo.plaf.mcwin.McWinLookAndFeel");
            UIManager.setLookAndFeel("com.jtattoo.plaf.fast.FastLookAndFeel");
            UIManager.setLookAndFeel("com.jtattoo.plaf.bernstein.BernsteinLookAndFeel");
            UIManager.setLookAndFeel("com.jtattoo.plaf.aero.AeroLookAndFeel");
            UIManager.setLookAndFeel("com.jtattoo.plaf.acryl.AcrylLookAndFeel");
            UIManager.setLookAndFeel("com.jtattoo.plaf.graphite.GraphiteLookAndFeel");
            UIManager.setLookAndFeel("com.jtattoo.plaf.noire.NoireLookAndFeel");
            */

            //dark UI
            UIManager.setLookAndFeel("com.jtattoo.plaf.hifi.HiFiLookAndFeel");

        }
        catch (UnsupportedLookAndFeelException e) {
        }
        catch (ClassNotFoundException e) {
        }
        catch (InstantiationException e) {
        }
        catch (IllegalAccessException e) {
        }

        String host = "localhost";
        String playerName = "josh";
        /*
        String host = JOptionPane.showInputDialog(
                "Enter the host name of the\ncomputer that hosts the server:");
        if (host == null || host.trim().length() == 0)
            return;
        */
        playerName = JOptionPane.showInputDialog(
                "enter username");
        if (playerName == null || playerName.trim().length() == 0)
            return;
        PlayerTerminal window = new PlayerTerminal(host, playerName);
        window.setLocation(200,100);
        window.setVisible(true);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    /**
     * A TradeWarsClient connects to the Hub and is used to send messages to
     * and receive messages from a Hub.  Messages received from the
     * Hub will be of type ForwardedMessage and will contain the
     * ID number of the sender and the string that was sent by
     * that user.
     */
    private class TradeWarsClient extends Client {

        /**
         * Opens a connection the chat room server on a specified computer.
         */
        TradeWarsClient(String host) throws IOException {
            super(host, PORT);
        }

        /**
         * Processes Response objects from the server
         */
        @Override
        protected void messageReceived(Object message) {
            log.info("Enter messageReceieved()");
            log.debug("param message: " + message);
            try{
                Response response = (Response)message;
                log.debug("response.body: " + response.getBody());
                if(response.isClearRequest()){
                    transcript.setText(null);
                }
                addToTranscript(response.getBody());

            } catch (ClassCastException cc){
                log.error(cc);
                //print the error to the transcript
                addToTranscript("An error has occured:\n" + cc.getMessage());
            }
            log.info("Exit messageReceieved()");
        }

        /**
         * Called when the connection to the client is shut down because of some
         * error message.  (This will happen if the server program is terminated.)
         */
        @Override
        protected void connectionClosedByError(String message) {
            addToTranscript("Sorry, communication has shut down due to an error:\n     " + message);
            messageInput.setEnabled(false);
            messageInput.setEditable(false);
            messageInput.setText("");
            connected = false;
            connection = null;
        }

        @Override
        protected void extraHandshake(ObjectInputStream in, ObjectOutputStream out)
                throws IOException {
            log.info("Enter extraHandshake()");

            out.writeObject(playerName);
            out.flush();
            try{
                Object response = in.readObject();
                if(response instanceof DisconnectMessage){
                    log.debug("recieved disconnect message");
                    addToTranscript(((DisconnectMessage) response).message);
                    disconnect();
                }
                else if(response instanceof String){
                    addToTranscript((String)response);
                }
            } catch (ClassNotFoundException ex){
                log.error(ex);
            }

            log.info("Exit extraHandshake()");
        }
    } // end nested class TradeWarsClient

    /**
     * Constructor creates the window and starts the process of connecting
     * to the hub; the actual connection is done in a separate thread.
     * @param host  The IP address or host name of the computer where the server is running.
     */
    private PlayerTerminal(final String host, final String playerName) {
        super("TradeWards Client");
        this.playerName = playerName;
        this.setPreferredSize(new Dimension(500, 600));
        setBackground(Color.BLACK);
        transcript = new JTextPane();
        transcript.setFocusable(false);
        transcript.setBackground(Color.BLACK);
        JScrollPane scrollPane = new JScrollPane(transcript);
        scrollPane.setPreferredSize(new Dimension(300, 500));
        add(scrollPane, BorderLayout.CENTER);

        messageInput = new JTextField();
        messageInput.setPreferredSize(new Dimension(500, 30));
        ActionHandler ah = new ActionHandler();
        messageInput.addActionListener(ah);
        messageInput.setEditable(false);
        messageInput.setEnabled(false);

        messageInput.setBackground(Color.BLACK);
        messageInput.setForeground(DEFAULT_TEXT_COLOR);

        JPanel bottom = new JPanel();
        bottom.setLayout(new BorderLayout());
        bottom.setBackground(Color.BLACK);
        bottom.add(messageInput, BorderLayout.CENTER);
        add(bottom,BorderLayout.SOUTH);
        pack();
        addWindowListener( new WindowAdapter() { // calls doQuit if user closes window
            public void windowClosing(WindowEvent e) {
                doQuit();
            }
        });
        new Thread() {
            // This is a thread that opens the connection to the server.  Since
            // that operation can block, it's not done directly in the constructor.
            // Once the connection is established, the user interface elements are
            // enabled so the user can send messages.  The Thread dies after
            // the connection is established or after an error occurs.
            public void run() {
                try {
                    addToTranscript("Connecting to " + host + " ...");
                    connection = new TradeWarsClient(host);
                    connected = true;
                    messageInput.setEditable(true);
                    messageInput.setEnabled(true);
                    messageInput.requestFocus();
                }
                catch (IOException e) {
                    addToTranscript("Connection attempt failed.");
                    addToTranscript("Error: " + e);
                }
            }
        }.start();
    }

    /**
     * Called when the user clicks the Quit button or closes
     * the window by clicking its close box.
     */
    private void doQuit() {
        log.info("Enter doQuit()");

        if (connected)
            connection.disconnect();  // Sends a DisconnectMessage to the server.
        dispose();
        try {
            Thread.sleep(1000); // Time for DisconnectMessage to actually be sent.
        }
        catch (InterruptedException e) {
        }

        log.info("Exit doQuit()");
        System.exit(0);
    }

    /**
     * builds Request object from a string to send to server
     * @param message
     * @return
     */
    private Request buildRequest(String message){
        log.info("Enter buildRequest()");
        Request request = new Request(message);
        log.info("Exit buildRequest()");
        return request;
    }

    /**
     * Defines the object that handles all ActionEvents for the program.
     */
    private class ActionHandler implements ActionListener {
        public void actionPerformed(ActionEvent evt) {
            Object src = evt.getSource();
            if (src == messageInput) {
                String message = messageInput.getText();

                //don't send null requests
                if (message.trim().length() == 0)
                    return;

                //build request object and send to server
                Request request = buildRequest(message);
                connection.send(request);

                //clear out the message input for next command
                messageInput.setText(null);
                messageInput.requestFocus();
            }
        }
    }

    public String getPlayerName(){
        return playerName;
    }

    public void addToTranscript(String message){
        addToTranscript(message, DEFAULT_TEXT_COLOR);
    }

    public void addToTranscript(String message, Color color){
        log.info("Enter addToTranscript()");

        log.debug("message: " + message);
        log.debug("color: " + color);

        message += "\n";
        StyleContext styleContext = StyleContext.getDefaultStyleContext();
        AttributeSet attributeSet = styleContext.addAttribute(SimpleAttributeSet.EMPTY, StyleConstants.Foreground, color);

        attributeSet = styleContext.addAttribute(attributeSet, StyleConstants.FontFamily, "Lucida Console");
        attributeSet = styleContext.addAttribute(attributeSet, StyleConstants.Alignment, StyleConstants.ALIGN_JUSTIFIED);

        int len = transcript.getDocument().getLength();
        transcript.setCaretPosition(len);
        transcript.setCharacterAttributes(attributeSet, false);
        transcript.replaceSelection(message);

        log.info("Exit addToTranscript()");
    }

}

