package br.com.ga.service.intf;

import br.com.ga.entity.Picture;

public interface IPictureService {
    Picture createUpdate(Picture picture) throws Exception;

    Picture findById(long pictureId) throws Exception;

    void delete(Picture picture) throws Exception;

    int deleteById(long pictureId) throws Exception;
}
