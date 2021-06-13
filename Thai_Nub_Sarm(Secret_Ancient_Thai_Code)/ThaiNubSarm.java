import java.awt.*;
import java.awt.event.*;
import java.nio.charset.StandardCharsets;
import java.util.*;
import javax.swing.*;


class ThaiNubSarm extends JFrame{
    private JTextArea textArea;
    private JPanel panelDecode;
    private JButton decodeButt;
    private JButton clearButt;
    private JButton encodeButt;
    private JTextArea textDecoded;
    public ThaiNubSarm(){
        setSize(500, 600);
        setTitle("Thai Nub Sarm");
        JPanel panelButt = new JPanel();
        panelButt.setBounds(1, 300, 450, 200);
        panelButt.setBackground(Color.LIGHT_GRAY);
        panelButt.setLayout(new GridLayout(1,3));

        JPanel panelText = new JPanel();
        panelText.setBounds(1,1,400,300);
        panelText.setLayout(new BoxLayout(panelText,BoxLayout.LINE_AXIS));//for Dynamic TextArea when resized window
        textArea = new JTextArea(10,42);
        JScrollPane textScroll = new JScrollPane(textArea);//Scollable TextArea
        Font font = new Font("Microsoft Sans Serif",Font.PLAIN,20);
        textArea.setBackground(Color.WHITE);
        textArea.setFont(font);

        panelDecode = new JPanel();
        panelDecode.setBackground(Color.WHITE);
        panelDecode.setBounds(1,1,400,300);
        textDecoded = new JTextArea();
        textDecoded.setFont(font);
        panelDecode.add(textDecoded);

        
        encodeButt = new JButton("Encode");
        encodeButt.addActionListener(new ActionButton());
        decodeButt = new JButton("Decode");
        decodeButt.addActionListener(new ActionButton());
        clearButt = new JButton("Clear");
        clearButt.addActionListener(new ActionButton());
        
        encodeButt.setBounds(1,1,50,50);
        decodeButt.setBounds(1,1,50,50);
        clearButt.setBounds(1,1,50, 50);
        panelButt.add(encodeButt);
        panelButt.add(decodeButt);
        panelButt.add(clearButt);
        panelText.add(textScroll);
        add(panelText,BorderLayout.NORTH);
        add(panelDecode,BorderLayout.CENTER);
        add(panelButt,BorderLayout.SOUTH);

        setLocationRelativeTo(null);
        setDefaultCloseOperation(this.EXIT_ON_CLOSE);
        setVisible(true);
    }
    private class ActionButton implements ActionListener{
        public void actionPerformed(ActionEvent e){
            String input = textArea.getText();
            // encdoding
            byte [] bytes = input.getBytes(StandardCharsets.UTF_8);
            String encode = new String(bytes,StandardCharsets.UTF_8);
            String encodeString = encode.replaceAll("\\s","");//Delete SpaceBar
            String decode1 = "";
            String decode2 = "";
            String decode3 = "";
            char encrypt[] = new char[encodeString.length()+1];
            int i,j,a;
            int p1 = 0;
            int p2 = 0;
            int p3 = 0;
            if(e.getSource() == encodeButt){
                try{   
                    for(i = 0;i < encodeString.length() ;i++){
                        if(i%3 == 0 || i == 0)
                            p1+=1;
                        else if(i%3 == 1 && i!=0)
                            p2+=1;
                        else
                            p3+=1;         
                    }

                    j = 2;
                    for(i = 0;i < p3;i++){
                        encrypt[j] = encodeString.charAt(i);
                        System.out.println(encrypt[j]+" > "+j);
                        j+=3;
                    }
                    System.out.println(">>>>>>>");

                    j = 1;
                    a = p3+p1;
                    for(i = 0 ; i < p2;i++){
                        encrypt[j] = encodeString.charAt(a);
                        System.out.println(encrypt[j]+" > "+j);
                        j+=3;
                        a+=1;
                     }
                     System.out.println(">>>>>>>");

                     j = 0;
                    a = p3;
                    for(i = 0 ;i < p1 ;i++){
                        encrypt[j] = encodeString.charAt(a);
                        System.out.println(encrypt[j]+" > "+j);
                        j+=3;
                        a +=1;
                    }
                    System.out.println(">>>>>>>");
                    System.out.println();
                    

                    for(char c:encrypt)
                        decode1 += c;
                    textDecoded.setText(decode1);
                }
                catch(Exception error){
                    textDecoded.setText("ERROR! CAN'T ENCRYPT YOU TEXT.");
                }
            }
            if(e.getSource() == decodeButt){
                // System.out.println(exceptChar.length);
                try{
                    for(i = 0;i<encodeString.length();i++){
                        if(i%3 == 0 )
                            decode1 += encodeString.charAt(i);
                        else if(i%3 == 1 && i!=0)
                            decode2 += encodeString.charAt(i);
                        else
                            decode3 += encodeString.charAt(i);       
                    }
                    textDecoded.setText(decode3+""+decode1+""+decode2);
                }
                catch(Exception error){
                    textDecoded.setText("ERROR! PLEASE TYPE AGAIN.");
                }
            }
            if(e.getSource() == clearButt){
                textDecoded.setText("");
                textArea.setText("");
                
            }
        }
    }
    public static void main(String[] args) {
        ThaiNubSarm frame = new ThaiNubSarm();
        
    }
}
