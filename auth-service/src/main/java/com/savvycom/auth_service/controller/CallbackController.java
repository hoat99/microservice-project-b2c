package com.savvycom.auth_service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletContext;
import java.util.Arrays;

@RestController()
public class CallbackController {

    @Autowired
    ServletContext application;

    @Value("${security.oauth2.client.client-id:clientId}")
    private String clientId;

    @Value("${security.oauth2.client.client-secret:secret}")
    private String clientSecret;

    @Value("${security.oauth2.client.access-token-validity-seconds:21600}") // 12 hours
    private int accessTokenValiditySeconds;

    @Value("${security.oauth2.client.refresh-token-validity-seconds:43200}") // 30 days
    private int refreshTokenValiditySeconds;

    @Value("${security.oauth2.client.authorized-grant-types:authorization_code,refresh_token}")
    private String[] authorizedGrantTypes;

    @Value("${security.oauth2.client.scope:read}")
    private String[] scope;

    @Value("${security.oauth2.client.resource-id:nx-api}")
    private String resourceId;

    @RequestMapping(value = "/callback",method = RequestMethod.GET)
    String callback(@RequestParam("code") String code){
        String html = "<!DOCTYPE html>\n" +
                "<html>\n" +
                "<head>\n" +
                "    <style>\n" +
                "        table {\n" +
                "            font-family: arial, sans-serif;\n" +
                "            border-collapse: collapse;\n" +
                "            width: 100%;\n" +
                "        }\n" +
                "\n" +
                "        td, th {\n" +
                "            border: 1px solid #dddddd;\n" +
                "            text-align: left;\n" +
                "            padding: 8px;\n" +
                "        }\n" +
                "\n" +
                "        tr:nth-child(even) {\n" +
                "            background-color: #dddddd;\n" +
                "        }\n" +
                "    </style>\n" +
                "</head>\n" +
                "<body>\n" +
                "\n" +
                "<h2>Ecom Authorization Information</h2>\n" +
                "\n" +
                "<table>\n" +
                "    <tr>\n" +
                "        <th>Setting</th>\n" +
                "        <th>Value</th>\n" +
                "    </tr>\n" +
                "    <tr>\n" +
                "        <td>Authorization Code</td>\n" +
                "        <td>" + code + "</td>\n" +
                "    </tr>\n" +
                "    <tr>\n" +
                "        <td>Client Id</td>\n" +
                "        <td>" + clientId + "</td>\n" +
                "    </tr>\n" +
                "    <tr>\n" +
                "        <td>Client Secret</td>\n" +
                "        <td>" + clientSecret + "</td>\n" +
                "    </tr>\n" +
                "    <tr>\n" +
                "        <td>Access Token Validity Seconds</td>\n" +
                "        <td>" + accessTokenValiditySeconds + "</td>\n" +
                "    </tr>\n" +
                "    <tr>\n" +
                "        <td>Refresh Token Validity Seconds</td>\n" +
                "        <td>" + refreshTokenValiditySeconds + "</td>\n" +
                "    </tr>\n" +
                "    <tr>\n" +
                "        <td>Authorized Grant Types</td>\n" +
                "        <td>" + Arrays.toString( authorizedGrantTypes) +"</td>\n" +
                "    </tr>\n" +
                "    <tr>\n" +
                "        <td>Scope</td>\n" +
                "        <td>" + Arrays.toString(scope) + "</td>\n" +
                "    </tr>\n" +
                "    <tr>\n" +
                "        <td>Resource Id</td>\n" +
                "        <td>" + resourceId + "</td>\n" +
                "    </tr>\n" +
                "</table>\n" +
                "\n" +
                "</body>\n" +
                "</html>\n";
        return html;
    }
}
