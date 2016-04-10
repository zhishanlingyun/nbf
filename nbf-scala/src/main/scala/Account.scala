/**
 * Created by Administrator on 2016/4/10 0010.
 */

class Account{
  val id = Account.newUniqueNumber();

  private var balance = 0;

  def deposit(amount:Int)={
    balance += amount;
    balance;
  }
}

object Account {   // 伴生对象
  private var lastNumber = 0;

  def newUniqueNumber()={
    lastNumber +=1;
    lastNumber;
  }
}
