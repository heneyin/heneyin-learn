package com.henvealf.learn.scala.leetcode

object Solution {
  def removeDuplicates(nums: Array[Int]): Int = {
    if (nums == null || nums.isEmpty) return 0

    var size = 0

    for( i <- nums.indices ){
      if (nums(i) != nums(size)) {
        size += 1
        nums(size) = nums(i)
      }
    }
    size + 1
  }

  def rotate(nums: Array[Int], k: Int): Unit = {
    for( i <- (0 until k).reverse ) {
      val tmp = nums(nums.length - 1)
      for( j <- (1 until nums.length).reverse ) {
        nums(j) = nums(j - 1)
      }
      nums(0) = tmp
    }
  }

  def main(args: Array[String]): Unit = {
//    val array = Array.emptyIntArray
//    print(removeDuplicates(array))
//    array.foreach(println)
    val array = Array(1,2,3,4,5,6,7)
    rotate(array, -1)
    array.foreach(println)
  }
}
