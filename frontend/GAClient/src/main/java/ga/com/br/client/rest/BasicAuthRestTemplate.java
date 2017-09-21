/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ga.com.br.client.rest;

import java.util.ArrayList;
import java.util.List;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.InterceptingClientHttpRequestFactory;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author Marciano
 */
public class BasicAuthRestTemplate extends RestTemplate
{

    private final String username;
    private final String password;

    public BasicAuthRestTemplate(String username, String password)
    {
        super();
        this.username = username;
        this.password = password;
        addAuthentication();
    }

    private void addAuthentication()
    {
        if (StringUtils.isEmpty(username))
            throw new RuntimeException("Username is mandatory for Basic Auth");

        List<ClientHttpRequestInterceptor> interceptors;
        interceptors = new ArrayList<>();
        interceptors.add(new BasicAuthInterceptor(username, password));
        setRequestFactory(new InterceptingClientHttpRequestFactory(getRequestFactory(), interceptors));
    }
}
