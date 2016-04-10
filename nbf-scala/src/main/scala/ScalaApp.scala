/**
 * Created by Administrator on 2016/4/10 0010.
 */
object ScalaApp extends App{

  println("hello scala app!");

  val e = new Employee("123123123","kkk",2.0);
  println(e);

  println(e.isInstanceOf[Employee]);
  println(e.isInstanceOf[Person]);

}
