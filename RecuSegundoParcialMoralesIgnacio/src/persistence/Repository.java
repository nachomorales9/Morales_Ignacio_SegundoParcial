/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Nacho utn
 */
package persistence;

import java.util.List;
import java.util.Optional;

public interface Repository<T> {
    void add(T entity);

    Optional<T> findById(int id);

    List<T> findAll();
}
