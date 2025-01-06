import java.awt.*;
import java.awt.event.ActionEvent;
import javax.swing.*;

public class MainPage extends JFrame {

    public MainPage() {
        // Set up the frame
        setTitle("Furniture Sale Ordering Management System");
        setSize(1000, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center the frame on the screen
        setLayout(new BorderLayout());

        // Create and add a welcome label
        JLabel welcomeLabel = new JLabel("Welcome to Furniture Sale Ordering Management System Application", JLabel.CENTER);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 20));
        add(welcomeLabel, BorderLayout.CENTER);

        // Create and add a login button
        JButton loginButton = new JButton("Login");
        loginButton.addActionListener((ActionEvent e) -> {
            JOptionPane.showMessageDialog(MainPage.this, "Login button clicked!");
        });

        // Add the button to the bottom of the frame
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(loginButton);
        add(buttonPanel, BorderLayout.SOUTH);

        // Make the frame visible
        setVisible(true);
    }

    public static void main(String[] args) {
        // Run the application
        SwingUtilities.invokeLater(() -> new MainPage());
    }
}
