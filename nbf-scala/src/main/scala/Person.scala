/**
 * Created by Administrator on 2016/4/10 0010.
 */
class Person {

  private var id = "";
  private var name = "";

  override def toString()={
    "id = "+id+" name = "+name
  }

  def this(id:String,name:String){
    this();
    this.id = id;
    this.name = name;
  }

}
