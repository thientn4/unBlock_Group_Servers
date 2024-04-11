package com.unBlock_Group_Servers.middleware;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import jakarta.servlet.Filter;
import jakarta.servlet.http.HttpServletRequestWrapper;

import java.io.IOException;
import java.util.*;

@Component
public class TokenVerifier implements Filter {
    @Override
    public void doFilter(
        ServletRequest request,
        ServletResponse response,
        FilterChain chain
    ) throws ServletException, IOException {
        HttpServletRequest req = (HttpServletRequest) request;
        HeaderMapRequestWrapper requestWrapper = new HeaderMapRequestWrapper(req);
        if(!req.getMethod().equals("OPTIONS")) { // DONT CHECK FOR CORS PREFLIGHT
            char authType = requestWrapper.getHeader("token").charAt(0);
            String token = requestWrapper.getHeader("token").substring(1);
            //token = "AeyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiIsImtpZCI6IkZsR05mUnZHSW82MVJIckFUQmNucUZTb2RpOCJ9.eyJ2ZXIiOiIyLjAiLCJpc3MiOiJodHRwczovL2xvZ2luLm1pY3Jvc29mdG9ubGluZS5jb20vOTE4ODA0MGQtNmM2Ny00YzViLWIxMTItMzZhMzA0YjY2ZGFkL3YyLjAiLCJzdWIiOiJBQUFBQUFBQUFBQUFBQUFBQUFBQUFKby1vam94b1BUMHEzRElJeXR4bWYwIiwiYXVkIjoiYjA1YTgwNTAtNzhhNy00YTU3LWJkNjItZmUyOGRmMjgxY2ZkIiwiZXhwIjoxNzExNjY1NzAyLCJpYXQiOjE3MTE1NzkwMDIsIm5iZiI6MTcxMTU3OTAwMiwibmFtZSI6IlRoaWVuIE5ndXllbiIsInByZWZlcnJlZF91c2VybmFtZSI6Im50bXRoaWVuMDFAZ21haWwuY29tIiwib2lkIjoiMDAwMDAwMDAtMDAwMC0wMDAwLTZhZDgtM2U4M2VmYjE0NzAyIiwidGlkIjoiOTE4ODA0MGQtNmM2Ny00YzViLWIxMTItMzZhMzA0YjY2ZGFkIiwibm9uY2UiOiI2MjhlMGEwMi01NzBjLTRkMzQtYjFiYS1kY2I1ZTIxYzlkYTAiLCJhaW8iOiJEcVpyOXlDQUZCOWd3NlVoc1Q5d2JLRFU5RTNzN0J5eFk1REd2YURWeml4dHlENTR2UG1yRzREODVVOWU1V3ZXdW1WQ2l4Nm5oTjNldXhERHUxaUpLNUVVZTVLd2s0U0Q5UXpPNDEqKmZrRFZ4eUhobDZFR2xXNFlrKm85dHZnaTUhb2lZYzNmNVBtT1pRTUFkODR1TVdMV1hlWU5rNExTQzludE1aSjJwd3J5In0.a7kcjp3ZF-7wzW0TS_2IVfkz4PUJ0lcFxWpt441r8M8SgyB_CGLQCLt5EOq54fhsmh3HVgYmN3iEep_WSW2mJ9A6JiCjZIjqEZ-xDDwEqr0Pb7AKK6RaIHJXv4pB_YWkg6Xe002S1qMP2y6H42syhfjOtlcGb2a-Ytg79pwRYpQZRS3sPw7t2DU8QadevxeTmyUjbuHi4M3F19vtYVkoapDSBfwRKacYXFqtsusJfGtJm-vD7jEVMIJ2Aw3Q_pHhVzPl4htqZJBvNwWc5WtpfOtoBj0yKNUhni0wI33rXOqpyI9DN6f26CFtwtPHhoLheWSTwUJR2jYIVNt5vNN_Vw";

            byte[] payloadBytes = Base64.getDecoder().decode(token.split("\\.")[1]);
            Map<String, Object> payload = (new ObjectMapper()).readValue(new String(payloadBytes), new TypeReference<Map<String, Object>>() {
            });
            String email = payload.get(authType=='A'?"preferred_username":"email").toString().toLowerCase();
            requestWrapper.addHeader("email", email);
            String appId = payload.get("aud").toString();
            long issuedAt = ((Integer) payload.get("iat")).longValue();
            long now = System.currentTimeMillis() / 1000L;
            if ((now - issuedAt) > 24 * 60 * 60 || !appId.equals(authType=='A'?"b05a8050-78a7-4a57-bd62-fe28df281cfd":"823391589443-hh66v8666cqpklh4ubn6pcdntma3r5pi.apps.googleusercontent.com")) {
                HttpServletResponse httpResponse = (HttpServletResponse) response;
                httpResponse.setContentType("text/plain");
                httpResponse.getWriter().print("invalid token");
                httpResponse.setStatus(200);
                ///// MUST SET HEADER PARAMS FOR CORS
                httpResponse.setHeader("Access-Control-Allow-Origin", "*");
                httpResponse.setHeader("Access-Control-Allow-Methods", "*");
                httpResponse.setHeader("Access-Control-Max-Age", "3600");
                httpResponse.setHeader("Access-Control-Allow-Credentials", "true");
                httpResponse.setHeader("Access-Control-Allow-Headers", "*");
                return; // Reject the request here
            }
        }
        chain.doFilter(requestWrapper, response);

    }

    public static class HeaderMapRequestWrapper extends HttpServletRequestWrapper {

        public HeaderMapRequestWrapper(HttpServletRequest request) {
            super(request);
        }

        private Map<String, String> headerMap = new HashMap<String, String>();


        public void addHeader(String name, String value) {
            headerMap.put(name, value);
        }

        @Override
        public String getHeader(String name) {
            String headerValue = super.getHeader(name);
            if (headerMap.containsKey(name)) {
                headerValue = headerMap.get(name);
            }
            return headerValue;
        }

        @Override
        public Enumeration<String> getHeaderNames() {
            List<String> names = Collections.list(super.getHeaderNames());
            for (String name : headerMap.keySet()) {
                names.add(name);
            }
            return Collections.enumeration(names);
        }

        @Override
        public Enumeration<String> getHeaders(String name) {
            List<String> values = Collections.list(super.getHeaders(name));
            if (headerMap.containsKey(name)) {
                values.add(headerMap.get(name));
            }
            return Collections.enumeration(values);
        }

    }
}