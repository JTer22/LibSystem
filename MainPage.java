import java.awt.*;
import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.*;

public class MainPage extends JFrame {

    private JButton getStartedButton;
    private JLabel welcomeLabel;
    private JLabel signOutLabel;

    // Book class to store book details
    class Book {
        String title;
        String imagePath;
        String description;

        Book(String title, String imagePath, String description) {
            this.title = title;
            this.imagePath = imagePath;
            this.description = description;
        }
    }

    public MainPage() {
        setTitle("Library");
        setSize(800, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        Font labelFont = new Font("Arial", Font.PLAIN, 12);

        // LOGO
        ImageIcon logoIcon = new ImageIcon("Books/book.png");
        Image img = logoIcon.getImage();
        Image resizedImg = img.getScaledInstance(150, 150, Image.SCALE_SMOOTH);
        logoIcon = new ImageIcon(resizedImg);

        JLabel logoLabel = new JLabel(logoIcon);
        logoLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Left panel DASHBOARD
        JPanel leftDashboard = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                Color startColor = new Color(50, 20, 100);
                Color endColor = new Color(188, 154, 248);
                GradientPaint gradient = new GradientPaint(0, 0, startColor, getWidth(), getHeight(), endColor);
                g2d.setPaint(gradient);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };

        signOutLabel = new JLabel("<html><u>Sign out</u></html>");
        signOutLabel.setFont(labelFont);
        signOutLabel.setForeground(Color.BLACK);
        signOutLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        signOutLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                new LoginMain();  // Assuming LoginMain is a valid class
                dispose();
            }
        });

        leftDashboard.setPreferredSize(new Dimension(150, 500));
        leftDashboard.setLayout(new BoxLayout(leftDashboard, BoxLayout.Y_AXIS));
        leftDashboard.add(Box.createVerticalStrut(20));
        leftDashboard.add(logoLabel);
        leftDashboard.add(Box.createVerticalStrut(20));
        leftDashboard.add(Box.createVerticalGlue());
        leftDashboard.add(signOutLabel);
        add(leftDashboard, BorderLayout.WEST);

        // Right Panel with top dashboard inside it
        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new BorderLayout());

        // Top Panel with Light Orange Background inside right panel
        JPanel topDashboard = new JPanel();
        topDashboard.setBackground(new Color(255, 229, 153)); // Very light orange
        topDashboard.setLayout(new FlowLayout(FlowLayout.LEFT));

        // Add buttons to the top dashboard panel
        JButton addBooksButton = new JButton("Add Books");
        JButton viewBooksButton = new JButton("View Books");
        JButton borrowButton = new JButton("Borrow");
        JButton listButton = new JButton("List");

        // Customize buttons (optional)
        addBooksButton.setFont(new Font("Helvetica", Font.BOLD, 14));
        viewBooksButton.setFont(new Font("Helvetica", Font.BOLD, 14));
        borrowButton.setFont(new Font("Helvetica", Font.BOLD, 14));

        // ActionListener for Add Books button
        addBooksButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Add addFrame = new Add();  // Create the Add frame
                addFrame.setVisible(true);  // Make it visible
                dispose();  // Close MainPage frame (optional)
            }
        });
        

        // ActionListener for View Books button
        viewBooksButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ViewBooksButton(); // Assuming ViewBooksButton is a valid class
                dispose();
            }
        });

        // ActionListener for Borrow button
        borrowButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new BorrowButton(); // Assuming BorrowButton is a valid class
                dispose();
            }
        });

        // Add buttons to the top dashboard
        topDashboard.add(addBooksButton);
        topDashboard.add(viewBooksButton);
        topDashboard.add(borrowButton);

        // Add the top dashboard to the north of the right panel
        rightPanel.add(topDashboard, BorderLayout.NORTH);

        // Home Panel with books list inside the right panel
        JPanel homePanel = new JPanel();
        homePanel.setLayout(new GridLayout(0, 3, 20, 20));

        // Dummy book data
        List<Book> books = new ArrayList<>();
        books.add(new Book("Harry Potter", "Books/book1.jpg", "Description for Book 1"));
        books.add(new Book("Mein Kampf", "Books/book2.jpg", "Description for Book 2"));
        books.add(new Book("Jose Rizal", "Books/book3.jpg", "Description for Book 3"));
        books.add(new Book("Never Seen", "Books/book4.png", "Description for Book 4"));
        books.add(new Book("One Piece", "Books/book5.jpg", "Description for Book 5"));
        books.add(new Book("BurstFade", "Books/book6.jpg", "Burst fade parin to ya?"));

        // Loop through books and add them to the homePanel
        for (Book book : books) {
            JPanel bookPanel = new JPanel();
            bookPanel.setLayout(new BorderLayout());

            // Book Image
            ImageIcon bookImage = new ImageIcon(book.imagePath);
            Image imgBook = bookImage.getImage().getScaledInstance(100, 150, Image.SCALE_SMOOTH);
            bookImage = new ImageIcon(imgBook);
            JLabel bookImageLabel = new JLabel(bookImage);
            bookPanel.add(bookImageLabel, BorderLayout.CENTER);

            // Book Title and Description
            JPanel textPanel = new JPanel();
            textPanel.setLayout(new BoxLayout(textPanel, BoxLayout.Y_AXIS));

            // Title
            JLabel bookTitle = new JLabel(book.title);
            bookTitle.setFont(new Font("Arial", Font.BOLD, 14));
            bookTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
            textPanel.add(bookTitle);

            // Description
            JLabel bookDesc = new JLabel("<html>" + book.description + "</html>");
            bookDesc.setFont(new Font("Arial", Font.PLAIN, 12));
            bookDesc.setAlignmentX(Component.CENTER_ALIGNMENT);
            textPanel.add(bookDesc);

            bookPanel.add(textPanel, BorderLayout.SOUTH);

            // Add book panel to the homePanel
            homePanel.add(bookPanel);
        }

        // Add the homePanel to the center of the right panel
        rightPanel.add(homePanel, BorderLayout.CENTER);

        // Add rightPanel to the center of the frame (main content area)
        add(rightPanel, BorderLayout.CENTER);

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MainPage();
            }
        });
    }
}
