import org.apache.spark.{SparkConf, SparkContext}

/**
 * Created by Administrator on 2016/3/4 0004.
 */
object WordCount {

  def main(args: Array[String]) {
    val conf = new SparkConf().setAppName("spark-wordcount").setMaster("local[2]");
    val sc = new SparkContext(conf);
    //根据具体数据源创建RDD，数据源可以是HDFS,HBase,Local FS,DB,S3等
    //RDD创建方式有三种：1.外部数据来源;2.根据Scala数据集;3.RDD
    val lines = sc.textFile("D:\\lua\\f1.lua",1);

    //对初始的RDD进行Transformation,例如map,filter等高阶函数等的编程，来进行具体数据计算
    //拆分字符串
    val words = lines.flatMap{line => line.split(" ")};
    val pairs = words.map( word => (word,1) );
    val wordCount = pairs.reduceByKey(_+_);
    wordCount.foreach(wordNumberPair => println(wordNumberPair._1 + ": " + wordNumberPair._2))
    lines.flatMap(_.split(" ")).map((_, 1)).reduceByKey(_+_).collect().foreach(println);
    sc.stop();

    //println("@@@@@@@@@@@"+words);



  }

}
