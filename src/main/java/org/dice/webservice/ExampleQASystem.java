package org.dice.webservice;
import org.aksw.qa.commons.datastructure.Question;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExampleQASystem {
	private Logger log = LoggerFactory.getLogger(ExampleQASystem.class);
	
	
	public JSONObject getAnswersToQuestion(final Question q) {
		JSONObject answer = new JSONObject();
		q.setAnswerAsQALDJSON(answer);
		
		return q.getAnswerAsQALDJSON();
	}
}
