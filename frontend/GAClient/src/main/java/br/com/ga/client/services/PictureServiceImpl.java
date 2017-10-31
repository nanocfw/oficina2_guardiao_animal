package br.com.ga.client.services;

import br.com.ga.client.rest.BasicAuthRestTemplate;
import br.com.ga.client.rest.Service;
import br.com.ga.entity.Picture;
import br.com.ga.exceptions.EntityNotFound;
import br.com.ga.exceptions.InvalidEntity;
import br.com.ga.service.intf.IPictureService;
import br.com.ga.web.rest.ResponseCode;
import br.com.ga.web.rest.ResponseData;
import br.com.ga.web.rest.UrlMapping;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import javax.ejb.Stateless;
import java.util.HashMap;
import java.util.Map;

@Stateless
public class PictureServiceImpl extends Service implements IPictureService {

    @Override
    public Picture createUpdate(Picture picture) throws Exception {
        BasicAuthRestTemplate rest = getNewRestTemplate();
        ResponseEntity<ResponseData<Picture>> response;
        HttpEntity<Picture> httpPicture = new HttpEntity<>(picture);

        response = rest.exchange(
                getServerURL() + UrlMapping.PICTURE + UrlMapping.PICTURE_CREATE_UPDATE,
                HttpMethod.POST,
                httpPicture,
                new ParameterizedTypeReference<ResponseData<Picture>>() {
                });

        if (response.getBody().getStatus() == ResponseCode.CREATED)
            return response.getBody().getValue();

        if (response.getBody().getExceptionType() == InvalidEntity.class)
            throw new InvalidEntity(response.getBody().getExceptionMessage());

        throw new Exception(
                "ExceptionClass: " + response.getBody().getExceptionType().toString()
                        + " Message: " + response.getBody().getExceptionMessage());
    }

    @Override
    public Picture findById(long id) throws Exception {
        BasicAuthRestTemplate rest = getNewRestTemplate();
        ResponseEntity<ResponseData<Picture>> response;
        Map<String, Long> params = new HashMap<>();
        params.put("pictureId", id);

        response = rest.exchange(
                getServerURL() + UrlMapping.PICTURE + UrlMapping.PICTURE_GET,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<ResponseData<Picture>>() {
                },
                params
        );

        if (response.getBody().getStatus() == ResponseCode.FOUND)
            return response.getBody().getValue();

        if (response.getBody().getExceptionType() == EntityNotFound.class)
            throw new EntityNotFound(response.getBody().getExceptionMessage());

        throw new Exception(
                "ExceptionClass: " + response.getBody().getExceptionType().toString()
                        + " Message: " + response.getBody().getExceptionMessage());
    }

    @Override
    public void delete(Picture picture) throws Exception {
        BasicAuthRestTemplate rest = getNewRestTemplate();
        HttpEntity<Picture> httpPicture = new HttpEntity<>(picture);

        rest.exchange(
                getServerURL() + UrlMapping.PICTURE + UrlMapping.PICTURE_DELETE,
                HttpMethod.DELETE,
                httpPicture,
                void.class
        );
    }
}
