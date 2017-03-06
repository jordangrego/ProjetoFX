package br.com.montreal.negocio;

import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;

public class AcessoLdap {
	public static boolean authenticateJndi(String username, String password) throws Exception{
		try
	    {
	        // Set up the environment for creating the initial context
	        Hashtable<String, String> env = new Hashtable<String, String>();
	        env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
	        env.put(Context.PROVIDER_URL, "ldap://df.mi");
	        // 
	        env.put(Context.SECURITY_AUTHENTICATION, "simple");
	        env.put(Context.SECURITY_PRINCIPAL, "MIDF\\" + username); //we have 2 \\ because it's a escape char
	        env.put(Context.SECURITY_CREDENTIALS, password);

	        // Create the initial context

	        DirContext ctx = new InitialDirContext(env);
	        boolean result = ctx != null;

	        if(ctx != null)
	            ctx.close();

	        return result;
	    }
	    catch (Exception e)
	    {           
	        return false;
	    }
	}
}
