package br.com.ga.client.beans;


import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import java.io.IOException;
import java.io.Serializable;

public abstract class DefaultBean implements Serializable {
    public String redirectToMain() throws IOException {
        FacesContext ctx = FacesContext.getCurrentInstance();
        ExternalContext extContext = ctx.getExternalContext();
        String url;
        url = extContext.encodeActionURL(ctx.getApplication().getViewHandler().getActionURL(ctx, "/indexAuth.xhtml"));
        extContext.redirect(url);
        return "indexAuth";
    }

    public String redirectToMainClient() throws IOException {
        FacesContext ctx = FacesContext.getCurrentInstance();
        ExternalContext extContext = ctx.getExternalContext();
        String url;
        url = extContext.encodeActionURL(ctx.getApplication().getViewHandler().getActionURL(ctx, "/indexAuthClient.xhtml"));
        extContext.redirect(url);
        return "indexAuthClient";
    }

    public String redirectToEndRegister() throws IOException {
        FacesContext ctx = FacesContext.getCurrentInstance();
        ExternalContext extContext = ctx.getExternalContext();
        String url;
        url = extContext.encodeActionURL(ctx.getApplication().getViewHandler().getActionURL(ctx, "/endRegister.xhtml"));
        extContext.redirect(url);
        return "endRegister";
    }

    public String redirectToLogin() throws IOException {
        FacesContext ctx = FacesContext.getCurrentInstance();
        ExternalContext extContext = ctx.getExternalContext();
        String url;

        url = extContext.encodeActionURL(ctx.getApplication().getViewHandler().getActionURL(ctx, "/index.xhtml#login"));
        extContext.redirect(url);
        return "#login";
    }

    public String redirectToIndex() throws IOException {
        FacesContext ctx = FacesContext.getCurrentInstance();
        ExternalContext extContext = ctx.getExternalContext();
        String url;

        url = extContext.encodeActionURL(ctx.getApplication().getViewHandler().getActionURL(ctx, "/index.xhtml"));
        extContext.redirect(url);
        return "index";
    }

    public String redirectToAnimals() throws IOException {
        FacesContext ctx = FacesContext.getCurrentInstance();
        ExternalContext extContext = ctx.getExternalContext();
        String url;

        url = extContext.encodeActionURL(ctx.getApplication().getViewHandler().getActionURL(ctx, "/animalView.xhtml"));
        extContext.redirect(url);
        return "animalView";
    }

    public String redirectToAnimalsClient() throws IOException {
        FacesContext ctx = FacesContext.getCurrentInstance();
        ExternalContext extContext = ctx.getExternalContext();
        String url;

        url = extContext.encodeActionURL(ctx.getApplication().getViewHandler().getActionURL(ctx, "/animalViewClient.xhtml"));
        extContext.redirect(url);
        return "animalViewClient";
    }

    public String redirectToServices() throws IOException {
        FacesContext ctx = FacesContext.getCurrentInstance();
        ExternalContext extContext = ctx.getExternalContext();
        String url;

        url = extContext.encodeActionURL(ctx.getApplication().getViewHandler().getActionURL(ctx, "/servicoView.xhtml"));
        extContext.redirect(url);
        return "servicoView";
    }

    public String redirectToBuiedServices(boolean isServiceProvider) throws IOException {
        FacesContext ctx = FacesContext.getCurrentInstance();
        ExternalContext extContext = ctx.getExternalContext();
        String url;

        if (isServiceProvider)
            url = extContext.encodeActionURL(ctx.getApplication().getViewHandler().getActionURL(ctx, "/servicoContratado.xhtml"));
        else
            url = extContext.encodeActionURL(ctx.getApplication().getViewHandler().getActionURL(ctx, "/servicoContratadoClient.xhtml"));
        extContext.redirect(url);
        return "#servicos";
    }

    public String redirectToProvidedServices() throws IOException {
        FacesContext ctx = FacesContext.getCurrentInstance();
        ExternalContext extContext = ctx.getExternalContext();
        String url;

        url = extContext.encodeActionURL(ctx.getApplication().getViewHandler().getActionURL(ctx, "/servicoPrestadoView.xhtml"));
        extContext.redirect(url);
        return "#servicos";
    }
}
