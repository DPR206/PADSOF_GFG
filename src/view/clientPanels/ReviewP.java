package view.clientPanels;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * The type Review p.
 * @author Duna P.R.
 * @version 1.0
 */
public class ReviewP extends JFrame {
	
		private static final long serialVersionUID = 1L;
		private JComboBox<Integer> comboStars;
	    private JTextArea txtComment;
	    private JButton btnSubmit;
	    private JButton btnCancel;

	    public ReviewP() {
	        setTitle("Add Review");
	        setSize(400, 300);
	        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	        setLocationRelativeTo(null);
	        setLayout(new BorderLayout(10, 10));

	        JPanel mainPanel = new JPanel();
	        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
	        mainPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

	        JPanel starsPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
	        starsPanel.add(new JLabel("Puntuation (Stars): "));
	        
	        Integer[] starsOptions = {1, 2, 3, 4, 5};
	        comboStars = new JComboBox<>(starsOptions);
	        comboStars.setSelectedIndex(4); 
	        starsPanel.add(comboStars);
	        
	        mainPanel.add(starsPanel);
	        mainPanel.add(Box.createVerticalStrut(10)); 

	        JPanel commentPanel = new JPanel(new BorderLayout());
	        commentPanel.add(new JLabel("Your comment:"), BorderLayout.NORTH);
	        
	        txtComment = new JTextArea(6, 30);
	        txtComment.setLineWrap(true);
	        txtComment.setWrapStyleWord(true);
	        JScrollPane scrollPane = new JScrollPane(txtComment);
	        commentPanel.add(scrollPane, BorderLayout.CENTER);
	        
	        mainPanel.add(commentPanel);
	        add(mainPanel, BorderLayout.CENTER);

	        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
	        btnSubmit = new JButton("Add Review");
	        btnCancel = new JButton("Cancel");
	        
	        buttonPanel.add(btnCancel);
	        buttonPanel.add(btnSubmit);
	        add(buttonPanel, BorderLayout.SOUTH);
	    }
	    
	    public int getSelectedStars() {
	        return (Integer) comboStars.getSelectedItem();
	    }

	    public String getComment() {
	        return txtComment.getText().trim();
	    }

	    
	    public void addSubmitListener(ActionListener listener) {
	        btnSubmit.addActionListener(listener);
	    }

	    public void addCancelListener(ActionListener listener) {
	        btnCancel.addActionListener(listener);
	    }

	    public void mostrarMensaje(String mensaje, String titulo, int tipo) {
	        JOptionPane.showMessageDialog(this, mensaje, titulo, tipo);
	    }
	

}
