package br.com.ga.service.implementations;

import br.com.ga.dao.intf.IPictureDao;
import br.com.ga.entity.Picture;
import br.com.ga.service.intf.IPictureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PictureServiceImpl implements IPictureService {

    @Autowired
    IPictureDao pictureDao;

    @Override
    public Picture createUpdate(Picture picture) throws Exception {
        return pictureDao.createUpdate(picture);
    }

    @Override
    public Picture findById(long id) throws Exception {
        return pictureDao.findById(id);
    }

    @Override
    public void delete(Picture picture) throws Exception {
        pictureDao.delete(picture);
    }

    @Override
    public int deleteById(long pictureId) throws Exception {
        return pictureDao.deleteById(pictureId);
    }
}
