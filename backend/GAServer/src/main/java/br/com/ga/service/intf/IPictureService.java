package br.com.ga.service.intf;

import br.com.ga.entity.Picture;

public interface IPictureService {
    Picture createUpdate(Picture picture) throws Exception;

    Picture findById(long id) throws Exception;

    void delete(Picture picture) throws Exception;
}
