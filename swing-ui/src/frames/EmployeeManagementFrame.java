package frames;

import models.Employee;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Base64;
import java.util.Vector;

class EmployeeManagementFrame extends JFrame {

    private static final String EMPLOYEE_API_URL = "http://localhost:8081/empl";

    public static void showEmployeeFrame(String username, String password, String response) {
        JFrame frame = new JFrame("Employee Management");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);

        // Table Model and Table
        String[] columnNames = {
                "Select", "Full Name", "Employee ID", "Job Title", "Department",
                "Hire Date", "Employment Status", "Contact Information", "Address"
        };
        DefaultTableModel model = new DefaultTableModel(columnNames, 0) {
            @Override
            public Class<?> getColumnClass(int columnIndex) {
                return columnIndex == 0 ? Boolean.class : String.class;
            }
        };
        JTable table = new JTable(model);

        // Load data from API
        loadEmployeeData(model, username, password);

        // Scroll Pane
        JScrollPane scrollPane = new JScrollPane(table);

        // Buttons
        JButton createButton = new JButton("Create");
        JButton updateButton = new JButton("Update");
        JButton deleteButton = new JButton("Delete");

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(createButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(deleteButton);

        // Frame Layout
        frame.setLayout(new BorderLayout());
        frame.add(scrollPane, BorderLayout.CENTER);
        frame.add(buttonPanel, BorderLayout.SOUTH);

        frame.setVisible(true);
    }

    private static void loadEmployeeData(DefaultTableModel model, String username, String password) {
        try {
            URL url = new URL(EMPLOYEE_API_URL);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            String authHeader = "Basic " + Base64.getEncoder().encodeToString((username + ":" + password).getBytes());
            connection.setRequestProperty("Authorization", authHeader);

            int responseCode = connection.getResponseCode();
            if (responseCode == 200) {
                try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                    String response = reader.readLine();

                    // Assuming JSON response
                    String[] employees = response.split("},\\{"); // Simplified parsing for demo purposes
                    for (String employee : employees) {
                        String[] fields = employee.replace("{", "").replace("}", "").split(",");
                        Vector<Object> row = new Vector<>();
                        row.add(false); // Checkbox
                        for (String field : fields) {
                            row.add(field.split(":")[1].replace("\"", ""));
                        }
                        model.addRow(row);
                    }
                }
            } else {
                JOptionPane.showMessageDialog(null, "Failed to load employees. Status: " + responseCode);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error loading employees: " + e.getMessage());
        }
    }
}
