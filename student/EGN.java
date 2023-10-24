package student;

public class EGN {
 //���� �� ������� (long ������ ������ 10-������� ����� � 8 �����)
 private long data;
 //����������� �� long
 public EGN(long n) {
  data=n;
 }
 //����������� �� String
 public EGN(String n) {
  try {
   data=Long.parseLong(n);
  }catch(Exception ex) {
   data=0;
  }
 }
 //�������� �� ����������
 public boolean isCorrect() {
  //�� �� � � ������ �� 10 �����
  if (data>=10000000000L) return false;
  //����� �� ������� �� ������ ������ �� �����
  byte[] d=new byte[10];
  long t=data;//����� �� ������� - �� �� �� ��������!
  //�������� �� ����� ����� � ������ -
  // �� ���-�������� (� ������ 9) ��� ���-�������� (� ������ 0) 
  for (int i=9;i>=0;i--) {
   d[i]=(byte)(t%10);//���������� ����� �� t
   t/=10;//������ ���������� ����� �� t
  }
  int year=10*d[0]+d[1];//���������� ��� ����� �� ��������
  int month=10*d[2]+d[3];//����� (���������� +40 �� �������� ���� 1999 �.)
  int day=10*d[4]+d[5];//���
  if (month>40) {
   month-=40;//�������� �� ������, ��� � ���� 1999 �....
   year+=2000;//...� ����� ������ �� �������
  }
  else year+=1900;//����� ������ �� ������� �� �������� ����� 2000 �.
  //�������� �� �������� ����
  if (day<=0||day>31) return false;
  if (month<=0||month>12) return false;
  //�������� ��� 31-�� ���
  if ((month==2||month==4||month==6||month==9||
       month==11) && day==31) return false;
  //��������
  if (month==2 && day==30) return false;
   // ��������� ������ ��� ��
  if (month==2 && day==29 && year%4!=0 && year!=1900) return false;
  //��������� �� ���-����:
  // ������ �� 0 �� 8, i-���� ����� �� 2^i
  int s=0;
  for (int i=8;i>=0;i--) s=2*s+d[i];
  s*=2;//��� �� ������ ����� ����� �� ���
  //����, ������ �� 0 �� 8, ����� ����� � �������� (i+1) ���� �� ���
  s=(s%11)%10;//��������� �� ��������
  //��� � �����, ��� s ������� � ���������� �����
  return s==d[9];
 }
 //����� � ��������� �� �������������� �����
 public char sex() {
  if (!isCorrect()) return '?';
  int s=(int)(data/10)%2;
  return (s==0)?'M':'F';
 }
 //��������� ���� �� �������� 6 �����
 public String birthDate() {
  if (!isCorrect()) return "Incorrect!";
  int year=(int)(data/100000000);//������� �� �������� ��� �����
  int month=(int)(data/1000000)%100;//�� ���������� ��� �����
  int day=(int)(data/10000)%100;//�� ���������� ��� �����
  //���������� �� ������ � ������ ������
  if (month>40) {
   month-=40;
   year+=2000;
  }
  else year+=1900;
  //���������� ��������: ��.��.����
  return String.format("%02d.%02d.%d", day,month,year);  
 }
 @Override
 public String toString() {
  if (!isCorrect()) return "Incorrect!";
  return String.format("%010d", data);
 }
 
}
