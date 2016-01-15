import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import parts_of_speech.*;

public class PrintToScreen extends JFrame {
	
	// ArrayList<String> sentence;
	// ArrayList<JLabel> word_labels;
	JPanel sentence_panel;
	JLabel highlighted_word = null;
	JTextField text_field;
	JButton text_field_button;
	
	final Font regular_font = new Font("Sans", Font.PLAIN, 20);
	final Font highlight_font = new Font("Sans", Font.ITALIC, 20);
	
	public PrintToScreen(final POS[]words) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//String[] words = new String[]{"Hello", ",", " ", "world", "!", " ", "How", " ", "are", " ", "you", "?"};
		
		setSize(400, 400);
		
		sentence_panel = new JPanel();
		sentence_panel.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
		add(sentence_panel);
		int i=0;
		for (POS word : words) {
			// sentence.add(word);\
			System.out.println("i");
			System.out.println(word.pos());
			AddWords.print(words);
			final JLabel label;
			if(word.isPOS("adjective")){
				label = new JLabel("the "+word.word+" ");
				//((Adjective)(word)).subject.hasThe=true;
			}
			else if(word.isPOS("noun") && !word.isPOS("name")){
				
				if(i!=0 && words[i-1].isPOS("adjective")){
					label = new JLabel(word.word+" ");
				}
				else{
					label = new JLabel("the "+word.word+" ");
				}
			}
			else if(word.isPOS("verb")){
				label = new JLabel(word.word+"s ");
			}
			else{
				label = new JLabel(word.word+" ");
			}
			
			label.setFont(regular_font);
			sentence_panel.add(label);
			
			label.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {
					setHighlightedLabel(label);
				}
			});
			i++;
			// word_labels.add(label);
			word.label = label;
		}
		
		JPanel text_panel = new JPanel();
		JLabel j=new JLabel("Part of speech:");
		text_panel.add(j);
		text_panel.remove(j);
		text_panel.add(new JLabel("POS : "));
		text_field = new JTextField();
		text_field.setPreferredSize(new Dimension(200, text_field.getPreferredSize().height));
		text_field.setEnabled(false);
		
		ActionListener listener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				System.out.println("You entered: " + text_field.getText() + ", currently highlighted word is: " + highlighted_word.getText());
			
				POS highlighted_pos = null;
				for (POS word : words) {
					if (word.label == highlighted_word) {
						highlighted_pos = word;
						break;
					}
				}
				
				if(check(highlighted_pos.word, text_field.getText(),words)==1){
					JOptionPane.showMessageDialog(PrintToScreen.this,
							"You got it right!",
							"Correct",
							JOptionPane.PLAIN_MESSAGE);
					//Checker.askQ();
				}
				else if(check(highlighted_pos.word, text_field.getText(),words)==0){

					JOptionPane.showMessageDialog(PrintToScreen.this,
						    "There is a more specific answer.",
						    "Correct",
						    JOptionPane.PLAIN_MESSAGE);
				}
				else{
				JOptionPane.showMessageDialog(PrintToScreen.this,
					    "You got it wrong.",
					    "Incorrect",
					    JOptionPane.ERROR_MESSAGE);
				}
			}
		};
		
		text_field.addActionListener(listener);
		text_panel.add(text_field);
		
		text_field_button = new JButton("OK");
		text_field_button.addActionListener(listener);
		text_field_button.setEnabled(false);
		text_panel.add(text_field_button);
		
		JRootPane rootPane = this.getRootPane(); 
		rootPane.setDefaultButton(text_field_button);
		
		add(text_panel, BorderLayout.SOUTH);
	}
	
	public static int check(String word, String POSguess, POS[]pos){
		int index=-1;
		for(int i=0;i<pos.length;i++){
			if(word.equals(pos[i].word)){
				index=i;
			}
		}
		if(POSguess.toLowerCase().equals(pos[index].function.toLowerCase())){
			return 1;
		}
		else if(pos[index].isPOS(POSguess.toLowerCase())){
			System.out.println("the correct answer is "+pos[index].function);
			return 0;
		}
		else{
			System.out.println("the correct answer is "+pos[index].function);
			return -1;
		}
	}
	
	public void setHighlightedLabel(JLabel label) {
		if (highlighted_word != null) {
			highlighted_word.setForeground(Color.BLACK);
			highlighted_word.setFont(regular_font);
		}
		
		if (label != highlighted_word) {
			label.setFont(highlight_font);
			label.setForeground(Color.RED);
			highlighted_word = label;
		} else {
			highlighted_word = null;
		}
		
		text_field.setEnabled(highlighted_word != null);
		text_field_button.setEnabled(highlighted_word != null);
		
		text_field.setText("");
	}
	public void run(PrintToScreen pts){
		pts.setVisible(true);
	}
}
