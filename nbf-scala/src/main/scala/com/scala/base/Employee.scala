package com.scala.base

import scala.beans.BeanProperty

/**
 * Created by Administrator on 2016/3/12 0012.
 */
class Employee extends User{

  @BeanProperty
  var job:String = _;

  def this(id:Long,name:String,job:String){
    this();
    super.setId(id);
    super.setName(name);
    this.job = job;
  }

}
