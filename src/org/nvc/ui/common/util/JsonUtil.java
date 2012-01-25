package org.nvc.ui.common.util;

import java.io.IOException;
import java.io.StringWriter;

import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.impl.DefaultPrettyPrinter;
import org.codehaus.jackson.map.ObjectMapper;
import org.nvc.ui.member.model.Member;
import org.nvc.ui.member.service.MemberService;

public class JsonUtil {

	@SuppressWarnings("deprecation")
	public static String generateJson(Object obj)
	{
		JsonGenerator jsonGenerator;
		
		try
		{
			jsonGenerator = new JsonFactory().createJsonGenerator(new StringWriter());
			
			StringWriter writer = new StringWriter();
			ObjectMapper mapper = new ObjectMapper();
			jsonGenerator = mapper.getJsonFactory().createJsonGenerator(writer);
			jsonGenerator.setPrettyPrinter(new DefaultPrettyPrinter());

			mapper.writeValue(jsonGenerator, obj);
			return writer.toString();
		}
		catch(IOException e)
		{
			System.out.println(MemberService.class+ " error while generation JsonGenrator");
			
			return null;
		}
	}
	
	public static <V> Object parseJson(String jsonNotation, Class<V> objType) throws Exception
	{
		JsonParser jsonParser;
		
		try
		{
			jsonParser = new JsonFactory().createJsonParser(jsonNotation);
			ObjectMapper mapper = new ObjectMapper();
			return mapper.readValue(jsonNotation, objType);
		}
		catch(IOException e)
		{
			System.out.println(MemberService.class+ " error while parsing JsonGenrator");
			System.out.println(e.toString());
			return null;
		}
		
	}
}
