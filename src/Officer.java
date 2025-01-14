import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class Officer extends JFrame {

    public Officer(String username) {
        // Set up the frame
        setTitle("Officer Page");
        setSize(1000, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout()); // Use BorderLayout for overall layout

        // ===== Header Panel =====
        JPanel headerPanel = new JPanel();
        headerPanel.setLayout(new FlowLayout(FlowLayout.RIGHT)); // Align buttons to the right
        headerPanel.setBackground(Color.LIGHT_GRAY);

        String[] headerButtons = {"Home", "Officer", "Admin", "Sales"};
        for (String text : headerButtons) {
            JButton button = new JButton(text);
            headerPanel.add(button);
        }

        // ===== Sidebar Panel =====
        JPanel sidebarPanel = new JPanel();
        sidebarPanel.setLayout(new BoxLayout(sidebarPanel, BoxLayout.Y_AXIS)); // Vertical layout
        sidebarPanel.setBackground(Color.DARK_GRAY);

        String[] sidebarButtons = {"Approval", "Search", "Modify", "Invoice", "Status (Furniture)", "Generate Report"};
        for (String text : sidebarButtons) {
            JButton button = new JButton(text);
            button.setAlignmentX(Component.CENTER_ALIGNMENT); // Center align buttons
            sidebarPanel.add(Box.createRigidArea(new Dimension(0, 20))); // Add spacing
            sidebarPanel.add(button);
        }

        // ===== Content Panel =====
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BorderLayout());
        contentPanel.setBackground(Color.WHITE);

        // Title for the content panel
        JLabel titleLabel = new JLabel("Search Sale Order Table", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        contentPanel.add(titleLabel, BorderLayout.NORTH);

        // Table for displaying data
        String[] columns = {"Customer Name", "OrderID", "IC/Passport", "Contact Number", "Email", "Status"};
        Object[][] data = {
                {"Jason Michael", "110", "030918087589", "018-9987189", "marshall@gmail.com", "Approve"}
        };
        JTable table = new JTable(new DefaultTableModel(data, columns));
        JScrollPane tableScrollPane = new JScrollPane(table);
        contentPanel.add(tableScrollPane, BorderLayout.CENTER);

        // Search bar at the top-right of the content panel
        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JLabel searchLabel = new JLabel("Search:");
        JTextField searchField = new JTextField(15);
        JButton searchButton = new JButton("Import");
        searchPanel.add(searchLabel);
        searchPanel.add(searchField);
        searchPanel.add(searchButton);
        contentPanel.add(searchPanel, BorderLayout.NORTH);

        // ===== Footer Panel (Optional) =====
        JPanel footerPanel = new JPanel();
        footerPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        footerPanel.setBackground(Color.LIGHT_GRAY);

        JLabel loggedInLabel = new JLabel("Logged in as: " + username);
        loggedInLabel.setFont(new Font("Arial", Font.ITALIC, 14));
        footerPanel.add(loggedInLabel);

        // Add panels to the frame
        add(headerPanel, BorderLayout.NORTH);
        add(sidebarPanel, BorderLayout.WEST);
        add(contentPanel, BorderLayout.CENTER);
        add(footerPanel, BorderLayout.SOUTH);

        // Make the frame visible
        setVisible(true);
    }

    public static void main(String[] args) {
        // Run the GUI application
        SwingUtilities.invokeLater(() -> new Officer("OfficerUser"));
    }
}
