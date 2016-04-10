package com.scala.obj.abs

/**
 * Created by Administrator on 2016/4/10 0010.
 */
class Student(override val id:Int) extends Person{

  override def toString = super.toString;

  override def equals(obj: scala.Any): Boolean = {
    val that = obj.asInstanceOf[Student];
    if(null == that){
      false;
    }else{
      this.id == that.id
    }
  }

  override def hashCode(): Int = {
    val state = Seq(id)
    state.map(_.hashCode()).foldLeft(0)((a, b) => 31 * a + b)
  }
}
