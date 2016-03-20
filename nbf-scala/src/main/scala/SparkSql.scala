import com.scala.base.Order
import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.sql.SQLContext

/**
 * Created by Administrator on 2016/3/19 0019.
 */
object SparkSql {

  def main(args: Array[String]) {

    val conf = new SparkConf().setAppName("spark-wordcount").setMaster("local[2]");
    val sc = new SparkContext(conf);

    val rdddata = sc.textFile("nbf-scala/src/data/order_history.txt").map(item => item.split(" ")).map(data => {
      Order(
        data(0).toString,
        data(1).toLong,
        data(2).toLong,
        data(3).toString,
        data(4).toInt,
        data(5).toFloat,
        data(6).toLong,
        data(7).toLong,
        data(8).toLong,
        data(9).toLong
      );
    });
    //rdddata.foreach(order => println(order));
    val sqlContext = new SQLContext(sc);
    val df = sqlContext.createDataFrame(rdddata).registerTempTable("temp");

    val result = sqlContext.sql("select * from temp");
    result.show();
    val r = sqlContext.sql("select orderId,count(1) from temp group by orderId");
    r.show();

    //val rdddata = sc.textFile("nbf-scala/src/data/example1.txt").map(item => item.length).reduce((a,b) => a+b);
    println(rdddata);
    sc.stop();

  }

}
