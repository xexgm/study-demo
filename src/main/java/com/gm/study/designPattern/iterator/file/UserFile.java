package com.gm.study.designPattern.iterator.file;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

import com.gm.study.designPattern.iterator.User;

/**
 * @Author: xexgm
 */
public class UserFile implements Iterable<User>{

    File file;

    public UserFile(File file) {
        this.file = file;
    }

    @Override
    public Iterator<User> iterator() {
        return new UserFileIte();
    }

    private class UserFileIte implements Iterator<User> {

        List<User> users = loadUserFromFile();

        int cursor = 0;

        private List<User> loadUserFromFile() {
            try {
                return Files.readAllLines(file.toPath()).stream().map((s) -> {
                    String substring = s.substring(1, s.length() - 1);
                    String[] split = substring.split(",");
                    String name = split[0];
                    Integer age = Integer.parseInt(split[1]);
                    return new User(name, age);
                }).toList();

            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }

        @Override
        public boolean hasNext() {
            return cursor != users.size();
        }

        @Override
        public User next() {
            if (cursor >= users.size()) {
                throw new NoSuchElementException();
            }
            int currentIndex = cursor;
            cursor++;
            return users.get(currentIndex);
        }
    }
}
