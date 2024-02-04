package com.generic.retailer;

import java.util.ArrayList;
import java.util.List;

public class Database {

    private static Database INSTANCE = null;

    public static Database getInstance() {
        if (INSTANCE == null) {
            return new Database();
        }
        return INSTANCE;
    }

    private Database() {
    }

    private static final List<Trolley> data = new ArrayList();

    public void add(Trolley trolley) {
        data.add(trolley);
    }

    public Trolley find(Integer index) {
        return data.get(index);
    }

    public List<Trolley> findAll() {
        return data;
    }

}