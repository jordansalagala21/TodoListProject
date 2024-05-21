package todo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TodoListGUI {
    private TodoList todoList;
    private JFrame frame;
    private DefaultListModel<Task> taskListModel;
    private JList<Task> taskList;

    public TodoListGUI() {
        todoList = new TodoList();
        frame = new JFrame("Todo List");
        taskListModel = new DefaultListModel<>();
        taskList = new JList<>(taskListModel);

        JButton addButton = new JButton("Add Task");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String description = JOptionPane.showInputDialog(frame, "Enter task description:");
                if (description != null && !description.isEmpty()) {
                    Task task = new Task(description);
                    todoList.addTask(task);
                    taskListModel.addElement(task);
                }
            }
        });

        JButton removeButton = new JButton("Remove Task");
        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = taskList.getSelectedIndex();
                if (selectedIndex != -1) {
                    Task selectedTask = taskListModel.get(selectedIndex);
                    todoList.removeTask(selectedTask);
                    taskListModel.remove(selectedIndex);
                }
            }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(addButton);
        buttonPanel.add(removeButton);

        frame.setLayout(new BorderLayout());
        frame.add(new JScrollPane(taskList), BorderLayout.CENTER);
        frame.add(buttonPanel, BorderLayout.SOUTH);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new TodoListGUI();
            }
        });
    }
}
