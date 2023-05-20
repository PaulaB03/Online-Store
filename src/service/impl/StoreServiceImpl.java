package service.impl;

import exceptions.StoreException;
import model.Store;
import service.StoreService;

import java.util.ArrayList;
import java.util.List;

import static constants.ExceptionConstants.INVALID_INT;
import static constants.InformationConstants.STORES;

public class StoreServiceImpl implements StoreService {
    private static final List<Store> stores = new ArrayList<>();

    @Override
    public void addStore(Store store) {
        stores.add(store);
    }

    @Override
    public List<Store> getStores() {
        return stores;
    }

    @Override
    public void printStores() {
        System.out.println(STORES);
        for (int i=0; i < stores.size(); i++)
            System.out.println("Index " + (i+1) + " " + stores.get(i).getStoreName());
    }

    @Override
    public Store getStoreFromIndex(int index) {
        if (index < 0 || index > stores.size())
            throw new StoreException(INVALID_INT);

        return stores.get(index);
    }
}