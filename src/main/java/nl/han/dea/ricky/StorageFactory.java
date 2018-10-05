package nl.han.dea.ricky;

import nl.han.dea.ricky.LocalStorage.LocalTokenStorage;
import nl.han.dea.ricky.Response.UserToken;

public class StorageFactory {
    UserToken userToken = new UserToken("1234-1234-1234", "Ricky Broers");
    LocalTokenStorage localTokenStorage = new LocalTokenStorage(userToken);

    public StorageFactory() {
    }

    public LocalTokenStorage getInstanceOf() {
        return localTokenStorage;
    }
}