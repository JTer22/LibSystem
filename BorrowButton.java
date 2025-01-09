import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class BorrowButton extends JFrame {

    // Book class to store book details
    class Book {
        String title;
        String description;
        String imagePath;

        Book(String title, String imagePath, String description) {
            this.title = title;
            this.imagePath = imagePath;
            this.description = description;
        }
    }

    public BorrowButton() {
        setTitle("Library - Borrow Books");
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // LOGO
        ImageIcon logoIcon = new ImageIcon("Books/book.png");  // Import your logo picture
        Image img = logoIcon.getImage(); 
        Image resizedImg = img.getScaledInstance(200, 200, Image.SCALE_SMOOTH); 
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
        leftPanel.setPreferredSize(new Dimension(150, 5)); 
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));  
        leftPanel.add(Box.createVerticalStrut(50));
        leftPanel.add(logoLabel); 
        leftPanel.add(Box.createVerticalStrut(50));

        add(leftPanel, BorderLayout.WEST);

        // Right panel with borrowable books
        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new BorderLayout());

        // List of books available for borrowing
        List<Book> books = new ArrayList<>();
        books.add(new Book("Harry Potter", "Books/book1.jpg", "A magical journey"));
        books.add(new Book("Mein kampf", "Books/book2.jpg", "A fantasy adventure"));
        books.add(new Book("Jose Rizal", "Books/book3.jpg", "Dystopian novel"));

        // Panel to hold the book list
        JPanel booksPanel = new JPanel();
        booksPanel.setLayout(new BoxLayout(booksPanel, BoxLayout.Y_AXIS)); // Vertical list

        // Add books to the panel
        for (Book book : books) {
            JPanel bookPanel = new JPanel();
            bookPanel.setLayout(new BoxLayout(bookPanel, BoxLayout.X_AXIS));  // Horizontal layout for image and text

            // Book Image
            ImageIcon bookImage = new ImageIcon(book.imagePath);  // Use actual image paths
            Image imgBook = bookImage.getImage().getScaledInstance(100, 150, Image.SCALE_SMOOTH); // Resize image
            bookImage = new ImageIcon(imgBook);
            JLabel bookImageLabel = new JLabel(bookImage);
            bookPanel.add(bookImageLabel);

            // Create a panel for book title and description
            JPanel textPanel = new JPanel();
            textPanel.setLayout(new BoxLayout(textPanel, BoxLayout.Y_AXIS)); // BoxLayout for vertical stacking

            // Title (clickable)
            JLabel bookTitle = new JLabel(book.title);
            bookTitle.setFont(new Font("Arial", Font.BOLD, 14));
            bookTitle.setAlignmentX(Component.CENTER_ALIGNMENT); // Center the title
            bookTitle.setCursor(new Cursor(Cursor.HAND_CURSOR)); // Change cursor to hand on hover
            bookTitle.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    JOptionPane.showMessageDialog(null, "You clicked on: " + book.title);
                }
            });
            textPanel.add(bookTitle);

            // Description
            JLabel bookDesc = new JLabel("<html>" + book.description + "</html>");
            bookDesc.setFont(new Font("Arial", Font.PLAIN, 12));
            bookDesc.setAlignmentX(Component.CENTER_ALIGNMENT); // Center the description
            textPanel.add(bookDesc);

            bookPanel.add(textPanel); // Add the text panel (title + description) to the book panel

            // Borrow Button for each book
           
            bookPanel.add(Box.createHorizontalGlue());
            

            booksPanel.add(bookPanel);
            booksPanel.add(Box.createVerticalStrut(10)); // Space between books
        }

        // Add books panel to the center of the right panel
        rightPanel.add(booksPanel, BorderLayout.CENTER);

        // Go Back link at the bottom
        JLabel goBackLabel = new JLabel("<html><u>Go Back</u></html>");
        goBackLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        goBackLabel.setForeground(Color.BLACK);
        goBackLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        goBackLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                new MainPage();  // Open MainPage window
                dispose();  // Close current window (BorrowButton)
            }
        });

        JPanel bottomPanel = new JPanel();
        bottomPanel.add(goBackLabel);

        rightPanel.add(bottomPanel, BorderLayout.SOUTH);

        // Add rightPanel to the center of the frame (main content area)
        add(rightPanel, BorderLayout.CENTER);

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new BorrowButton();
            }
        });
    }
}
