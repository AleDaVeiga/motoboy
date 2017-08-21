package com.wgsistemas.motoboy.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class InfoController {
	@Value("${application.name}")
    private String applicationName;

    @Value("${build.version}")
    private String buildVersion;

    @Value("${build.timestamp}")
    private String buildTimestamp;

    @RequestMapping(value = "/info", produces = MediaType.TEXT_PLAIN_VALUE)
    @ResponseBody
    public String getInfo() {
        StringBuilder sb = new StringBuilder();
        sb.append("\n[Informações da Aplicação]");
        sb.append("\nNome: " + applicationName);
        sb.append("\nVersão: " + buildVersion);
        sb.append("\nBuild: " + buildTimestamp);
        return sb.toString();
    }
}
