import java.awt.*;
import java.awt.event.ActionEvent;
import javax.swing.*;

public class MainPage extends JFrame {
    public MainPage() {
        // Set up the frame
        setTitle("APU Furniture Order Management System");
        setSize(1000, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center the window on the screen
        setLayout(new GridBagLayout()); // Use GridBagLayout for proper centering

        // Create a panel to hold all components
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS)); // Vertical layout for centering
        contentPanel.setAlignmentX(Component.CENTER_ALIGNMENT); // Center alignment
        contentPanel.setAlignmentY(Component.CENTER_ALIGNMENT);

        // Create a panel for the welcome message
        JPanel messagePanel = new JPanel();
        messagePanel.setLayout(new BoxLayout(messagePanel, BoxLayout.Y_AXIS)); // Vertical layout
        messagePanel.setAlignmentX(Component.CENTER_ALIGNMENT); // Center alignment

        // Add a logo to the welcome message panel
        JLabel logoLabel = new JLabel();
        ImageIcon logoIcon = new ImageIcon("logo.png"); // Replace with the path to your logo
        logoLabel.setIcon(logoIcon);
        logoLabel.setHorizontalAlignment(JLabel.CENTER);
        logoLabel.setAlignmentX(Component.CENTER_ALIGNMENT); // Center alignment
        logoLabel.setBorder(BorderFactory.createEmptyBorder(10, 100, 40, 100)); // Add padding around the logo

        // Add the logo and welcome message to the message panel
        JLabel welcomeLabel = new JLabel("Welcome to APU Furniture Order Management System", JLabel.CENTER);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 24)); // Adjust font size and style
        welcomeLabel.setAlignmentX(Component.CENTER_ALIGNMENT); // Center alignment
        messagePanel.add(logoLabel);
        messagePanel.add(welcomeLabel);
        messagePanel.setBorder(BorderFactory.createEmptyBorder(10, 100, 20, 100)); // Add padding around message

        // Create a panel for the buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(2, 1, 10, 20)); // 2 rows, 1 column, vertical gap of 20
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 100, 40, 100)); // Add padding around buttons

        // Add "Login" button
        JButton loginButton = new JButton("Login");
        loginButton.setFont(new Font("Arial", Font.BOLD, 16));
        loginButton.setFocusPainted(false);
        loginButton.setBackground(Color.LIGHT_GRAY);
        loginButton.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 2));
        loginButton.setPreferredSize(new Dimension(60, 60)); // Set button size to 60x60
        loginButton.addActionListener((ActionEvent e) -> {
            JOptionPane.showMessageDialog(MainPage.this, "Login button clicked!", "Information", JOptionPane.INFORMATION_MESSAGE);
            // Implement navigation to Login screen here
        });
        buttonPanel.add(loginButton);

        // Add "Exit" button
        JButton exitButton = new JButton("Exit System");
        exitButton.setFont(new Font("Arial", Font.BOLD, 16));
        exitButton.setFocusPainted(false);
        exitButton.setBackground(Color.LIGHT_GRAY);
        exitButton.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 2));
        exitButton.setPreferredSize(new Dimension(60, 60)); // Set button size to 60x60
        exitButton.addActionListener((ActionEvent e) -> {
            int confirm = JOptionPane.showConfirmDialog(MainPage.this, "Are you sure you want to exit?", "Exit Confirmation", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                System.exit(0);
            }
        });
        buttonPanel.add(exitButton);

        // Add components to the content panel
        contentPanel.add(messagePanel);
        contentPanel.add(buttonPanel);

        // Use GridBagLayout to center contentPanel vertically and horizontally
        add(contentPanel);

        // Set the frame visible
        setVisible(true);
    }

    public static void main(String[] args) {
        // Run the GUI application
        SwingUtilities.invokeLater(() -> new MainPage());
    }
}
