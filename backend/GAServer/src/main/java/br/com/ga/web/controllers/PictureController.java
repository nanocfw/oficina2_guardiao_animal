package br.com.ga.web.controllers;

import br.com.ga.entity.Picture;
import br.com.ga.exceptions.EntityNotFound;
import br.com.ga.exceptions.InvalidEntity;
import br.com.ga.service.intf.IPictureService;
import br.com.ga.web.rest.ResponseCode;
import br.com.ga.web.rest.ResponseData;
import br.com.ga.web.rest.UrlMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(UrlMapping.PICTURE)
public class PictureController {

    @Autowired
    IPictureService pictureService;

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(
            value = UrlMapping.PICTURE_GET,
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseData<Picture> findById(@PathVariable(value = "pictureId") int pictureId) {
        try {
            Picture p = pictureService.findById(pictureId);
            return new ResponseData<>(Picture.class, p, ResponseCode.FOUND);
        } catch (EntityNotFound e) {
            return new ResponseData<>(Picture.class, ResponseCode.NOT_FOUND, EntityNotFound.class, e.getMessage());
        } catch (Exception e) {
            return new ResponseData<>(Picture.class, ResponseCode.ERROR, e.getClass(), e.getMessage());
        }
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(
            value = UrlMapping.PICTURE_CREATE_UPDATE,
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseData<Picture> createUpdate(@RequestBody Picture picture) {
        try {
            Picture p = pictureService.createUpdate(picture);
            return new ResponseData<>(Picture.class, p, ResponseCode.CREATED);
        } catch (InvalidEntity e) {
            return new ResponseData<>(Picture.class, ResponseCode.ERROR, InvalidEntity.class, e.getMessage());
        } catch (Exception ex) {
            return new ResponseData<>(Picture.class, ResponseCode.ERROR, ex.getClass(), ex.getMessage());
        }
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(
            value = UrlMapping.PICTURE_DELETE,
            method = RequestMethod.DELETE)
    public ResponseData<Integer> deleteById(@PathVariable("pictureId") long pictureId) throws Exception {
        try {
            int deletedRows = pictureService.deleteById(pictureId);
            return new ResponseData<>(Integer.class, deletedRows, ResponseCode.DELETED);
        } catch (Exception e) {
            return new ResponseData<>(Integer.class, ResponseCode.ERROR, e.getClass(), e.getMessage());
        }
    }
}
