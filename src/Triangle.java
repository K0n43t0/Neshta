public class Triangle {
//������
private double[] side=new double[3];//������
private char[] vertex=new char[3];  //����� �� �������
//����������� �� ������ ��������
public Triangle(double a, double b, double c, char v1, char v2, char v3){
 side[0]=a;
 side[1]=b;
 side[2]=c;
 vertex[0]=v1;
 vertex[1]=v2;
 vertex[2]=v3;
}
//����������� �� ��� ������ (� ������� A, B � C)
public Triangle(double a, double b, double c) {
 side[0]=a;
 side[1]=b;
 side[2]=c;
 for(int i = 0; i < vertex.length; i++) {
  vertex[i] = (char)('A' + i);
 }
}
//������ �����������
public Triangle(){
}
//�������� �� ����������
 public boolean isCorrect(){
  //�������� ������ �� �� ����������� �����
  for (int i=0; i<3; i++) if (side[i]<=0) return false;
  //�������� ������ �� ���������� ������������� �� �����������
  for (int i=0; i<3; i++)
   if (side[i]+side[(i+1)%3]<=side[(i+2)%3]) return false;
  //��������� ������ �� �� �������� ������ �������� �����
  for (int i=0; i<3; i++)
   if (vertex[i]<'A' || vertex[i]>'Z') return false;
  //������� ������ �� �� ��������
  for (int i=0; i<3; i++)
   if (vertex[i]==vertex[(i+1)%3]) return false;
  return true;
 }
//������
 //��� �� ���� �� ������� �����
 public char getVertex(int n){
  if (!isCorrect()) return 0;
  if (n<0 || n>2) return 0;
  return vertex[n];
 }
 //������� �� ������ �� �����
 public double getSide(int n){
  if(!isCorrect()) return 0;
  if (n<0||n>2) return 0;
  return side[n];
 }
 //���������� �� ���� � ������ ���
 private boolean isPresent(char a){
  for(int i=0; i<3; i++)
   if(vertex[i]==a) return true;
  return false;
 }
 //������� �� ������ �� ���, ���������� �� ����� �����
 public double getSide(String a){
  if(!isCorrect()) return 0;
  if(a.length()!=2) return 0;
  if(!isPresent(a.charAt(0))) return 0;
  if(!isPresent(a.charAt(1))) return 0;
  if(a.charAt(0)==a.charAt(1)) return 0;
  if(a.equals(""+vertex[0]+vertex[1])) return side[0];
  if(a.equals(""+vertex[1]+vertex[0])) return side[0];
  if(a.equals(""+vertex[1]+vertex[2])) return side[1];
  if(a.equals(""+vertex[2]+vertex[1])) return side[1];
  return side[2];
 }
//������
 //������� �� ������
 public boolean setSide(int n,double newLength){
  if (n<0 || n>2) return false;
  side[n]=newLength;
  return true;
 }
 //������������ �� ���� �� �����
 public boolean setVertex(int n, char newName){
  if (n<0 || n>2) return false;
  vertex[n]=newName;
  return true;
 }
 //������������ �� ���� �� ������� ���
 public boolean setVertex(char name, char newName){
  for (int i=0;i<3;i++)
   if (vertex[i]==name){
	vertex[i]=newName;
	return true;
   }
  return false;
 }
//���������� � ������
 @Override
 public String toString(){
  if(!isCorrect()) return "Incorrect!";
//�� �� �� ������ Unicode-���������:
//Window>Preferences>General>Workspace:
// Text file encoding: Other> UTF-8
  String s=""+(char)916;
  for(int i=0; i<3; i++){
   s+=vertex[i];
  }
  s+="(";
  for(int i=0; i<3; i++){
   s+=String.format("%.2f",side[i]);
    if(i<2)s+=",";
   }
  return s+")";
 }
 //���������� �� �����������
 public double perimeter(){
  double sum=0;
  if(!isCorrect())return 0;
  for(int i=0;i<3;i++)
   sum+=side[i];
  return sum;
 }
//���� �� ���������� �� �������� ������� 
 public double area(){
 if(!isCorrect())return 0;
 double p=perimeter()/2;
 double s=p;
  for(int i=0; i<3; i++)
   s*=p-side[i];
  return Math.sqrt(s);
 }
//�������� �� ������� ����
 public double height(char v){
  if (!isCorrect()) return 0;
  for (int i=0;i<3;i++)
   if (vertex[i]==v){
	double s=area();
	return 2*s/side[(i+1)%3];
   }
  return 0;
 }
 //������� �� ������� ����
 public double median(char v){
  if (!isCorrect()) return 0;
  for (int i=0;i<3;i++)
   if (vertex[i]==v)
	return 0.5*Math.sqrt(2*side[i]*side[i]+2*side[(i+2)%3]*side[(i+2)%3]-side[(i+1)%3]*side[(i+1)%3]);
  return 0;
 }
 //������������ �� ������� ����
 public double bisector(char v){
  if (!isCorrect()) return 0;
  for (int i=0;i<3;i++)
   if (vertex[i]==v){
	 double p=perimeter()/2;
	 return 2*Math.sqrt(side[i]*side[(i+2)%3]*p*(p-side[(i+1)%3]))/(side[i]+side[(i+2)%3]);
   } 
  return 0;
 }
//������ �� ��������� ���������
 public double inradius(char v){
  if (!isCorrect()) return 0;
  return 2*area()/perimeter();
 }
//������ �� ��������� ���������
 public double circumradius(char v){
  if (!isCorrect()) return 0;
  return 0.25*side[0]*side[1]*side[2]/area();	 
 }
//�������� �� ���� 2 ����� Ox
 public double vertex2Abs(){
  return 0.5*(side[0]*side[0]+side[2]*side[2]-side[1]*side[1])/side[0];
 }
}
