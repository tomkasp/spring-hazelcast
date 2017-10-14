package com.tomkasp.springhazelcst;

/**
 * @author Tomasz Kasprzycki
 */
public interface SessionProvider {

    public String getSession(Long key);

    public boolean setSession(String session);

}
