package wasdev.sample.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

/**
 * Servlet implementation class SimpleServlet
 */
@WebServlet("/SimpleServlet")
public class SimpleServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        System.out.println("");
        getTrends();
        response.getWriter().print("Hello World!");
    }
    
public void getTrends() {
		
		try{ 
			
			//String alchemyEndpoint = System.getenv("ALCHEMY_URL");
			//String alchemyApiKey = System.getenv("ALCHEMY_API_KEY");
			
			String conceptsURL ="http://api.walmartlabs.com/v1/search?apiKey=agevmwa5rhme979szegdj3v6&query=PHOTO%20SHADOW%20BOX%20TRAY&sort=customerRating&order=desc&numItems=5";
			
			HttpPost httpPost = new HttpPost(conceptsURL);
			//httpPost.addHeader("Content-Type","application/json");
			CloseableHttpClient client = HttpClients.createDefault();
			CloseableHttpResponse response = client.execute(httpPost);
			
			if (response.getStatusLine().getStatusCode() != HttpStatus.SC_OK) {
		  		throw new RuntimeException(
			  			"An error occured when invoking alchmey concepts Service at : "
				  				+ response.getStatusLine());
			} else {
				
					String result  = EntityUtils.toString(response.getEntity());
					System.out.println("Result@@"+result);
					//JSONObject responseJson = (JSONObject) JSON.parse(result);

					//if(responseJson.get("concepts") != null)
						//return responseJson;
			}
		}catch(Exception e ){
			e.printStackTrace();
		}
		//return null;
		
	}

}
