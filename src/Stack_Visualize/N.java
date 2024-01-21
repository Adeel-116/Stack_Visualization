package Stack_Visualize;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

class GUI {
    
    JTextField t2;
    JButton pushButton;
    JButton popButton;
    JFrame frame;
    JPanel p1, p2;
    int currentIndex = 0; // Variable to store the index of the rectangle to be updated
    int index=0;
    int stacksize =20;
    JLabel a1,a2;
    String value1="";
    String top;
    int topdec=0;

    GUI() {
        frame = new JFrame();
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBounds(100, 30, 1100, 680);
        setIconForFrame(); // ICON Method CALL
        frame.setTitle("Stack visualizer");
        
         p1 = new JPanel(null) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                // Add gradient paint
                GradientPaint gradient = new GradientPaint(0, 0, new Color(9, 186, 217), 0, getHeight(),
                        new Color(16, 55, 131));
                Graphics2D g2d = (Graphics2D) g;
                g2d.setPaint(gradient);
                g2d.fillRect(0, 0, getWidth(), getHeight());

            }
        };
         
 
        JLabel l1 = new JLabel("STACK VISUALIZATION");
        l1.setBounds(300, 50, 650, 50);
        l1.setForeground(Color.DARK_GRAY);
        Font font = null;
        // Font load
        try {
            Font customFont = Font.createFont(Font.TRUETYPE_FONT,
                    new File("C:\\Users\\Muhammad_Adeel\\Downloads\\Open_Sans\\static\\OpenSans_Condensed-Bold.ttf"));
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(customFont);
            font = customFont.deriveFont(Font.PLAIN, 50); // Adjust the style and size as needed
        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
        }

        // Set the font for your JLabel
        l1.setFont(font);
        p1.add(l1);

        t2 = new JTextField();
        t2.setBounds(200, 170, 150, 30);
        p1.add(t2);

        pushButton = new JButton("PUSH");
        pushButton.setBounds(370, 170, 100, 30);
        p1.add(pushButton);
        

        popButton = new JButton("POP");
        popButton.setBounds(480, 170, 100, 30);
        p1.add(popButton);

        // panel2
        p2 = new JPanel(null) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                drawRectanglesOnP2(g);
            }
        };
        
        //Label for push value
       a1 = new JLabel();
       a1.setBounds(890,10 ,50 ,60 );
       
       //TOP VALUE LABEL 
       a2 = new JLabel();
       a2.setBounds(890,30 ,50 ,60 );
       
       p2.setBounds(0, 150, 1100, 400);
       p2.setBackground(Color.WHITE);
       p2.add(a1);
       p2.add(a2);
       

        frame.add(p1);
        p1.add(p2);
        frame.setResizable(false);
        frame.setVisible(true);

        pushButton.addActionListener(e -> {
            // Update the rectangle with the current index when PUSH button is clicked
            String text = t2.getText();
            if (!text.isEmpty()) {
                int value = Integer.parseInt(text);
                updateRectangle(value);
                t2.setText("");
            }
            else{
                JOptionPane.showMessageDialog(frame, "PLease Enter the value ", "Alert", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        popButton.addActionListener(e -> {
            removeLastRectangle();
        });
    }

    public void drawRectanglesOnP2(Graphics g) {
        // Add 20 rectangles to p2 with a black border and custom size
        int rectWidth = 87;
        int rectHeight = 50;
        int rows = 2;
        int columns = 10;
        int xSpacing = 20;
        int ySpacing = 50;
        int xOffsetStart = 20;
        int yOffsetStart = 130;
        int i = 0;
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < columns; col++) {
                int xOffset = xOffsetStart + col * (rectWidth + xSpacing);
                int yOffset = yOffsetStart + row * (rectHeight + ySpacing);
                g.setColor(Color.BLACK);
                g.drawRect(xOffset, yOffset, rectWidth, rectHeight);

                g.setColor(Color.BLUE);
                g.setFont(new Font("Arial", Font.PLAIN, 14));
                String text = Integer.toString(i);
                int textWidth = g.getFontMetrics().stringWidth(text);
                g.drawString(text, xOffset + (rectWidth - textWidth) / 2, yOffset + rectHeight + 15);
                i++;
                
                
                
                //side pr likha howa ho last push and pop value 
                g.setColor(Color.decode("#1e81b0"));
                g.setFont(new Font("Arial", Font.BOLD, 17));
                
                String text3 ="TOP INDEX  :";
                g.drawString(text3, 700, 70);
                
                
            }
        }
    }

    public void updateRectangle(int value) {
        if(index<stacksize){
        Timer timer = new Timer(120, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Graphics g = p2.getGraphics();
                int rectWidth = 87;
                int rectHeight = 50;
                int rows = 2;
                int columns = 10;
                int xSpacing = 20;
                int ySpacing = 50;
                int xOffsetStart = 20;
                int yOffsetStart = 130;

                int xOffset = xOffsetStart + (currentIndex % columns) * (rectWidth + xSpacing);
                int yOffset = yOffsetStart + (currentIndex / columns) * (rectHeight + ySpacing);

                // Generate a random color
                Color randomColor = new Color(
                        (int) (Math.random() * 256),
                        (int) (Math.random() * 256),
                        (int) (Math.random() * 256)
                );

                g.setColor(randomColor);
                g.fillRect(xOffset, yOffset, rectWidth, rectHeight);

                g.setColor(Color.WHITE);
                g.setFont(new Font("Arial", Font.PLAIN, 14));
                String value1 = Integer.toString(value);
                int textWidth = g.getFontMetrics().stringWidth(value1);
                g.drawString(value1, xOffset + (rectWidth - textWidth) / 2, yOffset + rectHeight / 2);
                
                
                 //set label for push value 
                
                currentIndex = (currentIndex + 1) % (rows * columns);
               int a=0;
                if(a==0){
                top =Integer.toString(index);
                a2.setFont(new Font("Arial", Font.BOLD, 18));
                a2.setText(top);
                topdec=index;
                }
                ((Timer) e.getSource()).stop(); // Stop the timer after one execution
                index++;
            }
        });
        timer.start();
        }
        else{
             JOptionPane.showMessageDialog(frame, "Stack is overflow", "Alert", JOptionPane.INFORMATION_MESSAGE);
        }

        
    }

    public void removeLastRectangle() {
        if (index > 0) {
            Timer timer = new Timer(100, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    Graphics g = p2.getGraphics();
                    int rectWidth = 87;
                    int rectHeight = 50;
                    int rows = 2;
                    int columns = 10;
                    int xSpacing = 20;
                    int ySpacing = 50;
                    int xOffsetStart = 20;
                    int yOffsetStart = 130;

                    int lastIndex = (currentIndex - 1 + (rows * columns)) % (rows * columns);
                    int xOffset = xOffsetStart + (lastIndex % columns) * (rectWidth + xSpacing);
                    int yOffset = yOffsetStart + (lastIndex / columns) * (rectHeight + ySpacing);

                    // Clear the properties of the last updated rectangle
                    
                    g.clearRect(xOffset, yOffset, rectWidth, rectHeight);
                    
                    int a=20;
                    topdec--;
                    if(a==20){
                        a2.setFont(new Font("Arial", Font.BOLD, 18));
                        a2.setText(Integer.toString(topdec));
                    }
                    currentIndex = lastIndex;
                    
                    
                    ((Timer) e.getSource()).stop(); // Stop the timer after one execution
                    index--;
                }
            });

            timer.start();
        } else {
            // Show a message if the stack is empty
            JOptionPane.showMessageDialog(frame, "Stack is empty", "Empty Stack", JOptionPane.INFORMATION_MESSAGE);
        }
    }
        
        private void setIconForFrame(){
        try {
            BufferedImage iconImage = ImageIO.read(getClass().getResource("big-data_8087600.png"));
            frame.setIconImage(iconImage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    

    public static void main(String[] args) {
        GUI obj = new GUI();
    }
}
