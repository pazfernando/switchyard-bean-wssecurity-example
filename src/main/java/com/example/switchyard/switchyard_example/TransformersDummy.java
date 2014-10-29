package com.example.switchyard.switchyard_example;

import java.io.StringReader;

import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMResult;
import javax.xml.transform.stream.StreamSource;

import org.switchyard.annotations.Transformer;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public final class TransformersDummy {

	@Transformer(to = "{urn:com.example.switchyard:switchyard-example:1.0}sayByeResponse")
	public Element transformStringToSayByeResponse(String from) {
		StringBuffer resultXml = new StringBuffer()
				.append("<result:sayByeResponse xmlns:result=\"urn:com.example.switchyard:switchyard-example:1.0\"><string>"
						+ from + "</string></result:sayByeResponse>");
		return toElement(resultXml.toString());
	}

	@Transformer(to = "{urn:com.example.switchyard:switchyard-example:1.0}sayHelloResponse")
	public Element transformStringToSayHelloResponse(String from) {
		StringBuffer resultXml = new StringBuffer()
				.append("<result:sayHelloResponse xmlns:result=\"urn:com.example.switchyard:switchyard-example:1.0\"><string>"
						+ from + "</string></result:sayHelloResponse>");
		return toElement(resultXml.toString());
	}

	@Transformer(from = "{urn:com.example.switchyard:switchyard-example:1.0}sayBye")
	public DummyGuy transformSayByeToDummyGuy(Element from) {
		DummyGuy dummy = new DummyGuy();
		dummy.setName(getElementValue(from, "name"));
		dummy.setLastName(getElementValue(from, "lastName"));
		return dummy;
	}

	@Transformer(from = "{urn:com.example.switchyard:switchyard-example:1.0}sayHello")
	public DummyGuy transformSayHelloToDummyGuy(Element from) {
		DummyGuy dummy = new DummyGuy();
		dummy.setName(getElementValue(from, "name"));
		dummy.setLastName(getElementValue(from, "lastName"));
		return dummy;
	}

	/**
	 * Returns the text value of a child node of parent.
	 */
	private String getElementValue(Element parent, String elementName) {
		String value = null;
		NodeList nodes = parent.getElementsByTagName(elementName);
		if (nodes.getLength() > 0) {
			value = nodes.item(0).getChildNodes().item(0).getNodeValue();
		}
		return value;
	}

	private Element toElement(String xml) {
		DOMResult dom = new DOMResult();
		try {
			TransformerFactory.newInstance().newTransformer()
					.transform(new StreamSource(new StringReader(xml)), dom);
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return ((Document) dom.getNode()).getDocumentElement();
	}

}
