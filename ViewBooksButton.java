import java.awt.*;
import javax.swing.*;
import java.util.List;
import java.util.ArrayList;
import java.awt.event.*;

public class ViewBooksButton extends JFrame {

    // Book class to store book details
    class Book {
        String title;
        String description;
        String availability;  // Availability of the book
        String imagePath;  // Path to the image for each book

        Book(String title, String description, String availability, String imagePath) {
            this.title = title;
            this.description = description;
            this.availability = availability;
            this.imagePath = imagePath;
        }
    }

    public ViewBooksButton() {
        setTitle("Library");
        setSize(800, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());  // Main layout for the JFrame

        // Left Panel (DASHBOARD)
        JPanel leftPanel = new JPanel() {
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
        leftPanel.setPreferredSize(new Dimension(150, 500));
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));

        // LOGO
        ImageIcon logoIcon = new ImageIcon("Books/book.png");
        Image img = logoIcon.getImage();
        Image resizedImg = img.getScaledInstance(150, 150, Image.SCALE_SMOOTH);
        logoIcon = new ImageIcon(resizedImg);
        JLabel logoLabel = new JLabel(logoIcon);
        logoLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Add logo to leftPanel
        leftPanel.add(Box.createVerticalStrut(50));  // Space before logo
        leftPanel.add(logoLabel);
        leftPanel.add(Box.createVerticalGlue());  // Push sign-out downwards

        // Right Panel (BOOK LIST)
        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new BorderLayout());  // Layout for right panel

        // Book data with image paths
        List<Book> books = new ArrayList<>();
        books.add(new Book("Harry Potter", "A young wizard's journey.", "Available", "Books/book1.jpg"));
        books.add(new Book("Mein Kampf", "Autobiography of Adolf Hitler.", "Not Available", "Books/book2.jpg"));
        books.add(new Book("Jose Rizal", "A national hero's life.", "Available", "Books/book3.jpg"));
        books.add(new Book("Never Seen", "A mysterious adventure.", "Available", "Books/book4.png"));
        books.add(new Book("One Piece", "The journey for the One Piece treasure.", "Not Available", "Books/book5.jpg"));
        books.add(new Book("BurstFade", "An action-packed story.", "Available", "Books/book6.jpg"));

        // Panel for labels above the list
        JPanel headerPanel = new JPanel();
        headerPanel.setLayout(new GridLayout(1, 4));  // Grid layout with 4 columns: Image, Title, Description, Availability
        JLabel imageLabel = new JLabel("");
        imageLabel.setFont(new Font("Arial", Font.BOLD, 14));
        JLabel titleLabel = new JLabel("Title");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 14));
        JLabel descriptionLabel = new JLabel("Description");
        descriptionLabel.setFont(new Font("Arial", Font.BOLD, 14));
        JLabel availabilityLabel = new JLabel("Availability");
        availabilityLabel.setFont(new Font("Arial", Font.BOLD, 14));

        headerPanel.add(imageLabel);
        headerPanel.add(titleLabel);
        headerPanel.add(descriptionLabel);
        headerPanel.add(availabilityLabel);
        rightPanel.add(headerPanel, BorderLayout.NORTH);

        // Panel to display book list
        JPanel bookListPanel = new JPanel();
        bookListPanel.setLayout(new GridLayout(books.size(), 4));  // 4 columns for Image, Title, Description, Availability

        // Loop through books and create rows for each book
        for (Book book : books) {
            // Image label for each book
            ImageIcon bookImageIcon = new ImageIcon(book.imagePath);
            Image bookImage = bookImageIcon.getImage();
            Image resizedBookImage = bookImage.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
            bookImageIcon = new ImageIcon(resizedBookImage);
            JLabel bookImageLabel = new JLabel(bookImageIcon);

            // Title label
            JLabel bookTitle = new JLabel(book.title);
            bookTitle.setFont(new Font("Arial", Font.PLAIN, 12));

            // Description label
            JLabel bookDescription = new JLabel(book.description);
            bookDescription.setFont(new Font("Arial", Font.PLAIN, 12));

            // Availability label
            JLabel bookAvailability = new JLabel(book.availability);
            bookAvailability.setFont(new Font("Arial", Font.PLAIN, 12));
            if (book.availability.equals("Available")) {
                bookAvailability.setForeground(Color.GREEN);  // Set color to green for "Available"
            } else if (book.availability.equals("Not Available")) {
                bookAvailability.setForeground(Color.RED);  // Set color to red for "Not Available"
            }

            // Add the image and labels to the bookListPanel
            bookListPanel.add(bookImageLabel);
            bookListPanel.add(bookTitle);
            bookListPanel.add(bookDescription);
            bookListPanel.add(bookAvailability);
        }

        // Add book list panel to the center of the right panel
        JScrollPane scrollPane = new JScrollPane(bookListPanel);  // Make the list scrollable
        rightPanel.add(scrollPane, BorderLayout.CENTER);

        // "Go Back" Button
        JButton goBackButton = new JButton("Go Back");
        goBackButton.setFont(new Font("Arial", Font.PLAIN, 14));

        // Add ActionListener for the Go Back button
        goBackButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new MainPage();
                dispose(); // Close the current frame (for now, you can add the action you want for the back button)
            }
        });

        // Add the Go Back button to the bottom of the right panel
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new BorderLayout());
        bottomPanel.add(goBackButton, BorderLayout.CENTER);
        rightPanel.add(bottomPanel, BorderLayout.SOUTH);

        // Add the panels to the frame
        add(leftPanel, BorderLayout.WEST);
        add(rightPanel, BorderLayout.CENTER);

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ViewBooksButton();
            }
        });
    }
}
