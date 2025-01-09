import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class GetStarted extends JFrame {

    private JButton getStartedButton;
    private JLabel welcomeLabel;

    public GetStarted() {
        setTitle("Library");
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Load and resize the logo
        ImageIcon logoIcon = new ImageIcon("Books/book.png");
        Image img = logoIcon.getImage();
        Image resizedImg = img.getScaledInstance(350, 350, Image.SCALE_SMOOTH);
        logoIcon = new ImageIcon(resizedImg);

        // Create a JLabel with the logo
        JLabel logoLabel = new JLabel(logoIcon);
        logoLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Create LeftDashboard with a gradient background and layout
        JPanel LeftDashboard = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                // GRADIENT COLOR
                Graphics2D g2d = (Graphics2D) g;
                Color startColor = new Color(30, 20, 100);
                Color endColor = new Color(200, 154, 248);

                GradientPaint gradient = new GradientPaint(0, 0, startColor, getWidth(), getHeight(), endColor);
                g2d.setPaint(gradient);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };

        // Set the layout to BoxLayout for vertical arrangement
        LeftDashboard.setLayout(new BoxLayout(LeftDashboard, BoxLayout.Y_AXIS));
        LeftDashboard.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Create the welcome label
        welcomeLabel = new JLabel("Welcome to the Library!");
        welcomeLabel.setFont(new Font("Helvetica", Font.BOLD, 27)); 
        welcomeLabel.setForeground(Color.white); 
        welcomeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Create the "Get Started" button
        getStartedButton = new JButton("Get Started");
        getStartedButton.setBackground(new Color(255, 92, 0)); 
        getStartedButton.setFont(new Font("Helvetica", Font.BOLD, 16));
        getStartedButton.setForeground(Color.white);
        getStartedButton.setPreferredSize(new Dimension(200, 40));
        getStartedButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        getStartedButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new MainPage();
                dispose();
            }
        });

        // Add components to LeftDashboard
        LeftDashboard.add(logoLabel);
        LeftDashboard.add(Box.createRigidArea(new Dimension(0, 20)));  // Add space between logo and welcome text
        LeftDashboard.add(welcomeLabel);
        LeftDashboard.add(Box.createRigidArea(new Dimension(0, 20)));  // Add space between welcome text and button
        LeftDashboard.add(getStartedButton);

        // Add the LeftDashboard to the main JFrame
        add(LeftDashboard, BorderLayout.CENTER);

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new GetStarted();
            }
        });
    }
}
