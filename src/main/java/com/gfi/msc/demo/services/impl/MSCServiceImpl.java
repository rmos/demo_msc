package com.gfi.msc.demo.services.impl;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gfi.msc.demo.dtos.EnvironmentsOut;
import com.gfi.msc.demo.services.IMSCService;

@Service
public class MSCServiceImpl implements IMSCService{
	
	private static final Logger log = LoggerFactory.getLogger(MSCServiceImpl.class);

	@Override
	public EnvironmentsOut obtenerEntornos()  {
		log.info("Iniciando obtenerEntornos");
		EnvironmentsOut out = new EnvironmentsOut();
		try {
			out = llamarRest();
			log.info("result {}", out.getEnvironments());
		} catch (IOException e) {
			log.info("Exception LlamadaRest {}", e);
		}
		return out;
	}
	
	public EnvironmentsOut llamarRest() throws IOException {

        CloseableHttpClient httpClient = HttpClients.createDefault();
        EnvironmentsOut environmentsOut = null;

        try {

            HttpGet request = new HttpGet("http://localhost:8761/cursomicros/env/environments");

            CloseableHttpResponse response = httpClient.execute(request);

            try {

                HttpEntity entity = response.getEntity();
                if (entity != null) {
                    ObjectMapper objectMapper = new ObjectMapper();
                    environmentsOut = objectMapper.readValue(EntityUtils.toString(entity), EnvironmentsOut.class);
                }

            } finally {
                response.close();
            }
        } finally {
            httpClient.close();
        }
		return environmentsOut;

    }

}
