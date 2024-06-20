package edu.icet.clothify.dao;

import edu.icet.clothify.dao.SuperDao;

public interface CrudDao <T> extends SuperDao {
    boolean save(T entity);
}
