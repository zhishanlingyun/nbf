import scala.beans.BeanProperty

/**
 * Created by Administrator on 2016/3/12 0012.
 */
class Counter {

  private var value = 0;

  val version = "0.1";

  @BeanProperty
  var name:String = _;

  def this(str:String){
    this();
    this.name = str;
  }

  def increment(): Unit ={
    value +=1;
  }

  def current = value;
}
