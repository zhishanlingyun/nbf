package com.scala.obj.abs

/**
 * Created by Administrator on 2016/4/10 0010.
 */
object AbsApp extends App{

  val s = new Student(123);
  println(s);
  val t = new Techer("kkk");
  println(t);
  val s2 = new Student(222);
  val s3 = new Student(123);
  println(s==s2);
  println(s.equals(s2));
  println(s==s3);
  println(s.equals(s3));

}
