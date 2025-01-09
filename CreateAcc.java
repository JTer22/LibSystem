import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CreateAcc extends JFrame {

    private JLabel GetbackLabel;

    public CreateAcc() {
        setTitle("Library");
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        Font labelFont = new Font("Arial", Font.PLAIN, 12); 

        // LOGO
        ImageIcon logoIcon = new ImageIcon("Books/book.png");  
        Image img = logoIcon.getImage(); 
        Image resizedImg = img.getScaledInstance(350, 350 ,Image.SCALE_SMOOTH); 
        logoIcon = new ImageIcon(resizedImg);

        JLabel logoLabel = new JLabel(logoIcon);
        logoLabel.setAlignmentX(Component.CENTER_ALIGNMENT); 

        // Left panel DASHBOARD
        JPanel leftPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                // GRADIENT COLOR
                Graphics2D g2d = (Graphics2D) g;
                Color startColor = new Color(50, 20, 100);
                Color endColor = new Color(188, 154, 248); 
                GradientPaint gradient = new GradientPaint(0, 0, startColor, getWidth(), getHeight(), endColor);
                g2d.setPaint(gradient);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        leftPanel.setPreferredSize(new Dimension(200, 5)); 
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));  
        leftPanel.add(Box.createVerticalStrut(50));
        leftPanel.add(logoLabel); 
        leftPanel.add(Box.createVerticalStrut(50)); 

        // Right panel for the Create Account form
        JPanel rightPanel = new JPanel();
        rightPanel.setBackground(new Color(245, 245, 245)); 
        rightPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // space between elements

        // Title label for the form
        JLabel titleLabel = new JLabel("Create Account");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        rightPanel.add(titleLabel, gbc);

        // Name label and text field
        JLabel nameLabel = new JLabel("Name:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        rightPanel.add(nameLabel, gbc);

        JTextField nameField = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 1;
        rightPanel.add(nameField, gbc);

        // Email label and text field
        JLabel emailLabel = new JLabel("Email:");
        gbc.gridx = 0;
        gbc.gridy = 2;
        rightPanel.add(emailLabel, gbc);

        JTextField emailField = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 2;
        rightPanel.add(emailField, gbc);

        // Password label and password field
        JLabel passwordLabel = new JLabel("Password:");
        gbc.gridx = 0;
        gbc.gridy = 3;
        rightPanel.add(passwordLabel, gbc);

        JPasswordField passwordField = new JPasswordField(20);
        gbc.gridx = 1;
        gbc.gridy = 3;
        rightPanel.add(passwordField, gbc);

        // Confirm Password label and password field
        JLabel confirmPasswordLabel = new JLabel("Confirm Password:");
        gbc.gridx = 0;
        gbc.gridy = 4;
        rightPanel.add(confirmPasswordLabel, gbc);

        JPasswordField confirmPasswordField = new JPasswordField(20);
        gbc.gridx = 1;
        gbc.gridy = 4;
        rightPanel.add(confirmPasswordField, gbc);

        // Create Account Button
        JButton createAccountButton = new JButton("Create Account");
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        createAccountButton.setBackground(new Color(50, 20, 100));
        createAccountButton.setForeground(Color.WHITE);
        createAccountButton.setFont(new Font("Arial", Font.BOLD, 14));
        createAccountButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Handle account creation logic here
                String name = nameField.getText();
                String email = emailField.getText();
                char[] password = passwordField.getPassword();
                char[] confirmPassword = confirmPasswordField.getPassword();

                // Perform validation and further actions for creating the account

                if (String.valueOf(password).equals(String.valueOf(confirmPassword))) {
                    JOptionPane.showMessageDialog(CreateAcc.this, "Account Created Successfully!");
                } else {
                    JOptionPane.showMessageDialog(CreateAcc.this, "Passwords do not match!");
                }
            }
        });
        rightPanel.add(createAccountButton, gbc);

        // "Go back" label
        GetbackLabel = new JLabel("<html><u>Sign in</u></html>");
        GetbackLabel.setFont(labelFont);
        GetbackLabel.setForeground(Color.BLACK); 
        GetbackLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        GetbackLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                new LoginMain();  // Open the login screen
                dispose();
            }
        });
        gbc.gridx = 0;
        gbc.gridy = 6;
        rightPanel.add(GetbackLabel, gbc);

        // Add panels to frame
        add(leftPanel, BorderLayout.WEST);
        add(rightPanel, BorderLayout.CENTER);

        setVisible(true);
    }

    public static void main (String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new CreateAcc(); 
            }
        });
    }
}
