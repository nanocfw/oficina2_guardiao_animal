package br.com.ga.client.beans;

import br.com.ga.entity.Person;
import br.com.ga.entity.Picture;
import br.com.ga.exceptions.EntityNotFound;
import br.com.ga.service.intf.IPersonService;
import br.com.ga.service.intf.IPictureService;
import br.com.ga.util.Consts;
import br.com.ga.util.FacesUtils;
import br.com.ga.util.SessionUtils;
import br.com.ga.util.Util;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.servlet.http.Cookie;
import java.util.UUID;

@ManagedBean
@SessionScoped
public class LoginBean extends DefaultBean {
    @EJB
    IPersonService personService;

    @EJB
    IPictureService pictureService;

    private Person authenticatedUser;
    private String profilePic;
    private Picture picture;

    public Person getAuthenticatedUser() {
        return authenticatedUser;
    }

    public void setAuthenticatedUser(Person authenticatedUser) {
        this.authenticatedUser = authenticatedUser;
        SessionUtils.setParam(Consts.PARAM_USER_SESSION, authenticatedUser);
        loadPictureFromDataBase();
    }

    public String getProfilePic() {
        return profilePic;
    }

    public void setProfilePic(String profilePic) {
        this.profilePic = profilePic;
    }

    public String login() {
        try {
            Person p = personService.findByEmailPassword(this.authenticatedUser.getEmail(), this.authenticatedUser.getPassword());
            // se não achar o usuário, o serviço irá disparar uma exceção

            p = updateToken(p);
            setAuthenticatedUser(p);

            if (p.isFinishedRegister())
                return redirectToMain();
            else
                return redirectToEndRegister();
        } catch (EntityNotFound e) {
            FacesUtils.addErrorMessage("lform:login", "Email ou senha está incorreto");
            return "error";
        } catch (Exception e) {
            FacesUtils.addErrorMessage("lform:login", e.getMessage());
            return "erro";
        }
    }

    public String logout() {
        SessionUtils.getSession().invalidate();
        Util.setCookie(Consts.COOKIE_NAME, "", 0);
        return "login";
    }

    public boolean validateCookie() {
        Cookie cookie = Util.getCookie(Consts.COOKIE_NAME);

        try {
            if (cookie == null || cookie.getValue().isEmpty())
                return false;

            UUID token = UUID.fromString(cookie.getValue());
            Person p = personService.findByValidToken(token);
            p = updateToken(p);
            setAuthenticatedUser(p);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public Person updateToken(Person person) throws Exception {
        Person p;
        p = personService.updateAuthToken(person);// atualiza o tempo de validade do token
        Util.setCookie(Consts.COOKIE_NAME,
                p.getAuthToken().toString(),
                Consts.COOKIE_LIFE_TIME);
        return p;
    }

    public void loadPictureFromDataBase() {
        if (authenticatedUser == null || authenticatedUser.getId() == 0 || authenticatedUser.getProfilePic() == 0) {
            profilePic = "";
            return;
        }

        if (picture != null && picture.getId() == authenticatedUser.getProfilePic() && picture.getPicture() == null && picture.getPicture().length > 0)// imagem já carregada e pertence ao currentPerson
            return;

        try {
            picture = pictureService.findById(authenticatedUser.getProfilePic());
            picture.setTag(Util.curDate().getTime());
            profilePic = picture.asString();
        } catch (EntityNotFound e) {
            picture = new Picture();
            profilePic = "";
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public LoginBean() {
        super();
        authenticatedUser = new Person();
        picture = null;
        profilePic = "";
    }
}
