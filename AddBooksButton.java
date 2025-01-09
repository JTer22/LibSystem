import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.filechooser.*;

public class AddBooksButton extends JFrame {

    public AddBooksButton() {
        setTitle("Add New Book");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Top Dashboard Panel with Gradient Background
        JPanel topDashboard = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                // Creating Gradient Background
                Graphics2D g2d = (Graphics2D) g;
                Color startColor = new Color(50, 20, 100);
                Color endColor = new Color(188, 154, 248);
                GradientPaint gradient = new GradientPaint(0, 0, startColor, getWidth(), getHeight(), endColor);
                g2d.setPaint(gradient);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        topDashboard.setLayout(new FlowLayout(FlowLayout.CENTER));  // Center the content inside the top dashboard
        topDashboard.setPreferredSize(new Dimension(getWidth(), 100));  // Set the height of the top dashboard

        // Header Label with centered text
        JLabel headerLabel = new JLabel("Add a New Book");
        headerLabel.setFont(new Font("Arial", Font.BOLD, 16));
        headerLabel.setForeground(Color.WHITE);  // Set text color to white to contrast with the background
        topDashboard.add(headerLabel);

        // Add the topDashboard panel to the top of the frame
        add(topDashboard, BorderLayout.NORTH);

        // Center panel with form fields
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridLayout(6, 2, 10, 10)); // 6 rows, 2 columns (adjusted for 6 fields)

        // Title
        JLabel titleLabel = new JLabel("Title: ");
        JTextField titleField = new JTextField();
        formPanel.add(titleLabel);
        formPanel.add(titleField);

        // Author
        JLabel authorLabel = new JLabel("Author: ");
        JTextField authorField = new JTextField();
        formPanel.add(authorLabel);
        formPanel.add(authorField);

        // Description
        JLabel descriptionLabel = new JLabel("Genre: ");
        JTextArea descriptionArea = new JTextArea(3, 20);
        JScrollPane descriptionScrollPane = new JScrollPane(descriptionArea);
        formPanel.add(descriptionLabel);
        formPanel.add(descriptionScrollPane);

        // Genre
        JLabel genreLabel = new JLabel("Description: ");
        JTextField genreField = new JTextField();
        formPanel.add(genreLabel);
        formPanel.add(genreField);

        // Image selection
        JLabel imageLabel = new JLabel("Cover Image: ");
        JButton selectImageButton = new JButton("Choose Image");
        formPanel.add(imageLabel);
        formPanel.add(selectImageButton);

        // Bottom panel with buttons
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new FlowLayout());

        JButton submitButton = new JButton("Submit");
        JButton cancelButton = new JButton("Cancel");

        bottomPanel.add(submitButton);
        bottomPanel.add(cancelButton);

        // Action listeners
        submitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Handle book submission (validation and adding to database)
                JOptionPane.showMessageDialog(null, "Book Added Successfully!");
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
               new MainPage();
                dispose(); // Close the current window
            }
        });

        // Image selection logic
        selectImageButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
                int result = fileChooser.showOpenDialog(null);
                if (result == JFileChooser.APPROVE_OPTION) {
                    String selectedFilePath = fileChooser.getSelectedFile().getAbsolutePath();
                    JOptionPane.showMessageDialog(null, "Image selected: " + selectedFilePath);
                }
            }
        });

        // Add panels to the frame
        add(formPanel, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new AddBooksButton();
            }
        });
    }
}
