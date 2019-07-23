package com.madbarsoft.springbootthymeleftspringsecurity.book;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.madbarsoft.springbootthymeleftspringsecurity.base.BaseRepository;


@Repository
public class BookRepository extends BaseRepository {
	
	@Autowired
	private EntityManager entityManager;
	
	public BookEntity findById(Long id) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<BookEntity> criteria = builder.createQuery( BookEntity.class );
        Root<BookEntity> root = criteria.from(BookEntity.class);
        criteria.select(root).where(
                builder.equal(root.get("id"), id),
                builder.equal(root.get("isDeleted"), false)
        );
        return entityManager.createQuery(criteria).getSingleResult();
    }

    public List<BookEntity> list() {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<BookEntity> criteria = builder.createQuery( BookEntity.class );
        Root<BookEntity> root = criteria.from(BookEntity.class);
        criteria.select(root).where(builder.equal(root.get("isDeleted"), false));
        return entityManager.createQuery(criteria).getResultList();
    }

    public Boolean save(BookEntity obj) {
        try{
            entityManager.persist(obj);
            return true;
        }catch (Exception ex){
            ex.printStackTrace();
            return false;
        }
    }

    public Boolean update(BookEntity obj){
        try{
            entityManager.merge(obj);
            return true;
        }catch (Exception ex){
            ex.printStackTrace();
            return false;
        }
    }
    public Boolean remove(BookEntity obj){
        try{
            obj.setDeleted(true);
            entityManager.merge(obj);
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return false;
    }

    public Boolean delete(BookEntity obj) {
        try{
            entityManager.remove(obj);
            return true;
        }catch (Exception ex){
            ex.printStackTrace();
            return false;
        }
    }

}
