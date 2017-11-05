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
        return "#login";
    }

}
