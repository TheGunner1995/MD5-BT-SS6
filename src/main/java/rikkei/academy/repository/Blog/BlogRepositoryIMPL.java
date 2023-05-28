package rikkei.academy.repository.Blog;

import rikkei.academy.model.Blog;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@Transactional
public class BlogRepositoryIMPL implements IBlogRepository {
    @PersistenceContext
    private EntityManager em;

    @Override
    public List findAll() {
        TypedQuery<Blog> query = em.createQuery("select b from Blog b", Blog.class);
        return query.getResultList();
    }

    @Override
    public Blog findById(Long id) {
        TypedQuery<Blog> query = em.createQuery("select b from Blog b where b.id = :id", Blog.class);
        query.setParameter("id",id);
        try {
            return query.getSingleResult();
        }catch (Exception e){
            return null;
        }

//        return em.find(Blog.class,id);
    }

    @Override
    public void save(Blog blog) {
        if (blog.getId() != null) {
            em.merge(blog);
        } else {
            em.persist(blog);
        }
    }

    @Override
    public void remove(Long id) {
        Blog blog = findById(id);
        if (blog != null){
            em.remove(blog);
        }
    }
}
