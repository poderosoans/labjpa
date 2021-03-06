/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.edu.cibertec.repositorio.impl;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import pe.edu.cibertec.dominio.Producto;
import pe.edu.cibertec.repositorio.ProductoRepositorio;

/**
 *
 * @author Poderosoans
 */
public class ProductoJpaRepositorioImpl implements ProductoRepositorio{
    
    private EntityManager em;
    
    public ProductoJpaRepositorioImpl setEm(EntityManager em) {
        this.em = em;
        return this;
    }
    
    @Override
    public Producto buscar(Long id) {
        return em.find(Producto.class, id);
    }

    @Override
    public void crear(Producto producto) {
        em.persist(producto);
    }

    @Override
    public void actualizar(Producto producto) {
        em.merge(producto);
    }

    @Override
    public void eliminar(Producto producto) {
        em.remove(producto);
    }
    
    private static final String SELECT_PRODUCTOS = "SELECT p FROM Producto p JOIN p.categoria c JOIN p.marca";
    
    @Override
    public List<Producto> obtenerTodos() {
        TypedQuery<Producto> query = em.createQuery(SELECT_PRODUCTOS,Producto.class);
        return query.getResultList();
    }

    @Override
    public List<Producto> obtenerPorCategoria(Long idCategoria) {
        TypedQuery<Producto> query = em.createNamedQuery(Producto.NQ_OBTENER_PRODUCTOS_POR_CATEGORIA, Producto.class);
        query.setParameter("idCategoria", idCategoria);
        return query.getResultList();
    }

    @Override
    public List<Producto> obtenerPorCategoriaCriteriaApi(Long idCategoria) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Producto> cq = cb.createQuery(Producto.class);
        Root<Producto> producto = cq.from(Producto.class);
        cq.select(producto).where(
                cb.equal(producto.get("categoria").get("id"), idCategoria)
        ).orderBy(cb.asc(producto.get("nombre")));
        TypedQuery<Producto> query = em.createQuery(cq);
        return query.getResultList();
    }
    
}
