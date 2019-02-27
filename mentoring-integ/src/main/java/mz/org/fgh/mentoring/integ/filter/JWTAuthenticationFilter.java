package mz.org.fgh.mentoring.integ.filter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import mz.co.mozview.frameworks.core.webservices.model.UserContext;
import mz.org.fgh.mentoring.integ.config.ConfigUtils;
import mz.org.fgh.mentoring.integ.resources.tutor.TutorResource;
import mz.org.fgh.mentoring.integ.resources.tutor.TutorResourceImpl;
import org.json.simple.JSONObject;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.util.Arrays;
import java.util.logging.Logger;

public class JWTAuthenticationFilter extends GenericFilterBean {
    protected static final Logger logger = Logger.getLogger(JWTAuthenticationFilter.class.getName());
    protected static final String TUTOR_RESET_PASSWORD_PATH =  new StringBuilder("/")
            .append(TutorResourceImpl.TUTOR_RESOURCE_PATH)
            .append("/")
            .append(TutorResource.RESET_PASSWORD_PATH)
            .toString();

    private String JWTKey;


    public JWTAuthenticationFilter(@NotNull final String JWTKey) {
        this.JWTKey = JWTKey;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        if(request instanceof HttpServletRequest) {
            HttpServletRequest httpServletRequest = (HttpServletRequest) request;
            logger.info("Path Info: " + httpServletRequest.getPathInfo());
            if(TUTOR_RESET_PASSWORD_PATH.equals(httpServletRequest.getPathInfo())) {
                chain.doFilter(request, response);
            } else {
                logger.info("Check principal authentication");
                HttpServletResponse httpServletResponse = (HttpServletResponse) response;

                if (JWTKey == null) {
                    String message = new StringBuilder("JWT authentication key property [").append(ConfigUtils.JWT_KEY_RUNTIME_PROPERTY_NAME)
                            .append(ConfigUtils.RUNTIME_PROPS_FILENAME).append(" file").toString();
                    logger.severe(message);
                    String userMessage = "JWT Key not set, please contact Mentoring system administrator";
                    sendJSONError(httpServletResponse, HttpServletResponse.SC_UNAUTHORIZED, userMessage, null);
                } else {
                    String header = httpServletRequest.getHeader("Authorization");

                    if (header == null || !header.startsWith("Bearer ")) {
                        String message = "No JWT token found in request headers";
                        String description = "The request has not been applied because it lacks valid JWT authentication token";
                        logger.warning(message);
                        sendJSONError(httpServletResponse, HttpServletResponse.SC_UNAUTHORIZED, message, description);
                    } else {
                        String authToken = header.substring(7);

                        try {
                            Algorithm algorithm = Algorithm.HMAC256(JWTKey);
                            JWTVerifier verifier = JWT.require(algorithm).build(); //Reusable verifier instance
                            verifier.verify(authToken);

                            chain.doFilter(httpServletRequest, response);
                        } catch (JWTVerificationException e) {
                            logger.severe(e.getMessage());
                            e.printStackTrace();
                            sendJSONError(httpServletResponse, HttpServletResponse.SC_UNAUTHORIZED, e.getMessage(), null);
                        }
                    }
                }
            }
        } else {
            chain.doFilter(request, response);
        }
    }

    protected void sendJSONError(HttpServletResponse httpServletResponse, int status, String message, String description) throws IOException {
        httpServletResponse.setStatus(status);
        httpServletResponse.setContentType("application/json");
        httpServletResponse.setCharacterEncoding("UTF-8");
        JSONObject errorObject = new JSONObject();
        errorObject.put("status", status);
        errorObject.put("message", message);
        if(description != null) {
            errorObject.put("description", description);
        }
        String jsonString = errorObject.toJSONString();
        httpServletResponse.setContentLength(jsonString.length());
        httpServletResponse.getWriter().write(jsonString);
    }
}
