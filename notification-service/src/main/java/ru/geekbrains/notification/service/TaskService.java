package ru.geekbrains.notification.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import ru.geekbrains.entity.bot.BotData;
import ru.geekbrains.common.rest.ResponseMessage;
import org.springframework.beans.factory.annotation.Value;

@Slf4j
@Service
public class TaskService {

	@Value("${REST_SERVICE_URL:http://localhost:8079}")
	private String REST_SERVICE_URL;
	private final RestTemplate restTemplate;

	public TaskService(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

	public void send(BotData botData) {
		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(REST_SERVICE_URL).path("/task/create");
		String url = builder.build().encode().toUriString();
		log.info(String.format("url = %s", url));
		log.info(String.format("Создаем задачу для парсеров по запросу пользователя = %s, фильтры = %s",
			botData.getUser().getLogin(),
			botData.getAnswer().toString()));
		ResponseEntity responseEntity = restTemplate.postForEntity(url, botData, ResponseMessage.class);
		ResponseMessage responseMessage = (ResponseMessage) responseEntity.getBody();
		log.info(responseMessage.toString());
	}
}
