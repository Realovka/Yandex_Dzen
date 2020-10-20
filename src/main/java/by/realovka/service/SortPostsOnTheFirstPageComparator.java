package by.realovka.service;

import by.realovka.entity.Post;

import java.util.Comparator;

public class SortPostsOnTheFirstPageComparator implements Comparator<Post> {
    @Override
    public int compare(Post o1, Post o2) { // если кол-во секунд, которое возвращает метод, для первой даты меньше чем для второй, значит это время было
        if(o1.getTimestamp().getTime() < o2.getTimestamp().getTime()){ //раньше
            return 1;
        } else return -1;
    }
}
