//����������� �������� ����������
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
//���������� �� ������ � �������
import org.eclipse.swt.graphics.*; 

public class Show {
//����������� ��������� �������� shell
 protected Shell shell;
//��������
 private Display display;
 private GC gc; //�������� ��������
//�������� ������ �� ���������� �� ��������
 private Text side1;
 private Text side2;
 private Text side3;
//����� �� ��������� �� �������� ��� ��������� �� ������
 private void errMessage(){
  MessageBox mb = new MessageBox(shell, SWT.OK | SWT.ICON_WARNING);
  mb.setMessage("���������� �����");
  mb.setText( "������" );
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
//�������� ��������
//����������� �������� ����� �� ���  Shell
  shell = new Shell();
  shell.setSize(450, 281);
  shell.setText("����������");
//�������		
  Label label = new Label(shell, SWT.NONE);
  label.setBounds(10, 68, 110, 20);
  label.setText("����� ������");
  Label label_1 = new Label(shell, SWT.NONE);
  label_1.setText("����� ������");
  label_1.setBounds(10, 107, 110, 20);
  Label label_2 = new Label(shell, SWT.NONE);
  label_2.setText("����� ������");
  label_2.setBounds(10, 145, 110, 20);
//�������� ������		
  side1 = new Text(shell, SWT.BORDER);
  side1.setBounds(143, 65, 78, 26);
  side2 = new Text(shell, SWT.BORDER);
  side2.setBounds(143, 104, 78, 26);
  side3 = new Text(shell, SWT.BORDER);
  side3.setBounds(143, 142, 78, 26);
//������	
final Canvas canvas = new Canvas(shell, SWT.NONE);
  canvas.setBounds(237, 10, 195, 214);
//��������� �� �������� ��������, ������� � �������� canvas
  gc=new GC(canvas);
//����� OK
  Button btnOk = new Button(shell, SWT.NONE);
  btnOk.setBounds(68, 194, 90, 30);
  btnOk.setText("OK");
  btnOk.addSelectionListener(new SelectionAdapter() {
   @Override
//�������: �������� ����� OK
   public void widgetSelected(SelectionEvent e) {
//��������� �� ���������� �� ���������� �����
    double a,b,c;//������ �� �����������
    //������ ����� �� �����������
    try{
     a=Double.parseDouble(side1.getText());
     b=Double.parseDouble(side2.getText());
     c=Double.parseDouble(side3.getText());
    }catch(Exception ex){
    //��� �������� ������ ��� ������������� �� ��� � �����
	  errMessage();
	  return;
    }
    //���������� �� ����� ������
    Triangle tri=new Triangle(a,b,c);
    //��� �� � �������� - ������
    if (!tri.isCorrect()){
     errMessage();
     return;
    }
    //������������ �� �����������
    final int GAP=5;//����� ���������� �� ������ ���� �� ��������
    //���������� ��������� �� ������������
    int maxX=canvas.getClientArea().width;
	int maxY=canvas.getClientArea().height-GAP;
    //���������� �� ��������� �� �����������
    double Ax=0,Ay=0,Bx=tri.getSide(0),By=0,
           Cx=tri.vertex2Abs(),Cy=tri.height('C');
    //��� Cx � ����������� - ����������� �������
    if (Cx<0){Ax=-Cx;Bx-=Cx;Cx=0;}
    //���������� ���������� �� x
    double w=Cx>Bx?Cx:Bx;
    double h=Cy;//���������� ���������� �� y
    //���������� ��������� �� x � �� y
    double m1=maxX/w,m2=maxY/h;
    //�������� ��-������ �� ���
    double m=m1<m2?m1:m2;
    //���������� �� ������������
    Ax*=m;Ay*=m;
    Bx*=m;By*=m;
    Cx*=m;Cy*=m;
    //����� �� ����������� ���������� �� ������� � drawPologon
    int[]cs=new int[3*2];//3 ����� �� 2 ����������
    cs[0]=(int)(Ax);cs[1]=maxY-(int)(Ay);//(0,0) � � ������ ��� ����
    cs[2]=(int)(Bx);cs[3]=maxY-(int)(By);
    cs[4]=(int)(Cx);cs[5]=maxY-(int)(Cy);
    //������������ �� ��� ����� ����
    gc.setBackground(display.getSystemColor(SWT.COLOR_WHITE));
    //��������� �� ����������� ����������
    gc.fillRectangle(new Rectangle(0,0,maxX,maxY+GAP));
    //������������ �� ����� ���� �� �������
    gc.setForeground(display.getSystemColor(SWT.COLOR_BLACK));
    //�������
    gc.drawPolygon(cs);
   }
  });
 }
}
