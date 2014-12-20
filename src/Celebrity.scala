object Celebrity {
  def find(n: Int, f: (Int, Int) => Boolean): Int = {
    0.until(n).foldLeft(0) { (current, i) =>
      if(f(current,  i) && !f(i, current)) i else current
    }
  }
}