package hr.java.production.enumeration;

public enum FilePath {
    CATEGORIES("src/main/dat/input/categories.txt"),
    ITEMS("src/main/dat/input/items.txt"),
    ADDRESSES("src/main/dat/input/addresses.txt"),
    FACTORIES("src/main/dat/input/factories.txt"),
    STORES("src/main/dat/input/stores.txt"),
    SERIALIZED_FACTORIES("src/main/dat/serialized-objects/serialized-factories.txt"),
    SERIALIZED_STORES("src/main/dat/serialized-objects/serialized-stores.txt");

    private final String path;

    FilePath(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }
}
