package com.unBlock_Group_Servers.middleware;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
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
        String token = requestWrapper.getHeader("token");
        token="AeyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiIsImtpZCI6IlhSdmtvOFA3QTNVYVdTblU3Yk05blQwTWpoQSJ9.eyJhdWQiOiJiMDVhODA1MC03OGE3LTRhNTctYmQ2Mi1mZTI4ZGYyODFjZmQiLCJpc3MiOiJodHRwczovL2xvZ2luLm1pY3Jvc29mdG9ubGluZS5jb20vODA2YThkMDAtNDg1ZS00ODQ5LWE1MTgtMzE1NjcwYzRhMTk1L3YyLjAiLCJpYXQiOjE3MTE0OTUzNDQsIm5iZiI6MTcxMTQ5NTM0NCwiZXhwIjoxNzExNDk5MjQ0LCJuYW1lIjoiVGhpZW4gTmd1eWVuIiwibm9uY2UiOiIwOWY2MmE0ZS01MDMxLTRmYzktODFhNi1lMmM2MTAwODkzYzgiLCJvaWQiOiI5ZDc4YzA5MC00NTVmLTRmNTItYjA2Yi1iYzcxNDM4NTRiNWEiLCJwcmVmZXJyZWRfdXNlcm5hbWUiOiJUaGllbk5ndXllbkBKTUVhZ2xlLmNvbSIsInJoIjoiMC5BVkFBQUkxcWdGNUlTVWlsR0RGV2NNU2hsVkNBV3JDbmVGZEt2V0wtS044b0hQMVFBQ2cuIiwic3ViIjoiX2xFNWl1Qndnell1Q0ZZZkwzZE5XbnFCOWxaVE5OQ0lsUnplV2Y0dk1nRSIsInRpZCI6IjgwNmE4ZDAwLTQ4NWUtNDg0OS1hNTE4LTMxNTY3MGM0YTE5NSIsInV0aSI6InNmWlZkVmpyYUVlcndOTkljam9EQVEiLCJ2ZXIiOiIyLjAifQ.Cp6z7iBz8tSISp8IxrnXGfEVKZ-4PPK6u5pPqlbaqkry57huRv4slTbD96p3UtDmItDrQhKLHoLy6zXm_WhvKrJYV2vmJrPmsVTQCh38geTxIjMWIDbsYD_W8tcQ8xSyoOnqNakjl_9QmQPLOGcpQC1A9XAa7yomTA_LcTmKWcbMYvuTZZ4lsz5bA4ksNpr_PASxmQmzyhXFmjAJ5zXvGjMxyiXDU2wj435bDp_Au8axUwmntF8uTiLStoiQDxpiSNrJ2vfcG82211KLnKnZRMgdlI2QqTvaJ_dmrIrxza6f0SetB8M_HLXx4t0XQGWWGg53KDlGEY_oCn5gNWMpzw";

        String[] chunks = token.split("\\.");
        Base64.Decoder decoder = Base64.getUrlDecoder();

        byte[] payloadBytes = Base64.getDecoder().decode(chunks[1]);
        Map<String, Object> payload = (new ObjectMapper()).readValue(new String(payloadBytes), new TypeReference<Map<String, Object>>() {});
        long issuedAt = ((Integer) payload.get("iat")).longValue();
        System.out.println(issuedAt);
        long now = System.currentTimeMillis() / 1000L;
        System.out.println(now);
        //(now - issuedAt) should be less than (24*60*60)
        String email = payload.get("preferred_username").toString().toLowerCase();

        byte[] headerBytes = Base64.getDecoder().decode(chunks[0]);
        //String header = new String(decoder.decode(chunks[0]));
        //String payload = Arrays.toString(decoder.decode(chunks[1]));

        requestWrapper.addHeader("email", email);

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