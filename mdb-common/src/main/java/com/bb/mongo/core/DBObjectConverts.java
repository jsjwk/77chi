package com.bb.mongo.core;

import com.google.code.morphia.Morphia;
import com.mongodb.DBObject;

public class DBObjectConverts {
    static Morphia morphia = new Morphia();
    static {
        morphia = new Morphia();
    }

    public static DBObject toDBObject(Object entity) {
        return morphia.toDBObject(entity);
    }

    public static <T> Object fromDBObject(Class<T> entityClass, DBObject dbObject) {
        return morphia.fromDBObject(entityClass, dbObject);
    }
}
