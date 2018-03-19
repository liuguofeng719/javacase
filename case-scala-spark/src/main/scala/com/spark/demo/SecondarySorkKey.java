package com.spark.demo;

import java.io.Serializable;

import scala.math.Ordered;

/**
 * Created with IntelliJ IDEA.
 *
 * @author lgfcxx
 * @version 1.0
 * @desc
 * @createtime 2017/10/8 上午9:47
 * @see jdk 1.7
 **/
public class SecondarySorkKey implements Ordered<SecondarySorkKey>, Serializable {

    private int first;
    private int second;

    public SecondarySorkKey(int first, int second) {
        this.first = first;
        this.second = second;
    }

    public int getFirst() {
        return first;
    }

    public void setFirst(int first) {
        this.first = first;
    }

    public int getSecond() {
        return second;
    }

    public void setSecond(int second) {
        this.second = second;
    }

    public int compare(SecondarySorkKey that) {
        if (this.first - that.getFirst() != 0) {
            return this.first - that.first;
        } else {
            return this.second - that.getSecond();
        }
    }

    public boolean $less(SecondarySorkKey that) {
        if (this.first < that.getFirst()) {
            return true;
        } else if (this.first == that.getFirst() && this.second < that.getSecond()) {
            return true;
        }
        return false;
    }

    public boolean $less$eq(SecondarySorkKey that) {
        if (this.$less(that)) {
            return true;
        } else if (this.first == that.getFirst() && this.second == that.getSecond()) {
            return true;
        }
        return false;
    }

    public boolean $greater(SecondarySorkKey that) {
        if (this.first > that.getFirst()) {
            return true;
        } else if (this.first == that.getFirst() && this.second > that.getSecond()) {
            return true;
        }
        return false;
    }

    public boolean $greater$eq(SecondarySorkKey that) {
        if (this.$greater(that)) {
            return true;
        } else if (this.first == that.getFirst() && this.second == that.getSecond()) {
            return true;
        }
        return false;
    }

    public int compareTo(SecondarySorkKey that) {
        if (this.first - that.getFirst() != 0) {
            return this.first - that.first;
        } else {
            return this.second - that.getSecond();
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        final SecondarySorkKey that = (SecondarySorkKey) o;

        if (first != that.first) return false;
        return second == that.second;
    }

    @Override
    public int hashCode() {
        int result = first;
        result = 31 * result + second;
        return result;
    }
}
