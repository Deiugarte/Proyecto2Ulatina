package dao.service;

import java.util.List;

/**
 *
 * @author José Pablo
 * @param <T>
 */
public interface IDao<T> {

    public List<T> buscar();

    public int insert(T data);

    public void delete(T data);

    public void update(T data);

}
