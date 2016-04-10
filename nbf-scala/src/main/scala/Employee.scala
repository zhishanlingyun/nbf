/**
 * Created by Administrator on 2016/4/10 0010.
 */
class Employee(id:String,name:String) extends Person(id,name){

  var salary = 0.0;

  def countSalary(money:Double)={
    salary += money;
    salary;
  }

  def this(id:String,name:String,salary: Double){
    this(id,name);
    this.salary = salary;
  }

  override def toString() = {
    super.toString() + " salary = "+salary;
  }
}
