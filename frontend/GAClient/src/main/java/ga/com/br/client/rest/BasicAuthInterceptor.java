/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ga.com.br.client.rest;

import java.io.IOException;
import org.apache.commons.codec.binary.Base64;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

/**
 *
 * @author Marciano
 */
public class BasicAuthInterceptor implements ClientHttpRequestInterceptor
{

    private final String username;
    private final String password;

    public BasicAuthInterceptor(String username, String password)
    {
        this.username = username;
        this.password = password;
    }

    @Override
    public ClientHttpResponse intercept(
            HttpRequest httpRequest,
            byte[] bytes,
            ClientHttpRequestExecution clientHttpRequestExecution
    ) throws IOException
    {
        HttpHeaders headers = httpRequest.getHeaders();
        headers.add(HttpHeaders.AUTHORIZATION, encodeCredentialsForBasicAuth(username, password));

        return clientHttpRequestExecution.execute(httpRequest, bytes);
    }

    public static String encodeCredentialsForBasicAuth(String username, String password)
    {
        return "Basic " + new Base64().encodeToString((username + ":" + password).getBytes());
    }
}
