import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class BorrowButton extends JFrame {
    // Shared list of borrowed records
    private static List<Record> borrowedRecords = new ArrayList<>();

    // Constructor
    public BorrowButton() {
        setTitle("Library - Borrow Books");
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Example books (you can replace this with actual book data)
        List<Book> books = new ArrayList<>();
        books.add(new Book("Harry Potter", "Books/book1.jpg", "A magical journey"));
        books.add(new Book("Mein Kampf", "Books/book2.jpg", "A fantasy adventure"));

        // Panel for books
        JPanel booksPanel = new JPanel();
        booksPanel.setLayout(new BoxLayout(booksPanel, BoxLayout.Y_AXIS));

        for (Book book : books) {
            JPanel bookPanel = new JPanel();
            bookPanel.setLayout(new BoxLayout(bookPanel, BoxLayout.X_AXIS));

            // Book Image
            ImageIcon bookImage = new ImageIcon(book.imagePath);
            Image imgBook = bookImage.getImage().getScaledInstance(100, 150, Image.SCALE_SMOOTH);
            bookImage = new ImageIcon(imgBook);
            JLabel bookImageLabel = new JLabel(bookImage);
            bookPanel.add(bookImageLabel);

            // Book Title and Description
            JPanel textPanel = new JPanel();
            textPanel.setLayout(new BoxLayout(textPanel, BoxLayout.Y_AXIS));

            JLabel bookTitle = new JLabel(book.title);
            bookTitle.setFont(new Font("Arial", Font.BOLD, 14));
            bookTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
            bookTitle.setCursor(new Cursor(Cursor.HAND_CURSOR));
            bookTitle.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    new BorrowBookFrame(book);  // Open BorrowBookFrame for this book
                }
            });

            textPanel.add(bookTitle);
            JLabel bookDesc = new JLabel("<html>" + book.description + "</html>");
            bookDesc.setFont(new Font("Arial", Font.PLAIN, 12));
            textPanel.add(bookDesc);

            bookPanel.add(textPanel);
            booksPanel.add(bookPanel);
        }

        // Go Back label
        JLabel goBackLabel = new JLabel("<html><u>Go Back</u></html>");
        goBackLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        goBackLabel.setForeground(Color.BLACK);
        goBackLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        goBackLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                new MainPage();  // Navigate to the main page
                dispose(); // Close this window when "Go Back" is clicked
            }
        });

        JPanel bottomPanel = new JPanel();
        bottomPanel.add(goBackLabel);

        

        // Add booksPanel to the center
        add(booksPanel, BorderLayout.CENTER);

        // Add bottomPanel to the south (bottom of the frame)
        add(bottomPanel, BorderLayout.SOUTH);

        // Make the frame visible
        setVisible(true);
    }

    // Book class to store book details
    class Book {
        String title;
        String description;
        String imagePath;
        boolean isAvailable;  // Availability status
        String dueDate;       // Due date for return (if borrowed)
        String borrowedBy;    // Who borrowed the book
        int borrowDuration;   // Duration of the borrow in days

        Book(String title, String imagePath, String description) {
            this.title = title;
            this.imagePath = imagePath;
            this.description = description;
            this.isAvailable = true;  // By default, the book is available
            this.dueDate = "";  // No due date until borrowed
            this.borrowedBy = ""; // No borrower yet
            this.borrowDuration = 0; // No borrow duration
        }

        void borrowBook(String userName, int borrowDays) {
            this.borrowedBy = userName;
            this.borrowDuration = borrowDays;
            Calendar cal = Calendar.getInstance();
            cal.add(Calendar.DAY_OF_YEAR, borrowDays);
            this.dueDate = cal.getTime().toString();
            this.isAvailable = false;
        }
    }

    // Record class to store borrowing details
    static class Record {
        String name;
        String bookTitle;
        String borrowDate;

        Record(String name, String bookTitle, String borrowDate) {
            this.name = name;
            this.bookTitle = bookTitle;
            this.borrowDate = borrowDate;
        }
    }

    // BorrowBookFrame class for borrowing a book
    class BorrowBookFrame extends JFrame {
        public BorrowBookFrame(Book book) {
            setTitle("Borrow " + book.title);
            setSize(600, 400);
            setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            setLayout(new BorderLayout());

            // Book image
            ImageIcon bookImage = new ImageIcon(book.imagePath);
            Image imgBook = bookImage.getImage().getScaledInstance(150, 200, Image.SCALE_SMOOTH);
            bookImage = new ImageIcon(imgBook);
            JLabel bookImageLabel = new JLabel(bookImage);
            add(bookImageLabel, BorderLayout.NORTH);

            // Availability and Borrow button
            JPanel centerPanel = new JPanel();
            centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));

            JLabel availabilityLabel = new JLabel(book.isAvailable ? "Available" : "Unavailable");
            availabilityLabel.setFont(new Font("Arial", Font.PLAIN, 14));
            centerPanel.add(availabilityLabel);

            if (book.isAvailable) {
                JLabel nameLabel = new JLabel("Enter your name:");
                JTextField nameField = new JTextField();
                nameField.setPreferredSize(new Dimension(200, nameField.getPreferredSize().height));
                centerPanel.add(nameLabel);
                centerPanel.add(nameField);

                JLabel daysLabel = new JLabel("Choose number of days to borrow:");
                SpinnerNumberModel model = new SpinnerNumberModel(1, 1, 30, 1);
                JSpinner daysSpinner = new JSpinner(model);
                daysSpinner.setPreferredSize(new Dimension(60, daysSpinner.getPreferredSize().height));
                centerPanel.add(daysLabel);
                centerPanel.add(daysSpinner);

                JButton borrowButton = new JButton("Borrow");
                borrowButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String userName = nameField.getText();
                        int borrowDays = (int) daysSpinner.getValue();
                        if (userName.isEmpty()) {
                            JOptionPane.showMessageDialog(BorrowBookFrame.this, "Please enter your name.");
                            return;
                        }

                        // Borrow the book and set the due date
                        book.borrowBook(userName, borrowDays);
                        availabilityLabel.setText("Unavailable");

                        // Add the record to the borrowed records list
                        Record record = new Record(userName, book.title, Calendar.getInstance().getTime().toString());
                        borrowedRecords.add(record);

                        JOptionPane.showMessageDialog(BorrowBookFrame.this, "You borrowed " + book.title + ". Due date: " + book.dueDate);
                    }
                });
                centerPanel.add(borrowButton);
            } else {
                JLabel dueDateLabel = new JLabel("Return by: " + book.dueDate);
                centerPanel.add(dueDateLabel);
            }

            // Go Back label
            JLabel goBackLabel = new JLabel("<html><u>Go Back</u></html>");
            goBackLabel.setFont(new Font("Arial", Font.PLAIN, 12));
            goBackLabel.setForeground(Color.BLACK);
            goBackLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
            goBackLabel.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    new BorrowButton();  // Navigate to the main page
                    dispose(); // Close this window when "Go Back" is clicked
                }
            });

            JPanel bottomPanel = new JPanel();
            bottomPanel.add(goBackLabel);
            centerPanel.add(bottomPanel);  // Add the bottomPanel to the centerPanel

            // Add the centerPanel to the frame
            add(centerPanel, BorderLayout.CENTER);

            // Show the frame
            setVisible(true);
        }
    }

    // Main method to run the application
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new BorrowButton();
        });
    }
}
