public class Test {
 public static void main(String[] args) {
  Triangle t=new Triangle(3, 4, 5);
  t.setVertex('A', 'Z');
  System.out.println(t);
  System.out.println(t.perimeter());
  System.out.println(t.area());
  System.out.println(t.getSide("BZ"));
  char v=t.getVertex(0);
  if (v!=0)System.out.println(v);
  else System.out.println("Wrong parameter");
  System.out.printf("%.5f\n",t.height('B'));
 }
}
