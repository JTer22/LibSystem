import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.border.Border;

public class LoginMain extends JFrame {

    private JTextField emailField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JLabel welcomeLabel;
    private JLabel emailLabel;
    private JLabel passwordLabel;
    private JLabel createAccountLabel;

    public LoginMain() {
    
        setTitle("LIBRARY SYSTEM");
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

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

        // LEFT DASHBOARD
        leftPanel.setPreferredSize(new Dimension(200, 5)); 
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));  
        leftPanel.add(Box.createVerticalStrut(50));
        leftPanel.add(logoLabel); 
        leftPanel.add(Box.createVerticalStrut(50)); 

        // RIGHT PANEL
        JPanel rightPanel = new JPanel();
        rightPanel.setBackground(new Color(245, 245, 245)); 
        rightPanel.setLayout(new BorderLayout());

        // JPanel topDashboardPanel = new JPanel();
        // topDashboardPanel.setBackground(new Color(255, 200, 128)); 
        // topDashboardPanel.setPreferredSize(new Dimension(600, 50)); 
        // topDashboardPanel.setLayout(new FlowLayout(FlowLayout.LEFT));

        // rightPanel.add(topDashboardPanel, BorderLayout.NORTH);

        JPanel loginSystemPanel = new JPanel();
        loginSystemPanel.setLayout(new GridBagLayout()); 
        loginSystemPanel.setBackground(new Color(245, 245, 245));
        loginSystemPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); 

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); 

        Font labelFont = new Font("Arial", Font.PLAIN, 12); 
        Font buttonFont = new Font("Arial", Font.BOLD, 12);

        //  welcome label
        welcomeLabel = new JLabel("Welcome to LIBRARY MANAGEMENT");
        welcomeLabel.setFont(new Font("Helvetica", Font.BOLD, 16)); 
        welcomeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        welcomeLabel.setForeground(new Color(95, 43, 151)); 

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2; 
        gbc.insets = new Insets(20, 10, 10, 10); 
        loginSystemPanel.add(welcomeLabel, gbc);

        // email label and text field
        emailLabel = new JLabel("");
        emailLabel.setFont(labelFont);
        emailField = new JTextField(20);
        emailField.setFont(labelFont);
        emailField.setPreferredSize(new Dimension(200, 30));
        emailField.setMaximumSize(new Dimension(200, 30));
        emailField.setMinimumSize(new Dimension(200, 30));

        // Set a rounded border for the text field
        Border roundedBorder = BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(188, 154, 248), 2),
            BorderFactory.createEmptyBorder(5, 10, 5, 10) // Padding inside the text field
        );
        emailField.setBorder(roundedBorder);
        emailField.setText("Email");
        emailField.setForeground(Color.GRAY); 
        emailField.setHorizontalAlignment(SwingConstants.LEFT); 

        emailField.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent evt) {
                if (emailField.getText().equals("Email")) {
                    emailField.setText("");
                    emailField.setForeground(Color.BLACK);
                }
            }
            public void focusLost(FocusEvent evt) {
                if (emailField.getText().isEmpty()) {
                    emailField.setText("Email");
                    emailField.setForeground(Color.GRAY);  
                }
            }
        });

        gbc.gridx = 0;
        gbc.gridy = 1;
        loginSystemPanel.add(emailLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        loginSystemPanel.add(emailField, gbc);

        //password label and password field
        passwordLabel = new JLabel("");
        passwordLabel.setFont(labelFont);
        passwordField = new JPasswordField(20);
        passwordField.setFont(labelFont);
        passwordField.setPreferredSize(new Dimension(200, 30));
        passwordField.setMaximumSize(new Dimension(200, 30));
        passwordField.setMinimumSize(new Dimension(200, 30));

        passwordField.setBorder(roundedBorder);
        passwordField.setText("Password");
        passwordField.setForeground(Color.GRAY);  
        passwordField.setHorizontalAlignment(SwingConstants.LEFT); 

        passwordField.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent evt) {
                if (String.valueOf(passwordField.getPassword()).equals("Password")) {
                    passwordField.setText("");
                    passwordField.setForeground(Color.BLACK);
                }
            }
            public void focusLost(FocusEvent evt) {
                if (String.valueOf(passwordField.getPassword()).isEmpty()) {
                    passwordField.setText("Password");
                    passwordField.setForeground(Color.GRAY);
                }
            }
        });

        gbc.gridx = 0;
        gbc.gridy = 2;
        loginSystemPanel.add(passwordLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        loginSystemPanel.add(passwordField, gbc);

        // login button
        loginButton = new JButton("LOGIN");
        loginButton.setBackground(new Color(255, 92, 0)); 
        loginButton.setForeground(Color.WHITE);
        loginButton.setFont(new Font("Helvetica", Font.BOLD, 13));
        loginButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        loginButton.setFocusPainted(false);
        loginButton.setPreferredSize(new Dimension(220, 35)); 
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String email = emailField.getText();
                String password = new String(passwordField.getPassword());

                if (email.equals("123") && password.equals("123")) {
                    JOptionPane.showMessageDialog(null, "Login Successful!");
                    // Open the DashboardFrame if login is successful
                    new GetStarted(); // This opens the DashboardFrame
                    dispose(); // Close the Login frame
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid email or password!");
                }
            }
        });

        //Create Account clickable label
        createAccountLabel = new JLabel("<html><u>Create an account</u></html>");
        createAccountLabel.setFont(buttonFont);
        createAccountLabel.setForeground(new Color(188, 154, 248)); 
        createAccountLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        createAccountLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
               new CreateAcc();
               dispose();
            }
        });

        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new FlowLayout(FlowLayout.CENTER)); 
        bottomPanel.setBackground(new Color(245, 245, 245)); 
        bottomPanel.add(createAccountLabel);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        loginSystemPanel.add(loginButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        loginSystemPanel.add(bottomPanel, gbc);

        rightPanel.add(loginSystemPanel, BorderLayout.CENTER);

        add(leftPanel, BorderLayout.WEST); 
        add(rightPanel, BorderLayout.CENTER); 

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new LoginMain();
            }
        });
    }
}
