package lab2;

import javax.imageio.ImageIO;    // Part of javax.imageio - used for reading image files into Image objects
import javax.swing.*;   //This is used for creating GUI components
import java.awt.*;  // It includes AWT classes like Color, Image, BorderLayout, etc.
import java.io.IOException;   //It is used for handling input/output exceptions
import java.io.InputStream;   //This represents an input stream of bytes.
import java.net.URI;    //It represents a Uniform Resource Identifier
import java.net.http.HttpClient;     //It is used to send HTTP requests
import java.net.http.HttpRequest;   //It is used to build HTTP requests
import java.net.http.HttpResponse;   //It is used to handle HTTP requests


public class AvatarGenerator {
// It is the main method or the entry point of the program
    public static void main(String[] args) {

        try {
            // Method call: getRandomAvatarStream() is a static method in the same class.
            // Returns: InputStream containing image data from the avatar API
            var avatarStream = AvatarGenerator.getRandomAvatarStream();  // avatarStream is a variable of type InputStream (reference type)
            AvatarGenerator.showAvatar(avatarStream);  //Takes InputStream as argument. and Displays the image in a JFrame window.
        } catch (IOException | InterruptedException e) {
            //Handles exceptions during the operation
            e.printStackTrace();    // Instance method of class Throwable that prints the stack trace
        }

    }
    // Static method to retrieve a randomly generated avatar image from Dicebear API
    public static InputStream getRandomAvatarStream() throws IOException, InterruptedException {
        // Pick a random style
        String[] styles = { "adventurer", "adventurer-neutral", "avataaars", "big-ears", "big-ears-neutral", "big-smile", "bottts", "croodles", "croodles-neutral", "fun-emoji", "icons", "identicon", "initials", "lorelei", "micah", "miniavs", "open-peeps", "personas", "pixel-art", "pixel-art-neutral" }; // Array of avatar styles (String array - reference type)
        var style = styles[(int)(Math.random() * styles.length)];  // Math.random() is a static method in java.lang.Math that returns double between 0.0 to 1.0

        // Generate a random seed
        var seed = (int)(Math.random() * 10000);    // Creates a URI for the API request.

        // Create an HTTP request for a random avatar
        var uri = URI.create("https://api.dicebear.com/9.x/%s/png?seed=%d".formatted(style, seed));
        // URI.create() is a static method that returns a URI object (reference type)
        // String.formatted() is an instance method that returns a formatted string
        var request = HttpRequest.newBuilder(uri).build();
        // HttpRequest.newBuilder() is a static method returning a builder (HttpRequest.Builder - reference type)
        // .build() is an instance method that returns an HttpRequest object

        // Send the request
        try (var client = HttpClient.newHttpClient()) {
            // HttpClient.newHttpClient() is a static method that returns a new HttpClient instance (reference type)
            var response = client.send(request, HttpResponse.BodyHandlers.ofInputStream());
            // client.send() is an instance method that sends an HTTP request and returns a response (HttpResponse<InputStream>)
            // HttpResponse.BodyHandlers.ofInputStream() is a static method returning a handler to receive body as InputStream

            return response.body(); // Returns the body of the HTTP response (InputStream - reference type)
        }
    }
    // Static method that takes an InputStream and displays the image in a JFrame
    public static void showAvatar(InputStream imageStream) {
        // imageStream is a reference type (InputStream)
            JFrame frame = new JFrame("PNG Viewer");// Constructor call: Creates a new JFrame object (javax.swing package) and PNG viewer is a string i.e. reference type
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// Sets close operation - instance method of JFrame
        frame.setResizable(false);// Sets frame resizability to false - instance method
            frame.setSize(200, 200); // Sets frame size to 200x200 pixels - instance method
            frame.getContentPane().setBackground(Color.BLACK);// frame.getContentPane() is an instance method that returns a Container object i.e. reference type and setBackground(Color.BLACK) is an instance method; Color.BLACK is a static field of java.awt.Color

            try {
                // Load the PNG image
                Image image = ImageIO.read(imageStream);// ImageIO.read() is a static method that returns an Image object from input stream i.e. reference type

                // Create a JLabel to display the image
                JLabel imageLabel = new JLabel(new ImageIcon(image));// new ImageIcon(image) creates an ImageIcon object from the image (constructor call) and JLabel is from javax.swing, ImageIcon is from javax.swing
                frame.add(imageLabel, BorderLayout.CENTER);  // add() is an instance method from java.awt.Container and BorderLayout.CENTER is a static String constant

            } catch (IOException e) {
                e.printStackTrace();  // instance method for throwable
            }

            frame.setVisible(true); // Displays the frame - instance method; true is a primitive boolean
    }
}
