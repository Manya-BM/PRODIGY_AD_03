import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StopwatchApp extends JFrame {
    private JLabel timeDisplay;
    private JButton startButton, pauseButton, resetButton;
    private Timer timer;
    private int elapsedTime = 0; // Time in milliseconds
    private boolean isRunning = false;

    public StopwatchApp() {
        // Set up the JFrame
        setTitle("Stopwatch");
        setSize(500, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Initialize the time display label
        timeDisplay = new JLabel("00:00:000", SwingConstants.CENTER);
        timeDisplay.setFont(new Font("Arial", Font.BOLD, 24));

        // Initialize the buttons
        startButton = new JButton("Start");
        pauseButton = new JButton("Pause");
        resetButton = new JButton("Reset");

        // Add action listeners for buttons
        startButton.addActionListener(new StartAction());
        pauseButton.addActionListener(new PauseAction());
        resetButton.addActionListener(new ResetAction());

        // Layout the buttons
        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.add(startButton);
        buttonPanel.add(pauseButton);
        buttonPanel.add(resetButton);

        // Add components to the JFrame
        setLayout(new BorderLayout());
        add(timeDisplay, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        // Initialize the timer
        timer = new Timer(1, new TimerAction()); // 1 ms interval

        // Make the frame visible
        setVisible(true);
    }

    // Timer ActionListener: Updates the time display
    private class TimerAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            elapsedTime += 1; // Increment by 1 ms
            int minutes = (elapsedTime / 60000) % 60;
            int seconds = (elapsedTime / 1000) % 60;
            int milliseconds = elapsedTime % 1000;
            timeDisplay.setText(String.format("%02d:%02d:%03d", minutes, seconds, milliseconds));
        }
    }

    // Start button action
    private class StartAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (!isRunning) {
                timer.start();
                isRunning = true;
            }
        }
    }

    // Pause button action
    private class PauseAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (isRunning) {
                timer.stop();
                isRunning = false;
            }
        }
    }

    // Reset button action
    private class ResetAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            timer.stop();
            elapsedTime = 0;
            isRunning = false;
            timeDisplay.setText("00:00:000");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(StopwatchApp::new);
    }
}
