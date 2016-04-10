import java.util.Date

import com.scala.dao.HbaseDao

/**
 * Created by Administrator on 2016/4/9 0009.
 */
object HbaseTesa {

  def main(args: Array[String]) {
    val dao = new HbaseDao;
    //dao.createTable("scala-t1");
    /*for(i <- 1 to 100){
      dao.insert("scala-t1",new Date().getTime.toString,List("ttt"),Map("a"->"1","b"->"2"));
    }*/

    println(dao.query("scala-t1","1460198683999"));
    dao.delete("scala-t1","1460198683999");
    println(dao.query("scala-t1","1460198683999"));

  }

}
