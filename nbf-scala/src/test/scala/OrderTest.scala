import com.scala.base.Order

/**
 * Created by Administrator on 2016/3/19 0019.
 */
object OrderTest {

  def main(args: Array[String]) {
    val order = new Order("160219191555",1455880555729L,9757408,"京东",3,233,6990,0,565142,9812399);
    println(order);
    val str = "160219191555,1455880555729,9757408,京东金融,3,233,6990,123123,565142,9812399";
    //println(str.split(" ").length);
    //str.split(",").foreach(println);
    val order2 = str.split(",").map(item => {
      /*Order(
        item(0).toString,
        item(1).toLong,
        item(2).toLong,
        item(3).toString,
        item(4).toInt,
        item(5).toFloat,
        item(6).toLong,
        item(7).toLong,
        item(8).toLong,
        item(9).toLong
      );*/
      println(item(0))
    });
    //println(order2);
  }



}
