package hr.java.production.enumeration;

public enum FilePath {
    CATEGORIES("src/main/dat/input/categories.txt"),
    ITEMS("src/main/dat/input/items.txt"),
    ADDRESSES("src/main/dat/input/addresses.txt"),
    FACTORIES("src/main/dat/input/factories.txt"),
    STORES("src/main/dat/input/stores.txt"),
    SERIALIZED_FACTORIES("src/main/dat/serialized-objects/serialized-factories.txt"),
    SERIALIZED_STORES("src/main/dat/serialized-objects/serialized-stores.txt"),
    NEW_ITEMS_FILE_PATH("src/main/dat/user-input/user-added-items.txt"),
    NEW_CATEGORIES_FILE_PATH("src/main/dat/user-input/user-added-categories.txt"),
    NEW_FACTORIES_FILE_PATH("src/main/dat/user-input/user-added-factories.txt");

    private final String path;

    FilePath(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }
}
