package controllers;

import application.ApplicationTest;
import br.com.ga.entity.Person;
import br.com.ga.entity.enums.PersonType;
import br.com.ga.security.Config;
import br.com.ga.service.intf.IPersonService;
import br.com.ga.util.Consts;
import br.com.ga.util.Util;
import br.com.ga.web.controllers.PersonController;
import br.com.ga.web.rest.ResponseCode;
import br.com.ga.web.rest.ResponseData;
import br.com.ga.web.rest.UrlMapping;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;


//@RunWith(SpringRunner.class)
//@WebMvcTest(value = PersonController.class, secure = false)
//@ContextConfiguration(classes = {ApplicationTest.class})
public class PersonControllerTest {
//    private Person personTest;
//    private String personJSON;
//    private ResponseData<Person> personResponse;
//    private String personResponseJSON;
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @MockBean
//    private IPersonService personService;
//
//    @Before
//    public void setUp() throws Exception {
//        personTest = new Person(
//                1,
//                "Nome",
//                "SobreNome",
//                "email",
//                "senha",
//                "cidade",
//                "estado",
//                "pais",
//                true,
//                "12345678",
//                "endereço",
//                "SN",
//                "centro",
//                "fundos",
//                "123456789",
//                Util.curDate(),
//                "12345678",
//                "12345678",
//                "adicionais",
//                123.123,
//                321.321,
//                true,
//                1,
//                PersonType.MALE);
//
//        ObjectMapper mapper = new ObjectMapper();
//        personJSON = mapper.writeValueAsString(personTest);
//        personResponse = new ResponseData<>(Person.class, personTest, ResponseCode.CREATED);
//        personResponseJSON = mapper.writeValueAsString(personResponse);
//    }
//
//    @Test
//    public void createUpdatePerson() throws Exception {
//        RequestBuilder requestBuilder = MockMvcRequestBuilders
//                .post(UrlMapping.PERSON + UrlMapping.PERSON_CREATE_UPDATE)
//                .accept(MediaType.APPLICATION_JSON).content(personJSON)
//                .contentType(MediaType.APPLICATION_JSON);
//
//        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
//        MockHttpServletResponse response = result.getResponse();
//
//        Assert.assertEquals(HttpStatus.OK.value(), response.getStatus());
//
//        JSONAssert.assertEquals(personResponseJSON, result.getResponse().getContentAsString(), false);
//    }

//    @Test
//    public void retrievePerson() throws Exception {
//        RequestBuilder requestBuilder = MockMvcRequestBuilders
//                .get(UrlMapping.PERSON + UrlMapping.PERSON_GET.replace("{personId}", "1"))
//                .accept(MediaType.APPLICATION_JSON);
//
//        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
//
//        System.out.println(result.getResponse());
//        String expected = "{id:Course1,name:Spring,description:10 Steps}";
//
//        JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
//    }
}
