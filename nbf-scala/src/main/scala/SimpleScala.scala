import java.io.File

/**
 * Created by Administrator on 2016/2/27 0027.
 */
class SimpleScala {

  private var msg="scala";

  def setMsg(msg:String): Unit ={
    this.msg = msg;
  }

  def getMsg(): String ={
    msg;
  }

  def say(msg:String){
    println("say "+msg);
  }

  def fileList(path:String): Unit ={
    var filenames = new File(path).listFiles()
    for(file <-filenames){
      println(file);
    }
  }

  def printList(args : Array[Int]): Unit ={
    args.foreach(println);
  }

  def printL(): Unit ={
    val lt = List("aaa","bbb","ccc");
    //lt.foreach(println);
    println(lt.last);
    val lty = lt.map(s => s+"yyy");
    lty.foreach(print);
  }

  def printP(): Unit ={
    val pairs = ("aaa",123123123);
    println(pairs._1);
    println(pairs._2);
  }

}
