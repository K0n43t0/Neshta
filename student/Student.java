package student;

public class Student {
 private String name1,name2,name3;
 private EGN egn;
 public Student(String name1, String name2, String name3, String egn) {
  super();
  this.name1 = name1;
  this.name2 = name2;
  this.name3 = name3;
  egn=egn.toString();
 }
 public String getName1() {
  return name1;
 }
 public String getName2() {
  return name2;
 }
 public String getName3() {
  return name3;
 }
 public EGN getEgn() {
  return egn;
 }
 @Override
 public String toString() {
  return String.format("%s %s %s, ЕГН: %s",name1,name2,name3,egn);
 }
  
}
