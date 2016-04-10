import java.net.URL

import com.scala.base.{User, Employee}

import scala.collection.mutable.ArrayBuffer
import scala.io.Source

/**
 * Created by Administrator on 2016/3/6 0006.
 */
object BaseScala {

  def testFor(): Unit ={
    for(i <- 1 to 10){
      print(i + "\t")
    }
    println;
  }

  def testYield(): Unit ={
    for(i <- 1 to 10) yield print((i%2)+"\t")

  }

  def testArray(): Unit ={
    val array1 = Array("aaa","bbb","ccc");
    print(array1(0));
    val array2 = for(item <- array1) yield item + "yyy\t";
    array2.foreach(print);
    val array3 = array2.filter(item => item.contains("yyy")).map(_.toUpperCase);
    println;
    array3.foreach(print);
  }

  def abs(x:Int)={
    if(x<0){
      -x;
    }else{
      x;
    }
  }

  def box(str:String): Unit ={
    var border = "-"*str.length+"--\n";
    println(border+"|"+str+"|\n"+border);
  }

  def lookweb(url:String): Unit ={
    var in = new URL(url).openStream();
    try {
      println(in);
    }finally {
      in.close();
    }
  }

  def arry(): Unit ={
    val b = ArrayBuffer[Int]();
    b += (1,2,3,4,5,6,7,8,9);
    b.foreach(print);
    println;
    val newarry = for(e <- b) yield e*10;
    newarry.foreach(print);
    println;
    println(newarry.mkString(","));
    println(newarry.count(_>30));
  }

  def testmap(): Unit ={
    var mp = Map("a"->1,"b"->1,"c"->2);
    mp += ("d"->3);
    mp -="b";
    for(v <- mp) print(v+",");
    val m = for((k,v) <- mp) yield (v,k)
    println(mp("a"));
    println(mp("d"));
    for(v <- m) print(v+",");
    //println(mp("b"));
  }

  def readFile(): Unit ={
    val source = Source.fromFile("E:\\登录_files\\common.js","utf-8");
    /*val lines = source.getLines();
    for(line <- lines) println(line);
    println("===========================================");*/
    val content = source.mkString;
    println(content);
  }

  def fileCount(): Unit ={
    val source = Source.fromFile("E:\\mvn-resp\\classworlds\\classworlds\\1.1\\_remote.repositories","utf-8");
    val content = source.mkString.split(" ");
    val numbers = content.map(_.toInt);
  }

  def funmap(f:Double)= (x:Double) => x*f;
  def main(args: Array[String]) {
    //testFor();
    //testYield();
    //testArray();
    //println(abs(-1));
    //box("hello scala");
    //lookweb("http://www.baidu.com");
    //arry();
    //testmap();
    //val counter = new Counter("hello scala");;[]
    //println(counter.current);
    //counter.increment();
    //println(counter.current);
    //counter.setName("hello");
    //println(counter.getName);
    /*val ep = new Employee(123123,"张三","司机");
    println(ep.isInstanceOf[User]);
    println(ep.getJob);*/
    //readFile();
    //fileCount();
   /* val r = funmap(2);
    println(r(3));*/
    /*val a1 = Account;
    val a2 = Account;
    println(a1.newUniqueNumber());
    println(a2.newUniqueNumber());
    println(a1==a2);*/

    val ac1 = new Account;
    val ac2 = new Account;
    println(ac1==ac2);
    println(ac1.deposit(2));
    println(ac2.deposit(5));
    println(ac1.id);
    println(ac2.id);
    println(ac1.deposit(1));
    println(ac2.deposit(1));
  }

}
