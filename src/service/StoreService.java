package service;

import model.Store;

import java.util.List;

public interface StoreService {
    void addStore(Store store);
    List<Store> getStores();
    void printStores();
    Store getStoreFromIndex(int index);
}