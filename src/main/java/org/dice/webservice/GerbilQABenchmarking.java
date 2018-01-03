package org.dice.webservice;

import java.io.IOException;
import java.text.ParseException;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import javax.servlet.http.HttpServletResponse;

import org.aksw.qa.commons.datastructure.Question;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

@RestController
public class GerbilQABenchmarking {
	private Logger log = LoggerFactory.getLogger(GerbilQABenchmarking.class);
	private ExampleQASystem pipeline = new ExampleQASystem();
	
	// test via
	//TODO unit test for Who is the president of Europe?
	// curl -d "query=What is the capital of Germany?&lang=en" -X POST http://localhost:8080/ask-gerbil
	@RequestMapping(value = "/ask-gerbil", method = RequestMethod.POST)
	public String askGerbil(@RequestParam Map<String, String> params, final HttpServletResponse response) throws ExecutionException, RuntimeException, IOException, ParseException {
		log.debug("Received question = " + params.get("query"));
		log.debug("Language of question = " + params.get("lang"));
		// CORS to allow for communication between https and http
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
		response.setHeader("Access-Control-Max-Age", "3600");
		response.setHeader("Access-Control-Allow-Headers", "x-requested-with");

		String question = params.get("query");
		String lang = params.get("lang");

		Question q = new Question();
		q.getLanguageToQuestion().put("en", question);
		JSONObject answer = pipeline.getAnswersToQuestion(q);

		GerbilFinalResponse resp = new GerbilFinalResponse();
		resp.setQuestions(q);
		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
		String json = ow.writeValueAsString(resp);

		log.info("\n\n JSON object: \n\n" + json);

		return json;
	}

}