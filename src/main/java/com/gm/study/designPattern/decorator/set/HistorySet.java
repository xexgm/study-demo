package com.gm.study.designPattern.decorator.set;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * @Author: xexgm
 * des: 当前类是一个set，同时需要具备 保存被删除的元素 的功能
 */
public class HistorySet<E> implements Set<E> {

    // 1.首先是一个 set，所以实现 set 接口
    // 2.要保存被删除的元素，要用一个集合来存
    // 3.只对 remove 方法做增强，所以要原封不动的实现其他方法，那怎么实现呢？由一个Set的实现类来做
    
    // 4.如果我们要让 set 是有序的呢？ -> 可以把 HashSet 改为 TreeSet  -> 所以，这个set，可以让用户传参进来,我们所有的方法，都让 代理 去做




    List<E> removeList = new ArrayList<>();

    private final Set<E> delegate;


    public HistorySet(Set<E> c) {
        this.delegate = c;
    }

    @Override
    public boolean remove(Object o) {

        if (delegate.remove(o)) {
            removeList.add((E)o);
            return true;
        }

        return false;
    }

    @Override
    public int size() {
        return delegate.size();
    }

    @Override
    public boolean isEmpty() {
        return delegate.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return delegate.contains(o);
    }

    @Override
    public Iterator iterator() {
        return null;
    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @Override
    public boolean add(Object o) {
        return delegate.add((E)o);
    }


    @Override
    public boolean addAll(Collection c) {
        return false;
    }

    @Override
    public void clear() {

    }

    @Override
    public boolean removeAll(Collection c) {
        return false;
    }

    @Override
    public boolean retainAll(Collection c) {
        return false;
    }

    @Override
    public boolean containsAll(Collection c) {
        return false;
    }

    @Override
    public Object[] toArray(Object[] a) {
        return new Object[0];
    }

    @Override
    public String toString() {
        return delegate.toString() + " removeList: " + removeList.toString();
    }
}
