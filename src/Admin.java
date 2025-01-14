import java.awt.*;
import javax.swing.*;

public class Admin extends JFrame {

    static CardLayout cardLayout;
        static JPanel mainPanel;
                            
        public Admin(String username) {
            // Set up the frame
            setTitle("Admin Page");
            setSize(1000, 600);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setLocationRelativeTo(null);
    
            // Initialize CardLayout and mainPanel
            cardLayout = new CardLayout();
            mainPanel = new JPanel(cardLayout);
    
            // Add all pages to the CardLayout
            mainPanel.add(createAdminPage(username), "AdminPage");
            mainPanel.add(new HomePage(), "HomePage");
            mainPanel.add(new ViewModifyPersonalProfile(), "PersonalProfile");
            mainPanel.add(new ViewModifySalesPersonProfile(), "SalesPersonProfile");
            mainPanel.add(new ViewModifyOfficerProfile(), "OfficerProfile");
            mainPanel.add(new Report(), "Report");
    
            // Add mainPanel to the frame
            add(mainPanel);
    
            // Display the frame
            setVisible(true);
        }
    
        private JPanel createAdminPage(String username) {
            JPanel adminPage = new JPanel(new BorderLayout());
    
            // ===== Header Panel =====
            JPanel headerPanel = new JPanel(new BorderLayout());
            JPanel headerButtonsPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 10));
    
            String[] headerButtons = {"Home", "Officer", "Admin", "SalesPerson"};
            for (String text : headerButtons) {
                JButton button = new JButton(text);
                button.setPreferredSize(new Dimension(120, 30));
                button.addActionListener(e -> {
                    switch (text) {
                        case "Home" -> cardLayout.show(mainPanel, "HomePage");
                        case "Officer" -> JOptionPane.showMessageDialog(this, "Officer page coming soon!");
                        case "Admin" -> JOptionPane.showMessageDialog(this, "Admin page coming soon!");
                        case "SalesPerson" -> JOptionPane.showMessageDialog(this, "SalesPerson page coming soon!");
                    }
                });
                headerButtonsPanel.add(button);
            }
            headerPanel.add(headerButtonsPanel, BorderLayout.EAST);
    
            // ===== Content Panel =====
            JPanel contentPanel = new JPanel(new GridBagLayout());
            JPanel buttonPanel = new JPanel();
            buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
    
            String[] buttonTexts = {
                "View / Modify Personal Profile",
                "View / Modify Salesperson Profile",
                "View / Modify Officer Profile",
                "Report"
            };
    
            for (String text : buttonTexts) {
                JButton button = new JButton(text);
                button.setPreferredSize(new Dimension(400, 80)); // Adjust button size as needed
                button.setMaximumSize(new Dimension(400, 80)); // Ensure consistent sizing
                button.setAlignmentX(Component.CENTER_ALIGNMENT);
                button.addActionListener(e -> {
                    switch (text) {
                        case "View / Modify Personal Profile" -> cardLayout.show(mainPanel, "PersonalProfile");
                        case "View / Modify Salesperson Profile" -> cardLayout.show(mainPanel, "SalesPersonProfile");
                        case "View / Modify Officer Profile" -> cardLayout.show(mainPanel, "OfficerProfile");
                        case "Report" -> cardLayout.show(mainPanel, "Report");
                    }
                });
                buttonPanel.add(button);
                buttonPanel.add(Box.createRigidArea(new Dimension(0, 20))); // Spacing
            }
    
            contentPanel.add(buttonPanel);
    
            // ===== Footer Panel =====
            JPanel footerPanel = new JPanel(new BorderLayout(10, 10));
            footerPanel.setPreferredSize(new Dimension(1000, 60));

            // Add padding around the footer panel
            footerPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Top, left, bottom, right padding

            JLabel loggedInLabel = new JLabel("Logged in as: " + username);
            loggedInLabel.setFont(new Font("Arial", Font.ITALIC, 14));
            footerPanel.add(loggedInLabel, BorderLayout.WEST);

            JButton exitButton = new JButton("Exit System");
            exitButton.setFont(new Font("Arial", Font.BOLD, 14));
            exitButton.addActionListener(e -> System.exit(0));
            footerPanel.add(exitButton, BorderLayout.EAST);

            adminPage.add(footerPanel, BorderLayout.SOUTH);
    
            adminPage.add(headerPanel, BorderLayout.NORTH);
            adminPage.add(contentPanel, BorderLayout.CENTER);
            adminPage.add(footerPanel, BorderLayout.SOUTH);
    
            return adminPage;
        }
    
        public static void main(String[] args) {
            SwingUtilities.invokeLater(() -> new Admin("AdminUser"));
        }
    }
    
    // ===== Additional Pages =====
    class HomePage extends JPanel {
        public HomePage() {
            setLayout(new BorderLayout());
            JLabel label = new JLabel("Welcome to Home Page", SwingConstants.CENTER);
            label.setFont(new Font("Arial", Font.BOLD, 18));
            add(label, BorderLayout.CENTER);
        }
    }
    
    class ViewModifyPersonalProfile extends JPanel {
        public ViewModifyPersonalProfile() {
            setLayout(new BorderLayout());
            JLabel label = new JLabel("View / Modify Personal Profile", SwingConstants.CENTER);
            label.setFont(new Font("Arial", Font.BOLD, 18));
            add(label, BorderLayout.CENTER);
    
            JButton backButton = new JButton("Back");
            backButton.addActionListener(e -> Admin.cardLayout.show(Admin.mainPanel, "AdminPage"));
            add(backButton, BorderLayout.SOUTH);
        }
    }
    
    class ViewModifySalesPersonProfile extends JPanel {
        public ViewModifySalesPersonProfile() {
            setLayout(new BorderLayout());
            JLabel label = new JLabel("View / Modify SalesPerson Profile", SwingConstants.CENTER);
            label.setFont(new Font("Arial", Font.BOLD, 18));
            add(label, BorderLayout.CENTER);
    
            JButton backButton = new JButton("Back");
            backButton.addActionListener(e -> Admin.cardLayout.show(Admin.mainPanel, "AdminPage"));
            add(backButton, BorderLayout.SOUTH);
        }
    }
    
    class ViewModifyOfficerProfile extends JPanel {
        public ViewModifyOfficerProfile() {
            setLayout(new BorderLayout());
    
            // ===== Header Panel =====
            JLabel headerLabel = new JLabel();
    
            JPanel headerPanel = new JPanel(new BorderLayout());
            headerPanel.setPreferredSize(new Dimension(1000, 50));
            headerPanel.add(headerLabel, BorderLayout.WEST);
    
            // Officer ID Panel
            JPanel officerIdPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
    
            JLabel officerIdLabel = new JLabel("Officer ID:");
            officerIdLabel.setFont(new Font("Arial", Font.BOLD, 14));
            JTextField officerIdField = new JTextField(20);
            JButton enterButton = new JButton("Enter");
    
            officerIdPanel.add(officerIdLabel);
            officerIdPanel.add(officerIdField);
            officerIdPanel.add(enterButton);
            headerPanel.add(officerIdPanel, BorderLayout.CENTER);
    
            // Header Buttons Panel
            JPanel headerButtonsPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 10));
    
            String[] headerButtons = {"Home", "Officer", "Admin", "SalesPerson"};
            for (String text : headerButtons) {
                JButton button = new JButton(text);
                button.setPreferredSize(new Dimension(120, 30));
                button.addActionListener(e -> {
                    switch (text) {
                        case "Home" -> Admin.cardLayout.show(Admin.mainPanel, "HomePage");
                        case "Officer" -> JOptionPane.showMessageDialog(this, "Officer page coming soon!");
                        case "Admin" -> JOptionPane.showMessageDialog(this, "Admin page coming soon!");
                        case "SalesPerson" -> JOptionPane.showMessageDialog(this, "SalesPerson page coming soon!");
                    }
                });
                headerButtonsPanel.add(button);
            }
    
            headerPanel.add(headerButtonsPanel, BorderLayout.EAST);
    
            // Add the header panel to the main panel
            add(headerPanel, BorderLayout.NORTH);
    
            JPanel formPanel = new JPanel();
            formPanel.setLayout(new GridBagLayout());
    
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.insets = new Insets(10, 10, 10, 10);
    
            // Add form elements to formPanel
            JLabel nameLabel = new JLabel("Display Name:");
            JLabel icLabel = new JLabel("IC:");
            JLabel genderLabel = new JLabel("Gender:");
            JLabel contactLabel = new JLabel("Contact Number:");
            JLabel emailLabel = new JLabel("Email:");
    
            gbc.gridx = 0;
            gbc.gridy = 0;
            formPanel.add(nameLabel, gbc);
            gbc.gridy = 1;
            formPanel.add(icLabel, gbc);
            gbc.gridy = 2;
            formPanel.add(genderLabel, gbc);
            gbc.gridy = 3;
            formPanel.add(contactLabel, gbc);
            gbc.gridy = 4;
            formPanel.add(emailLabel, gbc);
    
            gbc.gridx = 1;
            gbc.gridy = 0;
            formPanel.add(new JLabel("Placeholder Name"), gbc);
            gbc.gridy = 1;
            formPanel.add(new JLabel("Placeholder IC"), gbc);
            gbc.gridy = 2;
            formPanel.add(new JLabel("Placeholder Gender"), gbc);
            gbc.gridy = 3;
            formPanel.add(new JLabel("Placeholder Contact"), gbc);
            gbc.gridy = 4;
            formPanel.add(new JLabel("Placeholder Email"), gbc);
    
            // ===== Footer Panel =====
            JPanel footerPanel = new JPanel(new BorderLayout());

            // Add padding around the footer panel
            footerPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

            // Back Button on the left
            JButton backButton = new JButton("Back");
            backButton.setPreferredSize(new Dimension(100, 40));
            backButton.addActionListener(e -> Admin.cardLayout.show(Admin.mainPanel, "AdminPage"));

            // Panel to hold the Back button with padding
            JPanel backButtonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
            backButtonPanel.setOpaque(false); // Make it match the footer background
            backButtonPanel.add(backButton);
            footerPanel.add(backButtonPanel, BorderLayout.WEST);

            // Modify Button on the right
            JButton modifyButton = new JButton("Modify");
            modifyButton.setPreferredSize(new Dimension(100, 40));
            modifyButton.addActionListener(e -> JOptionPane.showMessageDialog(this, "Modify action to be implemented!"));

            // Panel to hold the Modify button with padding
            JPanel modifyButtonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 0, 0));
            modifyButtonPanel.setOpaque(false); // Make it match the footer background
            modifyButtonPanel.add(modifyButton);
            footerPanel.add(modifyButtonPanel, BorderLayout.EAST);

            // Add footerPanel to the main layout
            add(footerPanel, BorderLayout.SOUTH);
    
            // Add panels to the main layout
            add(formPanel, BorderLayout.CENTER);
            add(footerPanel, BorderLayout.SOUTH);
        }
    }    
    
    class Report extends JPanel {
        public Report() {
            setLayout(new BorderLayout());
            JLabel label = new JLabel("Report Page", SwingConstants.CENTER);
            label.setFont(new Font("Arial", Font.BOLD, 18));
            add(label, BorderLayout.CENTER);
    
            JButton backButton = new JButton("Back");
            backButton.addActionListener(e -> Admin.cardLayout.show(Admin.mainPanel, "AdminPage"));
            add(backButton, BorderLayout.SOUTH);
    }
}
