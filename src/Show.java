//Автоматично добавени библиотеки
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
//Библиотека за работа с графика
import org.eclipse.swt.graphics.*; 

public class Show {
//Автоматично създадено свойство shell
 protected Shell shell;
//Свойства
 private Display display;
 private GC gc; //Графичен контекст
//Текстови полета за големините на страните
 private Text side1;
 private Text side2;
 private Text side3;
//Метод за извеждане на прозорец със съобщение за грешка
 private void errMessage(){
  MessageBox mb = new MessageBox(shell, SWT.OK | SWT.ICON_WARNING);
  mb.setMessage("Некоректни данни");
  mb.setText( "ГРЕШКА" );
  mb.open();
 }
/**
 * Launch the application.
 * @param args
*/
 public static void main(String[] args) {
  try {
   Show window = new Show();
   window.open();
  }catch (Exception e) {
    e.printStackTrace();
  }
 }
/**
 * Open the window.
*/
 public void open() {
  display = Display.getDefault();
  createContents();
  shell.open();
  shell.layout();
  while (!shell.isDisposed()) 
   if (!display.readAndDispatch()) display.sleep();
 }
/**
 * Create contents of the window.
*/
 protected void createContents() {
//Графични елементи
//Автоматично създаден обект от тип  Shell
  shell = new Shell();
  shell.setSize(450, 281);
  shell.setText("Триъгълник");
//Етикети		
  Label label = new Label(shell, SWT.NONE);
  label.setBounds(10, 68, 110, 20);
  label.setText("Първа страна");
  Label label_1 = new Label(shell, SWT.NONE);
  label_1.setText("Втора страна");
  label_1.setBounds(10, 107, 110, 20);
  Label label_2 = new Label(shell, SWT.NONE);
  label_2.setText("Трета страна");
  label_2.setBounds(10, 145, 110, 20);
//Текстови полета		
  side1 = new Text(shell, SWT.BORDER);
  side1.setBounds(143, 65, 78, 26);
  side2 = new Text(shell, SWT.BORDER);
  side2.setBounds(143, 104, 78, 26);
  side3 = new Text(shell, SWT.BORDER);
  side3.setBounds(143, 142, 78, 26);
//Канава	
final Canvas canvas = new Canvas(shell, SWT.NONE);
  canvas.setBounds(237, 10, 195, 214);
//Създаване на графичен контекст, свързан с канавата canvas
  gc=new GC(canvas);
//Бутон OK
  Button btnOk = new Button(shell, SWT.NONE);
  btnOk.setBounds(68, 194, 90, 30);
  btnOk.setText("OK");
  btnOk.addSelectionListener(new SelectionAdapter() {
   @Override
//Събитие: натиснат бутон OK
   public void widgetSelected(SelectionEvent e) {
//Създаване на триъгълник по въведените данни
    double a,b,c;//Страни на триъгълника
    //Входни данни от потребителя
    try{
     a=Double.parseDouble(side1.getText());
     b=Double.parseDouble(side2.getText());
     c=Double.parseDouble(side3.getText());
    }catch(Exception ex){
    //Ако възникне грешка при преобразуване от низ в число
	  errMessage();
	  return;
    }
    //Триъгълник по трите страни
    Triangle tri=new Triangle(a,b,c);
    //Ако не е коректен - грешка
    if (!tri.isCorrect()){
     errMessage();
     return;
    }
    //Изобразяване на триъгълника
    final int GAP=5;//Малко разстояние от долния край на канавата
    //Максимални стойности на координатите
    int maxX=canvas.getClientArea().width;
	int maxY=canvas.getClientArea().height-GAP;
    //Координати на върховете на триъгълника
    double Ax=0,Ay=0,Bx=tri.getSide(0),By=0,
           Cx=tri.vertex2Abs(),Cy=tri.height('C');
    //Ако Cx е отрицателно - преместване надясно
    if (Cx<0){Ax=-Cx;Bx-=Cx;Cx=0;}
    //максимална координата по x
    double w=Cx>Bx?Cx:Bx;
    double h=Cy;//Максимална координата по y
    //Мащабиращи множители по x и по y
    double m1=maxX/w,m2=maxY/h;
    //Избираме по-малкия от тях
    double m=m1<m2?m1:m2;
    //Мащабиране на координатите
    Ax*=m;Ay*=m;
    Bx*=m;By*=m;
    Cx*=m;Cy*=m;
    //Масив от целочислени координати за чертане с drawPologon
    int[]cs=new int[3*2];//3 точки по 2 координати
    cs[0]=(int)(Ax);cs[1]=maxY-(int)(Ay);//(0,0) е в горния ляв ъгъл
    cs[2]=(int)(Bx);cs[3]=maxY-(int)(By);
    cs[4]=(int)(Cx);cs[5]=maxY-(int)(Cy);
    //Установяване на бял фонов цвят
    gc.setBackground(display.getSystemColor(SWT.COLOR_WHITE));
    //Изтриване на досегашното съдържание
    gc.fillRectangle(new Rectangle(0,0,maxX,maxY+GAP));
    //Установяване на черен цвят за чертане
    gc.setForeground(display.getSystemColor(SWT.COLOR_BLACK));
    //Чертане
    gc.drawPolygon(cs);
   }
  });
 }
}
