import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class Add extends JFrame {

    // Components
    private JTextField bookNameTextField;
    private JTextField bookIdTextField;
    private JTextField authorNameTextField;
    private JTextField statusTextField;
    private JButton addRecordButton;
    private JButton deleteRecordButton;
    private JButton deleteAllRecordsButton;
    private JTable bookTable;
    private DefaultTableModel tableModel;
    private JLabel goBackLabel;  // Go Back label

    // Data
    private List<Book> books = new ArrayList<>();

    public Add() {
        // Set up the JFrame
        setTitle("Library Management System");
        setSize(800, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Create the left panel for input fields with gradient background
        JPanel leftPanel = new GradientPanel(); // Use custom gradient panel
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
        leftPanel.setBorder(BorderFactory.createTitledBorder("Book Information"));

        // Labels and text fields for the left panel
        JLabel bookNameLabel = new JLabel("Book Name:");
        bookNameTextField = new JTextField();
        leftPanel.add(bookNameLabel);
        leftPanel.add(bookNameTextField);

        JLabel bookIdLabel = new JLabel("Book ID:");
        bookIdTextField = new JTextField();
        leftPanel.add(bookIdLabel);
        leftPanel.add(bookIdTextField);

        JLabel authorNameLabel = new JLabel("Author Name:");
        authorNameTextField = new JTextField();
        leftPanel.add(authorNameLabel);
        leftPanel.add(authorNameTextField);

        JLabel statusLabel = new JLabel("Status of the Book:");
        statusTextField = new JTextField();
        leftPanel.add(statusLabel);
        leftPanel.add(statusTextField);

        // Add action buttons to the left panel
        addRecordButton = new JButton("Add Record");
        addRecordButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addRecord();
            }
        });
        leftPanel.add(addRecordButton);

        deleteRecordButton = new JButton("Delete Record");
        deleteRecordButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteRecord();
            }
        });
        leftPanel.add(deleteRecordButton);

        deleteAllRecordsButton = new JButton("Delete All Records");
        deleteAllRecordsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteAllRecords();
            }
        });
        leftPanel.add(deleteAllRecordsButton);

        // Create Go Back label
        goBackLabel = new JLabel("<html><u>Go Back</u></html>");
        goBackLabel.setFont(new Font("Helvetica", Font.BOLD, 14));
        goBackLabel.setForeground(Color.BLACK);
        goBackLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        goBackLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                // Go back to the MainPage
                MainPage mainPage = new MainPage();
                mainPage.setVisible(true);
                dispose();  // Close the current Add frame
            }
        });

        // Add the Go Back label directly to the left panel (without extra panel)
        leftPanel.add(Box.createVerticalStrut(20)); // Add spacing before the label
        leftPanel.add(goBackLabel);  // Add "Go Back" label to the panel

        // Create the right panel for the book table
        tableModel = new DefaultTableModel(new String[]{"Book Name", "Book ID", "Author", "Status"}, 0);
        bookTable = new JTable(tableModel);
        JScrollPane tableScrollPane = new JScrollPane(bookTable);
        JPanel rightPanel = new JPanel(new BorderLayout());
        rightPanel.setBorder(BorderFactory.createTitledBorder("Books List"));
        rightPanel.add(tableScrollPane, BorderLayout.CENTER);

        // Split the frame into left (input fields) and right (book table)
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, leftPanel, rightPanel);
        splitPane.setDividerLocation(400); // Set divider position
        splitPane.setResizeWeight(0.5); // Make the split resizable and balanced

        // Add the split pane to the frame
        add(splitPane, BorderLayout.CENTER);
    }

    private void addRecord() {
        String bookName = bookNameTextField.getText();
        String bookId = bookIdTextField.getText();
        String authorName = authorNameTextField.getText();
        String status = statusTextField.getText();

        if (!bookName.isEmpty() && !bookId.isEmpty() && !authorName.isEmpty() && !status.isEmpty()) {
            // Check if bookId is unique
            for (Book book : books) {
                if (book.getBookId().equals(bookId)) {
                    JOptionPane.showMessageDialog(this, "Book ID already exists. Please enter a unique Book ID.");
                    return;
                }
            }

            // Create a new Book object and add it to the list
            Book newBook = new Book(bookName, bookId, authorName, status);
            books.add(newBook);

            // Add the book details to the table
            tableModel.addRow(new Object[]{bookName, bookId, authorName, status});

            // Clear the input fields after adding the record
            clearFields();
        } else {
            JOptionPane.showMessageDialog(this, "Please fill in all fields.");
        }
    }

    private void deleteRecord() {
        int selectedRow = bookTable.getSelectedRow();
        if (selectedRow != -1) {
            String bookId = (String) tableModel.getValueAt(selectedRow, 1);  // Get the Book ID from the selected row

            // Find and remove the book from the list
            books.removeIf(book -> book.getBookId().equals(bookId));

            // Remove the row from the table
            tableModel.removeRow(selectedRow);
        } else {
            JOptionPane.showMessageDialog(this, "Please select a record to delete.");
        }
    }

    private void deleteAllRecords() {
        books.clear();
        tableModel.setRowCount(0);
    }

    private void clearFields() {
        bookNameTextField.setText("");
        bookIdTextField.setText("");
        authorNameTextField.setText("");
        statusTextField.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Add lms = new Add();
            lms.setVisible(true);
        });
    }
}

// Custom JPanel with gradient background for left panel
class GradientPanel extends JPanel {
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
}

class Book {
    private String bookName;
    private String bookId;
    private String authorName;
    private String status;

    public Book(String bookName, String bookId, String authorName, String status) {
        this.bookName = bookName;
        this.bookId = bookId;
        this.authorName = authorName;
        this.status = status;
    }

    public String getBookName() {
        return bookName;
    }

    public String getBookId() {
        return bookId;
    }

    public String getAuthorName() {
        return authorName;
    }

    public String getStatus() {
        return status;
    }
}
