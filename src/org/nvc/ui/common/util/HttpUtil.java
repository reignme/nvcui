package org.nvc.ui.common.util;

import java.io.IOException;
import org.apache.commons.httpclient.*;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;

public class HttpUtil
{
    public static String post(String url, String data) 
    {
    	String response = "";
        HttpClient client = new HttpClient();
        PostMethod method = new PostMethod(url);
        
        NameValuePair[] postData = 
        {
        	new NameValuePair("data", data)
        };
        method.setRequestBody(postData);
        
        // Provide custom retry handler is necessary
        method.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, 
        		new DefaultHttpMethodRetryHandler(3, false));

        try 
        {
          // Execute the method.
          int statusCode = client.executeMethod(method);

          if (statusCode != HttpStatus.SC_OK) 
          {
            System.err.println("Method failed: " + method.getStatusLine());
          }

          // Deal with the response.
          // Use caution: ensure correct character encoding and is not binary data
          response = method.getResponseBodyAsString();
        
        } 
        catch (HttpException e) 
        {
          System.err.println("Fatal protocol violation: " + e.getMessage());
          e.printStackTrace();
        } 
        catch (IOException e) 
        {
          System.err.println("Fatal transport error: " + e.getMessage());
          e.printStackTrace();
        } 
        finally 
        {
          	//Release the connection.
        	method.releaseConnection();
        }  
        
        return response;
    }
}