package br.com.ga.dao.implementations;

import br.com.ga.dao.intf.IPictureDao;
import br.com.ga.entity.Picture;
import br.com.ga.exceptions.EntityNotFound;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;

@Transactional
@Repository
public class PictureDaoImpl implements IPictureDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Picture createUpdate(Picture picture) throws Exception {
        Picture p = em.merge(picture);
        em.flush();
        return p;
    }

    @Override
    public Picture findById(long id) throws Exception {
        TypedQuery<Picture> qry = em
                .createQuery("SELECT p FROM Picture p WHERE p.id = :id", Picture.class);
        try {
            return (Picture) qry
                    .setParameter("id", id)
                    .getSingleResult();
        } catch (NoResultException ex) {
            throw new EntityNotFound();
        }
    }

    @Override
    public void delete(Picture picture) throws Exception {
        em.remove(picture);
        em.flush();
    }

    @Override
    public int deleteById(long pictureId) throws Exception {
        if (pictureId <= 0)
            throw new EntityNotFound("Id inválido");

        Query qry = em
                .createQuery("DELETE FROM Picture p WHERE p.id = :pictureId")
                .setParameter("pictureId", pictureId);
        return qry.executeUpdate();
    }
}
