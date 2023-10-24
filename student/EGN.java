package student;

public class EGN {
 //Поле за данните (long събира всички 10-цифрени числа в 8 байта)
 private long data;
 //Контсруктор по long
 public EGN(long n) {
  data=n;
 }
 //Контсруктор по String
 public EGN(String n) {
  try {
   data=Long.parseLong(n);
  }catch(Exception ex) {
   data=0;
  }
 }
 //Проверка за коректност
 public boolean isCorrect() {
  //Да не е с повече от 10 цифри
  if (data>=10000000000L) return false;
  //Масив от цифрите за удобен достъп до всяка
  byte[] d=new byte[10];
  long t=data;//Копие на данните - да не ги развалим!
  //Отделяне на всяка цифра в масива -
  // от най-младшата (с индекс 9) към най-старшата (с индекс 0) 
  for (int i=9;i>=0;i--) {
   d[i]=(byte)(t%10);//Последната цифра на t
   t/=10;//Махаме последната цифра на t
  }
  int year=10*d[0]+d[1];//Последните две цифри на годината
  int month=10*d[2]+d[3];//Месец (евентуално +40 за родените след 1999 г.)
  int day=10*d[4]+d[5];//Ден
  if (month>40) {
   month-=40;//Поправка на месеца, ако е след 1999 г....
   year+=2000;//...и пълна година на раждане
  }
  else year+=1900;//Пълна година на раждане на родените преди 2000 г.
  //Проверки за коректна дата
  if (day<=0||day>31) return false;
  if (month<=0||month>12) return false;
  //Месеците без 31-ви ден
  if ((month==2||month==4||month==6||month==9||
       month==11) && day==31) return false;
  //Февруари
  if (month==2 && day==30) return false;
   // Високосна година или не
  if (month==2 && day==29 && year%4!=0 && year!=1900) return false;
  //Създаване на чек-сума:
  // броено от 0 до 8, i-тата цифра по 2^i
  int s=0;
  for (int i=8;i>=0;i--) s=2*s+d[i];
  s*=2;//Още по веднъж всяка цифра по две
  //Така, броено от 0 до 8, всяка цифра е умножена (i+1) пъти по две
  s=(s%11)%10;//Правилото за остатъка
  //ЕГН е верен, ако s съвпада с последната цифра
  return s==d[9];
 }
 //Полът е четността на предпоследната цифра
 public char sex() {
  if (!isCorrect()) return '?';
  int s=(int)(data/10)%2;
  return (s==0)?'M':'F';
 }
 //Рождената дата от старшите 6 цифри
 public String birthDate() {
  if (!isCorrect()) return "Incorrect!";
  int year=(int)(data/100000000);//Числото от старшите две цифри
  int month=(int)(data/1000000)%100;//от следващите две цифри
  int day=(int)(data/10000)%100;//от следващите две цифри
  //Определяне на месеца и цялата година
  if (month>40) {
   month-=40;
   year+=2000;
  }
  else year+=1900;
  //Форматиран резултат: дд.мм.гггг
  return String.format("%02d.%02d.%d", day,month,year);  
 }
 @Override
 public String toString() {
  if (!isCorrect()) return "Incorrect!";
  return String.format("%010d", data);
 }
 
}
