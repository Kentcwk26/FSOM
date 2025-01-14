import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.*;

public class LandingPage extends JFrame {
    public LandingPage() {
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
            LoginPage loginPage = new LoginPage(this); // Pass reference of LandingPage to LoginPage
            loginPage.setVisible(true); // Hide LandingPage initially
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
            int confirm = JOptionPane.showConfirmDialog(LandingPage.this, "Are you sure you want to exit?", "Exit Confirmation", JOptionPane.YES_NO_OPTION);
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
        SwingUtilities.invokeLater(() -> new LandingPage());
    }
}

class LoginPage extends JFrame {
    protected LandingPage landingPage; // Reference to LandingPage

    public LoginPage(LandingPage landingPage) {
        this.landingPage = landingPage; // Store the reference to LandingPage

        // Set up the frame
        setTitle("Login");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Close only this window
        setLocationRelativeTo(null);

        // Create a panel for login components
        JPanel loginPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Username label and text field
        JLabel usernameLabel = new JLabel("Username:");
        JTextField usernameField = new JTextField(20);

        // Password label and password field
        JLabel passwordLabel = new JLabel("Password:");
        JPasswordField passwordField = new JPasswordField(20);

        // Login button
        JButton loginButton = new JButton("Login");
        loginButton.addActionListener((ActionEvent e) -> {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());
            String userType = authenticate(username, password);
            if (userType != null) {
                JOptionPane.showMessageDialog(this, "Login Successful", "Success", JOptionPane.INFORMATION_MESSAGE);

                landingPage.dispose(); // Close the LandingPage
                this.dispose(); // Close the LoginPage

                switch (userType) {
                    case "Admin" -> new Admin(username).setVisible(true);
                    case "Officer" -> new Officer(username).setVisible(true);
                    case "SalesPerson" -> new SalesPerson(username).setVisible(true);
                    default -> JOptionPane.showMessageDialog(this, "Unknown user type: " + userType, "Error", JOptionPane.ERROR_MESSAGE);
                }

            } else {
                JOptionPane.showMessageDialog(this, "Invalid username or password", "Error", JOptionPane.ERROR_MESSAGE);
                usernameField.setText(""); // Clear the username field
                passwordField.setText(""); // Clear the password field
            }
        });

        // Add components to the panel
        gbc.gridx = 0; gbc.gridy = 0;
        loginPanel.add(usernameLabel, gbc);
        gbc.gridx = 1;
        loginPanel.add(usernameField, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        loginPanel.add(passwordLabel, gbc);
        gbc.gridx = 1;
        loginPanel.add(passwordField, gbc);

        gbc.gridx = 0; gbc.gridy = 2; gbc.gridwidth = 2;
        loginPanel.add(loginButton, gbc);

        // Add the login panel to the frame
        add(loginPanel);

        // Set the frame visible
        setVisible(true);
    }

    private String authenticate(String username, String password) {
        File file = new File("user.txt");
        if (!file.exists()) {
            JOptionPane.showMessageDialog(this, "User file not found", "Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 8) {
                    String fileUsername = parts[0].trim();
                    String filePassword = parts[1].trim();
                    String userType = parts[7].trim();
                    if (fileUsername.equals(username) && filePassword.equals(password)) {
                        return userType; // Return the user type if credentials match
                    }
                }
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "An error occurred while reading the user file.", "Error", JOptionPane.ERROR_MESSAGE);
        }
        return null;
    }
}