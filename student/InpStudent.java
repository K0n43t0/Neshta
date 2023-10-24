package student;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.border.LineBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import java.awt.Color;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.Point;
import java.awt.Component;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class InpStudent extends JFrame {

 /**
  * 
  */
 private static final long serialVersionUID = 1L;
 private JPanel contentPane;
 private JTextField textName1;
 private JTextField textName2;
 private JTextField textName3;
 private JTextField textEGN;
 private JLabel lblResult;
 private JLabel lblEGN;
 //Метод за промяна на цвета на маркера за коректност 
 private void updateMark() {
  EGN egn=new EGN((textEGN.getText()));
  if (egn.isCorrect()) lblEGN.setBackground(Color.GREEN);
  else lblEGN.setBackground(Color.RED);
 }
 /**
  * Launch the application.
  */
 public static void main(String[] args) {
  EventQueue.invokeLater(new Runnable() {
   public void run() {
    try {
     InpStudent frame = new InpStudent();
     //Центриране на екрана:
     // - център на екрана
     Point center = GraphicsEnvironment.getLocalGraphicsEnvironment().getCenterPoint();
     // -размери на формата
     int width = 500;
     int height = 300;
     frame.setBounds(center.x - width / 2, center.y - height / 2, width, height);
     frame.setVisible(true);
    } catch (Exception e) {
     e.printStackTrace();
    }
   }
  });
 }

 public InpStudent() {
  //Параметри на рамката
   //Заглавие
  setTitle("Student input form");
   //Шрифт
  setFont(new Font("Times New Roman", Font.PLAIN, 20));
   //Стил на затваряне
  setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
   //Нова форма (JPanel) за съдържание
  contentPane = new JPanel();
   //Очертания на формата
  contentPane.setBorder(new LineBorder(Color.BLACK));
   //Съдържанието на рамката е тази форма
  setContentPane(contentPane);
   //Формата запълва рамката
  contentPane.setLayout(null);

  //Графични обекти върху формата
  // Етикети
  JLabel lblNewLabel = new JLabel("Име");
  lblNewLabel.setHorizontalAlignment(SwingConstants.LEFT);
  lblNewLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
  lblNewLabel.setFont(new Font("Times New Roman", Font.PLAIN, 20));
  lblNewLabel.setBounds(89, 10, 36, 24);
  contentPane.add(lblNewLabel);
  
  JLabel lblNewLabel_1 = new JLabel("Презиме");
  lblNewLabel_1.setHorizontalAlignment(SwingConstants.LEFT);
  lblNewLabel_1.setFont(new Font("Times New Roman", Font.PLAIN, 20));
  lblNewLabel_1.setAlignmentX(0.5f);
  lblNewLabel_1.setBounds(290, 10, 74, 24);
  contentPane.add(lblNewLabel_1);
  
  JLabel lblNewLabel_2 = new JLabel("Фамилия");
  lblNewLabel_2.setHorizontalAlignment(SwingConstants.LEFT);
  lblNewLabel_2.setFont(new Font("Times New Roman", Font.PLAIN, 20));
  lblNewLabel_2.setAlignmentX(0.5f);
  lblNewLabel_2.setBounds(89, 87, 96, 24);
  contentPane.add(lblNewLabel_2);
  
  JLabel lblNewLabel_3 = new JLabel("ЕГН");
  lblNewLabel_3.setHorizontalAlignment(SwingConstants.LEFT);
  lblNewLabel_3.setFont(new Font("Times New Roman", Font.PLAIN, 20));
  lblNewLabel_3.setAlignmentX(0.5f);
  lblNewLabel_3.setBounds(290, 87, 74, 24);
  contentPane.add(lblNewLabel_3);
  // Текстови полета
  textName1 = new JTextField();
  textName1.setFont(new Font("Times New Roman", Font.PLAIN, 20));
  textName1.setBounds(89, 44, 110, 24);
  contentPane.add(textName1);
  textName1.setColumns(10);
  
  textName2 = new JTextField();
  textName2.setFont(new Font("Times New Roman", Font.PLAIN, 20));
  textName2.setColumns(10);
  textName2.setBounds(290, 44, 110, 24);
  contentPane.add(textName2);
  
  textName3 = new JTextField();
  textName3.setFont(new Font("Times New Roman", Font.PLAIN, 20));
  textName3.setColumns(10);
  textName3.setBounds(89, 128, 110, 24);
  contentPane.add(textName3);
  
  textEGN = new JTextField();
  //Ако използваме събитието "промяна на текста"
  textEGN.getDocument().addDocumentListener(new DocumentListener() {
   @Override
   public void removeUpdate(DocumentEvent e) {
    updateMark();
   }
   @Override
   public void insertUpdate(DocumentEvent e) {
    updateMark();
   }
   @Override
   public void changedUpdate(DocumentEvent e) {
    updateMark();
   }
  });
  //Ако използваме събитието "натиснат клавиш"
  textEGN.addKeyListener(new KeyAdapter() {
   @Override
   public void keyTyped(KeyEvent e) {
    //Защита от нецифров клавиш
    if (e.getKeyChar()<'0'||e.getKeyChar()>'9')
     e.consume();//Обяви събитието за обслужено 
    // (Има недостатъци)
/*    try {
    EGN egn=new EGN(textEGN.getText()+e.getKeyChar());
    if (egn.isCorrect()) lblEGN.setBackground(Color.GREEN);
    else lblEGN.setBackground(Color.RED);
    }catch(Exception ex) {
     lblEGN.setBackground(Color.RED);
    }
*/
   }
  });
  textEGN.setFont(new Font("Times New Roman", Font.PLAIN, 20));
  textEGN.setColumns(10);
  textEGN.setBounds(290, 121, 110, 24);
  contentPane.add(textEGN);
  // Бутон OK
  JButton btnOK = new JButton("OK");
  btnOK.addActionListener(new ActionListener() {
   public void actionPerformed(ActionEvent e) {
    Student st=new Student(textName1.getText(),textName2.getText(),
                           textName3.getText(),textEGN.getText());
    lblResult.setText(st.toString());
   }
  });
  btnOK.setVerifyInputWhenFocusTarget(false);
  btnOK.setFont(new Font("Times New Roman", Font.PLAIN, 20));
  btnOK.setBounds(199, 175, 85, 21);
  contentPane.add(btnOK);
  //Установяване на бутона като бутон по подразбиране
  // (<Enter> на формата генерира ActionEvent)
  getRootPane().setDefaultButton(btnOK);
  
  // Етикет за изобразяване на коректност
  lblEGN = new JLabel("");
  lblEGN.setBackground(Color.RED);
  lblEGN.setOpaque(true);
  lblEGN.setHorizontalAlignment(SwingConstants.LEFT);
  lblEGN.setFont(new Font("Times New Roman", Font.PLAIN, 20));
  lblEGN.setAlignmentX(0.5f);
  lblEGN.setBounds(263, 121, 27, 24);
  contentPane.add(lblEGN);
  
  // Етикет за краен резултат
  lblResult = new JLabel("");
  lblResult.setHorizontalAlignment(SwingConstants.CENTER);
  lblResult.setFont(new Font("Times New Roman", Font.PLAIN, 20));
  lblResult.setAlignmentX(0.5f);
  lblResult.setBounds(45, 218, 398, 24);
  contentPane.add(lblResult);
 }
}
