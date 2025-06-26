import java.lang.*;
import javax.swing.*;
import java.awt.*; 
import java.awt.event.*;

public class Basepage extends JFrame implements MouseListener,ActionListener {

	//1
	JPanel panel;

	//2
	JLabel USER , NUM , LIST , ADDRESS , imgIcon , imgLabel, CATEGORYTEXT;
	
	//3
	JTextField USERTF , NUMTF , ADDTF;
	
	//4
	JButton NEXTBUTTON, CLOSEBUTTON;
	
	//5
	ButtonGroup bg;
	
	//6
	ImageIcon img;

	Basepage () {
		
	//Frame
	super ("INFORMATION PAGE - ONLINE FOOD ORDERING");
	setSize (1000,700) ; 
	ImageIcon Image = new ImageIcon("icon.jpg");
	setIconImage (Image.getImage());
	getContentPane().setBackground(new Color(255,250,250));
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
	
	//Panel 
	panel = new JPanel();
	panel.setLayout(null);
	panel.setBackground(new Color(255, 250, 250)); 
	add (panel);
	
	//IMAGE AT TOP
	img = new ImageIcon("banner.jpg");
	imgLabel = new JLabel(img);
	imgLabel.setBounds(0, 0,1000,210); 
	panel.add(imgLabel);
	
	//Label
	//USER
	USER = new JLabel("NAME : ");
	USER.setBounds (200,230,80,30); 
	USER.setOpaque(true);
    panel.add (USER);
	
	//USER FIELD
    USERTF = new JTextField();
    USERTF.setBounds(300,230,200,30); 
    panel.add(USERTF);
		
    //NUMBER
	NUM = new JLabel("NUMBER : ");
	NUM.setBounds (200,280,80,30); 
	NUM.setOpaque(true);
    panel.add (NUM);
	
	//NUMBER FIELD
	NUMTF = new JTextField();
	NUMTF.setBounds(300,280,200,30); 
	panel.add(NUMTF);
		
	//ADDRESS
	ADDRESS = new JLabel("ADDRESS : ");
	ADDRESS.setBounds (200,330,80,30); 
	ADDRESS.setOpaque(true);
    panel.add (ADDRESS);
	
	//ADDRESS FIELD
	ADDTF = new JTextField();
    ADDTF.setBounds(300,330,300,30);
	panel.add(ADDTF);
	
	//CATEGORY TEXT
CATEGORYTEXT = new JLabel("★ You can select SNACKS | DESSERTS | DRINKS in the next page ★");
CATEGORYTEXT.setBounds(150, 390, 700, 40); // wider label to fit text
CATEGORYTEXT.setFont(new Font("Serif", Font.BOLD | Font.ITALIC, 18));
CATEGORYTEXT.setForeground(new Color(0, 51, 102)); // Dark navy blue
panel.add(CATEGORYTEXT);
	

	//BUTTON
	NEXTBUTTON = new JButton("NEXT");
	NEXTBUTTON.setBounds(440, 460 , 100,30); 
	NEXTBUTTON.setBackground(new Color(130,130,130));
	NEXTBUTTON.addMouseListener(this);
	NEXTBUTTON.addActionListener(this);
	panel.add(NEXTBUTTON);
		
	CLOSEBUTTON = new JButton("CLOSE");
	CLOSEBUTTON.setBounds(320, 460, 100,30);
	CLOSEBUTTON.setBackground(new Color(130,130,130));
	CLOSEBUTTON.addMouseListener(this);
	CLOSEBUTTON.addActionListener(this);
	panel.add(CLOSEBUTTON);
	      setVisible(true);
	}
	
        public void mouseClicked(MouseEvent me){}
	public void mousePressed(MouseEvent me){}
	public void mouseReleased(MouseEvent me){}
	public void mouseEntered(MouseEvent me){
		if(me.getSource() == NEXTBUTTON){
			   NEXTBUTTON.setBackground(new Color(0,122,255));
			   NEXTBUTTON.setForeground(Color.WHITE);
		}
		else if(me.getSource()== CLOSEBUTTON){
			   CLOSEBUTTON.setBackground(new Color(0,122,255));
			   CLOSEBUTTON.setForeground(Color.WHITE);
	    }
	}
	
	public void mouseExited(MouseEvent me){
		if(me.getSource() == NEXTBUTTON){
			NEXTBUTTON.setBackground(new Color(130,130,130));
			NEXTBUTTON.setForeground(Color.BLACK);
		}else if(me.getSource() == CLOSEBUTTON){
			CLOSEBUTTON.setBackground(new Color(130,130,130));
			CLOSEBUTTON.setForeground(Color.BLACK);
		}
		
	}

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == NEXTBUTTON ){
            String name = USERTF.getText();
            String number = NUMTF.getText();
            String address = ADDTF.getText();

           
          if (!name.isEmpty() && !number.isEmpty() && !address.isEmpty()) {
    // PROPER NAME
    if (!name.matches("[a-zA-Z]+")) {
        JOptionPane.showMessageDialog(null, "Name must contain only letters and spaces.");
        return;
    }

    // PROPER NUMBER (BANGLADESHI NUMBER 11 DIGIT)
    if (!number.matches("\\d{11}")) {
        JOptionPane.showMessageDialog(null, "Number must be exactly 11 digits.");
        return;
    }

    // PROPER ADDRESS (MINIMUM 4 DIGIT)
    if (address.trim().isEmpty() || address.length() < 4) {
        JOptionPane.showMessageDialog(null, "Please enter a valid address.");
        return;
    }

    // IF VALID THEN PROCEED
    new SecondPage(name, number, address).setVisible(true);
    this.setVisible(false);
} 
else {
    JOptionPane.showMessageDialog(this, "Please fill in all fields.");
}


        } else if (e.getSource() == CLOSEBUTTON) {
            JOptionPane.showMessageDialog(this, "CLOSED");
            System.exit(0);
        }
    }
}
