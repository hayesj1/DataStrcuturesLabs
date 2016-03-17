package ach.list.iterator;

import java.util.Iterator;

/**
 * Group Members: Christian Abate-Wong, Karen Camaso, Jacob Hayes
 *
 * @author Christian Abate-Wong
 * @author Karen Camaso
 * @author Jacob Hayes
 *         <p>
 *         Created by hayesj3 on 3/17/2016.
 */
public interface CustomIterable<E> extends Iterable<E> {

	IIterator<E> getCustomIterator();

	@Override
	default Iterator<E> iterator() { return ((Iterator<E>) getCustomIterator()); }
}
