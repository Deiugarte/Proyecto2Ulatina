
package dao.service;

import java.util.List;

/**
 *
 * @author José Pablo
 * @param <T>
 */
public interface IDao<T> {
    
    public List<T> buscarUsuarios();

    public void insert(T data);

    public void delete(T data);

    public void update(T data);
    
}
